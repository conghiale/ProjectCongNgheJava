package com.example.bookstore.iservice;

import com.example.bookstore.model.Product;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IProductService {
    List<Product> selectAll();
    Page<Product> selectAll(Pageable pageable);
    Product selectById(Integer id);
    Product insert(Product product);
    boolean deleteById(Integer id);
    Product update(Product productUpdate);
    List<Product> searchProductByKeyword(String keyword);
    List<Product> findByPriceBetween(int index);
    List<Product> getProductsOnSale(List<Product> productList);
    List<Product> searchProductByAuthor(UUID keyword);
}
