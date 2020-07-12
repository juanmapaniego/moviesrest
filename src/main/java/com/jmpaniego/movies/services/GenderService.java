package com.jmpaniego.movies.services;

import com.jmpaniego.movies.dto.GenderDto;
import com.jmpaniego.movies.mapper.GenderMapper;
import com.jmpaniego.movies.repositories.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenderService {
  private final GenderRepository genderRepository;
  private final GenderMapper genderMapper;

  @Autowired
  public GenderService(GenderRepository genderRepository, GenderMapper genderMapper) {
    this.genderRepository = genderRepository;
    this.genderMapper = genderMapper;
  }

  public List<GenderDto> findAll(){
    return genderRepository.findAll()
        .stream()
        .map(genderMapper::mapToDto)
        .collect(Collectors.toList());
  }
}
