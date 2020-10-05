package com.appledroideirl.appuntomarcafreelancer.interactor.usuario;

import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseRefuseRequest;

public interface RefuseRequestCallback {
    void onRefuseRequestSuccess(WsResponseRefuseRequest responseRefuseRequest);

    void onRefuseRequestError(String message);
}
