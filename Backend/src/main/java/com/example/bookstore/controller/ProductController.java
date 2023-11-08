package com.example.bookstore.controller;

import com.example.bookstore.dto.ProductDTO;
import com.example.bookstore.iservice.*;
import com.example.bookstore.model.Author;
import com.example.bookstore.model.Category;
import com.example.bookstore.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/products")
public class ProductController {

    private final IProductService productService;

    private final ICategoryService categoryService;

    private final IAuthorService authorService;

    @Value("${uploadDir}")
    private String uploadDir;

//    Trả về tất cả products đang bán (success)
    @GetMapping
    public ResponseEntity<Map<String, Object>> getProducts(@RequestParam(defaultValue = "0") int page,
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

        Page<Product> productList = productService.selectAll(pageable);

        List<Product> products = new ArrayList<>();
        if (productList != null && !productList.isEmpty()) {
            products = productService.getProductsOnSale(productList.getContent());
        }

        if (!products.isEmpty())
            statusCode = HttpStatus.OK;
        else
            statusCode = HttpStatus.NO_CONTENT; // 204

        data.put("products", products);
        return ResponseEntity.status(statusCode).body(data);
    }

//    add product (success)
    @PostMapping
    public ResponseEntity<Map<String, Object>> handleInsertProduct(@RequestBody ProductDTO productDTO) throws IOException {
        Map<String, Object> data = new HashMap<>();
        HttpStatus statusCode;

        Product product = handleProduct(productDTO, new Product());

        if (product.getImage() == null || product.getImage().equals(""))
            product.setImage("image.jpg");

        product = productService.insert(product);

        if (product != null) {

            Author author = authorService.selectById(productDTO.getAuthorId());
            author.getProducts().add(product);
            author = authorService.update(author);

            Category category = categoryService.selectById(productDTO.getCategoryId());
            category.getProducts().add(product);
            category = categoryService.update(category);

            if (author != null && category != null)
                statusCode = HttpStatus.OK;
            else
                statusCode = HttpStatus.SERVICE_UNAVAILABLE;
        }else
            statusCode = HttpStatus.SERVICE_UNAVAILABLE; // 503

        data.put("product", product);

        return ResponseEntity.status(statusCode).body(data);
    }

//    update product (success)
    @PutMapping(value = "/{id}")
    public ResponseEntity<Map<String, Object>> handleEditProduct(@PathVariable("id") Integer id,
                                                                 @RequestBody ProductDTO productDTO) throws IOException {
        Map<String, Object> data = new HashMap<>();
        HttpStatus statusCode;

        Product productUpdate = productService.selectById(id);
        Product product;

        if (productUpdate != null) {
            handleProduct(productDTO, productUpdate);
            product = productService.update(productUpdate);

            if (product != null) {
                Author author = authorService.selectById(productDTO.getAuthorId());
                Product product1 = author.getProducts().stream()
                                                    .filter(p -> Objects.equals(p.getId(), product.getId()))
                                                    .findFirst().orElse(null);
                if (product1 != null)
                    author.getProducts().remove(product1);
                author.getProducts().add(product);
                author = authorService.update(author);

                Category category = categoryService.selectById(productDTO.getCategoryId());
                product1 = category.getProducts().stream()
                                                .filter(p -> Objects.equals(p.getId(), product.getId()))
                                                .findFirst().orElse(null);
                if (product1 != null)
                    category.getProducts().remove(product1);
                category.getProducts().add(product);
                category = categoryService.update(category);

                if (author != null && category != null)
                    statusCode = HttpStatus.OK;
                else
                    statusCode = HttpStatus.SERVICE_UNAVAILABLE;
            } else
                statusCode = HttpStatus.SERVICE_UNAVAILABLE; // 503
        } else {
            product = null;
            statusCode = HttpStatus.NO_CONTENT;
        }

        data.put("product", product);

        return ResponseEntity.status(statusCode).body(data);
    }

//    Delete product hay ngừng bán một sản phẩm (Xoá mềm) (success)
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Map<String, Object>> stopSaleProduct(@PathVariable("id") Integer id) {
        Map<String, Object> data = new HashMap<>();
        HttpStatus statusCode;

        Product productUpdate = productService.selectById(id);

        Product product = null;
        if (productUpdate != null) {
            productUpdate.setSale(false);
            product = productService.update(productUpdate);

            if (product != null)
                statusCode = HttpStatus.OK;
            else
                statusCode = HttpStatus.SERVICE_UNAVAILABLE; // 503
        } else
            statusCode = HttpStatus.NO_CONTENT; // 204

        data.put("product", product);
        return ResponseEntity.status(statusCode).body(data);
    }

//    Trả về 1 product (success)
    @GetMapping(value = "/{id}")
    public ResponseEntity<Map<String, Object>> getProductById(@PathVariable("id") Integer id) {
        Map<String, Object> data = new HashMap<>();
        HttpStatus statusCode;

        Product product = productService.selectById(id);

        if (product != null && product.getSale())
            statusCode = HttpStatus.OK;
        else
            statusCode = HttpStatus.NO_CONTENT; // 204

        data.put("product", product);
        return ResponseEntity.status(statusCode).body(data);
    }

    //tìm sản phẩm theo tác giả
    @GetMapping(value = "/author")
    public ResponseEntity<Map<String, Object>> getProductByAuthor(@RequestParam("id") String authorId) {
        Map<String, Object> data = new HashMap<>();
        HttpStatus statusCode;

        UUID id = UUID.fromString(authorId);
        List<Product> products = productService.searchProductByAuthor(id);

        if (!products.isEmpty())
            statusCode = HttpStatus.OK;
        else
            statusCode = HttpStatus.NO_CONTENT;
        data.put("products", products);
        return ResponseEntity.status(statusCode).body(data);
    }
//    Tìm kiếm các sản phẩm khi nhập keyword vào ô tìm kiếm (success)
    @GetMapping(value = "/search")
    public ResponseEntity<Map<String, Object>> searchProducts(@RequestParam("keyword") String keyword,
                                                              @RequestParam(defaultValue = "1") Integer page,
                                                              @RequestParam(defaultValue = "5") Integer size,
                                                              @RequestParam(defaultValue = "id, desc") String[] sort,
                                                              @RequestParam(defaultValue = "-1") Integer index,
                                                              @RequestParam(defaultValue = "-1") Integer categoryId) {
        Map<String, Object> data = new HashMap<>();
        HttpStatus statusCode;

        List<Product> products = productService.searchProductByKeyword(keyword);

        List<Product> products2;
        if (index != -1) {
            products2 = productService.findByPriceBetween(index);
            products.retainAll(products2);
        }

        List<Product> products3;
        if (categoryId != -1) {
            Category category = categoryService.selectById(categoryId);
            Set<Product> productByCategory = category.getProducts();
            products3 = new ArrayList<>(productByCategory);
            products.retainAll(products3);
        }

        if (!products.isEmpty()) {

            Comparator<Product> comparator;
            if (sort[0].equals("price")) {
                if (sort.length > 1) {
                    if (sort[1].equals("asc")) {
                        comparator = Comparator.comparingDouble(Product::getPrice);
                    } else
                        comparator = Comparator.comparingDouble(Product::getPrice).reversed();
                } else
                    comparator = Comparator.comparingDouble(Product::getPrice).reversed();
            } else {
                if (sort.length > 1) {
                    if (sort[1].equals("asc")) {
                        comparator = Comparator.comparingDouble(Product::getId);
                    } else
                        comparator = Comparator.comparingDouble(Product::getId).reversed();
                } else
                    comparator = Comparator.comparingDouble(Product::getId).reversed();
            }

            products.sort(comparator); // sort

            int totalProducts = products.size();
            int startIndex = (page - 1) * size;
            int endIndex = Math.min(startIndex + size, totalProducts);

            products = products.subList(startIndex, endIndex); // pageable
            statusCode = HttpStatus.OK;
        } else
            statusCode = HttpStatus.NO_CONTENT;

        data.put("products", products);
        return ResponseEntity.status(statusCode).body(data);
    }

//    handle edit or insert product
    private Product handleProduct(ProductDTO productDTO, Product product) throws IOException {
//        Xử lý ảnh
        MultipartFile file = productDTO.getFile();
        if (file != null) {
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            String fileExtension = fileName.substring(fileName.lastIndexOf("."));
            Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
            String newFileName = getMD5(fileName + System.currentTimeMillis()) + fileExtension; // tên lưu vào folder
            Path filePath = uploadPath.resolve(newFileName);

            if (!Files.exists(uploadPath))
                Files.createDirectories(uploadPath);

            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            product.setImage(newFileName);
        }

        product.setName(productDTO.getName());
        product.setTitle(productDTO.getTitle());
        product.setPrice(productDTO.getPrice());
        product.setPublicationDate(productDTO.getPublicationDate());
        product.setPublisher(productDTO.getPublisher());
        product.setSale(productDTO.getSale());

        Author author = authorService.selectById(productDTO.getAuthorId());
        product.setAuthor(author);

        Category category = categoryService.selectById(productDTO.getCategoryId());
        product.setCategory(category);

        return product;
    }

//    Mã hoá chuoi
    public String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            StringBuilder hashText = new StringBuilder(no.toString(16));
            while (hashText.length() < 32) {
                hashText.insert(0, "0");
            }
            return hashText.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
