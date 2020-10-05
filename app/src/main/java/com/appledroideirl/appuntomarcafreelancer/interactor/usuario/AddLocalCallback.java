package com.appledroideirl.appuntomarcafreelancer.interactor.usuario;

import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseAgregarLocal;

public interface AddLocalCallback {
    void onAddLocalSuccess(WsResponseAgregarLocal wsResponseAgregarLocal);

    void onAddLocalError(String message);
}
