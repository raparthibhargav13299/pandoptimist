package com.stackroute.medicalrequestservice.controller;

import com.stackroute.medicalrequestservice.Consumer.MedicalRequestConsumer;
//import com.stackroute.medicalrequestservice.model.Bed;
//import com.stackroute.medicalrequestservice.model.MedicalEquipment;
//import com.stackroute.medicalrequestservice.model.MedicalRequest;
//import com.stackroute.medicalrequestservice.model.Medicine;
import com.stackroute.medicalrequestservice.model.EmailQueue;
import com.stackroute.medicalrequestservice.model.MedicalRequest;
import com.stackroute.medicalrequestservice.model.Resources;

import com.stackroute.medicalrequestservice.rabbitmqConfig.MessageConfig;
import com.stackroute.medicalrequestservice.repository.MedicalRequestRepository;
import com.stackroute.medicalrequestservice.service.MedicalRequestService;
import com.stackroute.medicalrequestservice.service.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("api/v1/resource")
@CrossOrigin(origins = "http://localhost:4200")
public class ResourceController {

    private MedicalRequestRepository medicalRequestRepository;
    private MedicalRequestConsumer medicalRequestConsumer;
    private MedicalRequestService medicalRequestService;
    private ResourceService resourceService;

    @Autowired
    public ResourceController(MedicalRequestRepository medicalRequestRepository, MedicalRequestConsumer medicalRequestConsumer, MedicalRequestService medicalRequestService, ResourceService resourceService) {
        this.medicalRequestRepository = medicalRequestRepository;
        this.medicalRequestConsumer = medicalRequestConsumer;
        this.medicalRequestService = medicalRequestService;
        this.resourceService = resourceService;
    }

    @Autowired
    private RabbitTemplate template;

    @PostMapping("/addResource")
    public ResponseEntity<Resources> saveResources(@RequestBody Resources resources) {
        Resources savedResource = resourceService.saveResource(resources);
        return new ResponseEntity<>(savedResource, HttpStatus.CREATED);
    }
    @GetMapping("/allResources")
    public ResponseEntity<List<Resources>> getResources(){
        return new ResponseEntity<List<Resources>>((List<Resources>) resourceService.getAllResource(), HttpStatus.OK);
    }

    @GetMapping("/openRequest")
    public ResponseEntity<List<MedicalRequest>> saveBed() {

        List<MedicalRequest> openReq=medicalRequestConsumer.getAllRequests();
        return new ResponseEntity<List<MedicalRequest>>((List<MedicalRequest>)openReq, HttpStatus.CREATED);
    }
    @GetMapping("/openRequest/random")
    public ResponseEntity<MedicalRequest> randomRequest() {

        MedicalRequest randomopenReq=medicalRequestConsumer.randomOpenRequests();
        return new ResponseEntity<MedicalRequest>((MedicalRequest)randomopenReq, HttpStatus.CREATED);
    }
    @GetMapping("/search/{requirment}/{city}")
    public ResponseEntity<List<Resources>> Request(@PathVariable("requirment") String requirment,@PathVariable("city") String city) {

        List<Resources> result=resourceService.getSpecificResource(requirment,city);
        return new ResponseEntity<List<Resources>>((List<Resources>)result, HttpStatus.CREATED);
    }
    @GetMapping("/searchMsg/{requirment}/{city}")
    public ResponseEntity<String> resourceRequest(@PathVariable("requirment") String requirment,@PathVariable("city") String city) {

        List<Resources> result=resourceService.getSpecificResource(requirment,city);
        String message="Medicine"+result.get(0).getMedicineName() +" is avilable in city"+result.get(0).getAvalabilityPlace() +
                " address: "+result.get(0).getAddress()+
                " Kindly connect Mr/Mrs : "+result.get(0).getContactPersonName()+" (Phone number: "+ result.get(0).getContactMobileNumber()+
                "\n Get Well Soon, Stay safe.";
        return ResponseEntity.status(HttpStatus.OK)
                .body(message);
    }

    @PostMapping("/close/{id}/{mailId}")
    public String closeRequest(@PathVariable("id") int id, @RequestParam(value = "body") String body, EmailQueue queue,@PathVariable("mailId")String mailId ,MedicalRequest medicalRequest) {

        EmailQueue emailQueue=new EmailQueue();
        emailQueue.setMailId(mailId);
        emailQueue.setBody(body);
        template.convertAndSend(MessageConfig.EXCHANGE2,MessageConfig.ROUTING_KEY2,emailQueue);
        medicalRequestService.sendResource(id,body,queue,mailId,medicalRequest);
        log.info("Send to quueeu");
        return body;
    }

    @PutMapping("/pass/{id}")
    public ResponseEntity<MedicalRequest> closeRequest(@PathVariable("id") int id, @RequestBody MedicalRequest medicalRequest) {

        MedicalRequest updatedRequest=medicalRequestService.updataStatus(id,medicalRequest);
        return ResponseEntity.ok(updatedRequest);

    }


}
