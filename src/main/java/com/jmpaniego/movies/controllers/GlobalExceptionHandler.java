package com.jmpaniego.movies.controllers;

import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.ServletException;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(IllegalArgumentException.class)
  public String handlerIllegal(IllegalArgumentException e){
    return e.getMessage();
  }

  @ExceptionHandler(EmptyResultDataAccessException.class)
  public String handlerEmpty(EmptyResultDataAccessException e){
    return e.getMessage();
  }


}
