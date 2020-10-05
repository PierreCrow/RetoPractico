package com.appledroideirl.appuntomarcafreelancer.interactor.usuario;

import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseAgregarUsuario;

public interface AddUserCallback {
    void onAddUserSuccess(WsResponseAgregarUsuario wsResponseAgregarUsuario);

    void onAddUserError(String message);
}
