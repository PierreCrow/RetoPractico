package com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.security.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WsResponseToken {

    @SerializedName("access_token")
    @Expose
    private String access_token = null;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
