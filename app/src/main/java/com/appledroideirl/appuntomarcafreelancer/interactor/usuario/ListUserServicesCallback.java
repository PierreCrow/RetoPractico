package com.appledroideirl.appuntomarcafreelancer.interactor.usuario;

import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseServices;

public interface ListUserServicesCallback {
    void onListUserServicesSuccess(WsResponseServices wsResponseServices);

    void onListUserServicesError(String message);
}
