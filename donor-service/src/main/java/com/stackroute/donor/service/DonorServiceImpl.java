package com.stackroute.donor.service;

import com.stackroute.donor.Exceptions.DonorAlradyExistsException;
import com.stackroute.donor.model.Donor;
import com.stackroute.donor.repository.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonorServiceImpl implements  DonorService {


    private DonorRepository donorRepository ;

    public DonorServiceImpl(DonorRepository donorRepository) {
        this.donorRepository = donorRepository;
    }
    @Override
    public Donor saveDonor(Donor donor) throws DonorAlradyExistsException {
        return donorRepository.save(donor);
    }

    @Override
    public List<Donor> getAllDonor() {
        return (List<Donor>) donorRepository.findAll();
    }
}
