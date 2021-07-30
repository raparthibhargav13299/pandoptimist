package com.stackroute.donor.service;

import com.stackroute.donor.Exceptions.DonorAlradyExistsException;
import com.stackroute.donor.model.Donor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DonorService {
    Donor saveDonor(Donor donor) throws DonorAlradyExistsException;
    List<Donor> getAllDonor();
}