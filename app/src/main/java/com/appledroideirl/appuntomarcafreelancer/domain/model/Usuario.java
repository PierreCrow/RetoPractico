package com.appledroideirl.appuntomarcafreelancer.domain.model;


import java.io.Serializable;
import java.util.List;


public class Usuario implements Serializable {

    private String about;
    private Double avg_rate;
    private String cellphone;
    private String user;
    private String document_number;
    private String full_name;
    private int id;
    private int id_type_document;
    private String mail;
    private String password;
    private String photo;
    private String social_name;
    private String type_user;
    private List<UserStore> userStore;
    private String token;
    private boolean hasLocation;
    private String lat;
    private String lng;
    private boolean logged;
    private String fcm;


    public Usuario(int id, int id_type_document,String user, String about, Double avg_rate, String cellphone,
                   String document_number, String full_name, String mail, String password, String photo,
                   String social_name, String type_user, List<UserStore> userStore,String token, boolean hasLocation, String lat, String lng,boolean logged,String fcm) {
        this.id = id;
        this.user=user;
        this.id_type_document = id_type_document;
        this.about = about;
        this.avg_rate = avg_rate;
        this.cellphone = cellphone;
        this.document_number = document_number;
        this.full_name = full_name;
        this.mail = mail;
        this.password = password;
        this.photo = photo;
        this.social_name = social_name;
        this.type_user = type_user;
        this.userStore = userStore;
        this.token = token;
        this.hasLocation = hasLocation;
        this.lat = lat;
        this.lng = lng;
        this.logged=logged;
        this.fcm=fcm;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Double getAvg_rate() {
        return avg_rate;
    }

    public void setAvg_rate(Double avg_rate) {
        this.avg_rate = avg_rate;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getDocument_number() {
        return document_number;
    }

    public void setDocument_number(String document_number) {
        this.document_number = document_number;
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

    public int getId_type_document() {
        return id_type_document;
    }

    public void setId_type_document(int id_type_document) {
        this.id_type_document = id_type_document;
    }

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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getSocial_name() {
        return social_name;
    }

    public void setSocial_name(String social_name) {
        this.social_name = social_name;
    }

    public String getType_user() {
        return type_user;
    }

    public void setType_user(String type_user) {
        this.type_user = type_user;
    }

    public List<UserStore> getUserStore() {
        return userStore;
    }

    public void setUserStore(List<UserStore> userStore) {
        this.userStore = userStore;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isHasLocation() {
        return hasLocation;
    }

    public void setHasLocation(boolean hasLocation) {
        this.hasLocation = hasLocation;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public String getFcm() {
        return fcm;
    }

    public void setFcm(String fcm) {
        this.fcm = fcm;
    }
}
