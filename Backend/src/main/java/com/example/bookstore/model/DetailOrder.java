package com.example.bookstore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
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
@IdClass(DetailOrderID.class)
@Table(name = "detail_orders")
public class DetailOrder {
    @Id
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Id
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer idProduct;
    private Integer quantity;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate createAt;

    private Double Cost;

    @Column(name = "is_pay", nullable = false, columnDefinition = "boolean default false")
    private Boolean pay;
}
