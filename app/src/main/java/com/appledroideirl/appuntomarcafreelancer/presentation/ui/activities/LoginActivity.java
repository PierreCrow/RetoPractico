package com.appledroideirl.appuntomarcafreelancer.presentation.ui.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appledroideirl.appuntomarcafreelancer.R;

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
import com.appledroideirl.appuntomarcafreelancer.presentation.ui.dialogfragment.ForgotPasswordDialog;
import com.appledroideirl.appuntomarcafreelancer.presentation.ui.dialogfragment.NoEntryLoginDialog;
import com.appledroideirl.appuntomarcafreelancer.presentation.utils.Helper;
import com.appledroideirl.appuntomarcafreelancer.presentation.utils.SingleClick;
import com.appledroideirl.appuntomarcafreelancer.presentation.utils.TransparentProgressDialog;
import com.appledroideirl.appuntomarcafreelancer.presentation.view.UserView;

import butterknife.BindView;

public class LoginActivity extends BaseActivity implements UserView {

    TransparentProgressDialog loading;
    SingleClick singleClick;

    @BindView(R.id.btn_login)
    TextView btn_login;

    @BindView(R.id.tvCrearCuenta)
    TextView tvCrearCuenta;

    @BindView(R.id.tvemail)
    EditText tvemail;

    @BindView(R.id.tvpass)
    EditText tvpass;

    @BindView(R.id.ivPass)
    ImageView ivPass;

    @BindView(R.id.tvForgotPassword)
    TextView tvForgotPassword;


    UserPresenter userPresenter;
    Usuario usuario;
    boolean passView = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appu_login_activity);
        injectView();
        initUI();
        loadPresenter();

    }

    void loadPresenter()
    {
        userPresenter= new UserPresenter();
        userPresenter.addView(this);
    }

    void initUI() {
        onClickListener();
        btn_login.setOnClickListener(singleClick);
        tvCrearCuenta.setOnClickListener(singleClick);
        tvForgotPassword.setOnClickListener(singleClick);
        ivPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickViewPass();
            }
        });
        usuario= Helper.getUserAppPreference(getContext());
        loading = new TransparentProgressDialog(getContext());
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    private void onClickListener() {
        singleClick = new SingleClick() {
            @Override
            public void onSingleClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_login:
                        if (!loading.isShowing()) {
                            loading.show();
                        }
                        String emaill=tvemail.getText().toString().replace(" ","");
                        String passss=tvpass.getText().toString().replace(" ","");




                        SharedPreferences preferenciasssee = getApplicationContext().getSharedPreferences("FCM", Context.MODE_PRIVATE);
                        String holaaatoken=preferenciasssee.getString("tokenfcm","");
                        userPresenter.login(Helper.getUserAppPreference(getContext()).getToken(),holaaatoken,emaill,passss);
                        break;
                    case R.id.tvCrearCuenta:
                        next(CuentanosEresActivity.class,null);
                        break;
                    case R.id.tvForgotPassword:
                        showOlvidarContraseña();
                        break;
                }
            }
        };
    }

    void showOlvidarContraseña() {
        ForgotPasswordDialog df = new ForgotPasswordDialog();
        df.show(getSupportFragmentManager(), "");
    }

    void clickViewPass() {
        if (!passView) {
            tvpass.setTransformationMethod(null);
            passView = true;
        } else {
            tvpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
            passView = false;
        }
    }


    @Override
    public void onPause() {
        super.onPause();
    }


    @Override
    public void tokenGenerated(String token) {

    }

    @Override
    public void loginSuccess(WsResponseLogin wsResponseLogin) {
        if (loading.isShowing()) {
            loading.dismiss();
        }


            SharedPreferences preferenciasssee = getApplicationContext().getSharedPreferences("FCM", Context.MODE_PRIVATE);
            String holaaatoken=preferenciasssee.getString("tokenfcm","");
            usuario.setFcm(holaaatoken);
            usuario.setPassword(tvpass.getText().toString());
            usuario.setLogged(true);
            //  usuario.setUser();
            usuario.setId(wsResponseLogin.getWsDataUser().getId());
            usuario.setFull_name(wsResponseLogin.getWsDataUser().getFull_name());
            usuario.setMail(wsResponseLogin.getWsDataUser().getMail());
            usuario.setAbout(wsResponseLogin.getWsDataUser().getAbout());
            usuario.setPhoto(wsResponseLogin.getWsDataUser().getPhoto());
            usuario.setType_user(wsResponseLogin.getWsDataUser().getType_user());
            usuario.setId_type_document(wsResponseLogin.getWsDataUser().getId_type_document());
            usuario.setDocument_number(wsResponseLogin.getWsDataUser().getDocument_number());
            usuario.setCellphone(wsResponseLogin.getWsDataUser().getCellphone());


            if(wsResponseLogin.getWsDataUser().getAvg_rate()!=null)
            {
                usuario.setAvg_rate(wsResponseLogin.getWsDataUser().getAvg_rate());
            }


            Helper.saveUserAppPreference(getApplicationContext(),usuario);
            next(MainActivity.class,null);
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
    public void deleteLocalSuccess(String mensaje) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showErrorMessage(String message) {
        if (loading.isShowing()) {
            loading.dismiss();
        }
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public Context getContext() {
        return this;
    }
}