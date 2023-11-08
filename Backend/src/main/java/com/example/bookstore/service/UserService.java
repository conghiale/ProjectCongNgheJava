package com.example.bookstore.service;

import com.example.bookstore.iservice.IUserService;
import com.example.bookstore.model.Product;
import com.example.bookstore.model.User;
import com.example.bookstore.model.UserProduct;
import com.example.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    @Transactional
    public List<User> selectAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public Page<User> selectAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public User selectById(UUID id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public User selectByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    @Transactional
    public User insert(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public boolean deleteById(UUID id) {
        if (userRepository.existsById(id))
            userRepository.deleteById(id);
        return !userRepository.existsById(id);
    }

    @Override
    @Transactional
    public User update(User userUpdate) {
        return userRepository.save(userUpdate);
    }

    @Override
    @Transactional
    public List<Product> getProductsBought(Pageable pageable, UUID id) {
        return userRepository.getProductsBought(pageable, id);
    }

    @Transactional
    public UserProduct increaseProductQuantitiesForUser(User user, Product product) {
//        Lấy danh sách user_product của user
        Set<UserProduct> userProducts = user.getUserProducts();

        // Tìm user_product theo user và product
        UserProduct userProduct = userProducts.stream()
                .filter(up -> up.getProduct().getId().equals(product.getId()))
                .findFirst()
                .orElse(null);

//            Nếu không tìm thấy user_product thì tạo mới
        if (userProduct == null) {
            userProduct = new UserProduct(user, product, 1, product.getPrice(), LocalDate.now());
        } else {
//            Nếu tìm thấy user_product thì cập nhật quantity
            userProduct.setQuantity(userProduct.getQuantity() + 1);
            userProduct.setCost(userProduct.getProduct().getPrice() * userProduct.getQuantity());
        }

        return userProduct;
    }

    @Transactional
    public UserProduct decreaseProductQuantitiesForUser(User user, Product product) {
//        Lấy danh sách user_product của user
        Set<UserProduct> userProducts = user.getUserProducts();

        // Tìm user_product theo user và product
        UserProduct userProduct = userProducts.stream()
                .filter(up -> up.getProduct().getId().equals(product.getId()))
                .findFirst()
                .orElse(null);

//        Nếu tìm thấy user_product thì cập nhật quantity
        if (userProduct != null) {
            int quantity = userProduct.getQuantity();
            if (quantity > 0) {
                userProduct.setQuantity(userProduct.getQuantity() - 1);
            }
        }

        return userProduct;
    }
}
