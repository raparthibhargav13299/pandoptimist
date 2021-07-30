package com.stackroute.patientservice.client;

import com.stackroute.patientservice.model.Donor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@FeignClient(name="graph-service",url="localhost:8086")
public interface GraphServiceClient {


    @GetMapping("api/v1/donors")
    public List<Donor> getAllDonor();

}
