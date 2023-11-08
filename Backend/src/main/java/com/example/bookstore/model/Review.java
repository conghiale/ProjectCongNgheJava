package com.example.bookstore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer rating;
    private String comment;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
