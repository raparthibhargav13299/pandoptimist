package com.stackroute.patientservice.service;

import com.stackroute.patientservice.exception.RequestAlreadyExistsException;
import com.stackroute.patientservice.model.MedicalRequest;

import java.util.List;

public interface MedicalRequestService {

    MedicalRequest savePatientRequest(MedicalRequest medicalRequest) throws  RequestAlreadyExistsException;

    List<MedicalRequest> getAllPatientRequest();
}
