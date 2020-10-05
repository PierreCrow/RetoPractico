package com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WsResponseAddBankAccount {

    @SerializedName("entity")
    @Expose
    private WsDataAddBankAccount wsDataAddBankAccount;

    @SerializedName("message")
    @Expose
    private String message = null;

    @SerializedName("status")
    @Expose
    private int status;

    public WsDataAddBankAccount getWsDataAddBankAccount() {
        return wsDataAddBankAccount;
    }

    public void setWsDataAddBankAccount(WsDataAddBankAccount wsDataAddBankAccount) {
        this.wsDataAddBankAccount = wsDataAddBankAccount;
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
