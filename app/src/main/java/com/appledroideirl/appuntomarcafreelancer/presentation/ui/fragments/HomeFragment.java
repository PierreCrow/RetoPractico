package com.appledroideirl.appuntomarcafreelancer.presentation.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appledroideirl.appuntomarcafreelancer.R;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsDataRequest;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsDataService;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsDataSubService;
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
import com.appledroideirl.appuntomarcafreelancer.domain.model.SubService;
import com.appledroideirl.appuntomarcafreelancer.domain.model.Usuario;
import com.appledroideirl.appuntomarcafreelancer.presentation.presenter.UserPresenter;
import com.appledroideirl.appuntomarcafreelancer.presentation.ui.activities.PedidosActivity;
import com.appledroideirl.appuntomarcafreelancer.presentation.ui.activities.TuDisponibilidadActivity;
import com.appledroideirl.appuntomarcafreelancer.presentation.ui.adapters.ServiciosHomeListDataAdapter;
import com.appledroideirl.appuntomarcafreelancer.presentation.ui.dialogfragment.AgregarServicioDialog;
import com.appledroideirl.appuntomarcafreelancer.presentation.ui.dialogfragment.AjustesServicioDialog;
import com.appledroideirl.appuntomarcafreelancer.presentation.utils.Constants;
import com.appledroideirl.appuntomarcafreelancer.presentation.utils.Helper;
import com.appledroideirl.appuntomarcafreelancer.presentation.utils.TransparentProgressDialog;
import com.appledroideirl.appuntomarcafreelancer.presentation.view.UserView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class HomeFragment extends BaseFragment
        implements UserView, ServiciosHomeListDataAdapter.OnServiciosHomeListDataAdapterClickListener {

    @BindView(R.id.llPedidos)
    LinearLayout llPedidos;
    @BindView(R.id.llDisponibilidad)
    CardView llDisponibilidad;
    @BindView(R.id.llservicio)
    LinearLayout llservicio;
    @BindView(R.id.llagregarservicio)
    LinearLayout llagregarservicio;
    @BindView(R.id.tvTotalSolicitudesPendientes)
    TextView tvTotalSolicitudesPendientes;
    @BindView(R.id.tvUserName)
    TextView tvUserName;
    @BindView(R.id.rv_services)
    RecyclerView rv_services;

    TransparentProgressDialog loading;
    UserPresenter userPresenter;
    List<WsDataRequest> solicitudes;

    ServiciosHomeListDataAdapter.OnServiciosHomeListDataAdapterClickListener mlistener;
    ServiciosHomeListDataAdapter adapter;
    public static List<Service> services;
    public static List<Service> userServices;

    public static Service serviceSelected;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View x = inflater.inflate(R.layout.appu_home_fragment, null);
        injectView(x);

        mlistener = this;

        String hola="";
        hola.equals("");

        Usuario client = Helper.getUserAppPreference(getContext());

        if (client.isLogged()) {
            String nombre = "";
            int firstSpace = client.getFull_name().indexOf(" "); // detect the first space character
            if(firstSpace==-1)
            {
                 nombre = client.getFull_name();
            }
            else
            {
                 nombre = client.getFull_name().substring(0, firstSpace);
            }
            tvUserName.setText("Hola " + nombre);
        } else {
            tvUserName.setText("Hola");
        }

        loading = new TransparentProgressDialog(getContext());

        loadPresenter();

        llPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                next(PedidosActivity.class, getContext(), null);

            }
        });

        llDisponibilidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                next(TuDisponibilidadActivity.class, getContext(), null);

            }
        });

        llservicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AjustesServicioDialog df = new AjustesServicioDialog();
                df.show(getFragmentManager(), "");

            }
        });

        llagregarservicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AgregarServicioDialog df = new AgregarServicioDialog();
                df.show(getFragmentManager(), "");

            }
        });
        //  cargaTofdo();


        return x;
    }

    void loadPresenter() {
        userPresenter = new UserPresenter();
        userPresenter.addView(this);
        if (!loading.isShowing()) {
            loading.show();
        }
        String token = Helper.getUserAppPreference(getContext()).getToken();
        userPresenter.requestList(token, Helper.getUserAppPreference(getContext()).getId());
    }

    void showSubServices() {
        AjustesServicioDialog df = new AjustesServicioDialog();
        df.show(getFragmentManager(), "");
    }


    @Override
    public void tokenGenerated(String token) {

    }

    @Override
    public void loginSuccess(WsResponseLogin wsResponseLogin) {

    }

    @Override
    public void requestListSuccess(WsResponseRequestList wsResponseRequestList) {

        Integer solicitudesPendientes = 0;

        solicitudes = wsResponseRequestList.getWsDataRequests();

        for (WsDataRequest wsDataRequest : solicitudes) {
            if (wsDataRequest.getStatus_sale() == Constants.SOLICITUD_TYPES.PENDIENTE) {
                solicitudesPendientes++;
            }
        }

        tvTotalSolicitudesPendientes.setText("Tienes " + solicitudesPendientes.toString() + " pedidos pendientes de aprobación");

        userPresenter.listUserServices(Helper.getUserAppPreference(getContext()).getToken(), Helper.getUserAppPreference(getContext()).getId());

        userPresenter.listServices(Helper.getUserAppPreference(getContext()).getToken());

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

        services = new ArrayList<>();

        for (WsDataService wsDataServices : wsResponseServices.getWsDataServices()) {
            Service service = new Service(wsDataServices.getId(), wsDataServices.getColor(), wsDataServices.getFile_image(),
                    wsDataServices.getFull_name(), wsDataServices.getEnable(), null);
            services.add(service);
        }
    }

    @Override
    public void listUserServices(WsResponseServices wsResponseServices) {

        if (loading.isShowing()) {
            loading.dismiss();
        }

        userServices = new ArrayList<>();

        for (WsDataService wsDataServices : wsResponseServices.getWsDataServices()) {
            if (wsDataServices.getEnable() == 1) {


                List<SubService> subServices = new ArrayList<>();

                for (WsDataSubService wsDataSubService : wsDataServices.getWsDataSubServices()) {

                    Double chargeeee = wsDataSubService.getCharge();

                    if(chargeeee==null)
                    {
                        chargeeee=0.0;
                    }

                    SubService subService = new SubService(wsDataSubService.getId(), wsDataSubService.getId_service(),
                            chargeeee.toString(), wsDataSubService.getIn_filter(),
                            wsDataSubService.getFull_name(), wsDataSubService.getEnable());
                    subServices.add(subService);
                }


                Service service = new Service(wsDataServices.getId(), wsDataServices.getColor(), wsDataServices.getFile_image(),
                        wsDataServices.getFull_name(), wsDataServices.getEnable(), subServices);
                userServices.add(service);
            }
        }

        Service unoMas = new Service(999, "", "NUEVO",
                "", 0, null);
        userServices.add(unoMas);

        adapter = new ServiciosHomeListDataAdapter(mlistener, getContext(), userServices, true);
        rv_services.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 3);
        rv_services.setLayoutManager(mLayoutManager);
        rv_services.setAdapter(adapter);

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
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public void onServiciosHomeListDataAdapterClicked(View v, Integer position) {

        String esNuevo = userServices.get(position).getFile_image();
        serviceSelected = userServices.get(position);

        if (esNuevo.equals("NUEVO")) {
            AgregarServicioDialog df = new AgregarServicioDialog();
            df.show(getFragmentManager(), "");
        } else {
            showSubServices();
        }


    }
}