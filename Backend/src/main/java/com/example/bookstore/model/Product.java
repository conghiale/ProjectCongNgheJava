package com.example.bookstore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String title;
    private Double price;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate publicationDate;
    private String publisher;
    private String image;
    private Boolean sale;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @JsonBackReference
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<DetailOrder> detailOrders = new HashSet<>();


    @JsonManagedReference
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<Review> reviews = new HashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<UserProduct> userProducts = new HashSet<>();
}
