package com.stackroute.medicalrequestservice.repository;

import com.stackroute.medicalrequestservice.model.MedicalRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MedicalRequestRepository extends MongoRepository<MedicalRequest,Integer> {
}
