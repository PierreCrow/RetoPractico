package com.appledroideirl.appuntomarcafreelancer.interactor.usuario;

import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseReportes;

public interface ListReportCallback {
    void onListReportSuccess(WsResponseReportes wsResponseReportes);

    void onListReportError(String message);
}
