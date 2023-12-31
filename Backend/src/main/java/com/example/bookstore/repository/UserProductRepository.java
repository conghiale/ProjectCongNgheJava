package com.example.bookstore.repository;

import com.example.bookstore.model.Product;
import com.example.bookstore.model.User;
import com.example.bookstore.model.UserProduct;
import com.example.bookstore.model.UserProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProductRepository extends JpaRepository<UserProduct, UserProductId> {
    List<UserProduct> findByUser(User user);

    List<UserProduct> findByProduct(Product product);

    UserProduct findByUserAndProduct(User user, Product product);

    void deleteByUserAndProduct(User user, Product product);
}
