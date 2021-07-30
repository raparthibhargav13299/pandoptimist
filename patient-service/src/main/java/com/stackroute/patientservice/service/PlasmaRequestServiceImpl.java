package com.stackroute.patientservice.service;

import com.stackroute.patientservice.exception.RequestAlreadyExistsException;

import com.stackroute.patientservice.model.PlasmaRequest;
import com.stackroute.patientservice.repository.PlasmaRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PlasmaRequestServiceImpl implements  PlasmaRequestService{


    private PlasmaRequestRepository plasmaRequestRepository;
    @Autowired
    public PlasmaRequestServiceImpl(PlasmaRequestRepository plasmaRequestRepository) {
        this.plasmaRequestRepository = plasmaRequestRepository;
    }

    @Override
    public PlasmaRequest savePlasmaRequest(PlasmaRequest plasmaRequest) throws RequestAlreadyExistsException {
        if (plasmaRequestRepository.existsById(plasmaRequest.getId())) {
            throw new RequestAlreadyExistsException();
        }
        PlasmaRequest savedPlasmaRequest = plasmaRequestRepository.save(plasmaRequest);
        return savedPlasmaRequest;
    }

    @Override
    public List<PlasmaRequest> getAllPlasmaRequest() {
        return plasmaRequestRepository.findAll();
    }
}
