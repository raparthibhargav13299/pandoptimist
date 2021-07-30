package com.stackroute.doctorconsultationservice.controller;

import com.stackroute.doctorconsultationservice.exceptions.DoctorAlreadyPresentException;
import com.stackroute.doctorconsultationservice.exceptions.DoctorNotFoundException;
import com.stackroute.doctorconsultationservice.model.Doctor;
import com.stackroute.doctorconsultationservice.model.DoctorRedis;
import com.stackroute.doctorconsultationservice.repository.DoctorRedisRepository;
import com.stackroute.doctorconsultationservice.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;
import java.util.Set;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1")
public class DoctorRedisController {
    @Autowired
    private DoctorRedisRepository doctorRedisRepository;
    @Autowired
    private DoctorService doctorService;

    @PostMapping("setstatusOnline")
    public ResponseEntity<String> setstatusOnline1(@RequestBody DoctorRedis doctorOnline) throws DoctorAlreadyPresentException, DoctorNotFoundException {
        System.out.println(doctorOnline.getEmail());
//        Doctor doctor = doctorService.findByEmail(doctorOnline.getEmail());
//        doctorOnline.setName(doctor.getName());
        doctorRedisRepository.save(doctorOnline,doctorOnline.getEmail());
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @PostMapping("updatestatusBusy")
    public  ResponseEntity<String> update(@RequestBody DoctorRedis doctorbusy) throws DoctorNotFoundException,DoctorAlreadyPresentException {
        doctorRedisRepository.update(doctorbusy);
        return new ResponseEntity<>("DoctorWasBusy", HttpStatus.OK);
    }

    @GetMapping("getdoctorsOnline")
    public ResponseEntity<Set<DoctorRedis>> getAllOnlineDoctors() throws DoctorNotFoundException {
        Set<DoctorRedis> doctorOnline = doctorRedisRepository.findAll();
        return new ResponseEntity<>(doctorOnline, HttpStatus.OK);
    }

}

