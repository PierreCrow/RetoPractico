package com.appledroideirl.appuntomarcafreelancer.interactor.usuario;

import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseAgregarLocal;

public interface RecoveryPasswordCallback {
    void onRecoveryPasswordSuccess(String message);

    void onRecoveryPasswordError(String message);
}
