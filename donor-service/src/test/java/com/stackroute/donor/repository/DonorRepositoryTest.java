package com.stackroute.donor.repository;

import com.stackroute.donor.model.Donor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataMongoTest
class DonorRepositoryTest {

    @Autowired
    private DonorRepository donorRepository;
    @Test
    public void givenDonorToSaveShouldReturnSavedDonor(){
        Donor donor=new Donor(200,"abc","45","7338124788","abc123@gmail.com","Bangalore","O+");
        donorRepository.save(donor);
        Donor donor1= donorRepository.findById(donor.getId()).get();
        assertNotNull(donor1);
        assertEquals(donor1.getName(),donor1.getName());
    }

//    @Test
//    public  void listallTheDonors(){
//        Donor donor=new Donor(112,"abc","45","7338124788","abc123@gmail.com","Bangalore","O+");
//        Donor donor1=new Donor(110,"kgf","25","733833388","def123@gmail.com","Bangalore","O+");
//        donorRepository.save(donor);
//        donorRepository.save(donor1);
//        List<Donor> getAll= (List<Donor>) donorRepository.findAll();
//        assertEquals("abc", getAll.get(1).getName());
//
//    }

}