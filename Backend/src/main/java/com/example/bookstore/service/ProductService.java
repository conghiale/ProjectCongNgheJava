package com.example.bookstore.service;

import com.example.bookstore.iservice.IProductService;
import com.example.bookstore.model.Product;
import com.example.bookstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    @Transactional
    public List<Product> selectAll() {
        return productRepository.findAll();
    }

    @Override
    @Transactional
    public Page<Product> selectAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Product selectById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Product insert(Product product) {
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public boolean deleteById(Integer id) {
        if (productRepository.existsById(id))
            productRepository.deleteById(id);
        return !productRepository.existsById(id);
    }

    @Override
    @Transactional
    public Product update(Product productUpdate) {
        return productRepository.save(productUpdate);
    }

    @Override
    @Transactional
    public List<Product> searchProductByAuthor(UUID id) {
        return productRepository.searchProductsByAuthor(id);
    }
    @Override
    @Transactional
    public List<Product> searchProductByKeyword(String keyword) {
        List<Product> products = new ArrayList<>();
        List<Product> searchProductsByString = productRepository.searchProductsByString(keyword);

        List<Product> searchByPrice = null;
        if (isDouble(keyword)) {
            double priceKey = Double.parseDouble(keyword);
            Double minPrice = priceKey - 20000;
            Double maxPrice = priceKey + 20000;
            searchByPrice = productRepository.findByPriceBetween(minPrice, maxPrice);
        }

        Product searchById = null;
        if (isInteger(keyword)) {
            Integer id = Integer.parseInt(keyword);
            searchById = selectById(id);
        }

        if (searchProductsByString != null && !searchProductsByString.isEmpty()) {
            products.addAll(searchProductsByString);
        }

        if (searchByPrice != null && !searchByPrice.isEmpty()) {
            products.addAll(searchByPrice);
        }

        if (searchById != null) {
            products.add(searchById);
        }

        return getProductsOnSale(products);
    }

    @Override
    @Transactional
    public List<Product> findByPriceBetween(int index) {
        return switch (index) {
            case 1 -> productRepository.findByPriceBetween(0.0, 100000.0);
            case 2 -> productRepository.findByPriceBetween(100000.0, 300000.0);
            case 3 -> productRepository.findByPriceBetween(300000.0, 600000.0);
            default -> productRepository.findByPriceBetween(600000.0, 60000000.0);
        };
    }

    @Override
    public List<Product> getProductsOnSale(List<Product> allProduct) {
        List<Product> products = new ArrayList<>();
        if (allProduct != null) {
            for (Product product : allProduct) {
                if (product.getSale())
                    products.add(product);
            }
        }
        return products;
    }


    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
