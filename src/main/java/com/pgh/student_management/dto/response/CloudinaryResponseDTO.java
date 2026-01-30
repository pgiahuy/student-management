package com.pgh.student_management.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CloudinaryResponseDTO {
    private String secure_url;
    private String public_id;
}
