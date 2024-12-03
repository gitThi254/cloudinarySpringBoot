package com.example.uploadImage.controller;

import com.example.uploadImage.service.FileUpload;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class FileUploadController {

    private final FileUpload fileUpload;

    @GetMapping
    public String home(){
        return "home";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("image") MultipartFile multipartFile) throws IOException {
        try {
           return fileUpload.uploadFile(multipartFile);
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}