package com.pgh.student_management.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.pgh.student_management.dto.response.CloudinaryResponseDTO;
import com.pgh.student_management.service.CloudinaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/upload")
@RequiredArgsConstructor
public class UploadController {
    private final CloudinaryService cloudinaryService;

    @PostMapping
    public ResponseEntity<CloudinaryResponseDTO> upload(@RequestParam("file") MultipartFile file) {
        Map<String, Object> data = this.cloudinaryService.upload(file);

        return ResponseEntity.ok(
                new CloudinaryResponseDTO(
                        data.get("secure_url").toString(),
                        data.get("public_id").toString()
                )
        );

    }
}
