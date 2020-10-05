package com.appledroideirl.appuntomarcafreelancer.data.repository;

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
import com.appledroideirl.appuntomarcafreelancer.data.datasource.datastore.UsuarioDataStore;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.datastore.UserDataStoreFactory;
import com.appledroideirl.appuntomarcafreelancer.domain.repository.RepositoryCallback;
import com.appledroideirl.appuntomarcafreelancer.domain.repository.UserRepository;
import com.appledroideirl.appuntomarcafreelancer.interactor.usuario.AceptRequestCallback;
import com.appledroideirl.appuntomarcafreelancer.interactor.usuario.AddBankAccountCallback;
import com.appledroideirl.appuntomarcafreelancer.interactor.usuario.AddDateAvailableCallback;
import com.appledroideirl.appuntomarcafreelancer.interactor.usuario.AddLocalCallback;
import com.appledroideirl.appuntomarcafreelancer.interactor.usuario.AddServiceCallback;
import com.appledroideirl.appuntomarcafreelancer.interactor.usuario.AddSubServiceCallback;
import com.appledroideirl.appuntomarcafreelancer.interactor.usuario.AddUserCallback;
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
import com.appledroideirl.appuntomarcafreelancer.presentation.utils.Constants;

import java.util.List;


public class UserDataRepository implements UserRepository {

    private final UserDataStoreFactory userDataStoreFactory;

    public UserDataRepository(UserDataStoreFactory userDataStoreFactory) {
        this.userDataStoreFactory = userDataStoreFactory;
    }

    @Override
    public void generateToken(String user, String password, GenerateTokenCallback generateTokenCallback) {
        final UsuarioDataStore usuarioDataStore = userDataStoreFactory.create(Constants.STORE.CLOUD);
        usuarioDataStore.generateToken(user, password, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                generateTokenCallback.ontokenError(message);
            }

            @Override
            public void onSuccess(Object object) {
                String tokenn = (String) object;
                generateTokenCallback.onTokenSuccess(tokenn);
            }
        });
    }

    @Override
    public void login(String token, String fcm, String mail, String password, LoginCallback loginCallback) {
        final UsuarioDataStore usuarioDataStore = userDataStoreFactory.create(Constants.STORE.CLOUD);
        usuarioDataStore.login(token, fcm, mail, password, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                loginCallback.onLoginError(message);
            }

            @Override
            public void onSuccess(Object object) {
                WsResponseLogin wsResponseLogin = (WsResponseLogin) object;
                loginCallback.onLoginSuccess(wsResponseLogin);
            }
        });
    }

    @Override
    public void requestList(String token, int idUser, RequestListCallback requestListCallback) {
        final UsuarioDataStore usuarioDataStore = userDataStoreFactory.create(Constants.STORE.CLOUD);
        usuarioDataStore.requestList(token, idUser, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                requestListCallback.onRequestListError(message);
            }

            @Override
            public void onSuccess(Object object) {
                WsResponseRequestList wsResponseRequestList = (WsResponseRequestList) object;
                requestListCallback.onRequestListSuccess(wsResponseRequestList);
            }
        });
    }

    @Override
    public void aceptRequest(String token, int idRequest, AceptRequestCallback aceptRequestCallback) {
        final UsuarioDataStore usuarioDataStore = userDataStoreFactory.create(Constants.STORE.CLOUD);
        usuarioDataStore.aceptRequest(token, idRequest, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                aceptRequestCallback.onAceptRequestError(message);
            }

            @Override
            public void onSuccess(Object object) {
                WsResponseAcceptRequest wsResponseAcceptRequest = (WsResponseAcceptRequest) object;
                aceptRequestCallback.onAceptRequestSuccess(wsResponseAcceptRequest);
            }
        });
    }

    @Override
    public void refuseRequest(String token, int idRequest, RefuseRequestCallback refuseRequestCallback) {
        final UsuarioDataStore usuarioDataStore = userDataStoreFactory.create(Constants.STORE.CLOUD);
        usuarioDataStore.refuseRequest(token, idRequest, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                refuseRequestCallback.onRefuseRequestError(message);
            }

            @Override
            public void onSuccess(Object object) {
                WsResponseRefuseRequest wsResponseRefuseRequest = (WsResponseRefuseRequest) object;
                refuseRequestCallback.onRefuseRequestSuccess(wsResponseRefuseRequest);
            }
        });
    }

    @Override
    public void listAvailableDateHour(String token, int idUser, ListAvailableDateHourCallback listAvailableDateHourCallback) {

        final UsuarioDataStore usuarioDataStore = userDataStoreFactory.create(Constants.STORE.CLOUD);
        usuarioDataStore.listAvailableDateHour(token, idUser, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                listAvailableDateHourCallback.onListAvailableDateHourError(message);
            }

            @Override
            public void onSuccess(Object object) {
                WsResponseAvailableDateHour wsResponseAvailableDateHour = (WsResponseAvailableDateHour) object;
                listAvailableDateHourCallback.onListAvailableDateHourSuccess(wsResponseAvailableDateHour);
            }
        });
    }

    @Override
    public void listReports(String token, int idUser, ListReportCallback listReportCallback) {

        final UsuarioDataStore usuarioDataStore = userDataStoreFactory.create(Constants.STORE.CLOUD);
        usuarioDataStore.listReports(token, idUser, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                listReportCallback.onListReportError(message);
            }

            @Override
            public void onSuccess(Object object) {
                WsResponseReportes wsResponseReportes = (WsResponseReportes) object;
                listReportCallback.onListReportSuccess(wsResponseReportes);
            }
        });
    }

    @Override
    public void listServices(String token, ListServicesCallback listServicesCallback) {

        final UsuarioDataStore usuarioDataStore = userDataStoreFactory.create(Constants.STORE.CLOUD);
        usuarioDataStore.listServices(token, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                listServicesCallback.onListServicesError(message);
            }

            @Override
            public void onSuccess(Object object) {
                WsResponseServices wsResponseServices = (WsResponseServices) object;
                listServicesCallback.onListServicesSuccess(wsResponseServices);
            }
        });
    }

    @Override
    public void listUserServices(String token, int idUser, ListUserServicesCallback listUserServicesCallback) {
        final UsuarioDataStore usuarioDataStore = userDataStoreFactory.create(Constants.STORE.CLOUD);
        usuarioDataStore.listUserServices(token, idUser, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                listUserServicesCallback.onListUserServicesError(message);
            }

            @Override
            public void onSuccess(Object object) {
                WsResponseServices wsResponseServices = (WsResponseServices) object;
                listUserServicesCallback.onListUserServicesSuccess(wsResponseServices);
            }
        });
    }

    @Override
    public void addService(String token, List<WsParameterAgregarServicio> services, AddServiceCallback addServiceCallback) {

        final UsuarioDataStore usuarioDataStore = userDataStoreFactory.create(Constants.STORE.CLOUD);
        usuarioDataStore.addService(token, services, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                addServiceCallback.onAddServiceError(message);
            }

            @Override
            public void onSuccess(Object object) {
                WsResponseAgregarServicio wsResponseAgregarServicio = (WsResponseAgregarServicio) object;
                addServiceCallback.onAddServiceSuccess(wsResponseAgregarServicio);
            }
        });
    }

    @Override
    public void addDateAvailable(String token, WsParameterAgregarDateAvailable wsParameterAgregarDateAvailable, AddDateAvailableCallback addDateAvailableCallback) {

        final UsuarioDataStore usuarioDataStore = userDataStoreFactory.create(Constants.STORE.CLOUD);
        usuarioDataStore.addDateAvailable(token, wsParameterAgregarDateAvailable, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                addDateAvailableCallback.onAddDateAvailableError(message);
            }

            @Override
            public void onSuccess(Object object) {
                String mensaje = (String) object;
                addDateAvailableCallback.onAddDateAvailableSuccess(mensaje);
            }
        });
    }

    @Override
    public void addUser(String token, WsParameterAgregarUsuario wsParameterAgregarUsuario, AddUserCallback addUserCallback) {

        final UsuarioDataStore usuarioDataStore = userDataStoreFactory.create(Constants.STORE.CLOUD);
        usuarioDataStore.addUser(token, wsParameterAgregarUsuario, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                addUserCallback.onAddUserError(message);
            }

            @Override
            public void onSuccess(Object object) {
                WsResponseAgregarUsuario wsResponseAgregarUsuario = (WsResponseAgregarUsuario) object;
                addUserCallback.onAddUserSuccess(wsResponseAgregarUsuario);
            }
        });
    }

    @Override
    public void addBankAccount(String token, WsParameterAddBankAccount wsParameterAddBankAccount, AddBankAccountCallback addBankAccountCallback) {
        final UsuarioDataStore usuarioDataStore = userDataStoreFactory.create(Constants.STORE.CLOUD);
        usuarioDataStore.addBankAccount(token, wsParameterAddBankAccount, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                addBankAccountCallback.onAddBankAccountError(message);
            }

            @Override
            public void onSuccess(Object object) {
                WsResponseAddBankAccount wsResponseAddBankAccount = (WsResponseAddBankAccount) object;
                addBankAccountCallback.onAddBankAccountSuccess(wsResponseAddBankAccount);
            }
        });
    }

    @Override
    public void listBankAccount(String token, int idUser, ListBankAccountCallback listBankAccountCallback) {
        final UsuarioDataStore usuarioDataStore = userDataStoreFactory.create(Constants.STORE.CLOUD);
        usuarioDataStore.listBankAccount(token, idUser, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                listBankAccountCallback.onListBankAccountError(message);
            }

            @Override
            public void onSuccess(Object object) {
                WsResponseListBankAccount wsResponseListBankAccount = (WsResponseListBankAccount) object;
                listBankAccountCallback.onListBankAccountSuccess(wsResponseListBankAccount);
            }
        });
    }

    @Override
    public void addSubService(String token, List<WsParameterAddSubService> wsParameterAddSubServices, AddSubServiceCallback addSubServiceCallback) {
        final UsuarioDataStore usuarioDataStore = userDataStoreFactory.create(Constants.STORE.CLOUD);
        usuarioDataStore.addSubService(token, wsParameterAddSubServices, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                addSubServiceCallback.onAddSubServiceError(message);
            }

            @Override
            public void onSuccess(Object object) {
                WsResponseAddSubService wsResponseAddSubService = (WsResponseAddSubService) object;
                addSubServiceCallback.onAddSubServiceSuccess(wsResponseAddSubService);
            }
        });
    }

    @Override
    public void editUser(String token, WsParameterEditarUsuario wsParameterEditarUsuario, EditUserCallback editUserCallback) {
        final UsuarioDataStore usuarioDataStore = userDataStoreFactory.create(Constants.STORE.CLOUD);
        usuarioDataStore.editUser(token, wsParameterEditarUsuario, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                editUserCallback.onEditUserError(message);
            }

            @Override
            public void onSuccess(Object object) {
                String mensaje = (String) object;
                editUserCallback.onEditUserSuccess(mensaje);
            }
        });
    }

    @Override
    public void listLocales(String token, int idUser, ListLocalesCallback listLocalesCallback) {
        final UsuarioDataStore usuarioDataStore = userDataStoreFactory.create(Constants.STORE.CLOUD);
        usuarioDataStore.listLocales(token, idUser, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                listLocalesCallback.onListLocalesError(message);
            }

            @Override
            public void onSuccess(Object object) {
                WsResponseListLocales wsResponseListLocales = (WsResponseListLocales) object;
                listLocalesCallback.onListLocalesSuccess(wsResponseListLocales);
            }
        });
    }

    @Override
    public void addLocal(String token, WsParameterAgregarLocal wsParameterAgregarLocal, AddLocalCallback addLocalCallback) {

        final UsuarioDataStore usuarioDataStore = userDataStoreFactory.create(Constants.STORE.CLOUD);
        usuarioDataStore.addLocal(token, wsParameterAgregarLocal, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                addLocalCallback.onAddLocalError(message);
            }

            @Override
            public void onSuccess(Object object) {
                WsResponseAgregarLocal wsResponseAgregarLocal = (WsResponseAgregarLocal) object;
                addLocalCallback.onAddLocalSuccess(wsResponseAgregarLocal);
            }
        });

    }

    @Override
    public void recoveryPassword(String token, WsParameterRecoveryPassword wsParameterRecoveryPassword, RecoveryPasswordCallback recoveryPasswordCallback) {

        final UsuarioDataStore usuarioDataStore = userDataStoreFactory.create(Constants.STORE.CLOUD);
        usuarioDataStore.recoveryPassword(token, wsParameterRecoveryPassword, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                recoveryPasswordCallback.onRecoveryPasswordError(message);
            }

            @Override
            public void onSuccess(Object object) {
                String mensaje = (String) object;
                recoveryPasswordCallback.onRecoveryPasswordSuccess(mensaje);
            }
        });

    }
}
