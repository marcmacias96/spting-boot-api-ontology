package com.espe.topicos.ontology.ontologyapi.models;

public class TestModel {
    private Long id;

    private String name;
    private String email;
    private Integer priority;

    public TestModel(Long id, String name, String email, Integer priority) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.priority = priority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
