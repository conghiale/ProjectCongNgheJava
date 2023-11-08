package com.example.bookstore.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(UserProductId.class)
@Table(name = "user_products")
public class UserProduct {
    @Id
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @JsonManagedReference
    @ManyToOne (optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;
    private Double cost;
    private LocalDate dateChoose;
}
