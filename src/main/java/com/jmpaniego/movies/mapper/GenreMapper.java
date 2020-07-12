package com.jmpaniego.movies.mapper;

import com.jmpaniego.movies.dto.GenereDto;
import com.jmpaniego.movies.models.Genere;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GenreMapper {
  @Mapping(target = "id", ignore = true)
  Genere map(GenereDto genereDto);

  GenereDto mapToDto(Genere genere);
}
