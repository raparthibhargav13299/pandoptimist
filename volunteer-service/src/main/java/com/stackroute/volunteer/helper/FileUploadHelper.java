package com.stackroute.volunteer.helper;

import com.stackroute.volunteer.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileUploadHelper {

    public final String UPLOAD_DIR = "D:\\ProductDevelopment\\pandoptimist\\volunteer-service\\src\\main\\resources\\static\\images";
    @Autowired
    private VolunteerService volunteerService;

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