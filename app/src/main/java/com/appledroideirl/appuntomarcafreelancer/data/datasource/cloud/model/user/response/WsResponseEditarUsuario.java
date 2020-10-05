package com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WsResponseEditarUsuario {

    @SerializedName("entity")
    @Expose
    private int respuesta;

    @SerializedName("message")
    @Expose
    private String message = null;

    @SerializedName("status")
    @Expose
    private int status;

    public int getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(int respuesta) {
        this.respuesta = respuesta;
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
