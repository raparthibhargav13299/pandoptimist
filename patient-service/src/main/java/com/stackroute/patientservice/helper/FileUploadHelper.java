package com.stackroute.patientservice.helper;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileUploadHelper {
    public final String UPLOAD_DIR="F:\\A project\\pandoptimist\\patient-service\\src\\main\\resources\\static\\images";
    public boolean uploadFile(MultipartFile multipartFile){
        boolean image= false;

        try{


            Files.copy(multipartFile.getInputStream(), Paths.get(UPLOAD_DIR+File.separator+multipartFile.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            image=true;
        }
        catch(Exception e){
        e.printStackTrace();
        }
        return image;
    }
}
