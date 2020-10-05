package com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response;

import com.appledroideirl.appuntomarcafreelancer.interactor.usuario.ListBankAccountCallback;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WsDataService {

    @SerializedName("color")
    @Expose
    private String color = null;

    @SerializedName("enable")
    @Expose
    private int enable;

    @SerializedName("file_image")
    @Expose
    private String file_image = null;

    @SerializedName("full_name")
    @Expose
    private String full_name = null;

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("sub_services")
    @Expose
    private List<WsDataSubService> wsDataSubServices;





    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public String getFile_image() {
        return file_image;
    }

    public void setFile_image(String file_image) {
        this.file_image = file_image;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<WsDataSubService> getWsDataSubServices() {
        return wsDataSubServices;
    }

    public void setWsDataSubServices(List<WsDataSubService> wsDataSubServices) {
        this.wsDataSubServices = wsDataSubServices;
    }
}
