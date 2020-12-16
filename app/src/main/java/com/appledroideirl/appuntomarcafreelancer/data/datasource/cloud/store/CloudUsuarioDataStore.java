package com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.store;

import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.apiclient.ApiClient;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.apiclient.ApiInterface;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.security.parameter.WsParameterToken;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.security.response.WsResponseToken;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterAddBankAccount;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterAddSubService;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterAgregarDateAvailable;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterAgregarLocal;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterAgregarServicio;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterAgregarUsuario;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterEditarUsuario;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterLogin;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterRecoveryPassword;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseAcceptRequest;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseAddBankAccount;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseAddSubService;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseAgregarDateAvailable;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseAgregarLocal;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseAgregarServicio;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseAgregarUsuario;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseAvailableDateHour;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseDeleteLocal;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseEditarUsuario;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseListBankAccount;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseListLocales;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseLogin;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseRecoveryPassword;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseRefuseRequest;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseReportes;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseRequestList;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseServices;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.datastore.UsuarioDataStore;
import com.appledroideirl.appuntomarcafreelancer.data.mapper.UsuarioDataMapper;
import com.appledroideirl.appuntomarcafreelancer.domain.repository.RepositoryCallback;
import com.appledroideirl.appuntomarcafreelancer.presentation.utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CloudUsuarioDataStore implements UsuarioDataStore {
    private static final String TAG = "CloudUsuarioDataStore";

    private ApiInterface apiInterface;
    UsuarioDataMapper usuarioDataMapper;

    public CloudUsuarioDataStore() {
        // apiInterface = ApiClient.getApiClient("");
        usuarioDataMapper = new UsuarioDataMapper();
    }


    @Override
    public void generateToken(String user, String password, RepositoryCallback repositoryCallback) {

        WsParameterToken wsParameterToken = new WsParameterToken();
        wsParameterToken.setUser(user);
        wsParameterToken.setPassword("1234");

        Call<WsResponseToken> call = ApiClient.getApiClient("").generateToken(wsParameterToken);
        call.enqueue(new Callback<WsResponseToken>() {
            @Override
            public void onResponse(Call<WsResponseToken> call, Response<WsResponseToken> response) {
                if (response.code() == Constants.RESPONSE_CODES.SERVER_CONNECTION_SUCCESS) {
                    if (response.body() != null) {
                        WsResponseToken wsResponseToken = response.body();
                        repositoryCallback.onSuccess(wsResponseToken.getAccess_token());

                    } else {
                        repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                    }
                } else {
                    repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                }
            }

            @Override
            public void onFailure(Call<WsResponseToken> call, Throwable t) {
                repositoryCallback.onError(t.getMessage());
            }
        });


    }

    @Override
    public void login(String token, String fcm, String mail, String password, RepositoryCallback repositoryCallback) {

        WsParameterLogin wsParameterLogin = new WsParameterLogin();
        wsParameterLogin.setMail(mail);
        wsParameterLogin.setId_fire_base_token(fcm);
        wsParameterLogin.setPassword(password);

        Call<WsResponseLogin> call = ApiClient.getApiClient(token).login(wsParameterLogin);
        call.enqueue(new Callback<WsResponseLogin>() {
            @Override
            public void onResponse(Call<WsResponseLogin> call, Response<WsResponseLogin> response) {
                if (response.code() == Constants.RESPONSE_CODES.SERVER_CONNECTION_SUCCESS) {
                    if (response.body() != null) {
                        WsResponseLogin wsResponseLogin = response.body();
                        if (wsResponseLogin.getStatus() == Constants.RESPONSE_CODES.SUCCESS) {
                            repositoryCallback.onSuccess(wsResponseLogin);
                        } else {

                                repositoryCallback.onError(wsResponseLogin.getMessage());




                        }
                    } else {
                        repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                    }
                } else {
                    repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                }
            }

            @Override
            public void onFailure(Call<WsResponseLogin> call, Throwable t) {
                repositoryCallback.onError(t.getMessage());
            }
        });

    }

    @Override
    public void requestList(String token, int idUser, RepositoryCallback repositoryCallback) {

        Integer id = idUser;
        String url = Constants.URLS.REQUEST_LIST_USER + id.toString();

        Call<WsResponseRequestList> call = ApiClient.getApiClient(token).listRequestUser(url);
        call.enqueue(new Callback<WsResponseRequestList>() {
            @Override
            public void onResponse(Call<WsResponseRequestList> call, Response<WsResponseRequestList> response) {

                if (response.code() == Constants.RESPONSE_CODES.SERVER_CONNECTION_SUCCESS) {
                    if (response.body() != null) {
                        WsResponseRequestList wsResponseRequestList = response.body();
                        if (wsResponseRequestList.getStatus() == Constants.RESPONSE_CODES.SUCCESS) {
                            repositoryCallback.onSuccess(wsResponseRequestList);
                        } else {
                            repositoryCallback.onError(wsResponseRequestList.getMessage());
                        }
                    } else {
                        repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                    }
                } else {
                    repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                }

            }

            @Override
            public void onFailure(Call<WsResponseRequestList> call, Throwable t) {
                repositoryCallback.onError(t.getMessage());
            }
        });
    }

    @Override
    public void aceptRequest(String token, int idRequest, RepositoryCallback repositoryCallback) {

        Integer id = idRequest;
        String url = Constants.URLS.REQUEST_ACCEPT + id.toString();

        Call<WsResponseAcceptRequest> call = ApiClient.getApiClient(token).acceptRequest(url);
        call.enqueue(new Callback<WsResponseAcceptRequest>() {
            @Override
            public void onResponse(Call<WsResponseAcceptRequest> call, Response<WsResponseAcceptRequest> response) {

                if (response.code() == Constants.RESPONSE_CODES.SERVER_CONNECTION_SUCCESS) {
                    if (response.body() != null) {
                        WsResponseAcceptRequest wsResponseAcceptRequest = response.body();
                        if (wsResponseAcceptRequest.getStatus() == Constants.RESPONSE_CODES.SUCCESS) {
                            repositoryCallback.onSuccess(wsResponseAcceptRequest);
                        } else {
                            repositoryCallback.onError(wsResponseAcceptRequest.getMessage());
                        }
                    } else {
                        repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                    }
                } else {
                    repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                }

            }

            @Override
            public void onFailure(Call<WsResponseAcceptRequest> call, Throwable t) {
                repositoryCallback.onError(t.getMessage());
            }
        });
    }

    @Override
    public void refuseRequest(String token, int idRequest, RepositoryCallback repositoryCallback) {

        Integer id = idRequest;
        String url = Constants.URLS.REQUEST_REFUSE + id.toString();

        Call<WsResponseRefuseRequest> call = ApiClient.getApiClient(token).refuseRequest(url);
        call.enqueue(new Callback<WsResponseRefuseRequest>() {
            @Override
            public void onResponse(Call<WsResponseRefuseRequest> call, Response<WsResponseRefuseRequest> response) {

                if (response.code() == Constants.RESPONSE_CODES.SERVER_CONNECTION_SUCCESS) {
                    if (response.body() != null) {
                        WsResponseRefuseRequest wsResponseRefuseRequest = response.body();
                        if (wsResponseRefuseRequest.getStatus() == Constants.RESPONSE_CODES.SUCCESS) {
                            repositoryCallback.onSuccess(wsResponseRefuseRequest);
                        } else {
                            repositoryCallback.onError(wsResponseRefuseRequest.getMessage());
                        }
                    } else {
                        repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                    }
                } else {
                    repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                }

            }

            @Override
            public void onFailure(Call<WsResponseRefuseRequest> call, Throwable t) {
                repositoryCallback.onError(t.getMessage());
            }
        });
    }

    @Override
    public void listAvailableDateHour(String token, int idUser, RepositoryCallback repositoryCallback) {

        Integer id = idUser;
        String url = Constants.URLS.LIST_AVAILABLE_DATE_HOUR + id.toString();

        Call<WsResponseAvailableDateHour> call = ApiClient.getApiClient(token).listAvailableDateHour(url);
        call.enqueue(new Callback<WsResponseAvailableDateHour>() {
            @Override
            public void onResponse(Call<WsResponseAvailableDateHour> call, Response<WsResponseAvailableDateHour> response) {

                if (response.code() == Constants.RESPONSE_CODES.SERVER_CONNECTION_SUCCESS) {
                    if (response.body() != null) {
                        WsResponseAvailableDateHour wsResponseAvailableDateHour = response.body();
                        if (wsResponseAvailableDateHour.getStatus() == Constants.RESPONSE_CODES.SUCCESS) {
                            repositoryCallback.onSuccess(wsResponseAvailableDateHour);
                        } else {
                            repositoryCallback.onError(wsResponseAvailableDateHour.getMessage());
                        }
                    } else {
                        repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                    }
                } else {
                    repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                }
            }

            @Override
            public void onFailure(Call<WsResponseAvailableDateHour> call, Throwable t) {
                repositoryCallback.onError(t.getMessage());
            }
        });


    }

    @Override
    public void listReports(String token, int idUser, RepositoryCallback repositoryCallback) {

        Integer id = idUser;
        String url = Constants.URLS.LIST_REPORTS + id.toString();

        Call<WsResponseReportes> call = ApiClient.getApiClient(token).listReports(url);
        call.enqueue(new Callback<WsResponseReportes>() {
            @Override
            public void onResponse(Call<WsResponseReportes> call, Response<WsResponseReportes> response) {

                if (response.code() == Constants.RESPONSE_CODES.SERVER_CONNECTION_SUCCESS) {
                    if (response.body() != null) {
                        WsResponseReportes wsResponseReportes = response.body();
                        if (wsResponseReportes.getStatus() == Constants.RESPONSE_CODES.SUCCESS) {
                            repositoryCallback.onSuccess(wsResponseReportes);
                        } else {
                            repositoryCallback.onError(wsResponseReportes.getMessage());
                        }
                    } else {
                        repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                    }
                } else {
                    repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                }
            }

            @Override
            public void onFailure(Call<WsResponseReportes> call, Throwable t) {
                repositoryCallback.onError(t.getMessage());
            }
        });
    }

    @Override
    public void listServices(String token, RepositoryCallback repositoryCallback) {

        String url = Constants.URLS.LIST_SERVICES;

        Call<WsResponseServices> call = ApiClient.getApiClient(token).listServices(url);
        call.enqueue(new Callback<WsResponseServices>() {
            @Override
            public void onResponse(Call<WsResponseServices> call, Response<WsResponseServices> response) {

                if (response.code() == Constants.RESPONSE_CODES.SERVER_CONNECTION_SUCCESS) {
                    if (response.body() != null) {
                        WsResponseServices wsResponseServices = response.body();
                        if (wsResponseServices.getStatus() == Constants.RESPONSE_CODES.SUCCESS) {
                            repositoryCallback.onSuccess(wsResponseServices);
                        } else {
                            repositoryCallback.onError(wsResponseServices.getMessage());
                        }
                    } else {
                        repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                    }
                } else {
                    repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                }
            }

            @Override
            public void onFailure(Call<WsResponseServices> call, Throwable t) {
                repositoryCallback.onError(t.getMessage());
            }
        });
    }

    @Override
    public void listUserServices(String token, int idUser, RepositoryCallback repositoryCallback) {

        Integer id = idUser;
        String url = Constants.URLS.LIST_USER_SERVICES + id.toString();

        Call<WsResponseServices> call = ApiClient.getApiClient(token).listUserServices(url);
        call.enqueue(new Callback<WsResponseServices>() {
            @Override
            public void onResponse(Call<WsResponseServices> call, Response<WsResponseServices> response) {

                if (response.code() == Constants.RESPONSE_CODES.SERVER_CONNECTION_SUCCESS) {
                    if (response.body() != null) {
                        WsResponseServices wsResponseServices = response.body();
                        if (wsResponseServices.getStatus() == Constants.RESPONSE_CODES.SUCCESS) {
                            repositoryCallback.onSuccess(wsResponseServices);
                        } else {
                            repositoryCallback.onError(wsResponseServices.getMessage());
                        }
                    } else {
                        repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                    }
                } else {
                    repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                }
            }

            @Override
            public void onFailure(Call<WsResponseServices> call, Throwable t) {
                repositoryCallback.onError(t.getMessage());
            }
        });
    }

    @Override
    public void addService(String token, List<WsParameterAgregarServicio> services, RepositoryCallback repositoryCallback) {

        Call<WsResponseAgregarServicio> call = ApiClient.getApiClient(token).addService(services);
        call.enqueue(new Callback<WsResponseAgregarServicio>() {
            @Override
            public void onResponse(Call<WsResponseAgregarServicio> call, Response<WsResponseAgregarServicio> response) {

                if (response.code() == Constants.RESPONSE_CODES.SERVER_CONNECTION_SUCCESS) {
                    if (response.body() != null) {
                        WsResponseAgregarServicio wsResponseAgregarServicio = response.body();
                        if (wsResponseAgregarServicio.getStatus() == Constants.RESPONSE_CODES.SUCCESS) {
                            repositoryCallback.onSuccess(wsResponseAgregarServicio);
                        } else {
                            repositoryCallback.onError(wsResponseAgregarServicio.getMessage());
                        }
                    } else {
                        repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                    }
                } else {
                    repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                }
            }

            @Override
            public void onFailure(Call<WsResponseAgregarServicio> call, Throwable t) {
                repositoryCallback.onError(t.getMessage());
            }
        });

    }

    @Override
    public void addDateAvailable(String token, WsParameterAgregarDateAvailable wsParameterAgregarDateAvailable, RepositoryCallback repositoryCallback) {

        Call<WsResponseAgregarDateAvailable> call = ApiClient.getApiClient(token).addDateAvailable(wsParameterAgregarDateAvailable);
        call.enqueue(new Callback<WsResponseAgregarDateAvailable>() {
            @Override
            public void onResponse(Call<WsResponseAgregarDateAvailable> call, Response<WsResponseAgregarDateAvailable> response) {

                if (response.code() == Constants.RESPONSE_CODES.SERVER_CONNECTION_SUCCESS) {
                    if (response.body() != null) {
                        WsResponseAgregarDateAvailable wsResponseAgregarServicio = response.body();
                        if (wsResponseAgregarServicio.getStatus() == Constants.RESPONSE_CODES.SUCCESS) {
                            repositoryCallback.onSuccess(wsResponseAgregarServicio.getMessage());
                        } else {
                            repositoryCallback.onError(wsResponseAgregarServicio.getMessage());
                        }
                    } else {
                        repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                    }
                } else {
                    repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                }
            }

            @Override
            public void onFailure(Call<WsResponseAgregarDateAvailable> call, Throwable t) {
                repositoryCallback.onError(t.getMessage());
            }
        });
    }

    @Override
    public void addUser(String token, WsParameterAgregarUsuario wsParameterAgregarUsuario, RepositoryCallback repositoryCallback) {

        Call<WsResponseAgregarUsuario> call = ApiClient.getApiClient(token).addUser(wsParameterAgregarUsuario);
        call.enqueue(new Callback<WsResponseAgregarUsuario>() {
            @Override
            public void onResponse(Call<WsResponseAgregarUsuario> call, Response<WsResponseAgregarUsuario> response) {

                if (response.code() == Constants.RESPONSE_CODES.SERVER_CONNECTION_SUCCESS) {
                    if (response.body() != null) {
                        WsResponseAgregarUsuario wsResponseAgregarUsuario = response.body();
                        if (wsResponseAgregarUsuario.getStatus() == Constants.RESPONSE_CODES.SUCCESS) {
                            repositoryCallback.onSuccess(wsResponseAgregarUsuario);
                        } else {
                            if (wsResponseAgregarUsuario.getStatus() == -2) {
                                repositoryCallback.onSuccess(wsResponseAgregarUsuario);
                            } else {
                                repositoryCallback.onError(wsResponseAgregarUsuario.getMessage());
                            }

                        }
                    } else {
                        repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                    }
                } else {
                    repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                }
            }

            @Override
            public void onFailure(Call<WsResponseAgregarUsuario> call, Throwable t) {
                repositoryCallback.onError(t.getMessage());
            }
        });

    }

    @Override
    public void addBankAccount(String token, WsParameterAddBankAccount wsParameterAddBankAccount, RepositoryCallback repositoryCallback) {

        Call<WsResponseAddBankAccount> call = ApiClient.getApiClient(token).addBankAccount(wsParameterAddBankAccount);
        call.enqueue(new Callback<WsResponseAddBankAccount>() {
            @Override
            public void onResponse(Call<WsResponseAddBankAccount> call, Response<WsResponseAddBankAccount> response) {

                if (response.code() == Constants.RESPONSE_CODES.SERVER_CONNECTION_SUCCESS) {
                    if (response.body() != null) {
                        WsResponseAddBankAccount wsResponseAddBankAccount = response.body();
                        if (wsResponseAddBankAccount.getStatus() == Constants.RESPONSE_CODES.SUCCESS) {
                            repositoryCallback.onSuccess(wsResponseAddBankAccount);
                        } else {
                            repositoryCallback.onError(wsResponseAddBankAccount.getMessage());
                        }
                    } else {
                        repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                    }
                } else {
                    repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                }
            }

            @Override
            public void onFailure(Call<WsResponseAddBankAccount> call, Throwable t) {
                repositoryCallback.onError(t.getMessage());
            }
        });
    }

    @Override
    public void listBankAccount(String token, int idUser, RepositoryCallback repositoryCallback) {

        Integer id = idUser;
        String url = Constants.URLS.LIST_BANK_ACCOUNT + id.toString();

        Call<WsResponseListBankAccount> call = ApiClient.getApiClient(token).listBankAccount(url);
        call.enqueue(new Callback<WsResponseListBankAccount>() {
            @Override
            public void onResponse(Call<WsResponseListBankAccount> call, Response<WsResponseListBankAccount> response) {

                if (response.code() == Constants.RESPONSE_CODES.SERVER_CONNECTION_SUCCESS) {
                    if (response.body() != null) {
                        WsResponseListBankAccount wsResponseListBankAccount = response.body();
                        if (wsResponseListBankAccount.getStatus() == Constants.RESPONSE_CODES.SUCCESS) {
                            repositoryCallback.onSuccess(wsResponseListBankAccount);
                        } else {
                            repositoryCallback.onError(wsResponseListBankAccount.getMessage());
                        }
                    } else {
                        repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                    }
                } else {
                    repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                }
            }

            @Override
            public void onFailure(Call<WsResponseListBankAccount> call, Throwable t) {
                repositoryCallback.onError(t.getMessage());
            }
        });
    }

    @Override
    public void addSubService(String token, List<WsParameterAddSubService> wsParameterAddSubServices, RepositoryCallback repositoryCallback) {


        Call<WsResponseAddSubService> call = ApiClient.getApiClient(token).addSubService(wsParameterAddSubServices);
        call.enqueue(new Callback<WsResponseAddSubService>() {
            @Override
            public void onResponse(Call<WsResponseAddSubService> call, Response<WsResponseAddSubService> response) {

                if (response.code() == Constants.RESPONSE_CODES.SERVER_CONNECTION_SUCCESS) {
                    if (response.body() != null) {
                        WsResponseAddSubService wsResponseAddSubService = response.body();
                        if (wsResponseAddSubService.getStatus() == Constants.RESPONSE_CODES.SUCCESS) {
                            repositoryCallback.onSuccess(wsResponseAddSubService);
                        } else {
                            repositoryCallback.onError(wsResponseAddSubService.getMessage());
                        }
                    } else {
                        repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                    }
                } else {
                    repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                }
            }

            @Override
            public void onFailure(Call<WsResponseAddSubService> call, Throwable t) {
                repositoryCallback.onError(t.getMessage());
            }
        });

    }

    @Override
    public void editUser(String token, WsParameterEditarUsuario wsParameterEditarUsuario, RepositoryCallback repositoryCallback) {

        Call<WsResponseEditarUsuario> call = ApiClient.getApiClient(token).editUser(wsParameterEditarUsuario);
        call.enqueue(new Callback<WsResponseEditarUsuario>() {
            @Override
            public void onResponse(Call<WsResponseEditarUsuario> call, Response<WsResponseEditarUsuario> response) {

                if (response.code() == Constants.RESPONSE_CODES.SERVER_CONNECTION_SUCCESS) {
                    if (response.body() != null) {
                        WsResponseEditarUsuario wsResponseAddSubService = response.body();
                        if (wsResponseAddSubService.getStatus() == Constants.RESPONSE_CODES.SUCCESS) {
                            repositoryCallback.onSuccess("Se actualizaron sus datos con éxito");
                        } else {
                            repositoryCallback.onError(wsResponseAddSubService.getMessage());
                        }
                    } else {
                        repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                    }
                } else {
                    repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                }
            }

            @Override
            public void onFailure(Call<WsResponseEditarUsuario> call, Throwable t) {
                repositoryCallback.onError(t.getMessage());
            }
        });
    }

    @Override
    public void listLocales(String token, int idUser, RepositoryCallback repositoryCallback) {

        Integer id = idUser;
        String url = Constants.URLS.LIST_LOCALES + id.toString();

        Call<WsResponseListLocales> call = ApiClient.getApiClient(token).listLocales(url);
        call.enqueue(new Callback<WsResponseListLocales>() {
            @Override
            public void onResponse(Call<WsResponseListLocales> call, Response<WsResponseListLocales> response) {

                if (response.code() == Constants.RESPONSE_CODES.SERVER_CONNECTION_SUCCESS) {
                    if (response.body() != null) {
                        WsResponseListLocales wsResponseListBankAccount = response.body();
                        if (wsResponseListBankAccount.getStatus() == Constants.RESPONSE_CODES.SUCCESS) {
                            repositoryCallback.onSuccess(wsResponseListBankAccount);
                        } else {
                            repositoryCallback.onError(wsResponseListBankAccount.getMessage());
                        }
                    } else {
                        repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                    }
                } else {
                    repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                }
            }

            @Override
            public void onFailure(Call<WsResponseListLocales> call, Throwable t) {
                repositoryCallback.onError(t.getMessage());
            }
        });
    }

    @Override
    public void deleteLocal(String token, int idLocal, RepositoryCallback repositoryCallback) {

        Integer id = idLocal;
        String url = Constants.URLS.LIST_LOCALES + id.toString();

        Call<WsResponseDeleteLocal> call = ApiClient.getApiClient(token).deleteLocal(url);
        call.enqueue(new Callback<WsResponseDeleteLocal>() {
            @Override
            public void onResponse(Call<WsResponseDeleteLocal> call, Response<WsResponseDeleteLocal> response) {

                if (response.code() == Constants.RESPONSE_CODES.SERVER_CONNECTION_SUCCESS) {
                    if (response.body() != null) {
                        WsResponseDeleteLocal wsResponseDeleteLocal = response.body();
                        if (wsResponseDeleteLocal.getStatus() == Constants.RESPONSE_CODES.SUCCESS) {
                            repositoryCallback.onSuccess(wsResponseDeleteLocal.getMessage());
                        } else {
                            repositoryCallback.onError(wsResponseDeleteLocal.getMessage());
                        }
                    } else {
                        repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                    }
                } else {
                    repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                }
            }

            @Override
            public void onFailure(Call<WsResponseDeleteLocal> call, Throwable t) {
                repositoryCallback.onError(t.getMessage());
            }
        });
    }

    @Override
    public void addLocal(String token, WsParameterAgregarLocal wsParameterAgregarLocal, RepositoryCallback repositoryCallback) {

        Call<WsResponseAgregarLocal> call = ApiClient.getApiClient(token).addLocal(wsParameterAgregarLocal);
        call.enqueue(new Callback<WsResponseAgregarLocal>() {
            @Override
            public void onResponse(Call<WsResponseAgregarLocal> call, Response<WsResponseAgregarLocal> response) {

                if (response.code() == Constants.RESPONSE_CODES.SERVER_CONNECTION_SUCCESS) {
                    if (response.body() != null) {
                        WsResponseAgregarLocal wsResponseAgregarLocal = response.body();
                        if (wsResponseAgregarLocal.getStatus() == Constants.RESPONSE_CODES.SUCCESS) {
                            repositoryCallback.onSuccess(wsResponseAgregarLocal);
                        } else {
                            repositoryCallback.onError(wsResponseAgregarLocal.getMessage());
                        }
                    } else {
                        repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                    }
                } else {
                    repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                }
            }

            @Override
            public void onFailure(Call<WsResponseAgregarLocal> call, Throwable t) {
                repositoryCallback.onError(t.getMessage());
            }
        });

    }

    @Override
    public void recoveryPassword(String token, WsParameterRecoveryPassword wsParameterRecoveryPassword, RepositoryCallback repositoryCallback) {

        Call<WsResponseRecoveryPassword> call = ApiClient.getApiClient(token).recoveryPassword(wsParameterRecoveryPassword);
        call.enqueue(new Callback<WsResponseRecoveryPassword>() {
            @Override
            public void onResponse(Call<WsResponseRecoveryPassword> call, Response<WsResponseRecoveryPassword> response) {

                if (response.code() == Constants.RESPONSE_CODES.SERVER_CONNECTION_SUCCESS) {
                    if (response.body() != null) {
                        WsResponseRecoveryPassword wsResponseRecoveryPassword = response.body();
                        if (wsResponseRecoveryPassword.getStatus() == Constants.RESPONSE_CODES.SUCCESS) {
                            repositoryCallback.onSuccess(wsResponseRecoveryPassword.getMessage());
                        } else {
                            repositoryCallback.onError(wsResponseRecoveryPassword.getMessage());
                        }
                    } else {
                        repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                    }
                } else {
                    repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                }
            }

            @Override
            public void onFailure(Call<WsResponseRecoveryPassword> call, Throwable t) {
                repositoryCallback.onError(t.getMessage());
            }
        });

    }
}
