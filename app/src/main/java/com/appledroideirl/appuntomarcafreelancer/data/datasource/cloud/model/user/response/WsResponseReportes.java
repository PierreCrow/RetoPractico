package com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WsResponseReportes {

    @SerializedName("entity")
    @Expose
    private List<WsDataReporte> wsDataReportes;

    @SerializedName("message")
    @Expose
    private String message = null;

    @SerializedName("status")
    @Expose
    private int status;

    public List<WsDataReporte> getWsDataReportes() {
        return wsDataReportes;
    }

    public void setWsDataReportes(List<WsDataReporte> wsDataReportes) {
        this.wsDataReportes = wsDataReportes;
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
