package com.appledroideirl.appuntomarcafreelancer.presentation.ui.dialogfragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appledroideirl.appuntomarcafreelancer.R;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterAgregarServicio;
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
import com.appledroideirl.appuntomarcafreelancer.domain.model.Service;
import com.appledroideirl.appuntomarcafreelancer.presentation.presenter.UserPresenter;
import com.appledroideirl.appuntomarcafreelancer.presentation.ui.activities.MainActivity;
import com.appledroideirl.appuntomarcafreelancer.presentation.ui.adapters.ServiciosDialogListDataAdapter;
import com.appledroideirl.appuntomarcafreelancer.presentation.ui.adapters.ServiciosHomeListDataAdapter;
import com.appledroideirl.appuntomarcafreelancer.presentation.ui.fragments.HomeFragment;
import com.appledroideirl.appuntomarcafreelancer.presentation.utils.Helper;
import com.appledroideirl.appuntomarcafreelancer.presentation.utils.TransparentProgressDialog;
import com.appledroideirl.appuntomarcafreelancer.presentation.view.UserView;

import java.util.ArrayList;
import java.util.List;

public class AgregarServicioDialog extends DialogFragment
        implements ServiciosDialogListDataAdapter.OnServiciosDialogListDataAdapterClickListener, UserView {

    ImageView ivClose, ivContinue;
    RecyclerView rv_servicios_dialog;

    ServiciosDialogListDataAdapter.OnServiciosDialogListDataAdapterClickListener mlistener;
    ServiciosDialogListDataAdapter adapter;

    List<Service> servicesInUse;

    List<Service> servicesToShow;

    UserPresenter userPresenter;

    List<WsParameterAgregarServicio> servicioss;

    TransparentProgressDialog loading;

    @Override
    public void onActivityCreated(Bundle arg0) {
        super.onActivityCreated(arg0);
        getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.appu_agregar_servicio_dialog, new LinearLayout(getActivity()), false);


        mlistener = this;

        loading = new TransparentProgressDialog(getContext());

        userPresenter = new UserPresenter();
        userPresenter.addView(this);

        servicesToShow = HomeFragment.services;
        servicesInUse = HomeFragment.userServices;
        servicioss = new ArrayList<>();

        for (Service serviceInUse : servicesInUse) {
            for (Service service : servicesToShow) {
                if (serviceInUse.getId() == service.getId()) {
                    service.setEnable(1);
                }
            }
        }

        ivClose = (ImageView) view.findViewById(R.id.ivClose);
        ivContinue = (ImageView) view.findViewById(R.id.ivContinue);
        rv_servicios_dialog = (RecyclerView) view.findViewById(R.id.rv_servicios_dialog);
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dismiss();

            }
        });

        ivContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Helper.isConnectedToInternet(getContext()))
                {
                    showAlert();
                }
                else
                {
                    Toast.makeText(getContext(), "No tienes Internet", Toast.LENGTH_LONG).show();
                }
            }
        });

        adapter = new ServiciosDialogListDataAdapter(mlistener, getContext(), servicesToShow, true);
        rv_servicios_dialog.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 3);
        rv_servicios_dialog.setLayoutManager(mLayoutManager);
        rv_servicios_dialog.setAdapter(adapter);


        Dialog builder = new Dialog(getActivity());
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.setContentView(view);
        return builder;
    }

    void showAlert() {
        new AlertDialog.Builder(getContext(), R.style.AlertDialogCustom)
                .setTitle("Guardar servicios")
                .setMessage("¿Esta seguro que desea actualizar sus servicios?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (!loading.isShowing()) {
                            loading.show();
                        }
                        for (Service service : servicesToShow) {
                            WsParameterAgregarServicio wsParameterAgregarServicio = new WsParameterAgregarServicio();
                            wsParameterAgregarServicio.setId_service(service.getId());
                            wsParameterAgregarServicio.setEnable(service.getEnable());
                            wsParameterAgregarServicio.setId_user(Helper.getUserAppPreference(getContext()).getId());

                            servicioss.add(wsParameterAgregarServicio);
                        }

                        userPresenter.addService(Helper.getUserAppPreference(getContext()).getToken(), servicioss);
                    }
                })

                .setNegativeButton(android.R.string.no, null)
                //  .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }


    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(width, height);
            dialog.getWindow().getAttributes().alpha = 1f;
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        dismiss();
    }

    @Override
    public void onServiciosDialogListDataAdapterClicked(View v, Integer position, boolean add) {

        if (add) {
            servicesToShow.get(position).setEnable(1);
        } else {
            servicesToShow.get(position).setEnable(0);
        }

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

        if (loading.isShowing()) {
            loading.dismiss();
        }

        Toast.makeText(getContext(), "Se actualizaron sus servicios", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
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
}
