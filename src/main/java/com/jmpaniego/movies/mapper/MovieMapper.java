package com.jmpaniego.movies.mapper;

import com.jmpaniego.movies.dto.MovieDto;
import com.jmpaniego.movies.models.Gender;
import com.jmpaniego.movies.models.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class MovieMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "genders", source = "genders")
  public abstract Movie map(MovieDto movieDto, Set<Gender> genders);

  @Mapping(target = "genders", expression = "java(getGendersFromMovie(movie))")
  public abstract MovieDto mapToDto(Movie movie);

  Set<String> getGendersFromMovie(Movie movie){
    return movie.getGenders()
        .stream()
        .map(g -> g.getName())
        .collect(Collectors.toSet());
  }

}
