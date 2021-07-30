//package com.stackroute.volunteer.service;
//
//import com.stackroute.volunteer.model.Volunteer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class VolunteerProducer {
//    public static final String topic="volunteerTopic";
//
//    @Autowired
//    private KafkaTemplate<String, Volunteer> kafkaTemp;
//
//    public  void publishToTopic(Volunteer volunteerDetails){
//        System.out.println("Publishing data "+ topic);
//        System.out.println("Publishing data before "+ volunteerDetails.getName());
//        this.kafkaTemp.send(topic,volunteerDetails);
//        System.out.println("Publishing data after "+ volunteerDetails.getName());
//    }
//}
//
