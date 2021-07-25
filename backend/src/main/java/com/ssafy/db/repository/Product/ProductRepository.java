package com.ssafy.db.repository.Product;

import com.ssafy.db.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductIdAndUserId(Long productId, String userId);
//    List<Product> findAllById(String productId);
}
