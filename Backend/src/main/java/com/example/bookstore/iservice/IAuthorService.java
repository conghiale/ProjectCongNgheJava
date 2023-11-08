package com.example.bookstore.iservice;

import com.example.bookstore.model.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IAuthorService {
    List<Author> selectAll();
    Page<Author> selectAll(Pageable pageable);
    Author selectById(UUID id);
    Author selectByName(String name);
    Author insert(Author author);
    boolean deleteById(UUID id);
    Author update(Author authorUpdate);
}
