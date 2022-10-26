package com.spring.circuitBreakerEvaluation.infra.database;

import com.spring.circuitBreakerEvaluation.domain.Evaluation;

import com.spring.circuitBreakerEvaluation.domain.EvaluationRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class EvaluationRepositorylmpl implements EvaluationRepository {

    private static final List<Evaluation> EVALUATION = new ArrayList<>();

    private static long id = 1;

    static {
        EVALUATION.add(new Evaluation(nextId(), 10, "Thiago",
                "Produto superou minhas expectativas.", 1L));
        EVALUATION.add(new Evaluation(nextId(), 1, "Alexandre",
                "Veio com defeito.", 1L));
        EVALUATION.add(new Evaluation(nextId(), 4, "Maria",
                "Computador trava muito.", 1L));
        EVALUATION.add(new Evaluation(nextId(), 8, "Daniel",
                "Quase perfeito.", 2L));
        EVALUATION.add(new Evaluation(nextId(), 5, "Alex",
                "Não vem com sistema operacional.", 3L));
    }

    @Override
    public void save(Evaluation evaluation) {
        evaluation.setId(nextId());
        EVALUATION.add(evaluation);
    }

    @Override
    public Optional<Evaluation> getOne(Long id) {
        return EVALUATION.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    @Override
    public List<Evaluation> getAll() {
        return new ArrayList<>(EVALUATION);
    }

    private static long nextId() {
        return (id++);
    }
}
