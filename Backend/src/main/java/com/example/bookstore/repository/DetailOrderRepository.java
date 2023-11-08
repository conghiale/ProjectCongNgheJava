package com.example.bookstore.repository;

import com.example.bookstore.model.Author;
import com.example.bookstore.model.DetailOrder;
import com.example.bookstore.model.DetailOrderID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DetailOrderRepository extends JpaRepository<DetailOrder, DetailOrderID> {
}
