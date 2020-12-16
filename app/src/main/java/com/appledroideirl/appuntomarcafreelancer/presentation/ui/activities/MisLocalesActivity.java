package com.appledroideirl.appuntomarcafreelancer.presentation.ui.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appledroideirl.appuntomarcafreelancer.R;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsDataLocales;
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
import com.appledroideirl.appuntomarcafreelancer.interactor.usuario.ListLocalesCallback;
import com.appledroideirl.appuntomarcafreelancer.presentation.presenter.UserPresenter;
import com.appledroideirl.appuntomarcafreelancer.presentation.ui.adapters.LocalesListDataAdapter;
import com.appledroideirl.appuntomarcafreelancer.presentation.utils.Helper;
import com.appledroideirl.appuntomarcafreelancer.presentation.utils.TransparentProgressDialog;
import com.appledroideirl.appuntomarcafreelancer.presentation.view.UserView;

import java.util.List;

import butterknife.BindView;

public class MisLocalesActivity extends BaseActivity implements LocalesListDataAdapter.OnLocalesListDataAdapterClickListener, UserView {

    @BindView(R.id.ivClose)
    ImageView ivClose;

    @BindView(R.id.llAgregarLocal)
    LinearLayout llAgregarLocal;

    @BindView(R.id.rvLocales)
    RecyclerView rvLocales;

    @BindView(R.id.tvTittle)
    TextView tvTittle;

    @BindView(R.id.tvAgregarNueva)
    TextView tvAgregarNueva;


    WsResponseListLocales wsResponseListLocaless;



    TransparentProgressDialog loading;

    LocalesListDataAdapter.OnLocalesListDataAdapterClickListener mlistener;
    LocalesListDataAdapter adapter;

    UserPresenter userPresenter;

    List<WsDataLocales> wsDataLocales;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appu_mis_locales);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        injectView();
        if(Helper.isConnectedToInternet(getContext()))
        {
            loasPresenter();
        }
        else
        {
            Toast.makeText(getContext(), "No tienes Internet", Toast.LENGTH_LONG).show();
        }
        initUi();
    }

    void loasPresenter()
    {
        mlistener=this;

        userPresenter= new UserPresenter();
        userPresenter.addView(this);

        loading= new TransparentProgressDialog(getContext());
        if (!loading.isShowing()) {
            loading.show();
        }

        userPresenter.listLocales(Helper.getUserAppPreference(getContext()).getToken(),Helper.getUserAppPreference(getContext()).getId());
    }

    void initUi() {

        if (Helper.getUserAppPreference(getContext()).getType_user().equals("FREELANCER")) {
            tvTittle.setText("Mis direcciones");
            tvAgregarNueva.setText("Agregar nueva dirección");
        }
        else
        {
            tvTittle.setText("Mis locales");
            tvAgregarNueva.setText("Agregar nuevo local");
        }


        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        llAgregarLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                next(AgregarLocalActivity.class, null);

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onLocalesListDataAdapterClicked(View v, Integer position,boolean eliminar) {

        if(eliminar)
        {
            showAlert(wsResponseListLocaless.getWsDataLocales().get(position).getFull_name(),position);
        }

    }

    void showAlert(String nombreLocal,int posicion) {
        new AlertDialog.Builder(getContext(), R.style.AlertDialogCustom)
                .setTitle("Eliminar dirección")
                .setMessage("¿Esta seguro que desea eliminar la dirección : " + nombreLocal + "?")

                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (!loading.isShowing()) {
                            loading.show();
                        }

                        userPresenter.deleteLocal(Helper.getUserAppPreference(getContext()).getToken(),wsResponseListLocaless.getWsDataLocales().get(posicion).getId());
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                //  .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
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

        if (loading.isShowing()) {
            loading.dismiss();
        }

        wsResponseListLocaless=wsResponseListLocales;

        adapter = new LocalesListDataAdapter(mlistener, getContext(), wsResponseListLocales.getWsDataLocales());
        rvLocales.setHasFixedSize(true);
        rvLocales.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvLocales.setAdapter(adapter);

       // DividerItemDecoration DividerItemDecoration= new DividerItemDecoration(getContext(), androidx.recyclerview.widget.DividerItemDecoration.VERTICAL);

        int[] ATTRS = new int[]{android.R.attr.listDivider};

        TypedArray a = getContext().obtainStyledAttributes(ATTRS);
        Drawable divider = a.getDrawable(0);
        int inset = getResources().getDimensionPixelSize(R.dimen.marginrecyclwe);
        InsetDrawable insetDivider = new InsetDrawable(divider, inset, 0, inset, 0);
        a.recycle();

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(insetDivider);

        rvLocales.addItemDecoration(itemDecoration);

    }

    @Override
    public void localAddedSuccess(WsResponseAgregarLocal wsResponseAgregarLocal) {

    }

    @Override
    public void recoveryPasswordSuccess(String mensaje) {

    }

    @Override
    public void deleteLocalSuccess(String mensaje) {

        userPresenter.listLocales(Helper.getUserAppPreference(getContext()).getToken(),Helper.getUserAppPreference(getContext()).getId());


        Toast.makeText(getContext(), mensaje, Toast.LENGTH_LONG).show();

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