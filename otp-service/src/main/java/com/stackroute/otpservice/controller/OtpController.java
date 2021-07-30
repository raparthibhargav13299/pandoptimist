package com.stackroute.otpservice.controller;

import com.stackroute.otpservice.model.User;
import com.stackroute.otpservice.service.EmailService;
import com.stackroute.otpservice.service.OtpService;
import com.stackroute.otpservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class OtpController {
    @Autowired
    private EmailService service;
    @Autowired
    public OtpService otp;
    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public void saveUser(@RequestBody User user) {
        Integer ss=otp.generateOTP();
        user.setOtp(ss.toString());
        otp.add(user);
        service.sendEmail(user.getMail(), "Your OTP is:",ss);
        userService.saveUser(user);
    }


    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<List<User>>((List<User>) userService.getAllUser(), HttpStatus.OK);

    }


    @RequestMapping(value ="/validate/otp/{otpnum}/mail/{mail}", method = RequestMethod.GET)
    public  String validateOtp(@PathVariable("otpnum") Integer otpnum, @PathVariable("mail") String mail){
        System.out.println(otpnum+mail);
        String os=otp.validate(otpnum,mail);
        System.out.println(os);
        return os;
    }

}
