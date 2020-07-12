package com.jmpaniego.movies.services;

import com.jmpaniego.movies.dto.GenereDto;
import com.jmpaniego.movies.mapper.GenreMapper;
import com.jmpaniego.movies.models.Genere;
import com.jmpaniego.movies.repositories.GenereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenereService {
  private final GenereRepository genereRepository;
  private final GenreMapper genreMapper;

  @Autowired
  public GenereService(GenereRepository genereRepository, GenreMapper genreMapper) {
    this.genereRepository = genereRepository;
    this.genreMapper = genreMapper;
  }

  public List<GenereDto> findAll(){
    return genereRepository.findAll()
        .stream()
        .map(genreMapper::mapToDto)
        .collect(Collectors.toList());
  }
}
