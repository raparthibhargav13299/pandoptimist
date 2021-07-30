package com.stackroute.medicalrequestservice.service;

import com.stackroute.medicalrequestservice.model.EmailQueue;
import com.stackroute.medicalrequestservice.model.MedicalRequest;
import org.springframework.stereotype.Service;


@Service
public interface MedicalRequestService {

    String sendResource(int id, String body, EmailQueue email,String mailId, MedicalRequest medicalRequest);
    MedicalRequest updataStatus(int id,MedicalRequest request);
}
