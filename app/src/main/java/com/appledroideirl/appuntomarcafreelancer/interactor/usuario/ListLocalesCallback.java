package com.appledroideirl.appuntomarcafreelancer.interactor.usuario;

import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseListLocales;

public interface ListLocalesCallback {
    void onListLocalesSuccess(WsResponseListLocales wsResponseListLocales);

    void onListLocalesError(String message);
}
