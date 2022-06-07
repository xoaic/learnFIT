package com.example.se2project.service;

import com.example.se2project.entity.Category;

public interface CategoryService extends BaseService<Category, Long> {
    Category add(Category category);

}
