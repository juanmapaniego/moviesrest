package com.jmpaniego.movies.controllers;

import com.jmpaniego.movies.dto.MovieDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static  org.hamcrest.MatcherAssert.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MovieControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testFindAll() {

        List<MovieDto> movieDtos = restTemplate.getForObject("/api/v1/movies", List.class);

        assertThat(movieDtos,hasSize(146));
    }

    @Test
    void testFindAllByGender() {

        List<MovieDto> movieDtos = restTemplate.getForObject("/api/v1/movies?genders=Crime", List.class);

        assertThat(movieDtos,hasSize(33));
    }

    @Test
    void testFindById(){
        MovieDto movieDto = restTemplate.getForObject("/api/v1/movies/22", MovieDto.class);

        assertThat(movieDto.getTitle(),is("Beetlejuice"));
    }

    @Test
    void testFindAllOrderByRate() {
        List<MovieDto> movieDtos = restTemplate.getForObject("/api/v1/movies/order", List.class);

        assertThat(movieDtos,hasSize(146));
    }

    @Test
    void testFindTop() {
        List<MovieDto> movieDtos = restTemplate.getForObject("/api/v1/movies/top", List.class);

        assertThat(movieDtos,hasSize(10));
    }

    @Test
    void testCreate() {
        MovieDto movieDto = new MovieDto();
        movieDto.setRate(3.3);
        movieDto.setDirector("Juan");
        movieDto.setActors("Juan");
        movieDto.setGenders(Stream.of("Action").collect(Collectors.toSet()));
        movieDto.setPlot("New");
        movieDto.setPosterUrl("New");
        movieDto.setRuntime(100);
        movieDto.setTitle("New");
        movieDto.setYear(2020);

        ResponseEntity<MovieDto> response =
                restTemplate.postForEntity("/api/v1/movies", movieDto, MovieDto.class);

        assertThat(response.getStatusCode(),is(HttpStatus.CREATED));
    }

    @Test
    void testUpdate() {
        MovieDto movieDto = new MovieDto();
        movieDto.setRate(3.3);
        movieDto.setDirector("Juan");
        movieDto.setActors("Juan");
        movieDto.setGenders(Stream.of("Action").collect(Collectors.toSet()));
        movieDto.setPlot("New");
        movieDto.setPosterUrl("New");
        movieDto.setRuntime(100);
        movieDto.setTitle("New");
        movieDto.setYear(2020);

        ResponseEntity<MovieDto> response =
                restTemplate.exchange("/api/v1/movies/22", HttpMethod.PUT ,new HttpEntity<>(movieDto), MovieDto.class);

        assertThat(response.getBody().getDirector(),is("Juan"));
    }

    @Test
    void testDelete() {
        ResponseEntity<String> response = restTemplate.exchange("/api/v1/movies/22", HttpMethod.DELETE, null, String.class);


        assertThat(response.getStatusCode(),is(HttpStatus.OK));
    }
}