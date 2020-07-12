package com.jmpaniego.movies.mapper;

import com.jmpaniego.movies.dto.GenderDto;
import com.jmpaniego.movies.models.Gender;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GenderMapper {
  @Mapping(target = "id", ignore = true)
  Gender map(GenderDto genderDto);

  GenderDto mapToDto(Gender gender);
}
