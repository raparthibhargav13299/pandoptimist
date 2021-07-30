package com.stackroute.patientservice.controller;
import com.stackroute.patientservice.exception.RequestAlreadyExistsException;
import com.stackroute.patientservice.model.MedicalRequest;
import com.stackroute.patientservice.rabbitmqconfig.MedicalRequestConfig;
import com.stackroute.patientservice.service.MedicalRequestService;
import com.stackroute.patientservice.service.SequenceGeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("api/v1")
public class MedicalRequestController {

    private MedicalRequestService medicalRequestService;

    @Autowired
    public MedicalRequestController(MedicalRequestService medicalRequestService) {
        this.medicalRequestService = medicalRequestService;
    }
   @Autowired
   private RabbitTemplate rabbitTemplate;

    @Autowired
    private SequenceGeneratorService service;



    @PostMapping("/medicalRequest")
    public ResponseEntity<MedicalRequest> savePatientRequest(@RequestBody MedicalRequest medicalRequest) throws  RequestAlreadyExistsException {
        medicalRequest.setId(service.getSequenceNumber(MedicalRequest.SEQUENCE_NAME));
        MedicalRequest savedPatient = medicalRequestService.savePatientRequest(medicalRequest);
        log.info("Request Saved");
        rabbitTemplate.convertAndSend(MedicalRequestConfig.EXCHANGE, MedicalRequestConfig.ROUTING_KEY, savedPatient);
        return new ResponseEntity<>(savedPatient, HttpStatus.CREATED);
    }

    @GetMapping("/medicalRequests")
    public ResponseEntity<List<MedicalRequest>> getAllPatient() {
        return new ResponseEntity<List<MedicalRequest>>(medicalRequestService.getAllPatientRequest(), HttpStatus.OK);

    }
}
