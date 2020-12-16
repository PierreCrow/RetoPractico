package com.appledroideirl.appuntomarcafreelancer.interactor.usuario;

import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterAddBankAccount;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterAddSubService;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterAgregarDateAvailable;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterAgregarLocal;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterAgregarServicio;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterAgregarUsuario;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterEditarUsuario;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterRecoveryPassword;
import com.appledroideirl.appuntomarcafreelancer.domain.repository.UserRepository;

import java.util.List;

public class UserInteractor {

    private final UserRepository userRepository;

    public UserInteractor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void generateToken(String user, String password, GenerateTokenCallback generateTokenCallback) {
        userRepository.generateToken(user, password, generateTokenCallback);
    }

    public void login(String token, String fcm, String mail, String password, LoginCallback loginCallback) {
        userRepository.login(token, fcm, mail, password, loginCallback);
    }

    public void requestList(String token, int idUser, RequestListCallback requestListCallback) {
        userRepository.requestList(token, idUser, requestListCallback);
    }

    public void aceptRequest(String token, int idRequest, AceptRequestCallback aceptRequestCallback) {
        userRepository.aceptRequest(token, idRequest, aceptRequestCallback);
    }

    public void refuseRequest(String token, int idRequest, RefuseRequestCallback refuseRequestCallback) {
        userRepository.refuseRequest(token, idRequest, refuseRequestCallback);
    }


    public void listAvailableDateHour(String token, int idUser, ListAvailableDateHourCallback listAvailableDateHourCallback) {
        userRepository.listAvailableDateHour(token, idUser, listAvailableDateHourCallback);
    }

    public void listReports(String token, int idUser, ListReportCallback listReportCallback) {
        userRepository.listReports(token, idUser, listReportCallback);
    }

    public void listServices(String token, ListServicesCallback listServicesCallback) {
        userRepository.listServices(token, listServicesCallback);
    }

    public void listUserServices(String token, int idUser, ListUserServicesCallback listUserServicesCallback) {
        userRepository.listUserServices(token, idUser, listUserServicesCallback);
    }

    public void addService(String token, List<WsParameterAgregarServicio> services, AddServiceCallback addServiceCallback) {
        userRepository.addService(token, services, addServiceCallback);
    }

    public void addDateAvailable(String token, WsParameterAgregarDateAvailable wsParameterAgregarDateAvailable, AddDateAvailableCallback addDateAvailableCallback) {
        userRepository.addDateAvailable(token, wsParameterAgregarDateAvailable, addDateAvailableCallback);
    }

    public void addUser(String token, WsParameterAgregarUsuario wsParameterAgregarUsuario, AddUserCallback addUserCallback) {
        userRepository.addUser(token, wsParameterAgregarUsuario, addUserCallback);
    }

    public void addBankAccount(String token, WsParameterAddBankAccount wsParameterAddBankAccount, AddBankAccountCallback addBankAccountCallback) {
        userRepository.addBankAccount(token, wsParameterAddBankAccount, addBankAccountCallback);
    }

    public void listBankAccount(String token, int idUser, ListBankAccountCallback listBankAccountCallback) {
        userRepository.listBankAccount(token, idUser, listBankAccountCallback);
    }

    public void addSubService(String token, List<WsParameterAddSubService> wsParameterAddSubServices, AddSubServiceCallback addSubServiceCallback) {
        userRepository.addSubService(token, wsParameterAddSubServices, addSubServiceCallback);
    }

    public void editUser(String token, WsParameterEditarUsuario wsParameterEditarUsuario, EditUserCallback editUserCallback) {
        userRepository.editUser(token, wsParameterEditarUsuario, editUserCallback);
    }

    public void listLocales(String token, int idUser, ListLocalesCallback listLocalesCallback) {
        userRepository.listLocales(token, idUser, listLocalesCallback);
    }

    public void addLocal(String token, WsParameterAgregarLocal wsParameterAgregarLocal, AddLocalCallback addLocalCallback) {
        userRepository.addLocal(token, wsParameterAgregarLocal, addLocalCallback);
    }

    public void recoveryPassword(String token, WsParameterRecoveryPassword wsParameterRecoveryPassword, RecoveryPasswordCallback recoveryPasswordCallback) {
        userRepository.recoveryPassword(token, wsParameterRecoveryPassword, recoveryPasswordCallback);
    }

    public void deleteLocal(String token, int idLocal, DeleteLocalCallback deleteLocalCallback) {
        userRepository.deleteLocal(token, idLocal, deleteLocalCallback);
    }



}
