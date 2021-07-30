package com.stackroute.patientservice.service;

import com.stackroute.patientservice.exception.RequestAlreadyExistsException;
import com.stackroute.patientservice.model.MedicalRequest;
import com.stackroute.patientservice.model.Requirement;
import com.stackroute.patientservice.repository.MedicalRequestRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class MedicalRequestServiceTest {
    @Mock
    private MedicalRequestRepository medicalRequestRepository;

    @InjectMocks
    private MedicalRequestServiceImpl medicalRequestService;

    @Test
    public void givenUserToSave() throws  RequestAlreadyExistsException {
        List<Requirement> requirement= new ArrayList<Requirement>();

        MedicalRequest patient=new MedicalRequest(
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
        when(medicalRequestRepository.save(any())).thenReturn(patient);
        medicalRequestService.savePatientRequest(patient);
        verify(medicalRequestRepository,times(1)).save(any());
    }

}
