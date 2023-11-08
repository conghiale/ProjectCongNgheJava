package com.example.bookstore.repository;

import com.example.bookstore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p WHERE p.name LIKE %:keyword% OR p.title LIKE %:keyword% OR p.publisher LIKE %:keyword%")
    List<Product> searchProductsByString(@Param("keyword") String keyword);
    @Query("SELECT p FROM Product p WHERE p.author.id=:id")
    List<Product> searchProductsByAuthor(@Param("id") UUID id);
    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);
}
