package com.spring.circuitBreakerProduct.infra.database;

import com.spring.circuitBreakerProduct.domain.Product;
import com.spring.circuitBreakerProduct.domain.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProductRepositorylmpl implements ProductRepository {

    private static final List<Product> PRODUCTS = new ArrayList<>();
    private static long id = 1;

    static {
        PRODUCTS.add(new Product(nextId(), "Desktop 4GB"));
        PRODUCTS.add(new Product(nextId(), "Notebook 4GB"));
        PRODUCTS.add(new Product(nextId(), "Notebook 8GB"));
    }

    @Override
    public void save(Product product) {
        product.setId(nextId());
        PRODUCTS.add(product);
    }

    @Override
    public Optional<Product> getOne(Long id) {
        return (PRODUCTS.stream().filter(e -> e.getId().equals(id)).findFirst());
    }

    @Override
    public List<Product> getAll() {
        return new ArrayList<>(PRODUCTS);
    }

    private static long nextId() {
        return (id++);
    }
}
