package com.stackroute.patientservice.service;

import com.stackroute.patientservice.exception.RequestAlreadyExistsException;
import com.stackroute.patientservice.model.PlasmaRequest;
import com.stackroute.patientservice.repository.PlasmaRequestRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class PlasmaRequestServiceTest {

    @Mock
    private PlasmaRequestRepository plasmaRequestRepository;

    @InjectMocks
    private PlasmaRequestServiceImpl plasmaRequestService;

    @Test
    public void givenUserToSave() throws RequestAlreadyExistsException {
        PlasmaRequest plasmaRequest=new PlasmaRequest("PlasmaRequest",1,"tharun",21,"A+","Apollo","Chennai","IMG.jpg","open");
        when(plasmaRequestRepository.save(any())).thenReturn(plasmaRequest);
        plasmaRequestService.savePlasmaRequest(plasmaRequest);
        verify(plasmaRequestRepository,times(1)).save(any());
    }

}