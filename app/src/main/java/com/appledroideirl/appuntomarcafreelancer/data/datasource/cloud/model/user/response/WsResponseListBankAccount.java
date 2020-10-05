package com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WsResponseListBankAccount {

    @SerializedName("entity")
    @Expose
    private List<WsDataAddBankAccount> wsDataAddBankAccounts;

    @SerializedName("message")
    @Expose
    private String message = null;

    @SerializedName("status")
    @Expose
    private int status;

    public List<WsDataAddBankAccount> getWsDataAddBankAccounts() {
        return wsDataAddBankAccounts;
    }

    public void setWsDataAddBankAccounts(List<WsDataAddBankAccount> wsDataAddBankAccounts) {
        this.wsDataAddBankAccounts = wsDataAddBankAccounts;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
