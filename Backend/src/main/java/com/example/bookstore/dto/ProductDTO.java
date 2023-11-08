package com.example.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class ProductDTO {
    private MultipartFile file;
    private String name;
    private Double price;
    private String title;
    private String publisher;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate publicationDate;
    private Boolean sale = true;

//    private Integer typeId;
    private Integer categoryId;
    private UUID authorId;
}
