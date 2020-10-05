package com.appledroideirl.appuntomarcafreelancer.interactor.usuario;

import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseAvailableDateHour;

public interface ListAvailableDateHourCallback {
    void onListAvailableDateHourSuccess(WsResponseAvailableDateHour wsResponseAvailableDateHour);

    void onListAvailableDateHourError(String message);
}
