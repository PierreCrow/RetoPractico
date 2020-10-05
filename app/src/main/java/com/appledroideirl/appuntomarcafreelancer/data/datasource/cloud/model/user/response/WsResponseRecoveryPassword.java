package com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WsResponseRecoveryPassword {

    @SerializedName("entity")
    @Expose
    private WsDataUser wsDataUser;

    @SerializedName("message")
    @Expose
    private String message = null;

    @SerializedName("status")
    @Expose
    private int status;

    public WsDataUser getWsDataUser() {
        return wsDataUser;
    }

    public void setWsDataUser(WsDataUser wsDataUser) {
        this.wsDataUser = wsDataUser;
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
