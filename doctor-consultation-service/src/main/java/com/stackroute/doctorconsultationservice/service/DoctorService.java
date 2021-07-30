package com.stackroute.doctorconsultationservice.service;


import com.stackroute.doctorconsultationservice.exceptions.DoctorAlreadyPresentException;
import com.stackroute.doctorconsultationservice.exceptions.DoctorNotFoundException;
import com.stackroute.doctorconsultationservice.model.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DoctorService{

    Doctor saveDoctor(Doctor doctor);
    List<Doctor> getAllDoctor();
    Optional <Doctor> findById(int id);
    Doctor findByEmail(String email) throws DoctorNotFoundException;
    void updateDoctor(Doctor doctor) throws DoctorNotFoundException, DoctorAlreadyPresentException;



}
