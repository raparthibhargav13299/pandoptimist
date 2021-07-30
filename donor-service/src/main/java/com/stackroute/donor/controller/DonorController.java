package com.stackroute.donor.controller;


import com.stackroute.donor.Exceptions.DonorAlradyExistsException;
import com.stackroute.donor.model.Donor;
import com.stackroute.donor.rabbitmqConfig.MessageConfig;
import com.stackroute.donor.service.DonorService;
import com.stackroute.donor.service.SequenceGeneratorService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class DonorController {

    @Autowired
    private RabbitTemplate template;
    private DonorService donorService;


    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    public DonorController(DonorService donorService) {
        this.donorService = donorService;
    }



    @PostMapping("/donor")
    public ResponseEntity<Donor> saveDonor(@RequestBody Donor donor) throws DonorAlradyExistsException {
        donor.setId(sequenceGeneratorService.getSequenceNumber(Donor.SEQUENCE_NAME));
        Donor savedDonor= donorService.saveDonor(donor);
        template.convertAndSend(MessageConfig.EXCHANGE, MessageConfig.ROUTING_KEY, savedDonor);
        return new ResponseEntity<>(savedDonor, HttpStatus.CREATED);
    }
    @GetMapping("/donors")
    public ResponseEntity<List<Donor>> getAllDonor() throws Exception{
        return  new ResponseEntity<List<Donor>>((List<Donor>) donorService.getAllDonor(),HttpStatus.OK);

    }
}
