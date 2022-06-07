package com.example.se2project.service.impl;

import com.example.se2project.entity.Category;
import com.example.se2project.repository.CategoryRepository;
import com.example.se2project.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl extends BaseServiceImpl<Category, Long, CategoryRepository> implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public Category add(Category category) {
        Category c = categoryRepository.save(category);
        return c;
    }


}
