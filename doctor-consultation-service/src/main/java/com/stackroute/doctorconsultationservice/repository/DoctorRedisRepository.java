package com.stackroute.doctorconsultationservice.repository;

import com.stackroute.doctorconsultationservice.exceptions.DoctorAlreadyPresentException;
import com.stackroute.doctorconsultationservice.exceptions.DoctorNotFoundException;
import com.stackroute.doctorconsultationservice.model.DoctorRedis;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


import java.util.Set;


public interface DoctorRedisRepository {
    void save(DoctorRedis doctor, String mail) throws DoctorAlreadyPresentException;
    void update(DoctorRedis doctor) throws DoctorNotFoundException;
    Set<DoctorRedis> findAll() throws DoctorNotFoundException;



}

