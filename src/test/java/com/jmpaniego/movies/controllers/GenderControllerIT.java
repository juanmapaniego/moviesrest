package com.jmpaniego.movies.controllers;

import com.jmpaniego.movies.dto.GenderDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GenderControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testFindAll() {
        List<GenderDto> response = restTemplate.getForObject("/api/v1/genders", List.class);


        assertThat(response,hasSize(21));
    }
}