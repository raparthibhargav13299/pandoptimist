package com.stackroute.doctorconsultationservice.service;

import com.stackroute.doctorconsultationservice.exceptions.DoctorAlreadyPresentException;

import com.stackroute.doctorconsultationservice.model.Doctor;
import com.stackroute.doctorconsultationservice.repository.DoctorRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
class DoctorServiceTest {

    @Mock
    private DoctorRepository doctorRepository;

//    private List<Doctor> doctorList;
    @InjectMocks
    private DoctorServiceImpl doctorService;
//    @Test
//    public void givenUserToSaveShouldReturnSavedUser() throws DoctorAlreadyPresentException {
//        Doctor doctor=new Doctor(1,"abc","MCI123","7894561233","abc@gmail.com","image.jpg");
//        when(doctorRepository.save(any())).thenReturn(doctor);
//        doctorService.saveDoctor(doctor);
//        verify(doctorRepository,times(1)).save(any());
//    }



    @Test
    public  void listallTheUsers(){
        Doctor doctor= new Doctor(1,"abc","MCI123","7894561233","abc@gmail.com","image.jpg");
        doctorRepository.save(doctor);
        List <Doctor> doctorList = doctorService.getAllDoctor();
        when(doctorRepository.findAll()).thenReturn(doctorList);
        assertEquals(doctorList,doctorList);
        verify(doctorRepository,times(1)).save(doctor);
        verify(doctorRepository,times(1)).findAll();
    }
}