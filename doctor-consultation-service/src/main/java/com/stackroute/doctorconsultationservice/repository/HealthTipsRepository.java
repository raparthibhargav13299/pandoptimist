package com.stackroute.doctorconsultationservice.repository;

import com.stackroute.doctorconsultationservice.model.HealthTips;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthTipsRepository extends MongoRepository<HealthTips ,Integer> {
}
