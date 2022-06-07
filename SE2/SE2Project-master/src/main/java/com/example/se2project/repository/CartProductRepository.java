package com.example.se2project.repository;

import com.example.se2project.entity.CartProduct;
import com.example.se2project.entity.Product;
import com.example.se2project.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartProductRepository extends BaseRepository<CartProduct, Long> {
        List<CartProduct> findByUser_UserId(Long userId);
        CartProduct findByUserAndProduct(User user, Product product);
}
