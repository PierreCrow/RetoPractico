package com.appledroideirl.appuntomarcafreelancer.domain.model;


import java.io.Serializable;
import java.util.List;


public class SubService implements Serializable {

    private String charge;
    private int enable;
    private int in_filter;
    private String full_name;
    private int id;
    private int idService;


    public SubService(int id,int idService, String charge, int in_filter, String full_name, int enable) {
        this.id = id;
        this.idService=idService;
        this.charge = charge;
        this.in_filter = in_filter;
        this.full_name = full_name;
        this.enable = enable;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public int getIn_filter() {
        return in_filter;
    }

    public void setIn_filter(int in_filter) {
        this.in_filter = in_filter;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }
}
