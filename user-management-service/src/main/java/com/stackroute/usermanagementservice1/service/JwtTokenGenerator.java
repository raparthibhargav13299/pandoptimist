package com.stackroute.usermanagementservice1.service;

import com.stackroute.usermanagementservice1.model.User;

import java.util.Map;

public interface JwtTokenGenerator {
    Map<String, String> generateToken(User user);

}
