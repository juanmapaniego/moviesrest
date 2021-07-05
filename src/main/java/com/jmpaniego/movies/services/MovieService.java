package com.jmpaniego.movies.services;

import com.jmpaniego.movies.dto.MovieDto;
import com.jmpaniego.movies.mapper.MovieMapper;
import com.jmpaniego.movies.models.Gender;
import com.jmpaniego.movies.models.Movie;
import com.jmpaniego.movies.repositories.GenderRepository;
import com.jmpaniego.movies.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MovieService {
  private final GenderRepository genderRepository;
  private final MovieRepository movieRepository;
  private final MovieMapper movieMapper;

  @Autowired
  public MovieService(GenderRepository genderRepository, MovieRepository movieRepository, MovieMapper movieMapper) {
    this.genderRepository = genderRepository;
    this.movieRepository = movieRepository;
    this.movieMapper = movieMapper;
  }

  public List<MovieDto> findAll(){
    return movieRepository.findAll()
        .stream()
        .map(movieMapper::mapToDto)
        .collect(Collectors.toList());
  }

  public List<MovieDto> findAllOrderByRate() {
    return movieRepository.findAll(Sort.by(Sort.Direction.DESC,"rate"))
        .stream()
        .map(movieMapper::mapToDto)
        .collect(Collectors.toList());
  }

  public MovieDto findById(Long id) {
    Movie movie = movieRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Movie id "+ id +" not exist"));
    return movieMapper.mapToDto(movie);
  }

  public List<MovieDto> findAllByGender(List<String> genders) {
    return movieRepository.findAllByGendersNameIn(genders)
        .stream()
        .map(movieMapper::mapToDto)
        .collect(Collectors.toList());
  }

  public List<MovieDto> findTop(Integer top) {
    return movieRepository.findAll(PageRequest.of(0,top,Sort.by(Sort.Direction.DESC,"rate")))
        .stream()
        .map(movieMapper::mapToDto)
        .collect(Collectors.toList());
  }

  public MovieDto create(MovieDto movieDto) {
    Set<Gender> genders = movieDto.getGenders().stream()
        .map(g -> genderRepository.findByName(g).orElseThrow(() -> new IllegalArgumentException("Gender do not exist")))
        .collect(Collectors.toSet());

    Movie movie = movieRepository.save(
        movieMapper.map(movieDto, genders)
    );
    return movieMapper.mapToDto(movie);
  }

  public MovieDto update(Long id, MovieDto movieDto) {
    Set<Gender> genders = movieDto.getGenders().stream()
        .map(g -> genderRepository
            .findByName(g)
            .orElseThrow(() -> new IllegalArgumentException("Gender not exist")))
        .collect(Collectors.toSet());
    Movie movie = movieMapper.map(movieDto, genders);
    movie.setId(id);
    return movieMapper.mapToDto(
        movieRepository.save(movie)
    );
  }

  public String delete(Long id) {
    movieRepository.deleteById(id);
    return "Succesfuly deleted id "+ id;
  }
}
