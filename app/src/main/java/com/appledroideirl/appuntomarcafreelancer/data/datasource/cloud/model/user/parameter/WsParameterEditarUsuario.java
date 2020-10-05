package com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WsParameterEditarUsuario {

    @SerializedName("id")
    @Expose
    private int id ;

    @SerializedName("social_name")
    @Expose
    private String social_name = null;

    @SerializedName("full_name")
    @Expose
    private String full_name = null;

    @SerializedName("id_type_document")
    @Expose
    private int id_type_document;

    @SerializedName("document_number")
    @Expose
    private String document_number = null;


    @SerializedName("photo")
    @Expose
    private String photo = null;

    @SerializedName("cellphone")
    @Expose
    private String cellphone = null;

    @SerializedName("about")
    @Expose
    private String about = null;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSocial_name() {
        return social_name;
    }

    public void setSocial_name(String social_name) {
        this.social_name = social_name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public int getId_type_document() {
        return id_type_document;
    }

    public void setId_type_document(int id_type_document) {
        this.id_type_document = id_type_document;
    }

    public String getDocument_number() {
        return document_number;
    }

    public void setDocument_number(String document_number) {
        this.document_number = document_number;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
