package com.appledroideirl.appuntomarcafreelancer.domain.model;


import java.io.Serializable;
import java.util.List;


public class Service implements Serializable {

    private String color;
    private int enable;
    private String file_image;
    private String full_name;
    private int id;
    private List<SubService> subServices;


    public Service(int id, String color, String file_image, String full_name, int enable,List<SubService> subServices) {
        this.id = id;
        this.color = color;
        this.file_image = file_image;
        this.full_name = full_name;
        this.enable = enable;
        this.subServices=subServices;
    }

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

    public List<SubService> getSubServices() {
        return subServices;
    }

    public void setSubServices(List<SubService> subServices) {
        this.subServices = subServices;
    }


}
