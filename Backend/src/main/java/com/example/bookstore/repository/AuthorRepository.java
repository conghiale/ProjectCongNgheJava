package com.example.bookstore.repository;

import com.example.bookstore.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthorRepository extends JpaRepository<Author, UUID> {
    Author findByName(String name);
}
