package com.appledroideirl.appuntomarcafreelancer.interactor.usuario;

import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseLogin;

public interface LoginCallback {
    void onLoginSuccess(WsResponseLogin wsResponseLogin);

    void onLoginError(String message);
}
