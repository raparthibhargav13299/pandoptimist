package com.stackroute.doctorconsultationservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@RedisHash("DoctorRedis")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DoctorRedis {

    private String email;
    private String name;
    private String status;


}
