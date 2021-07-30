package com.stackroute.medicalrequestservice.service;

import com.stackroute.medicalrequestservice.model.Resources;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ResourceService {
    Resources saveResource(Resources resources);
  List<Resources> getAllResource();
  List<Resources> findAllResource();
  List<Resources> getSpecificResource(String requirment,String city);
}
