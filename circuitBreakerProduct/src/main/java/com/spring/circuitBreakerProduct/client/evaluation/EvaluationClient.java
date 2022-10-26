package com.spring.circuitBreakerProduct.client.evaluation;

import java.util.List;

public interface EvaluationClient {

    List<EvaluationModel> searchAllProducts(Long productId);
}
