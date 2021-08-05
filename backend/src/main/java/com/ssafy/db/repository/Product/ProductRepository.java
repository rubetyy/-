package com.ssafy.db.repository.Product;

import com.ssafy.db.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByIdAndUserId(Long productId, String userId);
    List<Product> findAllByCategoryId(Long categoryId);
    List<Product> findTop12ByOrderByViewCountDesc();
    Product findTop1ByOrderByIdDesc();
}
