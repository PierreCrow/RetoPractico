package com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WsDataLocales {

    @SerializedName("id_user")
    @Expose
    private int id_user;

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("full_name")
    @Expose
    private String full_name = null;

    @SerializedName("address")
    @Expose
    private String address = null;

    @SerializedName("longitude")
    @Expose
    private Double longitude = null;

    @SerializedName("latitude")
    @Expose
    private Double latitude = null;

    @SerializedName("main")
    @Expose
    private int main;

    @SerializedName("status")
    @Expose
    private int status;

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public int getMain() {
        return main;
    }

    public void setMain(int main) {
        this.main = main;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
