package com.appledroideirl.appuntomarcafreelancer.presentation.ui.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.appledroideirl.appuntomarcafreelancer.R;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterAddBankAccount;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsDataAddBankAccount;
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
import com.appledroideirl.appuntomarcafreelancer.presentation.presenter.UserPresenter;
import com.appledroideirl.appuntomarcafreelancer.presentation.utils.Helper;
import com.appledroideirl.appuntomarcafreelancer.presentation.utils.TransparentProgressDialog;
import com.appledroideirl.appuntomarcafreelancer.presentation.view.UserView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MiCuentaBancariaActivity extends BaseActivity implements UserView {

    @BindView(R.id.ivClose)
    ImageView ivClose;

    @BindView(R.id.ivContinue)
    ImageView ivContinue;

    @BindView(R.id.spiBanks)
    Spinner spiBanks;

    @BindView(R.id.etNumberAccount)
    EditText etNumberAccount;

    @BindView(R.id.etCCI)
    EditText etCCI;


    UserPresenter userPresenter;
    TransparentProgressDialog loading;

    boolean newCuentaBancaria = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appu_mi_cuenta_bancaria);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        injectView();
        initUi();
    }

    void loadPresenter() {
        if (!loading.isShowing()) {
            loading.show();
        }

        userPresenter = new UserPresenter();
        userPresenter.addView(this);
        userPresenter.listBankAccount(Helper.getUserAppPreference(getContext()).getToken(), Helper.getUserAppPreference(getContext()).getId());
    }

    void initUi() {

        SeteaSpinner(spiBanks, getApplicationContext());
        loading = new TransparentProgressDialog(getContext());

        loadPresenter();


        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        ivContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!loading.isShowing()) {
                    loading.show();
                }

                WsParameterAddBankAccount wsParameterAddBankAccount = new WsParameterAddBankAccount();
                wsParameterAddBankAccount.setAccount_number(etNumberAccount.getText().toString());
                wsParameterAddBankAccount.setCci(etCCI.getText().toString());
                wsParameterAddBankAccount.setId_bank(spiBanks.getSelectedItemPosition());
                wsParameterAddBankAccount.setId_user(Helper.getUserAppPreference(getContext()).getId());

                userPresenter.addBankAccount(Helper.getUserAppPreference(getContext()).getToken(), wsParameterAddBankAccount);

            }
        });

    }


    void SeteaSpinner(Spinner spiner, Context ctx) {
        //   final List<String> almacenamientos = new ArrayList<>(Arrays.asList(Constantes.NATIONALITIES));

        List<String> lista = new ArrayList<>();
        lista.add("Entidad Bancaria");
        lista.add("BCP");//1
        lista.add("Interbank");//2
        lista.add("BBVA");//3
        lista.add("Continental");//4
        lista.add("Scotia Bank");//5

        final ArrayAdapter<String> spinnerArrayAdapter1 = new ArrayAdapter<String>(
                ctx, R.layout.spineritem, lista) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                return view;
            }
        };

        spinnerArrayAdapter1.setDropDownViewResource(R.layout.spineritem);
        spiner.setAdapter(spinnerArrayAdapter1);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void tokenGenerated(String token) {

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
        if (loading.isShowing()) {
            loading.dismiss();
        }

        if (newCuentaBancaria) {
            Toast.makeText(getContext(), "Cuenta registrada corréctamente", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(getContext(), "Cuenta actualizada", Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void listBankAccount(WsResponseListBankAccount wsResponseListBankAccount) {

        if (loading.isShowing()) {
            loading.dismiss();
        }

        if (wsResponseListBankAccount.getWsDataAddBankAccounts().size() == 0) {
            newCuentaBancaria = true;
        } else {
            WsDataAddBankAccount wsDataAddBankAccount = wsResponseListBankAccount.getWsDataAddBankAccounts().get(0);
            etNumberAccount.setText(wsDataAddBankAccount.getAccount_number());
            etCCI.setText(wsDataAddBankAccount.getCci());
            Integer posicionBanco = wsDataAddBankAccount.getId_bank();
            spiBanks.setSelection(posicionBanco);
            newCuentaBancaria = false;
        }

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