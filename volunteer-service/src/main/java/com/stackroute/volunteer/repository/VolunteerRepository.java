package com.stackroute.volunteer.repository;

import com.stackroute.volunteer.model.Volunteer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolunteerRepository extends MongoRepository<Volunteer,Integer> {
}
