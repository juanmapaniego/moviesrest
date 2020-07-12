package com.jmpaniego.movies.mapper;

import com.jmpaniego.movies.dto.MovieDto;
import com.jmpaniego.movies.models.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class MovieMapper {
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "genders", ignore = true)
  public abstract Movie map(MovieDto movieDto);

  @Mapping(target = "genders", expression = "java(getGenders(movie))")
  public abstract MovieDto mapToDto(Movie movie);

  Set<String> getGenders(Movie movie){
    return movie.getGenders()
        .stream()
        .map(g -> g.getName())
        .collect(Collectors.toSet());
  }
}
