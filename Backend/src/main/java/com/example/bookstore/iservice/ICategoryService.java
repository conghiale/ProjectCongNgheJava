package com.example.bookstore.iservice;

import com.example.bookstore.model.Category;
import com.example.bookstore.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICategoryService {
    List<Category> selectAll();
    Page<Category> selectAll(Pageable pageable);
    Category selectById(Integer id);
    Category insert(Category category);
    boolean deleteById(Integer id);
    Category update(Category categoryUpdate);
//    List<Product> getProductsByCategory(Integer id);
}
