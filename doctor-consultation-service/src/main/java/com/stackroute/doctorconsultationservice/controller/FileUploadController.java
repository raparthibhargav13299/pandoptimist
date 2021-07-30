package com.stackroute.doctorconsultationservice.controller;

import com.stackroute.doctorconsultationservice.service.DoctorService;
import com.stackroute.doctorconsultationservice.helper.FileUploadHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class FileUploadController {

    @Autowired
    FileUploadHelper fileUploadHelper;

    @Autowired
    private DoctorService doctorService;

    @PostMapping("/upload-file")
    public ResponseEntity<String> fileUpload(@RequestParam(value="file") MultipartFile file){
        try {

            log.info(file.getContentType());
            log.info(file.getOriginalFilename());
            log.info(file.getName());

            if (file.isEmpty()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Must have a file");
            }


            boolean f=fileUploadHelper.uploadFile(file);
            if(f){

                return ResponseEntity.ok("File is uploded");
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong!!!!!!!");
    }
}
