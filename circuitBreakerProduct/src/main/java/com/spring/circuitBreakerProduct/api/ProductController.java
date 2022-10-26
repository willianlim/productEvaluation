package com.spring.circuitBreakerProduct.api;

import com.spring.circuitBreakerProduct.client.evaluation.EvaluationClient;
import com.spring.circuitBreakerProduct.client.evaluation.EvaluationModel;
import com.spring.circuitBreakerProduct.domain.Product;
import com.spring.circuitBreakerProduct.domain.ProductRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository product;
    private final EvaluationClient evaluation;

    public ProductController(ProductRepository product, EvaluationClient evaluation) {
        this.product = product;
        this.evaluation = evaluation;
    }

    @GetMapping
    public List<ProductModel> searchAll() {
        return product.getAll()
                .stream()
                .map(this::convertProductToModel)
                .collect(Collectors.toList());
    }

    @GetMapping("/{productId}")
    public ProductModel searchById(@PathVariable Long productId) {
        return product.getOne(productId)
                .map(this::convertProductToTemplateWithReview)
                .orElseThrow(ResourceNotFoundException::new);
    }

    private ProductModel convertProductToModel(Product product) {
        return (ProductModel.of(product));
    }

    private ProductModel convertProductToTemplateWithReview(Product product) {
        return (ProductModel.of(product, lookingForProductReview(product.getId())));
    }

    private List<EvaluationModel> lookingForProductReview(Long productId) {
        return (evaluation.searchAllProducts(productId));
    }
}
