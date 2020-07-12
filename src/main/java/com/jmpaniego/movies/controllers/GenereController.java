package com.jmpaniego.movies.controllers;

import com.jmpaniego.movies.dto.GenereDto;
import com.jmpaniego.movies.models.Genere;
import com.jmpaniego.movies.services.GenereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/generes")
public class GenereController {
  private final GenereService genereService;

  @Autowired
  public GenereController(GenereService genereService) {
    this.genereService = genereService;
  }

  @GetMapping
  public ResponseEntity<List<GenereDto>> findAll(){
    return ResponseEntity.ok(
      genereService.findAll()
    );
  }
}
