package com.example.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String name;
    private String username;
    private String email;
    private String phone;
    private String gender;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dob;
}
