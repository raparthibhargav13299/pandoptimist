package com.stackroute.graphservice.repository;



import com.stackroute.graphservice.model.Donor;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonorRepository extends Neo4jRepository<Donor,Integer> {
}
