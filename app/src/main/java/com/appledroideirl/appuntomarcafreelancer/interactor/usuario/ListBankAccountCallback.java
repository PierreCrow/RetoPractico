package com.appledroideirl.appuntomarcafreelancer.interactor.usuario;

import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseListBankAccount;

public interface ListBankAccountCallback {
    void onListBankAccountSuccess(WsResponseListBankAccount wsResponseListBankAccount );

    void onListBankAccountError(String message);
}
