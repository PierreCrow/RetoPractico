package com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WsResponseListLocales {

    @SerializedName("entity")
    @Expose
    private List<WsDataLocales> wsDataLocales;

    @SerializedName("message")
    @Expose
    private String message = null;

    @SerializedName("status")
    @Expose
    private int status;

    public List<WsDataLocales> getWsDataLocales() {
        return wsDataLocales;
    }

    public void setWsDataLocales(List<WsDataLocales> wsDataLocales) {
        this.wsDataLocales = wsDataLocales;
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
