package com.example.bookstore.iservice;

import com.example.bookstore.model.Author;
import com.example.bookstore.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IOrderService {
    List<Order> selectAll();
    Page<Order> selectAll(Pageable pageable);
    Order selectById(Integer id);
    Order insert(Order order);
    boolean deleteById(Integer id);
    Order update(Order orderUpdate);
}
