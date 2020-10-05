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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appledroideirl.appuntomarcafreelancer.R;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterAddSubService;
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
import com.appledroideirl.appuntomarcafreelancer.domain.model.SubService;
import com.appledroideirl.appuntomarcafreelancer.presentation.presenter.UserPresenter;
import com.appledroideirl.appuntomarcafreelancer.presentation.ui.activities.MainActivity;
import com.appledroideirl.appuntomarcafreelancer.presentation.ui.adapters.EscogeSubServicioListDataAdapter;
import com.appledroideirl.appuntomarcafreelancer.presentation.ui.adapters.ServiciosHomeListDataAdapter;
import com.appledroideirl.appuntomarcafreelancer.presentation.ui.fragments.HomeFragment;
import com.appledroideirl.appuntomarcafreelancer.presentation.utils.Helper;
import com.appledroideirl.appuntomarcafreelancer.presentation.utils.TransparentProgressDialog;
import com.appledroideirl.appuntomarcafreelancer.presentation.view.UserView;

import java.util.ArrayList;
import java.util.List;

public class AjustesServicioDialog extends DialogFragment
        implements EscogeSubServicioListDataAdapter.OnEscogeSubServicioListDataAdapterClickListener, UserView {

    ImageView ivClose, ivGuardar;
    LinearLayout transparent_linear_filter;
    TextView tvServiceName;
    RecyclerView rvSubServices;
    CheckBox chbEscogeTodo;

    EscogeSubServicioListDataAdapter.OnEscogeSubServicioListDataAdapterClickListener mlistener;
    EscogeSubServicioListDataAdapter adapter;

    List<SubService> subServiciosTodosCheck;


    public static List<WsParameterAddSubService> subServicesToAdd;

    UserPresenter userPresenter;
    TransparentProgressDialog loading;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.appu_ajustes_servicio, new LinearLayout(getActivity()), false);
        ivClose = (ImageView) view.findViewById(R.id.ivClose);
        ivGuardar = (ImageView) view.findViewById(R.id.ivGuardar);
        rvSubServices = (RecyclerView) view.findViewById(R.id.rvSubServices);
        chbEscogeTodo = (CheckBox) view.findViewById(R.id.chbEscogeTodo);
        transparent_linear_filter = (LinearLayout) view.findViewById(R.id.transparent_linear_filter);
        tvServiceName = (TextView) view.findViewById(R.id.tvServiceName);

        loading = new TransparentProgressDialog(getContext());

        subServiciosTodosCheck = HomeFragment.serviceSelected.getSubServices();
        tvServiceName.setText(HomeFragment.serviceSelected.getFull_name());

        userPresenter = new UserPresenter();
        userPresenter.addView(this);

        mlistener = this;

        adapter = new EscogeSubServicioListDataAdapter(mlistener, getContext(), HomeFragment.serviceSelected.getSubServices());
        rvSubServices.setHasFixedSize(true);
        rvSubServices.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvSubServices.setAdapter(adapter);

        subServicesToAdd = new ArrayList<>();

        for (SubService subService : HomeFragment.serviceSelected.getSubServices()) {
            WsParameterAddSubService wsParameterAddSubService = new WsParameterAddSubService();
            wsParameterAddSubService.setCharge(Double.parseDouble(subService.getCharge()));
            wsParameterAddSubService.setEnable(subService.getEnable());
            wsParameterAddSubService.setId_service(subService.getIdService());
            wsParameterAddSubService.setId_sub_service(subService.getId());
            wsParameterAddSubService.setId_user(Helper.getUserAppPreference(getContext()).getId());

            subServicesToAdd.add(wsParameterAddSubService);
        }


        ivGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!loading.isShowing()) {
                    loading.show();
                }


                boolean esCorrecto = true;

                for (WsParameterAddSubService wsParameterAddSubService : subServicesToAdd) {
                    if (wsParameterAddSubService.getEnable() == 1 && wsParameterAddSubService.getCharge() == 0.0) {
                        esCorrecto = false;
                    }
                }
                if (esCorrecto) {
                    userPresenter.addSubService(Helper.getUserAppPreference(getContext()).getToken(), subServicesToAdd);
                } else {
                    if (loading.isShowing()) {
                        loading.dismiss();
                    }
                    Toast.makeText(getContext(), "Hay servicios habilitados sin precio", Toast.LENGTH_SHORT).show();
                }


            }
        });

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dismiss();

            }
        });
        transparent_linear_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dismiss();

            }
        });

        chbEscogeTodo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    for (SubService subService : subServiciosTodosCheck) {
                        subService.setEnable(1);
                    }
                    adapter = new EscogeSubServicioListDataAdapter(mlistener, getContext(), subServiciosTodosCheck);
                    rvSubServices.setHasFixedSize(true);
                    rvSubServices.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                    rvSubServices.setAdapter(adapter);

                } else {
                    adapter = new EscogeSubServicioListDataAdapter(mlistener, getContext(), HomeFragment.serviceSelected.getSubServices());
                    rvSubServices.setHasFixedSize(true);
                    rvSubServices.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                    rvSubServices.setAdapter(adapter);
                }


            }
        });


        Dialog builder = new Dialog(getActivity());
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.setContentView(view);
        return builder;
    }


    @Override
    public void onActivityCreated(Bundle arg0) {
        super.onActivityCreated(arg0);
        getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
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
    public void onEscogeSubServicioListDataAdapterClicked(View v, Integer position, boolean add, Double precio) {

        if (add) {
            subServicesToAdd.get(position).setEnable(1);
            subServicesToAdd.get(position).setCharge(precio);
        } else {
            subServicesToAdd.get(position).setEnable(0);
            subServicesToAdd.get(position).setCharge(precio);
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

        if (loading.isShowing()) {
            loading.dismiss();
        }

        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);

        Toast.makeText(getContext(), "Se actualizaron sus servicios", Toast.LENGTH_LONG).show();

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
}
