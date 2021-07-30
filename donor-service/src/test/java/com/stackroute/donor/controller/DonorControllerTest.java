package com.stackroute.donor.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.donor.model.Donor;
import com.stackroute.donor.service.DonorService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class DonorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private DonorService donorService;
    private Donor donor;
    private List<Donor> donorList;

    @InjectMocks
    private DonorController donorController;

    @BeforeEach
    public void setUp(){
        donor=new Donor(99,"raju","4","7338124788","abc123@gmail.com","Bangalore","O+");
        mockMvc= MockMvcBuilders.standaloneSetup(donorController).build();
    }
    public  static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
           throw new RuntimeException(e);
        }
    }
    @Test
    public void givenDonorToSaveShouldReturnSavedDonor() throws Exception {
        when(donorService.saveDonor(any())).thenReturn(donor);
        mockMvc.perform(post("/api/v1/donor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(donor)))
                .andExpect(status().isCreated());
        verify(donorService, times(1)).saveDonor(any());

    }
    @Test
    public void getAllTheDonorsInList() throws Exception {
        when(donorService.getAllDonor()).thenReturn(donorList);
        mockMvc.perform(get("/api/v1/donors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(donor)))
                .andExpect(status().isOk());
        verify(donorService, times(1)).getAllDonor();

    }

}