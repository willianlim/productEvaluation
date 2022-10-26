package com.spring.circuitBreakerProduct.domain;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository {

    void save(Product product);
    Optional<Product> getOne(Long id);
    List<Product> getAll();
}
