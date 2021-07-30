package com.stackroute.usermanagementservice1.controller;

import com.stackroute.usermanagementservice1.exceptions.UserNotFoundException;
import com.stackroute.usermanagementservice1.model.User;
import com.stackroute.usermanagementservice1.service.JwtTokenGenerator;
import com.stackroute.usermanagementservice1.service.RegistrationAndLoginService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "*")
@RequestMapping("api/")
public class RegistrationAndLoginController {

    private RegistrationAndLoginService registrationService;
    private JwtTokenGenerator jwtTokenGenerator;
    ResponseEntity<?> responseEntity;

    @Value("${app.controller.exception.message1}")
    private String message1;

    @Value("${app.controller.exception.message2}")
    private String message2;

    @Value("${app.controller.exception.message3}")
    private String message3;


    @Autowired
    public RegistrationAndLoginController(RegistrationAndLoginService registrationService, JwtTokenGenerator jwtTokenGenerator) {
        this.registrationService = registrationService;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }


    @PostMapping(value = "v11/updaterole")
    public String roleUpdate(@RequestBody User userWithRole) {
        registrationService.updateRoleByEmail(userWithRole);
        return "role Successfully updated";
    }

    @PostMapping(value = "v11/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) throws UserNotFoundException {
        Map<String, String> jwtToken11 = null;
        try {
            if (user.getEmail() == null || user.getPassword() == null) {
                throw new UserNotFoundException(message1);
            }
            User userDetails = registrationService.findByEmail(user.getEmail());
            if (userDetails == null) {
                throw new UserNotFoundException(message2);
            }
            if (!BCrypt.checkpw(user.getPassword(), userDetails.getPassword())) {
                throw new UserNotFoundException(message3);
            }
            jwtToken11 = jwtTokenGenerator.generateToken(userDetails);

        } catch (UserNotFoundException e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }

        User rolefromdb = this.registrationService.findByEmail(user.getEmail());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("token", jwtToken11.get("token"));
        httpHeaders.set("role", rolefromdb.getRole()); //keep role as empty string instead of null
        httpHeaders.set("email", user.getEmail());
        return new ResponseEntity<>("", httpHeaders, HttpStatus.OK);
    }

    @PostMapping("v11/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        User regUser = registrationService.saveRegisteredUser(user);
        return new ResponseEntity<User>(regUser, HttpStatus.CREATED);
    }


}
