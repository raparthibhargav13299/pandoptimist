package com.stackroute.graphservice.service;




import com.stackroute.graphservice.model.Donor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DonorService {
    Donor saveDonor(Donor donor) ;
    List<Donor> getAllDonor();
}