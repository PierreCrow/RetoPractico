package com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WsResponseAgregarServicio {

    @SerializedName("entity")
    @Expose
    private List<WsDataAgregarServicio> wsDataAgregarServicios;

    @SerializedName("message")
    @Expose
    private String message = null;

    @SerializedName("status")
    @Expose
    private int status;

    public List<WsDataAgregarServicio> getWsDataAgregarServicios() {
        return wsDataAgregarServicios;
    }

    public void setWsDataAgregarServicios(List<WsDataAgregarServicio> wsDataAgregarServicios) {
        this.wsDataAgregarServicios = wsDataAgregarServicios;
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
