package com.example.bookstore.iservice;

import com.example.bookstore.model.Author;
import com.example.bookstore.model.DetailOrder;
import com.example.bookstore.model.DetailOrderID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IDetailOrderService {
    List<DetailOrder> selectAll();
    Page<DetailOrder> selectAll(Pageable pageable);
    DetailOrder selectById(DetailOrderID id);
    DetailOrder insert(DetailOrder detailOrder);
    boolean deleteById(DetailOrderID id);
    DetailOrder update(DetailOrder detailOrderUpdate);
}
