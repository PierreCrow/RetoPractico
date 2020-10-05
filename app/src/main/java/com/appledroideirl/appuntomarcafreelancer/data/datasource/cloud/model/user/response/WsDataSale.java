package com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WsDataSale {


    @SerializedName("amount")
    @Expose
    private Double amount = null;

    @SerializedName("full_name")
    @Expose
    private String full_name = null;

    @SerializedName("full_name_service")
    @Expose
    private String full_name_service = null;

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("id_sale")
    @Expose
    private int id_sale;

    @SerializedName("id_sub_service")
    @Expose
    private int id_sub_service;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
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

    public int getId_sale() {
        return id_sale;
    }

    public void setId_sale(int id_sale) {
        this.id_sale = id_sale;
    }

    public int getId_sub_service() {
        return id_sub_service;
    }

    public void setId_sub_service(int id_sub_service) {
        this.id_sub_service = id_sub_service;
    }

    public String getFull_name_service() {
        return full_name_service;
    }

    public void setFull_name_service(String full_name_service) {
        this.full_name_service = full_name_service;
    }
}
