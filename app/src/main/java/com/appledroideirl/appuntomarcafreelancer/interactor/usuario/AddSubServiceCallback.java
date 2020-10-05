package com.appledroideirl.appuntomarcafreelancer.interactor.usuario;

import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseAddSubService;

public interface AddSubServiceCallback {
    void onAddSubServiceSuccess(WsResponseAddSubService wsResponseAddSubService);

    void onAddSubServiceError(String message);
}
