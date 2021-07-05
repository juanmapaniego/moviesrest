package com.jmpaniego.movies.controllers;

import static org.hamcrest.core.Is.is;
import com.jmpaniego.movies.dto.GenderDto;
import com.jmpaniego.movies.repositories.GenderRepository;
import com.jmpaniego.movies.repositories.MovieRepository;
import com.jmpaniego.movies.services.GenderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Arrays;


import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@ExtendWith(MockitoExtension.class)
@WebMvcTest(GenderController.class)
//@ActiveProfiles({"test"})
class GenderControllerTest {

    @MockBean
    GenderService genderService;

    //@InjectMocks
    //GenderController genderController;

    @Autowired
    MockMvc mockMvc;

    GenderDto validGender;

    @BeforeEach
    void setUp() {
        validGender = new GenderDto();
        validGender.setName("ACCION");

       // mockMvc = MockMvcBuilders.standaloneSetup(genderController).build();
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