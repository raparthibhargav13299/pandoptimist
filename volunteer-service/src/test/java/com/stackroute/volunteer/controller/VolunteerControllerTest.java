package com.stackroute.volunteer.controller;

import com.stackroute.volunteer.exceptions.VolunteerAlradyExistsException;
import com.stackroute.volunteer.model.Volunteer;
import com.stackroute.volunteer.service.VolunteerService;
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
class VolunteerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private VolunteerService volunteerService;
    private Volunteer volunteer;
    private List<Volunteer> volunteerList;

    @InjectMocks
    private VolunteerController volunteerController;

    @BeforeEach
    public void setUp(){
        volunteer=new Volunteer(1,"abc","7338124788","abc123@gmail.com","image1.jpg");
        mockMvc= MockMvcBuilders.standaloneSetup(volunteerController).build();
    }
    public  static String asJsonString(final Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);
    }
    @Test
    public void givenUserToSaveShouldReturnSavedUser() throws VolunteerAlradyExistsException, Exception {
        when(volunteerService.saveVolunteer(any())).thenReturn(volunteer);
        mockMvc.perform(post("/api/v1/volunteer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(volunteer)))
                .andExpect(status().isCreated());
        verify(volunteerService, times(1)).saveVolunteer(any());

    }
    @Test
    public void getAllTheUsersInList() throws Exception {
        when(volunteerService.getAllVolunteer()).thenReturn(volunteerList);
        mockMvc.perform(get("/api/v1/volunteers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(volunteer)))
                .andExpect(status().isOk());
        verify(volunteerService, times(1)).getAllVolunteer();

    }

}