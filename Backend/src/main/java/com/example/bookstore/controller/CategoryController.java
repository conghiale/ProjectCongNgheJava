package com.example.bookstore.controller;

import com.example.bookstore.dto.AuthorDTO;
import com.example.bookstore.dto.CategoryDTO;
import com.example.bookstore.iservice.ICategoryService;
import com.example.bookstore.iservice.IProductService;
import com.example.bookstore.iservice.IUserProductService;
import com.example.bookstore.iservice.IUserService;
import com.example.bookstore.model.Author;
import com.example.bookstore.model.Category;
import com.example.bookstore.model.Product;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/api/categories")
public class CategoryController {
    @Autowired
    private IProductService productService;
    @Autowired
    private ICategoryService categoryService;

    //    Trả về tất cả category (success)
    @GetMapping
    public ResponseEntity<Map<String, Object>> selectAllCategories(@RequestParam(defaultValue = "0") int page,
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

        Page<Category> categories = categoryService.selectAll(pageable);

        if (categories != null)
            statusCode = HttpStatus.OK;
        else
            statusCode = HttpStatus.NO_CONTENT; // 204

        data.put("categories", categories);
        return ResponseEntity.status(statusCode).body(data);
    }

    //    Trả về 1 category (success)
    @GetMapping(value = "/{id}")
    public ResponseEntity<Map<String, Object>> selectCategoryById(@PathVariable("id") Integer id) {
        Map<String, Object> data = new HashMap<>();
        HttpStatus statusCode;

        Category category = categoryService.selectById(id);

        if (category != null)
            statusCode = HttpStatus.OK;
        else
            statusCode = HttpStatus.NO_CONTENT; // 204

        data.put("category", category);
        return ResponseEntity.status(statusCode).body(data);
    }

    //    Trả về những sản phẩm theo category (success)
    @GetMapping(value = "/{id}/products")
    public ResponseEntity<Map<String, Object>> categoryProducts(@PathVariable("id") Integer id) {
        Map<String, Object> data = new HashMap<>();
        HttpStatus statusCode;

        Category category = categoryService.selectById(id);
        Set<Product> allProductByCategory = category.getProducts();

        List<Product> products = productService.getProductsOnSale(new ArrayList<>(allProductByCategory)); // Sản phẩm còn bán
        if (!products.isEmpty())
            statusCode = HttpStatus.OK;
        else
            statusCode = HttpStatus.NO_CONTENT; // 204

        data.put("products", products);
        return ResponseEntity.status(statusCode).body(data);
    }

//    Add category
    @PostMapping
    public ResponseEntity<Map<String, Object>> insertCategory(@RequestBody CategoryDTO categoryDTO) {
        Map<String, Object> data = new HashMap<>();
        HttpStatus statusCode;

        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());

        category = categoryService.insert(category);

        if (category != null)
            statusCode = HttpStatus.OK;
        else
            statusCode = HttpStatus.SERVICE_UNAVAILABLE;

        data.put("category", category);
        return ResponseEntity.status(statusCode).body(data);
    }

//    Update category
    @PutMapping(value = "/{id}")
    public ResponseEntity<Map<String, Object>> editCategory(@PathVariable("id") Integer id, @RequestBody CategoryDTO categoryDTO) {
        Map<String, Object> data = new HashMap<>();
        HttpStatus statusCode;

        Category category = categoryService.selectById(id);

        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());

        category = categoryService.update(category);

        if (category != null)
            statusCode = HttpStatus.OK;
        else
            statusCode = HttpStatus.SERVICE_UNAVAILABLE;

        data.put("category", category);
        return ResponseEntity.status(statusCode).body(data);
    }

//    Delete category
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Map<String, Object>> deleteCategory(@PathVariable("id") Integer id) {
        Map<String, Object> data = new HashMap<>();
        HttpStatus statusCode;

        boolean isRemove = categoryService.deleteById(id);

        if (isRemove)
            statusCode = HttpStatus.OK;
        else
            statusCode = HttpStatus.SERVICE_UNAVAILABLE;

        data.put("status", isRemove);
        return ResponseEntity.status(statusCode).body(data);
    }
}
