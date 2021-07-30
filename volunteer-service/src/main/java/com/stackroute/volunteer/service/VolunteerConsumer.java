//package com.stackroute.volunteer.service;
//
//
//import com.stackroute.volunteer.model.Volunteer;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;
//
//@Service
//public class VolunteerConsumer {
//
//    @KafkaListener(topics="volunteerTopic",groupId = "volunteerGroup")
//    public void consumeFromTopic(Volunteer volunteer){
//
//        System.out.println("Consumed data "+volunteer);
//
//    }
//}
