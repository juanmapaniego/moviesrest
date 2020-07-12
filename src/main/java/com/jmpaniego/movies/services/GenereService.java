package com.jmpaniego.movies.services;

import com.jmpaniego.movies.models.Genere;
import com.jmpaniego.movies.repositories.GenereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class GenereService {
  private final GenereRepository genereRepository;

  @Autowired
  public GenereService(GenereRepository genereRepository) {
    this.genereRepository = genereRepository;
  }

  public List<Genere> findAll(){
    return (List<Genere>) genereRepository.findAll();
  }
}
