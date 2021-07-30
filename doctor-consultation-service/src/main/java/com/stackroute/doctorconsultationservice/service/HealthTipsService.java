package com.stackroute.doctorconsultationservice.service;

import com.stackroute.doctorconsultationservice.model.HealthTips;

import java.util.List;

public interface HealthTipsService {
    HealthTips saveHealthTips(HealthTips healthTips);
   List<HealthTips> getHealthTips();

}
