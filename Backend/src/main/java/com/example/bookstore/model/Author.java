package com.example.bookstore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
@Table(name = "authors")
public class Author {
    @Id
    private UUID id;
    private String name;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dob;
    private String description;

    @JsonBackReference
    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Product> products = new HashSet<>();

    public Author() {
        super();
        this.id = UUID.randomUUID();
    }
}
