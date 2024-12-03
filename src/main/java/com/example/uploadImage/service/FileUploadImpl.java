package com.example.uploadImage.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileUploadImpl implements FileUpload{

    private final Cloudinary cloudinary;
    @Override
    public String uploadFile(MultipartFile multipartFile) throws IOException {
        // Tạo public ID ngẫu nhiên cho file
        String publicId = UUID.randomUUID().toString();

        // Upload file lên Cloudinary
        cloudinary.uploader()
                .upload(multipartFile.getBytes(),
                        Map.of("public_id", publicId)); // Upload với public_id

        // Tạo URL với transformation
        return cloudinary.url()
                .transformation(new Transformation()
                        .crop("fit")          // Loại cắt hình (fill)
                        .height(300)            // Chiều cao
                        .width(400))           // Chiều rộng
                .generate(publicId);
    }
}