package com.jmpaniego.movies.controllers;

import static org.hamcrest.core.Is.is;
import com.jmpaniego.movies.dto.GenderDto;
import com.jmpaniego.movies.services.GenderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class GenderControllerTest {
    @Mock
    GenderService genderService;

    @InjectMocks
    GenderController genderController;

    MockMvc mockMvc;

    GenderDto validGender;

    @BeforeEach
    void setUp() {
        validGender = new GenderDto();
        validGender.setName("ACCION");

        mockMvc = MockMvcBuilders.standaloneSetup(genderController).build();
    }

    @Test
    void testFindAll() throws Exception {
        given(genderService.findAll()).willReturn(Arrays.asList(validGender));

        mockMvc.perform(get("/api/v1/genders"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].name", is("ACCION")));
        //https://github.com/json-path/JsonPath
    }
}