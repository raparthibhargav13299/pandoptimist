package com.stackroute.volunteer.repository;

import com.stackroute.volunteer.model.Volunteer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataMongoTest
class VolunteerRepositoryTest {

    @Autowired
    private VolunteerRepository volunteerRepository;


    @Test
    public void givenVolunteerToSaveShouldReturnSavedVolunteer(){
        Volunteer volunteer=new Volunteer(100,"abc","7338124788","abc123@gmail.com","image1.jpg");
         volunteerRepository.save(volunteer);
        Volunteer volunteer1= volunteerRepository.findById(volunteer.getId()).get();
        assertNotNull(volunteer1);
        assertEquals(volunteer1.getName(),volunteer1.getName());
    }

    @Test
    public  void listallTheUsers(){
        Volunteer volunteer=new Volunteer(1,"abc","7338124788","abc123@gmail.com","image1.jpg");
        Volunteer volunteer1=new Volunteer(1,"def","7338124788","abc123@gmail.com","image2.jpg");
        volunteerRepository.save(volunteer);
        volunteerRepository.save(volunteer1);
        List<Volunteer> getAll= (List<Volunteer>) volunteerRepository.findAll();
        assertEquals("abc", getAll.get(1).getName());

    }


}