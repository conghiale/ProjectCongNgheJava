package com.example.bookstore.controller;

import com.example.bookstore.dto.AuthorDTO;
import com.example.bookstore.dto.OrderDto;
import com.example.bookstore.iservice.*;
import com.example.bookstore.model.*;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.http11.filters.IdentityOutputFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/orders")
public class OrderController {
    private final IUserService userService;

    private final IProductService productService;

    private final IUserProductService userProductService;
    private final IOrderService orderService;
    private final IDetailOrderService detailOrderService;

//    Trả về tất cả order (success)
    @GetMapping
    public ResponseEntity<Map<String, Object>> getOrders(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "5") int size,
                                                         @RequestParam(defaultValue = "id, desc") String[] sort) {
        Map<String, Object> data = new HashMap<>();
        HttpStatus statusCode;

        Sort sortable = Sort.by(sort[0]);
        if (sort.length > 1) {
            if ("desc".equals(sort[1])) {
                sortable = sortable.descending();
            } else {
                sortable = sortable.ascending();
            }
        }
        Pageable pageable = PageRequest.of(page, size, sortable);

        Page<Order> orders = orderService.selectAll(pageable);

        if (orders != null)
            statusCode = HttpStatus.OK;
        else
            statusCode = HttpStatus.NO_CONTENT; // 204

        data.put("orders", orders);
        return ResponseEntity.status(statusCode).body(data);
    }

//    Trả về 1 order (success)
    @GetMapping(value = "/{id}")
    public ResponseEntity<Map<String, Object>> getOrderById(@PathVariable("id") Integer id) {
        Map<String, Object> data = new HashMap<>();
        HttpStatus statusCode;

        Order order = orderService.selectById(id);

        if (order != null)
            statusCode = HttpStatus.OK;
        else
            statusCode = HttpStatus.NO_CONTENT; // 204

        data.put("order", order);
        return ResponseEntity.status(statusCode).body(data);
    }

//    Create order (success)
    @PostMapping
    public ResponseEntity<Map<String, Object>> insertOrder(@RequestBody OrderDto orderDto, Authentication authentication) {
        Map<String, Object> data = new HashMap<>();
        HttpStatus statusCode;

        List<Integer> idProducts = orderDto.getIdProducts();

        Order order = new Order();
        order = orderService.insert(order);

        User user = null;
        if (authentication != null) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            user = userService.selectByUsername(username);
        }

        Set<DetailOrder> detailOrders = new HashSet<>();

        Product product;
        UserProduct userProduct;
        double cost = 0.0;
        for (Integer idProduct : idProducts) {
            product = productService.selectById(idProduct);

            if (product != null && user != null) {
                userProduct = userProductService.findByUserAndProduct(user, product);

                if (userProduct != null) {
                    DetailOrder detailOrder = new DetailOrder();

                    detailOrder.setIdProduct(idProduct);
                    detailOrder.setCreateAt(LocalDate.now());
                    detailOrder.setQuantity(userProduct.getQuantity());
                    detailOrder.setPay(false);
                    detailOrder.setCost(userProduct.getCost());
                    cost += detailOrder.getCost();

                    detailOrder.setOrder(order);
                    detailOrder.setProduct(product);

                    detailOrder = detailOrderService.insert(detailOrder);
                    if (detailOrder != null) {
                        detailOrders.add(detailOrder);

                        product.getDetailOrders().add(detailOrder);
                        product = productService.update(product);
                        if (product == null)
                            throw new RuntimeException("Not update product for detail order.");
                    }
                }
            }
        }

        order.setUser(user);
        order.setAddress("Cong 7, Truong Dai Hoc Ton Duc Thang");
        order.setCreateAt(LocalDate.now());
        order.setDetailOrders(detailOrders);
        order.setCost(cost);

        order = orderService.insert(order);
        if (order != null) {
            assert user != null;
            user.getOrders().add(order);
            user = userService.update(user);
            if (user != null)
                statusCode = HttpStatus.OK;
            else
                statusCode = HttpStatus.SERVICE_UNAVAILABLE;
        } else
            statusCode = HttpStatus.SERVICE_UNAVAILABLE;

        data.put("order", order);
        return ResponseEntity.status(statusCode).body(data);
    }

//    Delete order
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Map<String, Object>> deleteOrder(@PathVariable("id") Integer id) {
        Map<String, Object> data = new HashMap<>();
        HttpStatus statusCode;

        boolean isRemove = orderService.deleteById(id);

        if (isRemove)
            statusCode = HttpStatus.OK;
        else
            statusCode = HttpStatus.SERVICE_UNAVAILABLE;

        data.put("isRemove", isRemove);
        return ResponseEntity.status(statusCode).body(data);
    }

}
