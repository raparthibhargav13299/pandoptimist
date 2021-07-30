package com.stackroute.medicalrequestservice.service;

import com.stackroute.medicalrequestservice.model.Resources;
import com.stackroute.medicalrequestservice.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResourceServiceImpl implements ResourceService{
    @Autowired
   SequenceGeneratorService sequenceGeneratorService;
    private ResourceRepository resourceRepository;
    List<Resources> allresourse=new ArrayList<>();

    @Autowired
    public ResourceServiceImpl(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
     Date createdDate= new Date();

    @Override
    public Resources saveResource(Resources resources) {
        resources.setId(sequenceGeneratorService.getSequenceNumber(resources.SEQUENCE_NAME));
        resources.setAddedTime(new Timestamp(createdDate.getTime()));
        return resourceRepository.save(resources);
    }

    @Override
    public List<Resources> getAllResource() {
        return (List<Resources>)resourceRepository.findAll();
    }

    @Override
    public List<Resources> findAllResource() {
        allresourse=(ArrayList<Resources>) resourceRepository.findAll();
        System.out.println(allresourse);
        return allresourse;
    }

    @Override
    public List<Resources> getSpecificResource(String requirment,String city) {
        List<Resources> specificResource=new ArrayList<>();
        allresourse=(ArrayList<Resources>) resourceRepository.findAll();
        System.out.println("............"+allresourse);
        specificResource=  allresourse.stream()
                .filter(res->((res.getMedicineName().equalsIgnoreCase(requirment)|| res.getBedType().equalsIgnoreCase(requirment) || res.getMedicalEquipmentName().equals(requirment)) && res.getAvalabilityPlace().equalsIgnoreCase(city))).collect(Collectors.toList());
        System.out.println(specificResource);
        return specificResource;
    }
}
// || res.getBedType().equals(requirment) || res.getMedicalEquipmentName().equals(requirment)