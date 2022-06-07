package com.example.se2project.service.impl;


import com.example.se2project.repository.BaseRepository;
import com.example.se2project.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class BaseServiceImpl<E, ID extends Serializable, R extends BaseRepository<E, ID>> implements BaseService<E, ID> {

    @Autowired
    private R repository;

    @Override
    public void insert(E e) {
        repository.save(e);
    }

    @Override
    public void update(E e) {
        repository.save(e);
    }

    @Override
    public void delete(E e) {
        repository.delete(e);
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    @Override
    public List<E> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<E> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<E> findAll(Specification<E> spec, Pageable pageable) {
        return findAll(spec, pageable);
    }

    @Override
    public Optional<E> findById(ID id) {
        return repository.findById(id);
    }
}
