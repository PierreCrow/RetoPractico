package com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WsResponseAvailableDateHour {

    @SerializedName("entity")
    @Expose
    private List<WsDataAvailableDateHour> wsDataAvailableDateHours;

    @SerializedName("message")
    @Expose
    private String message = null;

    @SerializedName("status")
    @Expose
    private int status;

    public List<WsDataAvailableDateHour> getWsDataAvailableDateHours() {
        return wsDataAvailableDateHours;
    }

    public void setWsDataAvailableDateHours(List<WsDataAvailableDateHour> wsDataAvailableDateHours) {
        this.wsDataAvailableDateHours = wsDataAvailableDateHours;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
