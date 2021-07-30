package com.stackroute.doctorconsultationservice.repository;

import com.stackroute.doctorconsultationservice.model.Doctor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends MongoRepository<Doctor,Integer> {
}
