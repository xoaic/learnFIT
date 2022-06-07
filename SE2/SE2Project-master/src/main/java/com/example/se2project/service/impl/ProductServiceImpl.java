package com.example.se2project.service.impl;

import com.example.se2project.entity.Product;
import com.example.se2project.repository.ProductRepository;
import com.example.se2project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl extends BaseServiceImpl<Product, Long, ProductRepository> implements ProductService {

    @Autowired
    private ProductRepository productRepository;


    @Override
    public Product add(Product product) {
        Product p = productRepository.save(product);
        return p;
    }

    @Override
    public List<Product> getProductByCategoryId(Long id) {
        List<Product> productList = productRepository.findAllByCategoryCategoriesId(id);
        return productList;
    }

    @Override
    public void update(Product product) {
        for(int i = 0; i < findAll().size();i++) {
            Product p = findAll().get(i);
            if(p.getProductId() == product.getProductId()) {
                findAll().set(i, product);
                break;
            }
        }
    }


}
