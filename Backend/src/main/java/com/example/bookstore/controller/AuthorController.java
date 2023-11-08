package com.example.bookstore.controller;

import com.example.bookstore.dto.AuthorDTO;
import com.example.bookstore.iservice.*;
import com.example.bookstore.model.*;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/authors")
public class AuthorController {

    private final IAuthorService authorService;


//    Trả về tất cả authors (success)
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAuthors(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "5") int size,
                                                          @RequestParam(defaultValue = "id, desc") String[] sort) {
        Map<String, Object> data = new HashMap<>();
        HttpStatus statusCode;

        Sort sortable = Sort.by(sort[0]);
        if (sort.length > 1) {
            if ("desc".equals(sort[1])) {
                sortable = sortable.descending();
            } else {
                sortable = sortable.ascending();
            }
        }
        Pageable pageable = PageRequest.of(page, size, sortable);

        Page<Author> authors = authorService.selectAll(pageable);

        if (authors != null)
            statusCode = HttpStatus.OK;
        else
            statusCode = HttpStatus.NO_CONTENT; // 204

        data.put("authors", authors);
        return ResponseEntity.status(statusCode).body(data);
    }

//    Trả về 1 author (success)
    @GetMapping(value = "/{id}")
    public ResponseEntity<Map<String, Object>> getAuthorById(@PathVariable("id") String id) {
        Map<String, Object> data = new HashMap<>();
        HttpStatus statusCode;

        UUID authorId = UUID.fromString(id);
        Author author = authorService.selectById(authorId);

        if (author != null)
            statusCode = HttpStatus.OK;
        else
            statusCode = HttpStatus.NO_CONTENT; // 204

        data.put("author", author);
        return ResponseEntity.status(statusCode).body(data);
    }

    //    Trả về 1 author (success)
    @GetMapping(value = "/author")
    public ResponseEntity<Map<String, Object>> getAuthorByName(@RequestParam(defaultValue = "") String name) {
        Map<String, Object> data = new HashMap<>();
        HttpStatus statusCode;

        Author author = authorService.selectByName(name);

        if (author != null)
            statusCode = HttpStatus.OK;
        else
            statusCode = HttpStatus.NO_CONTENT; // 204

        data.put("author", author);
        return ResponseEntity.status(statusCode).body(data);
    }

//    Add author
    @PostMapping
    public ResponseEntity<Map<String, Object>> insertAuthor(@RequestBody AuthorDTO authorDTO) {
        Map<String, Object> data = new HashMap<>();
        HttpStatus statusCode;

        Author author = new Author();
        author.setName(authorDTO.getName());
        author.setDob(authorDTO.getDob());
        author.setDescription(authorDTO.getDescription());

        author = authorService.insert(author);

        if (author != null)
            statusCode = HttpStatus.OK;
        else
            statusCode = HttpStatus.SERVICE_UNAVAILABLE;

        return ResponseEntity.status(statusCode).body(data);
    }

//    Update author
    @PutMapping(value = "/{id}")
    public ResponseEntity<Map<String, Object>> editAuthor(@PathVariable("id") String id, @RequestBody AuthorDTO authorDTO) {
        Map<String, Object> data = new HashMap<>();
        HttpStatus statusCode;

        UUID authorId = UUID.fromString(id);
        Author author = authorService.selectById(authorId);

        author.setName(authorDTO.getName());
        author.setDob(authorDTO.getDob());
        author.setDescription(authorDTO.getDescription());

        author = authorService.update(author);

        if (author != null)
            statusCode = HttpStatus.OK;
        else
            statusCode = HttpStatus.SERVICE_UNAVAILABLE;

        return ResponseEntity.status(statusCode).body(data);
    }

//    Delete author
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Map<String, Object>> deleteAuthor(@PathVariable("id") String id) {
        Map<String, Object> data = new HashMap<>();
        HttpStatus statusCode;

        UUID authorId = UUID.fromString(id);
        boolean isRemove = authorService.deleteById(authorId);

        if (isRemove)
            statusCode = HttpStatus.OK;
        else
            statusCode = HttpStatus.SERVICE_UNAVAILABLE;

        return ResponseEntity.status(statusCode).body(data);
    }
}
