package com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.security.parameter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WsParameterToken {

    @SerializedName("user")
    @Expose
    private String user = null;

    @SerializedName("password")
    @Expose
    private String password = null;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
