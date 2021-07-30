package com.stackroute.graphservice.controller;


import com.stackroute.graphservice.model.Donor;
import com.stackroute.graphservice.service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class DonorController {

    private DonorService donorService;

    @Autowired
    public DonorController(DonorService donorService) {
        this.donorService = donorService;
    }


    @PostMapping("/donor")
    public ResponseEntity<Donor> saveDonor(@RequestBody Donor donor)  {
        Donor savedDonor= donorService.saveDonor(donor);

        return new ResponseEntity<>(savedDonor, HttpStatus.CREATED);
    }
    @GetMapping("/donors")
    public ResponseEntity<List<Donor>> getAllDonor() {
        return  new ResponseEntity<List<Donor>>((List<Donor>) donorService.getAllDonor(),HttpStatus.OK);

    }
}
