package com.spring.circuitBreakerProduct.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.spring.circuitBreakerProduct.client.evaluation.EvaluationModel;
import com.spring.circuitBreakerProduct.domain.Product;

import java.util.List;

public class ProductModel {

    public Long id;
    public String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<EvaluationModel> evaluation;

    public ProductModel() {

    }

    public ProductModel(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ProductModel(Long id, String name, List<EvaluationModel> evaluation) {
        this.id = id;
        this.name = name;
        this.evaluation = evaluation;
    }

    public Long getId() {
        return (id);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return (name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EvaluationModel> getEvaluation() {
        return (evaluation);
    }

    public void setEvaluation(List<EvaluationModel> evaluation) {
        this.evaluation = evaluation;
    }

    public static ProductModel of(Product product) {
        return new ProductModel(
                product.getId(),
                product.getName()
        );
    }

    public static ProductModel of(Product product, List<EvaluationModel> evaluation) {
        return new ProductModel(
                product.getId(),
                product.getName(),
                evaluation
        );
    }
}
