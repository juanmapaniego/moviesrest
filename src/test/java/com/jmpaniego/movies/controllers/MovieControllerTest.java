package com.jmpaniego.movies.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jmpaniego.movies.dto.MovieDto;
import com.jmpaniego.movies.services.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.core.Is.is;

@WebMvcTest(MovieController.class)
class MovieControllerTest {

    @MockBean
    MovieService movieService;

    @Autowired
    MockMvc mockMvc;

    MovieDto movieDto;

    @BeforeEach
    void setUp() {
        movieDto = new MovieDto();
        movieDto.setId(1L);
        movieDto.setDirector("Juan");
        movieDto.setRate(2.5);
    }

    @Test
    void testCreate() throws Exception {
        given(movieService.create(any())).willReturn(movieDto);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(movieDto);
        mockMvc.perform(post("/api/v1/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                    .andExpect(status().isCreated())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.id",is(1)));
    }

    @Test
    void testUpdate() throws Exception {
        given(movieService.update(any(),any())).willReturn(movieDto);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(movieDto);
        mockMvc.perform(put("/api/v1/movies/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.director",is("Juan")));
    }

    @Test
    void testDelete() throws Exception {
        given(movieService.delete(anyLong())).willReturn("");

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(movieDto);
        mockMvc.perform(delete("/api/v1/movies/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testIae() throws Exception {
        mockMvc.perform(get("/api/v1/movies/top/diez"))
                        .andExpect(status().isBadRequest());
    }

    @Disabled
    @Test
    void testErdae() throws Exception {
        mockMvc.perform(get("/api/v1/movies/1"))
                .andExpect(status().isBadRequest());
    }

}