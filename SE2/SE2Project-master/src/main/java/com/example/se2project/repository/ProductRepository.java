package com.example.se2project.repository;

import com.example.se2project.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends BaseRepository<Product, Long> {
    List<Product>findAllByCategoryCategoriesId(Long id);

}
