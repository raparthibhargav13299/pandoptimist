//package com.stackroute.doctorconsultationservice.Consumer;
//
//import com.stackroute.doctorconsultationservice.model.Doctor;
//import com.stackroute.doctorconsultationservice.rabbitmqConfig.MessageConfig;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DoctorConsumer {
//
//    @RabbitListener(queues = MessageConfig.QUEUE)
//    public void consumeMessageFromQueue(Doctor doctor) {
//        System.out.println("Message recieved from queue : " + doctor);
//    }
//}
