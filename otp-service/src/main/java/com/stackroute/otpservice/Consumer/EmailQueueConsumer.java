//package com.stackroute.otpservice.Consumer;
//
//
//import com.stackroute.otpservice.model.EmailQueue;
//import com.stackroute.otpservice.rabbitmqConfig.MessageConfig;
//import com.stackroute.otpservice.service.EmailService;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//import java.util.stream.Collectors;
//
//@Component
//public class EmailQueueConsumer {
//
//    @Autowired
//    private EmailService service;
//
//   @RabbitListener(queues = MessageConfig.QUEUE2)
//    public void consumeMessageFromQueue(EmailQueue emailQueue) {
//       service.sendEmailToPatient(emailQueue.getMailId(), "Regarding the MedicalRequest",emailQueue.getBody());
//        System.out.println("Message recieved from queue : "+ emailQueue);
//
//    }
//}
