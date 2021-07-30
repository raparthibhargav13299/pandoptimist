package com.stackroute.doctorconsultationservice.repository;
import com.stackroute.doctorconsultationservice.model.Doctor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataMongoTest
class DoctorRepositoryTest {

    @Autowired
    DoctorRepository doctorRepository;
    @Test
    public void givenVolunteerToSaveShouldReturnSavedVolunteer(){
        Doctor doctor=new Doctor(1,"abc","MCI123","7894561233","abc@gmail.com","image.jpg");
        doctorRepository.save(doctor);
        Doctor doctor1= doctorRepository.findById(doctor.getId()).get();
        assertNotNull(doctor1);
        assertEquals(doctor1.getName(),doctor1.getName());
    }
    @Test
    public  void listallTheDoctors(){
        Doctor doctor=new Doctor(1,"abc","MCI123","7894561233","abc@gmail.com","image.jpg");
        Doctor doctor1=new Doctor(2,"def","MCI789","7894444233","egg@gmail.com","image2.jpg");
        doctorRepository.save(doctor);
        doctorRepository.save(doctor1);
        List<Doctor> getAll= (List<Doctor>) doctorRepository.findAll();
        assertEquals("def", getAll.get(1).getName());
    }
}