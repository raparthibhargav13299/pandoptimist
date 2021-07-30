package com.stackroute.otpservice.service;

import com.stackroute.otpservice.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void  sendEmail(String toEmail, String subject, int body){
        SimpleMailMessage message=new SimpleMailMessage();
//        message.setFrom("raparthibhargav13299@gmail.com");
        message.setTo(toEmail);

        message.setSubject(subject);
        message.setText(String.valueOf(body));

        mailSender.send(message);
        log.info("Mail sent");

    }

    public void  sendEmailToPatient(String toEmail, String subject, String body){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("raparthibhargav13299@gmail.com");
        message.setTo(String.valueOf(toEmail));

        message.setSubject(subject);
        message.setText(String.valueOf(body));

        mailSender.send(message);
        log.info("Mail sent");

    }

}
