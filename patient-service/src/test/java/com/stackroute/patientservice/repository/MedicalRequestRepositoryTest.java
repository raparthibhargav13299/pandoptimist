package com.stackroute.patientservice.repository;
import com.stackroute.patientservice.model.MedicalRequest;
import com.stackroute.patientservice.model.Requirement;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
 @ExtendWith(SpringExtension .class)
 @DataMongoTest
class MedicalRequestRepositoryTest {
     @Autowired
     private MedicalRequestRepository medicalRequestRepository;


     @Test
     public void saveRequest(){
         List<Requirement> requirement= new ArrayList<Requirement>();
         MedicalRequest patient=new MedicalRequest(
                 2,
                 "tharun",
                 "male",
                 "8328460303",
                 "tharun@gmail.com",
                 "no",
                 "chennai",
                 (ArrayList<Requirement>) requirement,
                 "IMG.jpg",
                 "open");
         medicalRequestRepository.save(patient);
         MedicalRequest patient1= medicalRequestRepository.findById(patient.getId()).get();
         assertNotNull(patient1);
         assertEquals(patient1.getName(),patient1.getName());
     }

}
