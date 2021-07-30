package com.stackroute.doctorconsultationservice.service;

import com.stackroute.doctorconsultationservice.model.HealthTips;
import com.stackroute.doctorconsultationservice.repository.HealthTipsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HealthTipsServiceImpl implements HealthTipsService{

    @Autowired
    HealthTipsRepository healthTipsRepository;

    @Override
    public HealthTips saveHealthTips(HealthTips healthTips) {
       return healthTipsRepository.save(healthTips);
    }

    @Override
    public List<HealthTips> getHealthTips() {
        return healthTipsRepository.findAll();
    }
}
