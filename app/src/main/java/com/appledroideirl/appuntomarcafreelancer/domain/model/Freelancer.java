package com.appledroideirl.appuntomarcafreelancer.domain.model;


import java.io.Serializable;


public class Freelancer implements Serializable {

    private String id;
    private String tittle;


    public Freelancer(String id, String tittle) {
        this.id = id;
        this.tittle = tittle;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

}
