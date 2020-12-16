package com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WsDataRequest {


    @SerializedName("address")
    @Expose
    private String address = null;

    @SerializedName("coupon")
    @Expose
    private String coupon = null;

    @SerializedName("date_availability")
    @Expose
    private String date_availability = null;

    @SerializedName("document_number")
    @Expose
    private String document_number = null;

    @SerializedName("expiration_month")
    @Expose
    private String expiration_month = null;

    @SerializedName("expiration_year")
    @Expose
    private String expiration_year = null;

    @SerializedName("full_name_card")
    @Expose
    private String full_name_card = null;

    @SerializedName("comment")
    @Expose
    private String comment = null;

    @SerializedName("full_name_type_availability")
    @Expose
    private String full_name_type_availability = null;

    @SerializedName("full_name_customer")
    @Expose
    private String full_name_customer = null;

    @SerializedName("url_image_customer")
    @Expose
    private String url_image_customer = null;

    @SerializedName("hour_availability")
    @Expose
    private int hour_availability;

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("id_customer")
    @Expose
    private int id_customer;

    @SerializedName("id_customer_address")
    @Expose
    private int id_customer_address;

    @SerializedName("id_type_availability")
    @Expose
    private int id_type_availability;

    @SerializedName("id_type_card")
    @Expose
    private int id_type_card;

    @SerializedName("id_user")
    @Expose
    private int id_user;

    @SerializedName("mail")
    @Expose
    private String mail = null;

    @SerializedName("status_sale")
    @Expose
    private int status_sale;

    @SerializedName("total_amount")
    @Expose
    private Double total_amount = null;

    @SerializedName("type_sales")
    @Expose
    private List<WsDataSale> wsDataSales = null;


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public String getDate_availability() {
        return date_availability;
    }

    public void setDate_availability(String date_availability) {
        this.date_availability = date_availability;
    }

    public String getDocument_number() {
        return document_number;
    }

    public void setDocument_number(String document_number) {
        this.document_number = document_number;
    }

    public String getExpiration_month() {
        return expiration_month;
    }

    public void setExpiration_month(String expiration_month) {
        this.expiration_month = expiration_month;
    }

    public String getExpiration_year() {
        return expiration_year;
    }

    public void setExpiration_year(String expiration_year) {
        this.expiration_year = expiration_year;
    }

    public String getFull_name_card() {
        return full_name_card;
    }

    public void setFull_name_card(String full_name_card) {
        this.full_name_card = full_name_card;
    }

    public String getFull_name_type_availability() {
        return full_name_type_availability;
    }

    public void setFull_name_type_availability(String full_name_type_availability) {
        this.full_name_type_availability = full_name_type_availability;
    }

    public int getHour_availability() {
        return hour_availability;
    }

    public void setHour_availability(int hour_availability) {
        this.hour_availability = hour_availability;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_customer() {
        return id_customer;
    }

    public void setId_customer(int id_customer) {
        this.id_customer = id_customer;
    }

    public int getId_customer_address() {
        return id_customer_address;
    }

    public void setId_customer_address(int id_customer_address) {
        this.id_customer_address = id_customer_address;
    }

    public int getId_type_availability() {
        return id_type_availability;
    }

    public void setId_type_availability(int id_type_availability) {
        this.id_type_availability = id_type_availability;
    }

    public int getId_type_card() {
        return id_type_card;
    }

    public void setId_type_card(int id_type_card) {
        this.id_type_card = id_type_card;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getStatus_sale() {
        return status_sale;
    }

    public void setStatus_sale(int status_sale) {
        this.status_sale = status_sale;
    }

    public Double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(Double total_amount) {
        this.total_amount = total_amount;
    }

    public List<WsDataSale> getWsDataSales() {
        return wsDataSales;
    }

    public void setWsDataSales(List<WsDataSale> wsDataSales) {
        this.wsDataSales = wsDataSales;
    }

    public String getFull_name_customer() {
        return full_name_customer;
    }

    public void setFull_name_customer(String full_name_customer) {
        this.full_name_customer = full_name_customer;
    }

    public String getUrl_image_customer() {
        return url_image_customer;
    }

    public void setUrl_image_customer(String url_image_customer) {
        this.url_image_customer = url_image_customer;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
