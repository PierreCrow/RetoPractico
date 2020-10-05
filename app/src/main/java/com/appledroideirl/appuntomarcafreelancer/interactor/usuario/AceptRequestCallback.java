package com.appledroideirl.appuntomarcafreelancer.interactor.usuario;

import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseAcceptRequest;

public interface AceptRequestCallback {
    void onAceptRequestSuccess(WsResponseAcceptRequest wsResponseAcceptRequest);

    void onAceptRequestError(String message);
}
