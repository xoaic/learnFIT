package com.example.se2project.service.impl;

import com.example.se2project.entity.CartProduct;
import com.example.se2project.entity.Product;
import com.example.se2project.entity.User;
import com.example.se2project.repository.CartProductRepository;
import com.example.se2project.repository.ProductRepository;
import com.example.se2project.repository.UserRepository;
import com.example.se2project.service.CartProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartProductServiceImpl extends BaseServiceImpl<CartProduct, Long, CartProductRepository> implements CartProductService {
    @Autowired
    CartProductRepository cartProductRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;
//    @Override
//    public void insert(CartProduct cartProduct) {
//        CartProduct cartProductExisted = cartProductRepository.getById(cartProduct.getProductID());
//
//        cartProductExisted.setQuantity(cartProduct.getQuantity() + cartProductExisted.getQuantity()) ;
//
//    }
//    @Override
//    public void update(Long productId, int quantity) {
//        CartProduct cartProduct = cartProductRepository.getById(productId);
//        cartProduct.setQuantity(quantity + cartProduct.getQuantity());
//        if(cartProduct.getQuantity() <= 0) {
//            cartProductRepository.deleteById(productId);
//        }
//    }

    @Override
    public List<CartProduct> getCartProduct(Long userId) {
        return cartProductRepository.findByUser_UserId(userId);
    }

    @Override
    public Integer addProduct(Long productId, Integer quantity, User user) {
        Integer addedQuant = quantity;
        Product product = productRepository.findById(productId).get();
        CartProduct cartProduct = cartProductRepository.findByUserAndProduct(user, product);
        if(cartProduct != null) {
            addedQuant = cartProduct.getQuantity() + quantity;
            cartProduct.setQuantity(addedQuant);
        }else {
            cartProduct = new CartProduct();
            cartProduct.setQuantity(quantity);
            cartProduct.setUser(user);
            cartProduct.setProduct(product);
        }
        cartProductRepository.save(cartProduct);
        return addedQuant;
    }

//    @Override
//    public double getTotalPrice() {
//        return cartProductRepository.findAll().stream().mapToDouble(product -> product.getQuantity() * product.getPrice()).sum();
//    }
//
//    @Override
//    public int getCartProductCount() {
//        if(cartProductRepository.findAll().isEmpty())
//            return 0;
//        return cartProductRepository.findAll().size();
//    }

}
