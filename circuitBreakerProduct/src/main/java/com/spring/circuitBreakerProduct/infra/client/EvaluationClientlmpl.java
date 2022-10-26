package com.spring.circuitBreakerProduct.infra.client;

import com.spring.circuitBreakerProduct.client.evaluation.EvaluationClient;
import com.spring.circuitBreakerProduct.client.evaluation.EvaluationModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class EvaluationClientlmpl implements EvaluationClient {

    private final Logger logger = LoggerFactory.getLogger(EvaluationClientlmpl.class);
    private final RestTemplate restTemplate;

    private final static String API_URL = UriComponentsBuilder
            .fromHttpUrl("http://localhost:8090/avaliacoes")
            .queryParam("productId", "{productId}")
            .encode()
            .toUriString();

    public EvaluationClientlmpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
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
        return (Arrays.asList(evaluation));
    }
}
