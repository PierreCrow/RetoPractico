package com.appledroideirl.appuntomarcafreelancer.data.datasource.db.store;

import android.content.Context;

import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterAddBankAccount;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterAddSubService;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterAgregarDateAvailable;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterAgregarLocal;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterAgregarServicio;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterAgregarUsuario;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterEditarUsuario;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterRecoveryPassword;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.datastore.UsuarioDataStore;
import com.appledroideirl.appuntomarcafreelancer.domain.repository.RepositoryCallback;

import java.util.List;

public class DbUsuarioDataStore implements UsuarioDataStore {


    public DbUsuarioDataStore(Context context) {

    }


    @Override
    public void generateToken(String user, String password, RepositoryCallback repositoryCallback) {

    }

    @Override
    public void login(String token, String fcm, String mail, String password, RepositoryCallback repositoryCallback) {

    }

    @Override
    public void requestList(String token, int idUser, RepositoryCallback repositoryCallback) {

    }

    @Override
    public void aceptRequest(String token, int idRequest, RepositoryCallback repositoryCallback) {

    }

    @Override
    public void refuseRequest(String token, int idRequest, RepositoryCallback repositoryCallback) {

    }

    @Override
    public void listAvailableDateHour(String token, int idUser, RepositoryCallback repositoryCallback) {

    }

    @Override
    public void listReports(String token, int idUser, RepositoryCallback repositoryCallback) {

    }

    @Override
    public void listServices(String token, RepositoryCallback repositoryCallback) {

    }

    @Override
    public void listUserServices(String token, int idUser, RepositoryCallback repositoryCallback) {

    }

    @Override
    public void addService(String token, List<WsParameterAgregarServicio> services, RepositoryCallback repositoryCallback) {

    }

    @Override
    public void addDateAvailable(String token, WsParameterAgregarDateAvailable wsParameterAgregarDateAvailable, RepositoryCallback repositoryCallback) {

    }

    @Override
    public void addUser(String token, WsParameterAgregarUsuario wsParameterAgregarUsuario, RepositoryCallback repositoryCallback) {

    }

    @Override
    public void addBankAccount(String token, WsParameterAddBankAccount wsParameterAddBankAccount, RepositoryCallback repositoryCallback) {

    }

    @Override
    public void listBankAccount(String token, int idUser, RepositoryCallback repositoryCallback) {

    }

    @Override
    public void addSubService(String token, List<WsParameterAddSubService> wsParameterAddSubService, RepositoryCallback repositoryCallback) {

    }

    @Override
    public void editUser(String token, WsParameterEditarUsuario wsParameterEditarUsuario, RepositoryCallback repositoryCallback) {

    }

    @Override
    public void listLocales(String token, int idUser, RepositoryCallback repositoryCallback) {

    }

    @Override
    public void deleteLocal(String token, int idLocal, RepositoryCallback repositoryCallback) {

    }

    @Override
    public void addLocal(String token, WsParameterAgregarLocal wsParameterAgregarLocal, RepositoryCallback repositoryCallback) {

    }

    @Override
    public void recoveryPassword(String token, WsParameterRecoveryPassword wsParameterRecoveryPassword, RepositoryCallback repositoryCallback) {

    }


}
