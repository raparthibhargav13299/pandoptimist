package com.stackroute.patientservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.patientservice.model.MedicalRequest;
import com.stackroute.patientservice.model.Requirement;
import com.stackroute.patientservice.service.MedicalRequestService;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ExtendWith(MockitoExtension.class)
class MedicalRequestControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private MedicalRequestService medicalRequestService;

    private MedicalRequest medicalRequest;


    @InjectMocks
    private MedicalRequestController medicalRequestController;

    @BeforeEach
    public void setUp() {
        List<Requirement> requirement= new ArrayList<Requirement>();

        medicalRequest = new MedicalRequest(
                2,
                "tharun",
                "male",
                "8328460303",
                "tharun@gmail.com",
                "no",
                "chennai",
                (ArrayList<Requirement>) requirement,
                "IMG.jpg",
                "open");
        mockMvc = MockMvcBuilders.standaloneSetup(medicalRequestController).build();
    }


    @Test
    public void savePatientRequest() throws Exception {
        when(medicalRequestService.savePatientRequest(any())).thenReturn(medicalRequest);
        mockMvc.perform(post("/api/v1/medicalRequest")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(medicalRequest)))
                .andExpect(status().isCreated());
        verify(medicalRequestService, times(1)).savePatientRequest(any());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}