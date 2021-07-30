package com.stackroute.doctorconsultationservice.helper;

import com.stackroute.doctorconsultationservice.model.Doctor;
import com.stackroute.doctorconsultationservice.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileUploadHelper {
    public final String UPLOAD_DIR="D:\\Product Development\\pandoptimist\\doctor-consultation-service\\src\\main\\resources\\static\\images";
    @Autowired
    private DoctorService doctorService;

    public boolean uploadFile(MultipartFile multipartFile){

        boolean f = false;
        try {
           Files.copy(multipartFile.getInputStream(), Paths.get(UPLOAD_DIR+ File.separator+multipartFile.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);

            f=true;

        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }

        return f;
    }
}
