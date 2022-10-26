package com.spring.circuitBreakerEvaluation.api;

import com.spring.circuitBreakerEvaluation.domain.EvaluationRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/avaliacoes")
public class EvaluationController {

    private final EvaluationRepository evaluation;

    public EvaluationController(EvaluationRepository evaluation) {
        this.evaluation = evaluation;
    }

    @GetMapping
    public List<EvaluationModel> searchByProduct(@RequestParam Long productId) {
        return evaluation.getAll()
                .stream()
                .filter(evaluation -> evaluation.getProductId().equals(productId))
                .map(EvaluationModel::of)
                .collect(Collectors.toList());
    }
}
