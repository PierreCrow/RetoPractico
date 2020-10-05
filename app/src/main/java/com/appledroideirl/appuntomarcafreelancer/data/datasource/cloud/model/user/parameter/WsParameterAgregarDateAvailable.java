package com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WsParameterAgregarDateAvailable {

    @SerializedName("id_user")
    @Expose
    private int id_user;

    @SerializedName("id_type_availability")
    @Expose
    private int id_type_availability;

    @SerializedName("date_availability")
    @Expose
    private String date_availability=null;

    @SerializedName("full_name")
    @Expose
    private String full_name=null;

    @SerializedName("hours_availability")
    @Expose
    private List<Integer> hours_availability=null;

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_type_availability() {
        return id_type_availability;
    }

    public void setId_type_availability(int id_type_availability) {
        this.id_type_availability = id_type_availability;
    }

    public String getDate_availability() {
        return date_availability;
    }

    public void setDate_availability(String date_availability) {
        this.date_availability = date_availability;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public List<Integer> getHours_availability() {
        return hours_availability;
    }

    public void setHours_availability(List<Integer> hours_availability) {
        this.hours_availability = hours_availability;
    }
}
