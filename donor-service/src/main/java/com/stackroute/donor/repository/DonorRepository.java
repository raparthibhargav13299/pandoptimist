package com.stackroute.donor.repository;

import com.stackroute.donor.model.Donor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonorRepository extends MongoRepository<Donor,Integer> {
}
