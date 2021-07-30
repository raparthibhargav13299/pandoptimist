package com.stackroute.otpservice.service;


import com.stackroute.otpservice.model.User;
import com.stackroute.otpservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
@Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    @Override
    public List<User> getAllUser() {
        return (List<User>) userRepository.findAll();
    }


@Override
public User saveUser(User user) {
    return userRepository.save(user);
}


}
