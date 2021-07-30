package com.stackroute.doctorconsultationservice.service;

import com.stackroute.doctorconsultationservice.exceptions.DoctorAlreadyPresentException;
import com.stackroute.doctorconsultationservice.exceptions.DoctorNotFoundException;
import com.stackroute.doctorconsultationservice.model.Doctor;
import com.stackroute.doctorconsultationservice.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
@Service
public class DoctorServiceImpl implements DoctorService{

    private DoctorRepository doctorRepository;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }


    @Override
    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }


    @Override
    public Optional<Doctor> findById(int id) throws NoSuchElementException{
        Optional<Doctor> myuser=doctorRepository.findById(id);
        if(myuser.get().getId()!=id){
            throw new NoSuchElementException();
        }
        return myuser;
    }

    @Override
    public Doctor findByEmail(String email) throws DoctorNotFoundException {
        Iterable<Doctor> alldoctors = doctorRepository.findAll();
        for (Doctor doctor : alldoctors) {
            if (doctor.getEmail().equalsIgnoreCase(email)) {
                return doctorRepository.save(doctor);
            }
        }
        return null;
    }

    @Override
    public void updateDoctor(Doctor doctor) throws DoctorNotFoundException, DoctorAlreadyPresentException {
        doctorRepository.save(doctor);
    }


    @Override
    public List<Doctor> getAllDoctor() {
        return (List<Doctor>) doctorRepository.findAll();
    }



}