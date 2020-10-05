package com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WsDataAddBankAccount {

    @SerializedName("id_user")
    @Expose
    private int id_user;

    @SerializedName("id_bank")
    @Expose
    private int id_bank;

    @SerializedName("account_number")
    @Expose
    private String account_number = null;

    @SerializedName("cci")
    @Expose
    private String cci = null;

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_bank() {
        return id_bank;
    }

    public void setId_bank(int id_bank) {
        this.id_bank = id_bank;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getCci() {
        return cci;
    }

    public void setCci(String cci) {
        this.cci = cci;
    }
}
