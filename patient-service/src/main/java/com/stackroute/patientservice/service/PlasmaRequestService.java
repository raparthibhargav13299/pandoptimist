package com.stackroute.patientservice.service;

import com.stackroute.patientservice.exception.RequestAlreadyExistsException;
import com.stackroute.patientservice.model.PlasmaRequest;

import java.util.List;

public interface PlasmaRequestService {

    PlasmaRequest savePlasmaRequest(PlasmaRequest plasmaRequest) throws RequestAlreadyExistsException;
     List<PlasmaRequest> getAllPlasmaRequest();
}
