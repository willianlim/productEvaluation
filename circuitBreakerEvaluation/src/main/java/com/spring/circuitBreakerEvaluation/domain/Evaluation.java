package com.spring.circuitBreakerEvaluation.domain;

public class Evaluation {

    private Long id;
    private Integer grade;
    private String description;
    private String evaluatorName;
    private Long productId;

    public Evaluation() {

    }

    public Evaluation(Long id, Integer grade, String description, String evaluatorName, Long productId) {
        this.id = id;
        this.grade = grade;
        this.description = description;
        this.evaluatorName = evaluatorName;
        this.productId = productId;
    }

    public Long getId() {
        return (id);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGrade() {
        return (grade);
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getDescription() {
        return (description);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEvaluatorName() {
        return (evaluatorName);
    }

    public void setEvaluatorName(String evaluatorName) {
        this.evaluatorName = evaluatorName;
    }

    public Long getProductId() {
        return (productId);
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
