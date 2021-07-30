package com.stackroute.volunteer.controller;

import com.stackroute.volunteer.helper.FileUploadHelper;
import com.stackroute.volunteer.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class FileUploadController {

    @Autowired
    FileUploadHelper fileUploadHelper;

    @Autowired
    private VolunteerService volunteerService;

    @PostMapping("/upload-file")
    public ResponseEntity<String> fileUpload(@RequestParam(value="file") MultipartFile file){
        try {

            System.out.println(file.getContentType());
            System.out.println(file.getOriginalFilename());
            System.out.println(file.getSize());
            System.out.println(file.getName());

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