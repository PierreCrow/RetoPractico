package com.appledroideirl.appuntomarcafreelancer.interactor.usuario;

import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseAgregarServicio;

public interface AddServiceCallback {
    void onAddServiceSuccess(WsResponseAgregarServicio wsResponseAgregarServicio);

    void onAddServiceError(String message);
}
