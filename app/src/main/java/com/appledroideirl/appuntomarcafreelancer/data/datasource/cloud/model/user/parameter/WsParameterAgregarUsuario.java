package com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WsParameterAgregarUsuario {

    @SerializedName("id")
    @Expose
    private String id = null;

    @SerializedName("mail")
    @Expose
    private String mail = null;

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

    @SerializedName("type_user")
    @Expose
    private String type_user = null;

    @SerializedName("photo")
    @Expose
    private String photo = null;

    @SerializedName("cellphone")
    @Expose
    private String cellphone = null;

    @SerializedName("about")
    @Expose
    private String about = null;

    @SerializedName("password")
    @Expose
    private String password = null;

    @SerializedName("user_store")
    @Expose
    private List<WsParameterDataUserStore> wsParameterDataUserStores = null;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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

    public String getType_user() {
        return type_user;
    }

    public void setType_user(String type_user) {
        this.type_user = type_user;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<WsParameterDataUserStore> getWsParameterDataUserStores() {
        return wsParameterDataUserStores;
    }

    public void setWsParameterDataUserStores(List<WsParameterDataUserStore> wsParameterDataUserStores) {
        this.wsParameterDataUserStores = wsParameterDataUserStores;
    }
}
