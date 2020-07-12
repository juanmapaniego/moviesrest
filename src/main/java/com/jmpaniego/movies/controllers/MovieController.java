package com.jmpaniego.movies.controllers;

import com.jmpaniego.movies.dto.MovieDto;
import com.jmpaniego.movies.models.Movie;
import com.jmpaniego.movies.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/movies")
public class MovieController {
  private final MovieService movieService;

  @Autowired
  public MovieController(MovieService movieService) {
    this.movieService = movieService;
  }

  @GetMapping
  public ResponseEntity<List<MovieDto>> findAll(@RequestParam Optional<List<String>> genders){
    if(genders.isPresent()) {
      return ResponseEntity.ok(
          movieService.findAllByGender(genders.get())
      );
    }else{
      return ResponseEntity.ok(
          movieService.findAll()
      );
    }
  }

  @GetMapping("order")
  public ResponseEntity<List<MovieDto>> findAllOrderByRate(){
    return ResponseEntity.ok(
        movieService.findAllOrderByRate()
    );
  }

  @GetMapping("{id}")
  public ResponseEntity<MovieDto> findById(@PathVariable Long id){
    return ResponseEntity.ok(
        movieService.findById(id)
    );
  }

  @GetMapping({"/top/{top}","/top"})
  public ResponseEntity<List<MovieDto>> findTop(@PathVariable("top") Optional<Integer> oTop){
    Integer top = oTop.orElse(  10);
    return ResponseEntity.ok(
        movieService.findTop(top)
    );
  }

}
