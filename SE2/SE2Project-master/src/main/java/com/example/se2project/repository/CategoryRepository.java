package com.example.se2project.repository;

import com.example.se2project.entity.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends BaseRepository<Category, Long> {
    public Category findByName(String name);
}
