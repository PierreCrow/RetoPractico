package com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WsResponseAddSubService {

    @SerializedName("entity")
    @Expose
    private List<WsDataAddSubService> wsDataAddSubServices;

    @SerializedName("message")
    @Expose
    private String message = null;

    @SerializedName("status")
    @Expose
    private int status;

    public List<WsDataAddSubService> getWsDataAddSubServices() {
        return wsDataAddSubServices;
    }

    public void setWsDataAddSubServices(List<WsDataAddSubService> wsDataAddSubServices) {
        this.wsDataAddSubServices = wsDataAddSubServices;
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
