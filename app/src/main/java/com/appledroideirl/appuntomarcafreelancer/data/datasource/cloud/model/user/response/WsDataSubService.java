package com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WsDataSubService {

    @SerializedName("charge")
    @Expose
    private Double charge = null;

    @SerializedName("enable")
    @Expose
    private int enable;

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("full_name")
    @Expose
    private String full_name = null;

    @SerializedName("id_service")
    @Expose
    private int id_service;

    @SerializedName("in_filter")
    @Expose
    private int in_filter;


    public Double getCharge() {
        return charge;
    }

    public void setCharge(Double charge) {
        this.charge = charge;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_service() {
        return id_service;
    }

    public void setId_service(int id_service) {
        this.id_service = id_service;
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
}
