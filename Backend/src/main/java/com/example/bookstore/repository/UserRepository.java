package com.example.bookstore.repository;

import com.example.bookstore.model.Product;
import com.example.bookstore.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);
    @Query(value = "SELECT up.product FROM UserProduct up " +
                    "INNER JOIN User u ON u.id = up.user.id " +
                    "WHERE u.id = :id")
    List<Product> getProductsBought(Pageable pageable, @Param("id") UUID id);
}
