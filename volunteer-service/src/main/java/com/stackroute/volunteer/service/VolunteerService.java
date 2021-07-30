package com.stackroute.volunteer.service;

import com.stackroute.volunteer.exceptions.VolunteerAlradyExistsException;
import com.stackroute.volunteer.model.Volunteer;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface VolunteerService {
    Volunteer saveVolunteer(Volunteer volunteer) throws VolunteerAlradyExistsException;
    List<Volunteer> getAllVolunteer();
}
