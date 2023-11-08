package com.example.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDTO {
    private String name;
    @JsonFormat(pattern = "dd/MM/yy")
    private LocalDate dob;
    private String description;
}
