package com.example.bookstore.service;

import com.example.bookstore.iservice.IUserProductService;
import com.example.bookstore.model.Product;
import com.example.bookstore.model.User;
import com.example.bookstore.model.UserProduct;
import com.example.bookstore.model.UserProductId;
import com.example.bookstore.repository.UserProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserProductService implements IUserProductService {
    @Autowired
    private UserProductRepository userProductRepository;

    @Override
    @Transactional
    public List<UserProduct> selectAll() {
        return userProductRepository.findAll();
    }

    @Override
    @Transactional
    public UserProduct selectById(UserProductId userProductId) {
        return userProductRepository.findById(userProductId).orElse(null);
    }

    @Override
    @Transactional
    public UserProduct insert(UserProduct userProduct) {
        return userProductRepository.save(userProduct);
    }

    @Override
    @Transactional
    public boolean deleteById(UserProductId userProductId) {
        if (userProductRepository.existsById(userProductId))
            userProductRepository.deleteById(userProductId);
        return !userProductRepository.existsById(userProductId);
//        Optional<UserProduct> userProductOptional = userProductRepository.findById(userProductId);
//        if (userProductOptional.isPresent()) {
//            userProductRepository.delete(userProductOptional.get());
//            return true;
//        }
//        return false;
    }

    @Override
    @Transactional
    public boolean deleteByUserAndProduct(UserProductId userProductId) {
        if (userProductRepository.existsById(userProductId))
            userProductRepository.deleteByUserAndProduct(userProductId.getUser(), userProductId.getProduct());
        return !userProductRepository.existsById(userProductId);
    }

    @Override
    @Transactional
    public UserProduct update(UserProduct userProductUpdate) {
        return userProductRepository.save(userProductUpdate);
    }

    @Override
    @Transactional
    public List<UserProduct> findByUser(User user) {
        return userProductRepository.findByUser(user);
    }

    @Override
    @Transactional
    public List<UserProduct> findByProduct(Product product) {
        return userProductRepository.findByProduct(product);
    }

    @Override
    @Transactional
    public UserProduct findByUserAndProduct(User user, Product product) {
        return userProductRepository.findByUserAndProduct(user, product);
    }
}
