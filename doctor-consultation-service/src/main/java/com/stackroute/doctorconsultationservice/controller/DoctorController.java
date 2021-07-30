package com.stackroute.doctorconsultationservice.controller;

import com.stackroute.doctorconsultationservice.exceptions.DoctorNotFoundException;
import com.stackroute.doctorconsultationservice.model.Doctor;
//import com.stackroute.doctorconsultationservice.rabbitmqConfig.MessageConfig;
import com.stackroute.doctorconsultationservice.service.DoctorService;
import com.stackroute.doctorconsultationservice.service.SequenceGeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class DoctorController {


    private DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

//    @Autowired
//    private RabbitTemplate template;

    public final String UPLOAD_DIR = "D:\\Product Development\\pandoptimist\\doctor-consultation-service\\src\\main\\resources\\static\\images";

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;


    @PostMapping("/doctor")
    public ResponseEntity<Doctor> saveUser(@RequestBody Doctor doctor) {
        doctor.setId(sequenceGeneratorService.getSequenceNumber(Doctor.SEQUENCE_NAME));
        Doctor savedDoctor = doctorService.saveDoctor(doctor);
//        template.convertAndSend(MessageConfig.EXCHANGE, MessageConfig.ROUTING_KEY, savedDoctor);
        return new ResponseEntity<>(savedDoctor, HttpStatus.CREATED);
    }

    @GetMapping("/doctors")
    public ResponseEntity<List<Doctor>> getAllUsers() {
        return new ResponseEntity<List<Doctor>>((List<Doctor>) doctorService.getAllDoctor(), HttpStatus.OK);
    }

    @PostMapping("/{mail}")
    public ResponseEntity<Doctor> saveHealthTips(@RequestBody String healthTip, @PathVariable("mail") final String mail) throws DoctorNotFoundException {
        Doctor doctor = doctorService.findByEmail(mail);
        if(mail.equalsIgnoreCase(doctor.getEmail())){
            doctor.setHealthTips(healthTip);
            doctorService.saveDoctor(doctor);
        }
        return new ResponseEntity<>(doctor,HttpStatus.CREATED);
    }
}
