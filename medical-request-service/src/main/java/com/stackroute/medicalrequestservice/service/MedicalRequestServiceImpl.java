package com.stackroute.medicalrequestservice.service;

import com.stackroute.medicalrequestservice.model.EmailQueue;
import com.stackroute.medicalrequestservice.model.MedicalRequest;
import com.stackroute.medicalrequestservice.repository.MedicalRequestRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



@Slf4j
@Service
public class MedicalRequestServiceImpl implements MedicalRequestService{


    @Autowired
    MedicalRequestRepository medicalRequestRepository;

    @Override
    public String sendResource(int id, String body, EmailQueue email,String mailId, MedicalRequest medicalRequest) {


        MedicalRequest medicalRequest1 = medicalRequestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medical Request not found for this id :: " + id));

        medicalRequest1.setRequestStatus("Closed");

        final MedicalRequest updatedRequest = medicalRequestRepository.save(medicalRequest1);
        return "Success";
    }

    @Override
    public MedicalRequest updataStatus(int id, MedicalRequest request) {
        MedicalRequest medicalRequest1 = medicalRequestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medical Request not found for this id :: " + id));

        medicalRequest1.setRequestStatus("Open");

        final MedicalRequest updatedRequest = medicalRequestRepository.save(medicalRequest1);
        return updatedRequest;
    }

}
