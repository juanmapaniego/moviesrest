package com.jmpaniego.movies.controllers;

import com.jmpaniego.movies.dto.GenderDto;
import com.jmpaniego.movies.services.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/genders")
public class GenderController {
  private final GenderService genderService;

  @Autowired
  public GenderController(GenderService genderService) {
    this.genderService = genderService;
  }

  @GetMapping
  public ResponseEntity<List<GenderDto>> findAll(){
    return ResponseEntity.ok(
      genderService.findAll()
    );
  }
}
