package com.stackroute.doctorconsultationservice.controller;
import com.stackroute.doctorconsultationservice.model.HealthTips;
import com.stackroute.doctorconsultationservice.repository.HealthTipsRepository;
import com.stackroute.doctorconsultationservice.service.HealthTipsService;
import com.stackroute.doctorconsultationservice.service.SequenceGeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class HealthTipsController {

    @Autowired
    private HealthTipsService healthTipsService;
    @Autowired
    private HealthTipsRepository healthTipsRepository;

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;


        @PostMapping("/healthTip")
    public ResponseEntity<HealthTips> saveHealthTips(@RequestBody HealthTips healthTips){
        healthTips.setId(sequenceGeneratorService.getSequenceNumber(HealthTips.SEQUENCE_NAME));
        HealthTips savedHealthtips= healthTipsService.saveHealthTips(healthTips);
        return  new ResponseEntity<>(savedHealthtips, HttpStatus.CREATED);
    }

    @GetMapping("/allhealthTips")
    public ResponseEntity <List<HealthTips>> getAllHealthTips(){
        return new ResponseEntity<List<HealthTips>>((List<HealthTips>) healthTipsService.getHealthTips(), HttpStatus.OK);
    }
}
