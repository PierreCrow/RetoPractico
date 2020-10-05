package com.appledroideirl.appuntomarcafreelancer.data.datasource.datastore;


import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterAddBankAccount;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterAddSubService;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterAgregarDateAvailable;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterAgregarLocal;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterAgregarServicio;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterAgregarUsuario;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterEditarUsuario;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterRecoveryPassword;
import com.appledroideirl.appuntomarcafreelancer.domain.repository.RepositoryCallback;

import java.util.List;

public interface UsuarioDataStore {

    void generateToken(String user, String password, RepositoryCallback repositoryCallback);

    void login(String token, String fcm, String mail, String password, RepositoryCallback repositoryCallback);

    void requestList(String token, int idUser, RepositoryCallback repositoryCallback);

    void aceptRequest(String token, int idRequest, RepositoryCallback repositoryCallback);

    void refuseRequest(String token, int idRequest, RepositoryCallback repositoryCallback);

    void listAvailableDateHour(String token, int idUser, RepositoryCallback repositoryCallback);

    void listReports(String token, int idUser, RepositoryCallback repositoryCallback);

    void listServices(String token, RepositoryCallback repositoryCallback);

    void listUserServices(String token, int idUser, RepositoryCallback repositoryCallback);

    void addService(String token, List<WsParameterAgregarServicio> services, RepositoryCallback repositoryCallback);

    void addDateAvailable(String token, WsParameterAgregarDateAvailable wsParameterAgregarDateAvailable, RepositoryCallback repositoryCallback);

    void addUser(String token, WsParameterAgregarUsuario wsParameterAgregarUsuario, RepositoryCallback repositoryCallback);

    void addBankAccount(String token, WsParameterAddBankAccount wsParameterAddBankAccount, RepositoryCallback repositoryCallback);

    void listBankAccount(String token, int idUser, RepositoryCallback repositoryCallback);

    void addSubService(String token, List<WsParameterAddSubService> wsParameterAddSubServices, RepositoryCallback repositoryCallback);

    void editUser(String token, WsParameterEditarUsuario wsParameterEditarUsuario, RepositoryCallback repositoryCallback);

    void listLocales(String token, int idUser, RepositoryCallback repositoryCallback);

    void addLocal(String token, WsParameterAgregarLocal wsParameterAgregarLocal, RepositoryCallback repositoryCallback);

    void recoveryPassword(String token, WsParameterRecoveryPassword wsParameterRecoveryPassword, RepositoryCallback repositoryCallback);


}
