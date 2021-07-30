package com.stackroute.patientservice.repository;

import com.stackroute.patientservice.model.PlasmaRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@DataMongoTest
class PlasmaRequestRepositoryTest {
    @Autowired
    private PlasmaRequestRepository plasmaRequestRepository;


    @Test
    public void saveRequest(){
        PlasmaRequest plasmaRequest=new PlasmaRequest("PlasmaRequest",1,"tharun",21,"A+","Apollo","Chennai","IMG.jpg","open");
        plasmaRequestRepository.save(plasmaRequest);
        PlasmaRequest plasmaRequest1=plasmaRequestRepository.findById(plasmaRequest.getId()).get();
        assertNotNull(plasmaRequest1);
        assertEquals(plasmaRequest1.getName(),plasmaRequest1.getName());
    }

}