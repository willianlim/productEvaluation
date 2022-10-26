package com.spring.circuitBreakerEvaluation.domain;

import java.util.List;
import java.util.Optional;

public interface EvaluationRepository {

    void save(Evaluation evaluation);
    Optional<Evaluation> getOne(Long id);
    List<Evaluation> getAll();
}
