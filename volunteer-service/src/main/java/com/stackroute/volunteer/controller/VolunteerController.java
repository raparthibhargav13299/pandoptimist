package com.stackroute.volunteer.controller;

import com.stackroute.volunteer.exceptions.VolunteerAlradyExistsException;
import com.stackroute.volunteer.model.Volunteer;
import com.stackroute.volunteer.service.SequenceGeneratorService;
import com.stackroute.volunteer.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/v1")
public class VolunteerController {
    private VolunteerService volunteerService;



    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    public VolunteerController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    public final String UPLOAD_DIR="D:\\ProductDevelopment\\pandoptimist\\volunteer-service\\src\\main\\resources\\static\\images";


    @PostMapping("/volunteer")
    public ResponseEntity<Volunteer> saveVolunteer(@RequestBody Volunteer volunteer) throws VolunteerAlradyExistsException  {
        volunteer.setId(sequenceGeneratorService.getSequenceNumber(Volunteer.SEQUENCE_NAME));
        Volunteer savedVolunteer= volunteerService.saveVolunteer(volunteer);
        return new ResponseEntity<>(savedVolunteer, HttpStatus.CREATED);
    }
    @GetMapping("/volunteers")
    public ResponseEntity<List<Volunteer>> getAllVolunteer() throws Exception{
        return  new ResponseEntity<List<Volunteer>>((List<Volunteer>) volunteerService.getAllVolunteer(),HttpStatus.OK);
    }
}
