package com.example.bookstore.auth;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
//@Builder
public class RegisterRequest {

    private String name;
    private String username;
    private String email;
    private String password;
    private String phone;
    private String gender;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dob;
}
