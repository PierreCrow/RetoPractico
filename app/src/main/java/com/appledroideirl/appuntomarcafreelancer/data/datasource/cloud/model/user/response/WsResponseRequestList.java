package com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WsResponseRequestList {

    @SerializedName("entity")
    @Expose
    private List<WsDataRequest> wsDataRequests;

    @SerializedName("message")
    @Expose
    private String message = null;

    @SerializedName("status")
    @Expose
    private int status;

    public List<WsDataRequest> getWsDataRequests() {
        return wsDataRequests;
    }

    public void setWsDataRequests(List<WsDataRequest> wsDataRequests) {
        this.wsDataRequests = wsDataRequests;
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
