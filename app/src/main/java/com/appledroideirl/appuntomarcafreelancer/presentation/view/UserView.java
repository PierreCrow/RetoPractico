package com.appledroideirl.appuntomarcafreelancer.presentation.view;

import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseAcceptRequest;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseAddBankAccount;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseAddSubService;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseAgregarLocal;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseAgregarServicio;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseAgregarUsuario;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseAvailableDateHour;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseListBankAccount;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseListLocales;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseLogin;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseRecoveryPassword;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseRefuseRequest;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseReportes;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseRequestList;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseServices;
import com.appledroideirl.appuntomarcafreelancer.domain.model.Usuario;

import java.util.List;

public interface UserView extends BaseView {

    void tokenGenerated(String token);

    void loginSuccess(WsResponseLogin wsResponseLogin);

    void requestListSuccess(WsResponseRequestList wsResponseRequestList);

    void requestAceptedSuccess(WsResponseAcceptRequest wsResponseAcceptRequest);

    void requestRefusedSuccess(WsResponseRefuseRequest responseRefuseRequest);

    void listAvailableDateHourSuccess(WsResponseAvailableDateHour wsResponseAvailableDateHour);

    void listReportSuccess(WsResponseReportes wsResponseReportes);

    void listServices(WsResponseServices wsResponseServices);

    void listUserServices(WsResponseServices wsResponseServices);

    void servicesAddesSuccess(WsResponseAgregarServicio wsResponseAgregarServicio);

    void dateAvailableAddedSuccess(String message);

    void userAddedSuccess(WsResponseAgregarUsuario wsResponseAgregarUsuario);

    void bankAccountAddedSuccess(WsResponseAddBankAccount wsResponseAddBankAccount);

    void listBankAccount(WsResponseListBankAccount wsResponseListBankAccount);

    void subServiceAddedSuccess(WsResponseAddSubService wsResponseAddSubService);

    void userEditedSuccess(String message);

    void listLocalesSuccess(WsResponseListLocales wsResponseListLocales);

    void localAddedSuccess(WsResponseAgregarLocal wsResponseAgregarLocal);

    void recoveryPasswordSuccess(String mensaje);


    void showLoading();

    void hideLoading();

    void showErrorMessage(String message);
}
