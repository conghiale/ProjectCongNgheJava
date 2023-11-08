package com.example.bookstore.controller;

import com.example.bookstore.dto.ProductDTO;
import com.example.bookstore.dto.UserDTO;
import com.example.bookstore.iservice.*;
import com.example.bookstore.model.*;
import jakarta.jws.soap.SOAPBinding;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/users")
public class UserController {
    private final IUserService userService;

    private final IProductService productService;

    private final IUserProductService userProductService;

//    @Autowired
//    private UserDetailsManager userDetailsManager;x

    @Value("${uploadDir}")
    private String uploadDir;

//    Trả về các user (success)
    @GetMapping
    public ResponseEntity<Map<String, Object>> getUsers(@RequestParam(defaultValue = "0") int page,
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

        Page<User> users = userService.selectAll(pageable);

        if (users != null)
            statusCode = HttpStatus.OK;
        else
            statusCode = HttpStatus.NO_CONTENT; // 204
        data.put("users", users);
        return ResponseEntity.status(statusCode).body(data);
    }

//    Trả về current user (success)
    @GetMapping(value = "/user")
    public ResponseEntity<Map<String, Object>> getCurrentUser(Authentication authentication) {
        Map<String, Object> data = new HashMap<>();
        HttpStatus statusCode;

        if (authentication != null) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            User user = userService.selectByUsername(username);
            if (user != null)
                statusCode = HttpStatus.OK;
            else
                statusCode = HttpStatus.NO_CONTENT; // 204
            data.put("user", user);
        } else
            statusCode = HttpStatus.UNAUTHORIZED;

        return ResponseEntity.status(statusCode).body(data);
    }

//    Trả về 1 user (success)
    @GetMapping(value = "/{id}")
    public ResponseEntity<Map<String, Object>> getUserById(@PathVariable("id") String idUser) {
        Map<String, Object> data = new HashMap<>();
        HttpStatus statusCode;

            UUID id = UUID.fromString(idUser);
            User user = userService.selectById(id);

            if (user != null)
                statusCode = HttpStatus.OK;
            else
                statusCode = HttpStatus.NO_CONTENT; // 204
            data.put("user", user);
        return ResponseEntity.status(statusCode).body(data);
    }

//    Update 1 user
    @PutMapping
    public ResponseEntity<Map<String, Object>> editCurrentUser(Authentication authentication, @RequestBody UserDTO userDTO) {
        Map<String, Object> data = new HashMap<>();
        HttpStatus statusCode;

        if (authentication != null) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            User user = userService.selectByUsername(username);

            if (user != null) {
                user.setName(userDTO.getName());
                user.setUsername(userDTO.getUsername());
                user.setEmail(userDTO.getEmail());
                user.setPhone(userDTO.getPhone());
                user.setGender(userDTO.getGender());
                user.setDob(userDTO.getDob());

                user = userService.update(user);

                if (user != null)
                    statusCode = HttpStatus.OK;
                else
                    statusCode = HttpStatus.SERVICE_UNAVAILABLE;
                data.put("user", user);
            } else
                statusCode = HttpStatus.NO_CONTENT; // 204
        }else
            statusCode = HttpStatus.UNAUTHORIZED;

        return ResponseEntity.status(statusCode).body(data);
    }

//    Remove 1 user
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Map<String, Object>> deleteUserById(@PathVariable("id") String idUser) {
        Map<String, Object> data = new HashMap<>();
        HttpStatus statusCode;

        UUID id = UUID.fromString(idUser);
        User user = userService.selectById(id);
        boolean isRemove = userService.deleteById(id);

        if (isRemove) {
            statusCode = HttpStatus.OK;
        } else
            statusCode = HttpStatus.NO_CONTENT; // 204

        data.put("user", user);
        return ResponseEntity.status(statusCode).body(data);
    }

//    Disable 1 user
    @PostMapping(value = "/user/disable/{id}")
    public ResponseEntity<Map<String, Object>> disableUserById(@PathVariable("id") String idUser) {
        Map<String, Object> data = new HashMap<>();
        HttpStatus statusCode;

        UUID id = UUID.fromString(idUser);
        User user = userService.selectById(id);

        if (user != null) {
            System.out.println("line176: " + user.getUsername());
            try {
                user.setEnabled(false);
                userService.update(user);
//                userDetailsManager.updateUser(org.springframework.security.core.userdetails.User.withUserDetails(user).disabled(true).build());
                statusCode = HttpStatus.OK;
            } catch (Exception e) {
                statusCode = HttpStatus.SERVICE_UNAVAILABLE;
                System.out.println("line175: " + e);
            }
        } else
            statusCode = HttpStatus.NO_CONTENT; // 204

        data.put("user", user);
        return ResponseEntity.status(statusCode).body(data);
    }


    //    Enable 1 user
    @PostMapping(value = "/user/enable/{id}")
    public ResponseEntity<Map<String, Object>> enableUserById(@PathVariable("id") String idUser) {
        Map<String, Object> data = new HashMap<>();
        HttpStatus statusCode;

        UUID id = UUID.fromString(idUser);
        User user = userService.selectById(id);

        if (user != null) {
            System.out.println("line176: " + user.getUsername());
            try {
                user.setEnabled(true);
                userService.update(user);
//                userDetailsManager.updateUser(org.springframework.security.core.userdetails.User.withUserDetails(user).disabled(true).build());
                statusCode = HttpStatus.OK;
            } catch (Exception e) {
                statusCode = HttpStatus.SERVICE_UNAVAILABLE;
                System.out.println("line175: " + e);
            }
        } else
            statusCode = HttpStatus.NO_CONTENT; // 204

        data.put("user", user);
        return ResponseEntity.status(statusCode).body(data);
    }

//    Trả về các product có trong cart (User đã chọn) (success)
    @GetMapping(value = "/cart")
    public ResponseEntity<Map<String, Object>> getProductsInCart(@RequestParam(defaultValue = "1") int page,
                                                                 @RequestParam(defaultValue = "5") int size,
                                                                 @RequestParam(defaultValue = "id, desc") String[] sort, Authentication authentication) {
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
        Pageable pageable = PageRequest.of(page - 1, size, sortable);

        User user = null;
        if (authentication != null) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            user = userService.selectByUsername(username);
        }

        assert user != null;
        List<Product> productsBought = userService.getProductsBought(pageable, user.getId());
        List<Product> products = productService.getProductsOnSale(productsBought);

        if (!products.isEmpty())
            statusCode = HttpStatus.OK;
        else
            statusCode = HttpStatus.NO_CONTENT; // 204
        data.put("products", products);
        return ResponseEntity.status(statusCode).body(data);
    }

//    Thêm một sản phẩm vào giỏ hàng (tăng số lượng một sản phẩm muốn mua)  (success)
    @PostMapping(value = "/cart/{id}")
    public ResponseEntity<Map<String, Object>> fluctuationOfProducts(@PathVariable("id") Integer productId,
                                                                     @RequestParam(defaultValue = "1") Integer fluctuation, Authentication authentication) {
        Map<String, Object> data = new HashMap<>();
        HttpStatus statusCode;

        User user = null;
        if (authentication != null) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            user = userService.selectByUsername(username);
        }

        Product product = productService.selectById(productId);

        if (product != null) {
            UserProduct userProduct;
            if (fluctuation == 1)
                userProduct = userService.increaseProductQuantitiesForUser(user, product);
            else
                userProduct = userService.decreaseProductQuantitiesForUser(user, product);

            UserProduct userProduct1 = userProductService.insert(userProduct);
            if (userProduct1 != null)
                statusCode = HttpStatus.OK;
            else
                statusCode = HttpStatus.SERVICE_UNAVAILABLE; // 503
        } else
            statusCode = HttpStatus.NO_CONTENT; // 204
        data.put("product", product);
        return ResponseEntity.status(statusCode).body(data);
    }

//    Xoá sản phẩm ra khỏi giỏ hàng (Khỏi danh sach khách hàng chọn mua) (success)
    @DeleteMapping(value = "/cart/{id}")
    public ResponseEntity<Map<String, Object>> removeProductFromCart(@PathVariable("id") Integer id, Authentication authentication) {
        Map<String, Object> data = new HashMap<>();
        HttpStatus statusCode;

        User user = null;
        if (authentication != null) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            user = userService.selectByUsername(username);
        }

        Product product = productService.selectById(id); // Sản phẩm muốn remove

        if (product != null) {
            UserProduct userProduct = userProductService.findByUserAndProduct(user, product);

            assert user != null;
            if (user.getUserProducts().contains(userProduct)) {
                boolean isRemove = user.getUserProducts().remove(userProduct);

                if (isRemove) {
                    user = userService.update(user);
                    if (user != null) {
                        UserProductId userProductId = new UserProductId(user, product);
                        isRemove = userProductService.deleteById(userProductId);
                        if (isRemove)
                            statusCode = HttpStatus.OK;
                        else
                            statusCode = HttpStatus.SERVICE_UNAVAILABLE;
                    } else
                        statusCode = HttpStatus.SERVICE_UNAVAILABLE; // 503
                } else
                    statusCode = HttpStatus.SERVICE_UNAVAILABLE; // 503
            } else
                statusCode = HttpStatus.NO_CONTENT; // 204

        } else
            statusCode = HttpStatus.NO_CONTENT; // 204
        data.put("product", product);

        return ResponseEntity.status(statusCode).body(data);
    }

//    get order (success)
    @GetMapping(value = "/order")
    public ResponseEntity<Map<String, Object>> getOrders(Authentication authentication) {
        Map<String, Object> data = new HashMap<>();
        HttpStatus statusCode;

        User user = null;
        if (authentication != null) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            user = userService.selectByUsername(username);
        }

        Set<Order> orders = null;
        if (user != null)
            orders = user.getOrders();

        if (orders != null) {
            statusCode = HttpStatus.OK;
        } else
            statusCode = HttpStatus.NO_CONTENT; // 204
        data.put("orders", orders);

        return ResponseEntity.status(statusCode).body(data);
    }

}
