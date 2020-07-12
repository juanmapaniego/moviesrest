package com.jmpaniego.movies.services;

import com.jmpaniego.movies.dto.MovieDto;
import com.jmpaniego.movies.mapper.MovieMapper;
import com.jmpaniego.movies.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
