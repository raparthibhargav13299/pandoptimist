package com.stackroute.usermanagementservice1.service;

import com.stackroute.usermanagementservice1.exceptions.UserNotFoundException;
import com.stackroute.usermanagementservice1.model.User;
import com.stackroute.usermanagementservice1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RegistrationAndLoginServiceImpl implements RegistrationAndLoginService {

    private UserRepository userrepo;
    @Value("${app.service.message1}")
    private String message1;

    @Value("${app.service.message2}")
    private String message2;


    @Autowired
    public RegistrationAndLoginServiceImpl(UserRepository userrepo) {
        this.userrepo = userrepo;
    }


    @Override
    public User saveRegisteredUser(User user) {
        return userrepo.save(user);
    }

    @Override
    public User findByEmail(String email) throws UserNotFoundException {
        User authUser = userrepo.findByEmail(email);
        if (authUser == null) {
            throw new UserNotFoundException(message2);
        }
        return authUser;
    }

    @Override
    public void updateRoleByEmail(User userWithRole) {
        User existingUser = userrepo.findByEmail(userWithRole.getEmail());
        existingUser.setRole(userWithRole.getRole());
        userrepo.updateUserRoleByEmailId(existingUser.getEmail(), existingUser.getRole());
    }

}
