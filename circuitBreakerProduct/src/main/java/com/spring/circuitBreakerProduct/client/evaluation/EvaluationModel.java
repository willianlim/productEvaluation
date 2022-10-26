package com.spring.circuitBreakerProduct.client.evaluation;

public class EvaluationModel {

    private Long id;
    private Integer grade;
    private String description;
    private String evaluatorName;

    public EvaluationModel() {

    }

    public EvaluationModel(Long id, Integer grade, String description, String evaluatorName) {
        this.id = id;
        this.grade = grade;
        this.description = description;
        this.evaluatorName = evaluatorName;
    }

    public Integer getGrade() {
        return (grade);
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Long getId() {
        return (id);
    }

    public void setId(Long id) {
        this.id = id;
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
}
