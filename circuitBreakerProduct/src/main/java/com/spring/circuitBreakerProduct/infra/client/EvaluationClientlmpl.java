package com.spring.circuitBreakerProduct.infra.client;

import com.spring.circuitBreakerProduct.client.evaluation.EvaluationClient;
import com.spring.circuitBreakerProduct.client.evaluation.EvaluationModel;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@Component
public class EvaluationClientlmpl implements EvaluationClient {

    private final Logger logger = LoggerFactory.getLogger(EvaluationClientlmpl.class);
    private final RestTemplate restTemplate;

    private final static String API_URL = UriComponentsBuilder
            .fromHttpUrl("http://localhost:8090/avaliacoes")
            .queryParam("productId", "{productId}")
            .encode()
            .toUriString();

    private final Map<Long, List<EvaluationModel>> CACHE = new HashMap<>();

    public EvaluationClientlmpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    @CircuitBreaker(name = "avaliacaoCB", fallbackMethod = "searchAllProductsInCache")
    public List<EvaluationModel> searchAllProducts(Long productId) {
        final List<EvaluationModel> evaluation = executeRequest(productId);
        return (evaluation);
    }

    private List<EvaluationModel> executeRequest(Long productId) {
        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("productId", productId);

        logger.info("searching evaluations");
        final EvaluationModel[] evaluation;

        try {
            evaluation = restTemplate.getForObject(API_URL, EvaluationModel[].class, parameters);
        } catch (Exception e) {
            logger.error("Error when fetching reviews.");
            throw e;
        }

        logger.info("Alimentando cache");
        CACHE.put(productId, Arrays.asList(evaluation));

        return (Arrays.asList(evaluation));
    }

    private List<EvaluationModel> searchAllProductsInCache(Long productId, Throwable e) {
        logger.info("Buscando no cache");
        return CACHE.getOrDefault(productId, new ArrayList<>());
    }
}
