package com.appledroideirl.appuntomarcafreelancer.presentation.ui.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appledroideirl.appuntomarcafreelancer.R;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsDataRequest;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsDataSale;
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
import com.appledroideirl.appuntomarcafreelancer.domain.model.Pedido;
import com.appledroideirl.appuntomarcafreelancer.presentation.presenter.UserPresenter;
import com.appledroideirl.appuntomarcafreelancer.presentation.ui.activities.MainActivity;
import com.appledroideirl.appuntomarcafreelancer.presentation.ui.adapters.PedidosListDataAdapter;
import com.appledroideirl.appuntomarcafreelancer.presentation.utils.Constants;
import com.appledroideirl.appuntomarcafreelancer.presentation.utils.Helper;
import com.appledroideirl.appuntomarcafreelancer.presentation.utils.TransparentProgressDialog;
import com.appledroideirl.appuntomarcafreelancer.presentation.view.UserView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;

public class PedidosFragment extends BaseFragment
        implements PedidosListDataAdapter.OnPedidosListDataAdapterClickListener, UserView {


    @BindView(R.id.ivClose)
    ImageView ivClose;

    @BindView(R.id.rv_pedidos_pendientes)
    RecyclerView rv_pedidos_pendientes;

    @BindView(R.id.rv_pedidos_aprobados)
    RecyclerView rv_pedidos_aprobados;

    @BindView(R.id.rv_pedidos_rechazados)
    RecyclerView rv_pedidos_rechazados;

    @BindView(R.id.rv_pedidos_pagados)
    RecyclerView rv_pedidos_pagados;

    @BindView(R.id.tvPedidosAprobados)
    TextView tvPedidosAprobados;

    @BindView(R.id.tvPedidosPendientes)
    TextView tvPedidosPendientes;

    @BindView(R.id.tvPedidosRechazados)
    TextView tvPedidosRechazados;

    @BindView(R.id.tvPedidosPagados)
    TextView tvPedidosPagados;


    PedidosListDataAdapter adapterAprobados;
    PedidosListDataAdapter adapterPendientes;
    PedidosListDataAdapter adapterRechazados;
    PedidosListDataAdapter adapterPagados;
    PedidosListDataAdapter.OnPedidosListDataAdapterClickListener mlistener;

    List<Pedido> pedidosAprobados;

    public static List<WsDataRequest> requests;

    List<WsDataRequest> requestsAcepted;
    List<WsDataRequest> requestsPending;
    List<WsDataRequest> requestsRechazados;
    List<WsDataRequest> requestsPagados;

    UserPresenter userPresenter;
    TransparentProgressDialog loading;

    public static String mensajeAceptarDenegar = "";


    LinearLayoutManager linearLayoutManager,linearLayoutManager1,linearLayoutManager3,linearLayoutManager4;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View x = inflater.inflate(R.layout.appu_pedidos_fragment, null);
        injectView(x);
        initUi();
        if(Helper.isConnectedToInternet(getContext()))
        {
            loadPresenter();
        }
        else
        {
           // Toast.makeText(getContext(), "No tienes Internet", Toast.LENGTH_LONG).show();
        }


        return x;
    }

    void loadPresenter() {
        userPresenter = new UserPresenter();
        userPresenter.addView(this);


        String token = Helper.getUserAppPreference(getContext()).getToken();
        userPresenter.requestList(token, Helper.getUserAppPreference(getContext()).getId());
    }

    void showRequest() {


        requestsAcepted = new ArrayList<>();
        requestsPending = new ArrayList<>();
        requestsRechazados = new ArrayList<>();
        requestsPagados = new ArrayList<>();

        for (WsDataRequest wsDataRequest : requests) {
            if (wsDataRequest.getStatus_sale() == Constants.SOLICITUD_TYPES.ACEPTADO_POR_USUARIO) {
                requestsAcepted.add(wsDataRequest);
            } else {
                if (wsDataRequest.getStatus_sale() == Constants.SOLICITUD_TYPES.PENDIENTE) {
                    requestsPending.add(wsDataRequest);
                } else {
                    if (wsDataRequest.getStatus_sale() == Constants.SOLICITUD_TYPES.RECHAZADO_POR_USUARIO) {
                        requestsRechazados.add(wsDataRequest);
                    } else {
                        if (wsDataRequest.getStatus_sale() == Constants.SOLICITUD_TYPES.PAGADO_POR_CLIENTE) {
                            requestsPagados.add(wsDataRequest);
                        }
                    }
                }
            }
        }

        adapterAprobados = new PedidosListDataAdapter(mlistener, getContext(), requestsAcepted);
        adapterPendientes = new PedidosListDataAdapter(mlistener, getContext(), requestsPending);
        adapterRechazados = new PedidosListDataAdapter(mlistener, getContext(), requestsRechazados);
        adapterPagados = new PedidosListDataAdapter(mlistener, getContext(), requestsPagados);

        if (requestsAcepted.size() > 0) {
            tvPedidosAprobados.setVisibility(View.VISIBLE);
        }

        if (requestsPending.size() > 0) {
            tvPedidosPendientes.setVisibility(View.VISIBLE);
        }

        if (requestsRechazados.size() > 0) {
            tvPedidosRechazados.setVisibility(View.VISIBLE);
        }

        if (requestsPagados.size() > 0) {
            tvPedidosPagados.setVisibility(View.VISIBLE);
        }

        linearLayoutManager = new LinearLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };


        linearLayoutManager1 = new LinearLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };

        linearLayoutManager3 = new LinearLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };

        linearLayoutManager4 = new LinearLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };


        rv_pedidos_pendientes.setLayoutManager(linearLayoutManager);
        rv_pedidos_pendientes.setAdapter(adapterPendientes);
        rv_pedidos_pendientes.setHasFixedSize(true);

        rv_pedidos_aprobados.setLayoutManager(linearLayoutManager1);
        rv_pedidos_aprobados.setAdapter(adapterAprobados);
        rv_pedidos_aprobados.setHasFixedSize(true);

        rv_pedidos_rechazados.setLayoutManager(linearLayoutManager3);
        rv_pedidos_rechazados.setAdapter(adapterRechazados);
        rv_pedidos_rechazados.setHasFixedSize(true);

        rv_pedidos_pagados.setLayoutManager(linearLayoutManager4);
        rv_pedidos_pagados.setAdapter(adapterPagados);
        rv_pedidos_pagados.setHasFixedSize(true);



    }

    void initUi() {

        mlistener = this;
        loading = new TransparentProgressDialog(getContext());

    }

    void showAlert(Integer idRequest,String nombreCustomer,String fecha, String action) {
        String texto = "";
        String textomayuscula = "";
        if (action.equals("ACEPT")) {
            texto = "aprobar";
            textomayuscula = "Aprobar";
        } else {
            texto = "denegar";
            textomayuscula = "Denegar";
        }
        new AlertDialog.Builder(getContext(), R.style.AlertDialogCustom)
                .setTitle(textomayuscula + " solicitud")
                .setMessage("¿Esta seguro que desea " + texto + " la solicitud de " + nombreCustomer+" para el día "+fecha)

                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (!loading.isShowing()) {
                            loading.show();
                        }
                        if (action.equals("REFUSE")) {
                            if (!loading.isShowing()) {
                                loading.show();
                            }
                            userPresenter.refuseRequest(Helper.getUserAppPreference(getContext()).getToken(), idRequest);
                        } else {
                            if (action.equals("ACEPT")) {
                                if (!loading.isShowing()) {
                                    loading.show();
                                }
                                userPresenter.aceptRequest(Helper.getUserAppPreference(getContext()).getToken(), idRequest);
                            }
                        }
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                //  .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }



    @Override
    public void onPedidosListDataAdapterClicked(View v, Integer position, String Action,WsDataRequest wsDataRequest) {

        Integer idRequest = wsDataRequest.getId();
        String solicitado =wsDataRequest.getFull_name_customer();
        String fecha= wsDataRequest.getDate_availability();

        showAlert(idRequest,solicitado,fecha,Action);
    }

    @Override
    public void tokenGenerated(String token) {

    }

    @Override
    public void loginSuccess(WsResponseLogin wsResponseLogin) {

    }

    @Override
    public void requestListSuccess(WsResponseRequestList wsResponseRequestList) {

        requests = wsResponseRequestList.getWsDataRequests();

        if(requests.size()==0)
        {
           // Toast.makeText(getContext(), "No tiene pedidos", Toast.LENGTH_SHORT).show();
        }

        showRequest();


        if (loading.isShowing()) {
            loading.dismiss();
        }

        if (mensajeAceptarDenegar.equals("")) {
        } else {
            Toast.makeText(getContext(), mensajeAceptarDenegar, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void requestAceptedSuccess(WsResponseAcceptRequest wsResponseAcceptRequest) {
        String token = Helper.getUserAppPreference(getContext()).getToken();
        userPresenter.requestList(token, Helper.getUserAppPreference(getContext()).getId());
        mensajeAceptarDenegar = "Solicitud Aceptada";
    }

    @Override
    public void requestRefusedSuccess(WsResponseRefuseRequest responseRefuseRequest) {
        String token = Helper.getUserAppPreference(getContext()).getToken();
        userPresenter.requestList(token, Helper.getUserAppPreference(getContext()).getId());
        mensajeAceptarDenegar = "Solicitud Denegada";
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
}
