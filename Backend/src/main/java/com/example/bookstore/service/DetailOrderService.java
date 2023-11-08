package com.example.bookstore.service;

import com.example.bookstore.iservice.IDetailOrderService;
import com.example.bookstore.model.DetailOrder;
import com.example.bookstore.model.DetailOrderID;
import com.example.bookstore.repository.DetailOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DetailOrderService implements IDetailOrderService {
    @Autowired
    private DetailOrderRepository detailOrderRepository;
    @Override
    @Transactional
    public List<DetailOrder> selectAll() {
        return detailOrderRepository.findAll();
    }

    @Override
    @Transactional
    public Page<DetailOrder> selectAll(Pageable pageable) {
        return detailOrderRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public DetailOrder selectById(DetailOrderID id) {
        return detailOrderRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public DetailOrder insert(DetailOrder detailOrder) {
        return detailOrderRepository.save(detailOrder);
    }

    @Override
    @Transactional
    public boolean deleteById(DetailOrderID id) {
        if (detailOrderRepository.existsById(id))
            detailOrderRepository.deleteById(id);
        return !detailOrderRepository.existsById(id);
    }

    @Override
    @Transactional
    public DetailOrder update(DetailOrder detailOrderUpdate) {
        return detailOrderRepository.save(detailOrderUpdate);
    }
}
