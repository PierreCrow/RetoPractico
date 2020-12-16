package com.appledroideirl.appuntomarcafreelancer.presentation.presenter;

import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterAddBankAccount;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterAddSubService;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterAgregarDateAvailable;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterAgregarLocal;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterAgregarServicio;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterAgregarUsuario;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterEditarUsuario;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterRecoveryPassword;
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
import com.appledroideirl.appuntomarcafreelancer.data.datasource.datastore.UserDataStoreFactory;
import com.appledroideirl.appuntomarcafreelancer.data.repository.UserDataRepository;
import com.appledroideirl.appuntomarcafreelancer.domain.repository.UserRepository;
import com.appledroideirl.appuntomarcafreelancer.interactor.usuario.AceptRequestCallback;
import com.appledroideirl.appuntomarcafreelancer.interactor.usuario.AddBankAccountCallback;
import com.appledroideirl.appuntomarcafreelancer.interactor.usuario.AddDateAvailableCallback;
import com.appledroideirl.appuntomarcafreelancer.interactor.usuario.AddLocalCallback;
import com.appledroideirl.appuntomarcafreelancer.interactor.usuario.AddServiceCallback;
import com.appledroideirl.appuntomarcafreelancer.interactor.usuario.AddSubServiceCallback;
import com.appledroideirl.appuntomarcafreelancer.interactor.usuario.AddUserCallback;
import com.appledroideirl.appuntomarcafreelancer.interactor.usuario.DeleteLocalCallback;
import com.appledroideirl.appuntomarcafreelancer.interactor.usuario.EditUserCallback;
import com.appledroideirl.appuntomarcafreelancer.interactor.usuario.GenerateTokenCallback;
import com.appledroideirl.appuntomarcafreelancer.interactor.usuario.ListAvailableDateHourCallback;
import com.appledroideirl.appuntomarcafreelancer.interactor.usuario.ListBankAccountCallback;
import com.appledroideirl.appuntomarcafreelancer.interactor.usuario.ListLocalesCallback;
import com.appledroideirl.appuntomarcafreelancer.interactor.usuario.ListReportCallback;
import com.appledroideirl.appuntomarcafreelancer.interactor.usuario.ListServicesCallback;
import com.appledroideirl.appuntomarcafreelancer.interactor.usuario.ListUserServicesCallback;
import com.appledroideirl.appuntomarcafreelancer.interactor.usuario.LoginCallback;
import com.appledroideirl.appuntomarcafreelancer.interactor.usuario.RecoveryPasswordCallback;
import com.appledroideirl.appuntomarcafreelancer.interactor.usuario.RefuseRequestCallback;
import com.appledroideirl.appuntomarcafreelancer.interactor.usuario.RequestListCallback;
import com.appledroideirl.appuntomarcafreelancer.interactor.usuario.UserInteractor;
import com.appledroideirl.appuntomarcafreelancer.presentation.view.UserView;

import java.util.List;

public class UserPresenter implements Presenter<UserView>, GenerateTokenCallback,
        LoginCallback, RequestListCallback, AceptRequestCallback,
        RefuseRequestCallback, ListAvailableDateHourCallback, ListReportCallback,
        ListServicesCallback, ListUserServicesCallback, AddServiceCallback,
        AddDateAvailableCallback, AddUserCallback, ListBankAccountCallback,
        AddBankAccountCallback, AddSubServiceCallback, EditUserCallback,
        ListLocalesCallback, RecoveryPasswordCallback, AddLocalCallback,DeleteLocalCallback {

    private UserView userView;
    private UserInteractor userInteractor;

    public void generateToken(String user, String password) {
        userInteractor.generateToken(user, password, this);
    }

    public void login(String token, String fcm, String mail, String password) {
        userInteractor.login(token, fcm, mail, password, this);
    }

    public void requestList(String token, int idUser) {
        userInteractor.requestList(token, idUser, this);
    }

    public void aceptRequest(String token, int idRequest) {
        userInteractor.aceptRequest(token, idRequest, this);
    }

    public void refuseRequest(String token, int idRequest) {
        userInteractor.refuseRequest(token, idRequest, this);
    }

    public void listAvailableDateHour(String token, int idUser) {
        userInteractor.listAvailableDateHour(token, idUser, this);
    }

    public void listReports(String token, int idUser) {
        userInteractor.listReports(token, idUser, this);
    }

    public void listServices(String token) {
        userInteractor.listServices(token, this);
    }

    public void listUserServices(String token, int idUser) {
        userInteractor.listUserServices(token, idUser, this);
    }

    public void addService(String token, List<WsParameterAgregarServicio> services) {
        userInteractor.addService(token, services, this);
    }

    public void addDateAvailable(String token, WsParameterAgregarDateAvailable wsParameterAgregarDateAvailable) {
        userInteractor.addDateAvailable(token, wsParameterAgregarDateAvailable, this);
    }

    public void addUser(String token, WsParameterAgregarUsuario wsParameterAgregarUsuario) {
        userInteractor.addUser(token, wsParameterAgregarUsuario, this);
    }

    public void addBankAccount(String token, WsParameterAddBankAccount wsParameterAddBankAccount) {
        userInteractor.addBankAccount(token, wsParameterAddBankAccount, this);
    }

    public void listBankAccount(String token, int idUser) {
        userInteractor.listBankAccount(token, idUser, this);
    }

    public void addSubService(String token, List<WsParameterAddSubService> wsParameterAddSubServices) {
        userInteractor.addSubService(token, wsParameterAddSubServices, this);
    }

    public void editUser(String token, WsParameterEditarUsuario wsParameterEditarUsuario) {
        userInteractor.editUser(token, wsParameterEditarUsuario, this);
    }

    public void listLocales(String token, int idUser) {
        userInteractor.listLocales(token, idUser, this);
    }

    public void addLocal(String token, WsParameterAgregarLocal wsParameterAgregarLocal) {
        userInteractor.addLocal(token, wsParameterAgregarLocal, this);
    }

    public void recoveryPassword(String token, WsParameterRecoveryPassword wsParameterRecoveryPassword) {
        userInteractor.recoveryPassword(token, wsParameterRecoveryPassword, this);
    }

    public void deleteLocal(String token, int idLocal) {
        userInteractor.deleteLocal(token, idLocal, this);
    }


    @Override
    public void addView(UserView view) {
        this.userView = view;
        UserRepository requestRepository = new UserDataRepository(new UserDataStoreFactory(this.userView.getContext()));
        userInteractor = new UserInteractor(requestRepository);
    }

    @Override
    public void removeView(UserView view) {
    }

    @Override
    public void onTokenSuccess(String token) {
        userView.tokenGenerated(token);
    }

    @Override
    public void ontokenError(String message) {
        userView.showErrorMessage(message);
    }

    @Override
    public void onLoginSuccess(WsResponseLogin wsResponseLogin) {
        userView.loginSuccess(wsResponseLogin);
    }

    @Override
    public void onLoginError(String message) {
        userView.showErrorMessage(message);
    }

    @Override
    public void onRequestListSuccess(WsResponseRequestList wsResponseRequestList) {
        userView.requestListSuccess(wsResponseRequestList);
    }

    @Override
    public void onRequestListError(String message) {
        userView.showErrorMessage(message);
    }

    @Override
    public void onAceptRequestSuccess(WsResponseAcceptRequest wsResponseAcceptRequest) {
        userView.requestAceptedSuccess(wsResponseAcceptRequest);
    }

    @Override
    public void onAceptRequestError(String message) {
        userView.showErrorMessage(message);
    }

    @Override
    public void onRefuseRequestSuccess(WsResponseRefuseRequest responseRefuseRequest) {
        userView.requestRefusedSuccess(responseRefuseRequest);
    }

    @Override
    public void onRefuseRequestError(String message) {
        userView.showErrorMessage(message);
    }

    @Override
    public void onListAvailableDateHourSuccess(WsResponseAvailableDateHour wsResponseAvailableDateHour) {
        userView.listAvailableDateHourSuccess(wsResponseAvailableDateHour);
    }

    @Override
    public void onListAvailableDateHourError(String message) {
        userView.showErrorMessage(message);
    }

    @Override
    public void onListReportSuccess(WsResponseReportes wsResponseReportes) {
        userView.listReportSuccess(wsResponseReportes);
    }

    @Override
    public void onListReportError(String message) {
        userView.showErrorMessage(message);
    }

    @Override
    public void onListServicesSuccess(WsResponseServices wsResponseServices) {
        userView.listServices(wsResponseServices);
    }

    @Override
    public void onListServicesError(String message) {
        userView.showErrorMessage(message);
    }

    @Override
    public void onListUserServicesSuccess(WsResponseServices wsResponseServices) {
        userView.listUserServices(wsResponseServices);
    }

    @Override
    public void onListUserServicesError(String message) {
        userView.showErrorMessage(message);
    }

    @Override
    public void onAddServiceSuccess(WsResponseAgregarServicio wsResponseAgregarServicio) {
        userView.servicesAddesSuccess(wsResponseAgregarServicio);
    }

    @Override
    public void onAddServiceError(String message) {
        userView.showErrorMessage(message);
    }

    @Override
    public void onAddDateAvailableSuccess(String message) {
        userView.dateAvailableAddedSuccess(message);
    }

    @Override
    public void onAddDateAvailableError(String message) {
        userView.showErrorMessage(message);
    }

    @Override
    public void onAddUserSuccess(WsResponseAgregarUsuario wsResponseAgregarUsuario) {
        userView.userAddedSuccess(wsResponseAgregarUsuario);
    }

    @Override
    public void onAddUserError(String message) {
        userView.showErrorMessage(message);
    }

    @Override
    public void onAddBankAccountSuccess(WsResponseAddBankAccount wsResponseAddBankAccount) {
        userView.bankAccountAddedSuccess(wsResponseAddBankAccount);
    }

    @Override
    public void onAddBankAccountError(String message) {
        userView.showErrorMessage(message);
    }

    @Override
    public void onListBankAccountSuccess(WsResponseListBankAccount wsResponseListBankAccount) {
        userView.listBankAccount(wsResponseListBankAccount);
    }

    @Override
    public void onListBankAccountError(String message) {
        userView.showErrorMessage(message);
    }

    @Override
    public void onAddSubServiceSuccess(WsResponseAddSubService wsResponseAddSubService) {
        userView.subServiceAddedSuccess(wsResponseAddSubService);
    }

    @Override
    public void onAddSubServiceError(String message) {
        userView.showErrorMessage(message);
    }

    @Override
    public void onEditUserSuccess(String message) {
        userView.userEditedSuccess(message);
    }

    @Override
    public void onEditUserError(String message) {
        userView.userEditedSuccess(message);
    }

    @Override
    public void onListLocalesSuccess(WsResponseListLocales wsResponseListLocales) {
        userView.listLocalesSuccess(wsResponseListLocales);
    }

    @Override
    public void onListLocalesError(String message) {
        userView.showErrorMessage(message);
    }

    @Override
    public void onAddLocalSuccess(WsResponseAgregarLocal wsResponseAgregarLocal) {
        userView.localAddedSuccess(wsResponseAgregarLocal);
    }

    @Override
    public void onAddLocalError(String message) {
        userView.showErrorMessage(message);
    }

    @Override
    public void onRecoveryPasswordSuccess(String message) {
        userView.recoveryPasswordSuccess(message);
    }

    @Override
    public void onRecoveryPasswordError(String message) {
        userView.showErrorMessage(message);
    }

    @Override
    public void onDeleteLocalSuccess(String message) {
        userView.deleteLocalSuccess(message);
    }

    @Override
    public void onDeleteLocalError(String message) {
        userView.showErrorMessage(message);
    }
}
