package com.stackroute.volunteer.service;

import com.stackroute.volunteer.exceptions.VolunteerAlradyExistsException;
import com.stackroute.volunteer.model.Volunteer;
import com.stackroute.volunteer.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VolunteerServiceImpl implements  VolunteerService {


    private VolunteerRepository volunteerRepository;

    public VolunteerServiceImpl(VolunteerRepository volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }
    @Override
    public Volunteer saveVolunteer(Volunteer volunteer) throws VolunteerAlradyExistsException {
        return volunteerRepository.save(volunteer);
    }

    @Override
    public List<Volunteer> getAllVolunteer() {
        return (List<Volunteer>) volunteerRepository.findAll();
    }
}
