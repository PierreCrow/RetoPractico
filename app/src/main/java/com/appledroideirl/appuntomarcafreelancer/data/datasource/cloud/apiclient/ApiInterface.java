package com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.apiclient;

import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.security.parameter.WsParameterToken;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.security.response.WsResponseToken;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterAddBankAccount;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterAddSubService;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterAgregarDateAvailable;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterAgregarLocal;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterAgregarServicio;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterAgregarUsuario;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterDataUserStore;
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
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseEditarUsuario;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseListBankAccount;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseListLocales;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseLogin;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseRecoveryPassword;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseRefuseRequest;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseReportes;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseRequestList;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsResponseServices;
import com.appledroideirl.appuntomarcafreelancer.presentation.utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Url;

public interface ApiInterface {

    @POST(Constants.URLS.GENERATE_TOKEN)
    Call<WsResponseToken> generateToken(@Body WsParameterToken wsParameterRegisterUser);

    @POST(Constants.URLS.LOGIN)
    Call<WsResponseLogin> login(@Body WsParameterLogin wsParameterLoginCliente);

    @GET
    Call<WsResponseRequestList> listRequestUser(@Url String url);

    @GET
    Call<WsResponseAcceptRequest> acceptRequest(@Url String url);

    @GET
    Call<WsResponseRefuseRequest> refuseRequest(@Url String url);

    @GET
    Call<WsResponseAvailableDateHour> listAvailableDateHour(@Url String url);

    @GET
    Call<WsResponseReportes> listReports(@Url String url);

    @GET
    Call<WsResponseServices> listServices(@Url String url);

    @GET
    Call<WsResponseServices> listUserServices(@Url String url);

    @POST(Constants.URLS.ADD_SERVICE)
    Call<WsResponseAgregarServicio> addService(@Body List<WsParameterAgregarServicio> wsParameterAgregarServicios);


    @POST(Constants.URLS.ADD_DATE_AVAILABLE)
    Call<WsResponseAgregarDateAvailable> addDateAvailable(@Body WsParameterAgregarDateAvailable wsParameterAgregarDateAvailable);

    @POST(Constants.URLS.ADD_USER)
    Call<WsResponseAgregarUsuario> addUser(@Body WsParameterAgregarUsuario wsParameterAgregarUsuario);


    @POST(Constants.URLS.ADD_BANK_ACCOUNT)
    Call<WsResponseAddBankAccount> addBankAccount(@Body WsParameterAddBankAccount wsParameterAddBankAccount);

    @GET
    Call<WsResponseListBankAccount> listBankAccount(@Url String url);

    @POST(Constants.URLS.ADD_SUB_SERVICE)
    Call<WsResponseAddSubService> addSubService(@Body List<WsParameterAddSubService> wsParameterAddSubServices);

    @PUT(Constants.URLS.ADD_USER)
    Call<WsResponseEditarUsuario> editUser(@Body WsParameterEditarUsuario wsParameterEditarUsuario);

    @GET
    Call<WsResponseListLocales> listLocales(@Url String url);

    @POST(Constants.URLS.ADD_LOCAL)
    Call<WsResponseAgregarLocal> addLocal(@Body WsParameterAgregarLocal wsParameterAgregarLocal);

    @POST(Constants.URLS.RECOVERY_PASSWORD)
    Call<WsResponseRecoveryPassword> recoveryPassword(@Body WsParameterRecoveryPassword wsParameterRecoveryPassword);

}
