package com.example.bookstore.service;

import com.example.bookstore.iservice.IAuthorService;
import com.example.bookstore.model.Author;
import com.example.bookstore.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class AuthorService implements IAuthorService {
    @Autowired
    private AuthorRepository authorRepository;
    @Override
    @Transactional
    public List<Author> selectAll() {
        return authorRepository.findAll();
    }

    @Override
    public Page<Author> selectAll(Pageable pageable) {
        return authorRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Author selectById(UUID id) {
        return authorRepository.findById(id).orElse(null);
    }

    @Override
    public Author selectByName(String name) {
        return authorRepository.findByName(name);
    }

    @Override
    @Transactional
    public Author insert(Author author) {
        return authorRepository.save(author);
    }

    @Override
    @Transactional
    public boolean deleteById(UUID id) {
        if (authorRepository.existsById(id))
            authorRepository.deleteById(id);
        return !authorRepository.existsById(id);
    }

    @Override
    @Transactional
    public Author update(Author authorUpdate) {
        return authorRepository.save(authorUpdate);
    }
}
