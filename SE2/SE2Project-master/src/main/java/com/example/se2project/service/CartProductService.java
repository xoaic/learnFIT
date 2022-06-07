package com.example.se2project.service;

import com.example.se2project.entity.CartProduct;
import com.example.se2project.entity.User;

import java.util.List;

public interface CartProductService extends BaseService<CartProduct, Long>{
//    void insert(CartProduct cartProduct);
//
//    void update(Long productId, int quantity);
//
    List<CartProduct> getCartProduct(Long userId);
    Integer addProduct(Long productId, Integer quantity, User user);
//    double getTotalPrice();

//    int getCartProductCount();
}
