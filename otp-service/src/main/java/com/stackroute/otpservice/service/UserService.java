package com.stackroute.otpservice.service;

import com.stackroute.otpservice.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user );
    List<User> getAllUser();


}
