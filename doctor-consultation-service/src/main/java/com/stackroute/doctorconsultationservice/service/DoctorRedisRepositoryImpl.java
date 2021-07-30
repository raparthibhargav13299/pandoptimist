package com.stackroute.doctorconsultationservice.service;

import com.stackroute.doctorconsultationservice.exceptions.DoctorNotFoundException;
import com.stackroute.doctorconsultationservice.model.DoctorRedis;
import com.stackroute.doctorconsultationservice.repository.DoctorRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DoctorRedisRepositoryImpl implements DoctorRedisRepository {
    @Autowired
    private RedisTemplate<String, DoctorRedis> redisTemplate;

    @Autowired
    private DoctorRedisRepository doctorRedisRepository;

    private static final String key="doctor";

    @Override
    public void save(DoctorRedis doctor1, String mail) {
        redisTemplate.opsForSet().add(key,doctor1);
    }

    @Override
    public void update(DoctorRedis doctor) {
        if(redisTemplate.opsForSet().isMember(key,doctor)){
            redisTemplate.opsForSet().remove(key,doctor);
        }
    }

    @Override
    public Set<DoctorRedis> findAll() {
        return redisTemplate.opsForSet().members(key);
    }

}
