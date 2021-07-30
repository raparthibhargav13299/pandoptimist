package com.stackroute.doctorconsultationservice.controller;

import com.stackroute.doctorconsultationservice.exceptions.DoctorAlreadyPresentException;
import com.stackroute.doctorconsultationservice.model.Doctor;
import com.stackroute.doctorconsultationservice.service.DoctorService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class DoctorControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private DoctorService doctorService;
    private Doctor doctor;
    private List<Doctor> doctorList;
    @InjectMocks
    private DoctorController doctorController;
    @BeforeEach
    public void setUp(){
        doctor=new Doctor(1,"abc","MCI123","7894561233","abc@gmail.com","image.jpg");
        mockMvc= MockMvcBuilders.standaloneSetup(doctorController).build();
    }
    public  static String asJsonString(final Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);
    }
    @Test
    public void givenUserToSaveShouldReturnSavedUser() throws DoctorAlreadyPresentException, Exception {
        when(doctorService.saveDoctor(any())).thenReturn(doctor);
        mockMvc.perform(post("/api/v1/doctor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(doctor)))
                .andExpect(status().isCreated());
        verify(doctorService, times(1)).saveDoctor(any());
    }
    @Test
    public void getAllTheUsersInList() throws Exception {
        when(doctorService.getAllDoctor()).thenReturn(doctorList);
        mockMvc.perform(get("/api/v1/doctors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(doctor)))
                .andExpect(status().isOk());
        verify(doctorService, times(1)).getAllDoctor();
    }

}