package com.stackroute.medicalrequestservice.Consumer;

import com.stackroute.medicalrequestservice.model.MedicalRequest;

import com.stackroute.medicalrequestservice.rabbitmqConfig.MessageConfig;
import com.stackroute.medicalrequestservice.repository.MedicalRequestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
@Slf4j
public class MedicalRequestConsumer {
   List<MedicalRequest> openRequests=new ArrayList<>();
   List<MedicalRequest> requests= new ArrayList<>();


    private MedicalRequestRepository medicalRequestRepository;
    @Autowired
    public MedicalRequestConsumer(MedicalRequestRepository medicalRequestRepository) {
        this.medicalRequestRepository = medicalRequestRepository;
    }

    @RabbitListener(queues = MessageConfig.QUEUE)
    public void consumeMessageFromQueue(MedicalRequest medicalRequest) {
        medicalRequestRepository.save(medicalRequest);
        log.info("Message recieved from queue : "+ medicalRequest);

    }

    public List<MedicalRequest> getAllRequests(){
        requests=(ArrayList<MedicalRequest>) medicalRequestRepository.findAll();
        System.out.println(requests);
        openRequests=requests.stream().filter(openReq->openReq.getRequestStatus().equalsIgnoreCase("open")).collect(Collectors.toList());
        Random rand = new Random();
        log.info("Filter single obj "+openRequests.get(rand.nextInt(openRequests.size())));
        return openRequests;
    }

    public MedicalRequest randomOpenRequests(){
        requests=(ArrayList<MedicalRequest>) medicalRequestRepository.findAll();
        openRequests=requests.stream().filter(openReq->openReq.getRequestStatus().equalsIgnoreCase("open")).collect(Collectors.toList());
        Random rand = new Random();
        log.info("Filter single obj "+openRequests.get(rand.nextInt(openRequests.size())));
        MedicalRequest randomRequest=openRequests.get(rand.nextInt(openRequests.size()));
        return randomRequest;
    }


}
