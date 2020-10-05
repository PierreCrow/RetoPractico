package com.appledroideirl.appuntomarcafreelancer.domain.model;


import java.io.Serializable;
import java.util.List;


public class UserStore implements Serializable {


    private String about;
    private Double avg_rate;
    private String cellphone;
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


    public UserStore(int id, int id_type_document, String about, Double avg_rate, String cellphone,
                     String document_number, String full_name, String mail, String password, String photo,
                     String social_name, String type_user, List<UserStore> userStore) {
        this.id = id;
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
    }


   
}
