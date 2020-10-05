package com.appledroideirl.appuntomarcafreelancer.interactor.usuario;

import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseAgregarUsuario;

public interface EditUserCallback {
    void onEditUserSuccess(String message);

    void onEditUserError(String message);
}
