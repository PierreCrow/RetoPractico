package com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WsParameterAddSubService {

    @SerializedName("id_user")
    @Expose
    private int id_user;

    @SerializedName("id_service")
    @Expose
    private int id_service;

    @SerializedName("id_sub_service")
    @Expose
    private int id_sub_service;

    @SerializedName("enable")
    @Expose
    private int enable;

    @SerializedName("charge")
    @Expose
    private Double charge = null;

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_service() {
        return id_service;
    }

    public void setId_service(int id_service) {
        this.id_service = id_service;
    }

    public int getId_sub_service() {
        return id_sub_service;
    }

    public void setId_sub_service(int id_sub_service) {
        this.id_sub_service = id_sub_service;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public Double getCharge() {
        return charge;
    }

    public void setCharge(Double charge) {
        this.charge = charge;
    }
}
