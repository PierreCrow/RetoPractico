package com.appledroideirl.appuntomarcafreelancer.presentation.ui.activities;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;

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
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseRefuseRequest;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseReportes;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseRequestList;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseServices;
import com.appledroideirl.appuntomarcafreelancer.domain.model.Usuario;
import com.appledroideirl.appuntomarcafreelancer.presentation.presenter.UserPresenter;
import com.appledroideirl.appuntomarcafreelancer.presentation.utils.Helper;
import com.appledroideirl.appuntomarcafreelancer.presentation.view.UserView;
import com.google.firebase.firestore.auth.User;


public class Splash extends BaseActivity implements UserView {


    UserPresenter userPresenter;
    Usuario usuario;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(com.appledroideirl.appuntomarcafreelancer.R.layout.appu_splash_screen);
        init();
        timerSplash();

    }

    void timerSplash(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if(Helper.getUserAppPreference(getContext()).isLogged())
                {
                    next(MainActivity.class, null);
                }
                else
                {
                    next(LoginActivity.class, null);
                }


            }
        }, 3000);
    }



    void init() {
        usuario = Helper.getUserAppPreference(getContext());

        userPresenter = new UserPresenter();
        userPresenter.addView(this);
        userPresenter.generateToken(usuario.getUser(), usuario.getPassword());
    }

    @Override
    public void tokenGenerated(String token) {
        usuario.setToken(token);
        Helper.saveUserAppPreference(getContext(), usuario);
    }

    @Override
    public void loginSuccess(WsResponseLogin wsResponseLogin) {

    }

    @Override
    public void requestListSuccess(WsResponseRequestList wsResponseRequestList) {

    }

    @Override
    public void requestAceptedSuccess(WsResponseAcceptRequest wsResponseAcceptRequest) {

    }

    @Override
    public void requestRefusedSuccess(WsResponseRefuseRequest responseRefuseRequest) {

    }

    @Override
    public void listAvailableDateHourSuccess(WsResponseAvailableDateHour wsResponseAvailableDateHour) {

    }

    @Override
    public void listReportSuccess(WsResponseReportes wsResponseReportes) {

    }

    @Override
    public void listServices(WsResponseServices wsResponseServices) {

    }

    @Override
    public void listUserServices(WsResponseServices wsResponseServices) {

    }

    @Override
    public void servicesAddesSuccess(WsResponseAgregarServicio wsResponseAgregarServicio) {

    }

    @Override
    public void dateAvailableAddedSuccess(String message) {

    }

    @Override
    public void userAddedSuccess(WsResponseAgregarUsuario wsResponseAgregarUsuario) {

    }

    @Override
    public void bankAccountAddedSuccess(WsResponseAddBankAccount wsResponseAddBankAccount) {

    }

    @Override
    public void listBankAccount(WsResponseListBankAccount wsResponseListBankAccount) {

    }

    @Override
    public void subServiceAddedSuccess(WsResponseAddSubService wsResponseAddSubService) {

    }

    @Override
    public void userEditedSuccess(String message) {

    }

    @Override
    public void listLocalesSuccess(WsResponseListLocales wsResponseListLocales) {

    }

    @Override
    public void localAddedSuccess(WsResponseAgregarLocal wsResponseAgregarLocal) {

    }

    @Override
    public void recoveryPasswordSuccess(String mensaje) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public Context getContext() {
        return this;
    }
}