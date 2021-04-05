package com.espe.topicos.ontology.ontologyapi.models;

public class UniOffer {

    private String university;
    private String career;

    public UniOffer(String university, String career) {
        this.university = university;
        this.career = career;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }
}
