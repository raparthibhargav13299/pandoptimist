package com.stackroute.patientservice.repository;

import com.stackroute.patientservice.model.MedicalRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalRequestRepository extends MongoRepository<MedicalRequest, Integer> {
}
