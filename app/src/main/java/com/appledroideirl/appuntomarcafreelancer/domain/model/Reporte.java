package com.appledroideirl.appuntomarcafreelancer.domain.model;


import java.io.Serializable;


public class Reporte implements Serializable {

    private int id;
    private String tittle;
    private Double value;


    public Reporte(int id, String tittle,Double value) {
        this.id = id;
        this.tittle = tittle;
        this.value=value;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
