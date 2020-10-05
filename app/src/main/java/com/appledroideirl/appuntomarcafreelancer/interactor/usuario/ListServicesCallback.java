package com.appledroideirl.appuntomarcafreelancer.interactor.usuario;

import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseServices;

public interface ListServicesCallback {
    void onListServicesSuccess(WsResponseServices wsResponseServices);

    void onListServicesError(String message);
}
