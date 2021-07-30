package com.stackroute.volunteer.service;

import com.stackroute.volunteer.exceptions.VolunteerAlradyExistsException;
import com.stackroute.volunteer.model.Volunteer;
import com.stackroute.volunteer.repository.VolunteerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class VolunteerServiceTest {

        @Mock
        private VolunteerRepository volunteerRepository;
        private Volunteer volunteer;
        private List<Volunteer> volunteerList;
        @InjectMocks
        private VolunteerServiceImpl volunteerService;

        @Test
        public void givenUserToSaveShouldReturnSavedUser() throws VolunteerAlradyExistsException {
            Volunteer volunteer=new Volunteer(1,"abc","7338124788","abc123@gmail.com","image1.jpg");
            when(volunteerRepository.save(any())).thenReturn(volunteer);
            volunteerService.saveVolunteer(volunteer);
            verify(volunteerRepository,times(1)).save(any());
        }

        @Test
        public  void listallTheUsers(){
            volunteerRepository.save(volunteer);
            when(volunteerRepository.findAll()).thenReturn(volunteerList);
            List <Volunteer> volunteerList = volunteerService.getAllVolunteer();
            assertEquals(volunteerList,volunteerList);
            verify(volunteerRepository,times(1)).save(volunteer);
            verify(volunteerRepository,times(1)).findAll();

        }

    }