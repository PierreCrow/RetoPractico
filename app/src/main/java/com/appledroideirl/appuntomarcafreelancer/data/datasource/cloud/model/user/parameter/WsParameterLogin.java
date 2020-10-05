package com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WsParameterLogin {

    @SerializedName("mail")
    @Expose
    private String mail = null;

    @SerializedName("password")
    @Expose
    private String password = null;

    @SerializedName("id_fire_base_token")
    @Expose
    private String id_fire_base_token = null;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId_fire_base_token() {
        return id_fire_base_token;
    }

    public void setId_fire_base_token(String id_fire_base_token) {
        this.id_fire_base_token = id_fire_base_token;
    }
}
