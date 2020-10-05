package com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WsResponseServices {

    @SerializedName("entity")
    @Expose
    private List<WsDataService> wsDataServices;

    @SerializedName("message")
    @Expose
    private String message = null;

    @SerializedName("status")
    @Expose
    private int status;

    public List<WsDataService> getWsDataServices() {
        return wsDataServices;
    }

    public void setWsDataServices(List<WsDataService> wsDataServices) {
        this.wsDataServices = wsDataServices;
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
