package com.stackroute.medicalrequestservice.repository;


import com.stackroute.medicalrequestservice.model.Resources;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRepository extends MongoRepository<Resources,Integer> {
}
