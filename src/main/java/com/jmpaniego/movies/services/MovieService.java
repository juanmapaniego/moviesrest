package com.jmpaniego.movies.services;

import com.jmpaniego.movies.models.Movie;
import com.jmpaniego.movies.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
  private final MovieRepository movieRepository;

  @Autowired
  public MovieService(MovieRepository movieRepository) {
    this.movieRepository = movieRepository;
  }

  public List<Movie> findAll(){
    return (List<Movie>) movieRepository.findAll();
  }
}
