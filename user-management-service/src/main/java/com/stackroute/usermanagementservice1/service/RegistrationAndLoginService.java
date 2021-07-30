package com.stackroute.usermanagementservice1.service;

import com.stackroute.usermanagementservice1.exceptions.UserNotFoundException;
import com.stackroute.usermanagementservice1.model.User;

public interface RegistrationAndLoginService {
    User saveRegisteredUser(User user);
    User findByEmail(String email) throws UserNotFoundException;
    void updateRoleByEmail(User userWithRole);

}
