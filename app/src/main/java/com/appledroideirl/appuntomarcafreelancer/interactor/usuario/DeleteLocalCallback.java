package com.appledroideirl.appuntomarcafreelancer.interactor.usuario;

import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseDeleteLocal;

public interface DeleteLocalCallback {
    void onDeleteLocalSuccess(String message);

    void onDeleteLocalError(String message);
}
