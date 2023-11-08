package com.example.bookstore.service;

import com.example.bookstore.iservice.ICategoryService;
import com.example.bookstore.model.Category;
import com.example.bookstore.model.Product;
import com.example.bookstore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    @Transactional
    public List<Category> selectAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Page<Category> selectAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Category selectById(Integer id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Category insert(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    @Transactional
    public boolean deleteById(Integer id) {
        if (categoryRepository.existsById(id))
            categoryRepository.deleteById(id);
        return !categoryRepository.existsById(id);
    }

    @Override
    @Transactional
    public Category update(Category categoryUpdate) {
        return categoryRepository.save(categoryUpdate);
    }

//    @Override
//    @Transactional
//    public List<Product> getProductsByCategory(Integer id) {
//        return categoryRepository.getProductsByCategory(id);
//    }
}
