package com.appledroideirl.appuntomarcafreelancer.domain.repository;


import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterAddBankAccount;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterAddSubService;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterAgregarDateAvailable;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterAgregarLocal;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterAgregarServicio;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterAgregarUsuario;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterEditarUsuario;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterRecoveryPassword;
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

import java.util.List;

public interface UserRepository {

    void generateToken(String user, String password, GenerateTokenCallback generateTokenCallback);

    void login(String token, String fcm, String mail, String password, LoginCallback loginCallback);

    void requestList(String token, int idUser, RequestListCallback requestListCallback);

    void aceptRequest(String token, int idRequest, AceptRequestCallback aceptRequestCallback);

    void refuseRequest(String token, int idRequest, RefuseRequestCallback refuseRequestCallback);

    void listAvailableDateHour(String token, int idUser, ListAvailableDateHourCallback listAvailableDateHourCallback);

    void listReports(String token, int idUser, ListReportCallback listReportCallback);

    void listServices(String token, ListServicesCallback listServicesCallback);

    void listUserServices(String token, int idUser, ListUserServicesCallback listUserServicesCallback);

    void addService(String token, List<WsParameterAgregarServicio> services, AddServiceCallback addServiceCallback);

    void addDateAvailable(String token, WsParameterAgregarDateAvailable wsParameterAgregarDateAvailable, AddDateAvailableCallback addDateAvailableCallback);

    void addUser(String token, WsParameterAgregarUsuario wsParameterAgregarUsuario, AddUserCallback addUserCallback);

    void addBankAccount(String token, WsParameterAddBankAccount wsParameterAddBankAccount, AddBankAccountCallback addBankAccountCallback);

    void listBankAccount(String token, int idUser, ListBankAccountCallback listBankAccountCallback);

    void addSubService(String token, List<WsParameterAddSubService> wsParameterAddSubServices, AddSubServiceCallback addSubServiceCallback);

    void editUser(String token, WsParameterEditarUsuario wsParameterEditarUsuario, EditUserCallback editUserCallback);

    void listLocales(String token, int idUser, ListLocalesCallback listLocalesCallback);

    void addLocal(String token, WsParameterAgregarLocal wsParameterAgregarLocal, AddLocalCallback addLocalCallback);

    void recoveryPassword(String token, WsParameterRecoveryPassword wsParameterRecoveryPassword, RecoveryPasswordCallback recoveryPasswordCallback);

    void deleteLocal(String token, int idLocal, DeleteLocalCallback deleteLocalCallback);


}
