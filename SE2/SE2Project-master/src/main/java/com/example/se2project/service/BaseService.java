package com.example.se2project.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseService<E, ID extends Serializable> {
    void insert(E e);

    void update(E e);

    void delete(E e);

    void deleteById(ID id);

    List<E> findAll();

    Page<E> findAll(Pageable pageable);

    Page<E> findAll(Specification<E> spec, Pageable pageable);

    Optional<E> findById(ID id);

}
