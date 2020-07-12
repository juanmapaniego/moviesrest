package com.jmpaniego.movies.services;

import com.jmpaniego.movies.dto.MovieDto;
import com.jmpaniego.movies.mapper.MovieMapper;
import com.jmpaniego.movies.models.Movie;
import com.jmpaniego.movies.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {
  private final MovieRepository movieRepository;
  private final MovieMapper movieMapper;

  @Autowired
  public MovieService(MovieRepository movieRepository, MovieMapper movieMapper) {
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
        .orElseThrow(() -> new IllegalArgumentException());
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
}
