package com.example.se2project.service;

import com.example.se2project.entity.Product;

import java.util.List;

public interface ProductService extends BaseService<Product, Long> {
    Product add(Product product);
    List<Product> getProductByCategoryId(Long id);

}
