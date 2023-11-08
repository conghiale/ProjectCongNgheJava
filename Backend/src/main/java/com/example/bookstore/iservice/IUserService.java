package com.example.bookstore.iservice;

import com.example.bookstore.model.Product;
import com.example.bookstore.model.User;
import com.example.bookstore.model.UserProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    List<User> selectAll();
    Page<User> selectAll(Pageable pageable);
    User selectById(UUID id);
    User selectByUsername(String username);
    User insert(User user);
    boolean deleteById(UUID id);
    User update(User userUpdate);
    List<Product> getProductsBought(Pageable pageable, UUID id);
    UserProduct increaseProductQuantitiesForUser(User user, Product product);
    UserProduct decreaseProductQuantitiesForUser(User user, Product product);
}
