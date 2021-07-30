package com.stackroute.patientservice.repository;


import com.stackroute.patientservice.model.PlasmaRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlasmaRequestRepository extends MongoRepository<PlasmaRequest, Integer> {
}
