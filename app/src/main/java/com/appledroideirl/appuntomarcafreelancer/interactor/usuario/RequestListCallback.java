package com.appledroideirl.appuntomarcafreelancer.interactor.usuario;

import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseRequestList;

public interface RequestListCallback {
    void onRequestListSuccess(WsResponseRequestList wsResponseRequestList);

    void onRequestListError(String message);
}
