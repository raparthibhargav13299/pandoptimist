package com.stackroute.patientservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.stackroute.patientservice.model.PlasmaRequest;

import com.stackroute.patientservice.service.PlasmaRequestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class PlasmaRequestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PlasmaRequestService plasmaRequestService;

    private PlasmaRequest plasmaRequest;

    @InjectMocks
    private PlasmaRequestController plasmaRequestController;

    @BeforeEach
    public void setUp() {
        plasmaRequest = new PlasmaRequest( "PlasmaRequest",1,"tharun",21,"A+","Apollo","Chennai","IMG.jpg","open");
        mockMvc = MockMvcBuilders.standaloneSetup(plasmaRequestController).build();
    }
    @Test
    public void savePatientRequest() throws Exception {
        when(plasmaRequestService.savePlasmaRequest(any())).thenReturn(plasmaRequest);
        mockMvc.perform(post("/api/v1/plasmaRequest")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(plasmaRequest)))
                .andExpect(status().isCreated());
        verify(plasmaRequestService, times(1)).savePlasmaRequest(any());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}




