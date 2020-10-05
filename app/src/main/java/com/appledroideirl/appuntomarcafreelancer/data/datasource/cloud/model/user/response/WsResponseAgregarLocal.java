package com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WsResponseAgregarLocal {

    @SerializedName("entity")
    @Expose
    private WsDataLocal wsDataLocal;

    @SerializedName("message")
    @Expose
    private String message = null;

    @SerializedName("status")
    @Expose
    private int status;

    public WsDataLocal getWsDataLocal() {
        return wsDataLocal;
    }

    public void setWsDataLocal(WsDataLocal wsDataLocal) {
        this.wsDataLocal = wsDataLocal;
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
