package com.stackroute.graphservice.service;

import com.stackroute.graphservice.model.Donor;
import com.stackroute.graphservice.repository.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonorServiceImpl implements  DonorService {

    private DonorRepository donorRepository ;
    @Autowired
    public DonorServiceImpl(DonorRepository donorRepository) {
        this.donorRepository = donorRepository;
    }
    @Override
    public Donor saveDonor(Donor donor) {

        return donorRepository.save(donor);
    }

    @Override
    public List<Donor> getAllDonor() {
        return (List<Donor>) donorRepository.findAll();
    }
}
