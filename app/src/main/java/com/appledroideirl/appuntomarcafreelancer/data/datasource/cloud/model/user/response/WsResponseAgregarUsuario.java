package com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WsResponseAgregarUsuario {

    @SerializedName("entity")
    @Expose
    private WsDataAgregarUsuario wsDataAgregarUsuario;

    @SerializedName("message")
    @Expose
    private String message = null;

    @SerializedName("status")
    @Expose
    private int status;


    public WsDataAgregarUsuario getWsDataAgregarUsuario() {
        return wsDataAgregarUsuario;
    }

    public void setWsDataAgregarUsuario(WsDataAgregarUsuario wsDataAgregarUsuario) {
        this.wsDataAgregarUsuario = wsDataAgregarUsuario;
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
