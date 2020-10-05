package com.appledroideirl.appuntomarcafreelancer.interactor.usuario;

import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseAddBankAccount;

public interface AddBankAccountCallback {
    void onAddBankAccountSuccess(WsResponseAddBankAccount wsResponseAddBankAccount);

    void onAddBankAccountError(String message);
}
