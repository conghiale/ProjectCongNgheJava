package com.example.bookstore.service;

import com.example.bookstore.iservice.IOrderService;
import com.example.bookstore.model.Author;
import com.example.bookstore.model.Order;
import com.example.bookstore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Override
    @Transactional
    public List<Order> selectAll() {
        return orderRepository.findAll();
    }

    @Override
    @Transactional
    public Page<Order> selectAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Order selectById(Integer id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Order insert(Order order) {
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public boolean deleteById(Integer id) {
        if (orderRepository.existsById(id))
            orderRepository.deleteById(id);
        return !orderRepository.existsById(id);
    }

    @Override
    @Transactional
    public Order update(Order orderUpdate) {
        return orderRepository.save(orderUpdate);
    }
}
