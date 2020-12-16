package com.appledroideirl.appuntomarcafreelancer.presentation.ui.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.appledroideirl.appuntomarcafreelancer.R;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterAgregarDateAvailable;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsDataAvailableDateHour;
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
import com.appledroideirl.appuntomarcafreelancer.presentation.ui.activities.MainActivity;
import com.appledroideirl.appuntomarcafreelancer.presentation.utils.Helper;
import com.appledroideirl.appuntomarcafreelancer.presentation.utils.SingleClick;
import com.appledroideirl.appuntomarcafreelancer.presentation.utils.TransparentProgressDialog;
import com.appledroideirl.appuntomarcafreelancer.presentation.view.UserView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.TimeZone;

import butterknife.BindView;

public class TabDisponibilidadOneFragment extends BaseFragment implements UserView {

    @BindView(R.id.calendarView)
    CalendarView calendarView;

    @BindView(R.id.tvTextFecha)
    TextView tvTextFecha;
    @BindView(R.id.swOcho)
    Switch swOcho;
    @BindView(R.id.swNueve)
    Switch swNueve;
    @BindView(R.id.swDiez)
    Switch swDiez;
    @BindView(R.id.swOnce)
    Switch swOnce;
    @BindView(R.id.swDoce)
    Switch swDoce;
    @BindView(R.id.swTrece)
    Switch swTrece;
    @BindView(R.id.swCatorce)
    Switch swCatorce;
    @BindView(R.id.swQuince)
    Switch swQuince;
    @BindView(R.id.swDiesiseis)
    Switch swDiesiseis;
    @BindView(R.id.swDiesisiete)
    Switch swDiesisiete;
    @BindView(R.id.swDiesiocho)
    Switch swDiesiocho;


    @BindView(R.id.swDiesinueve)
    Switch swDiesinueve;
    @BindView(R.id.swVeinte)
    Switch swVeinte;
    @BindView(R.id.swVeintiuno)
    Switch swVeintiuno;
    @BindView(R.id.swVeintidos)
    Switch swVeintidos;








    @BindView(R.id.ivClose)
    ImageView ivClose;

    @BindView(R.id.ivContinue)
    ImageView ivContinue;

    SingleClick singleClick;
    TransparentProgressDialog loading;


    List<WsDataAvailableDateHour> dateHours;
    UserPresenter userPresenter;
    String fechaasa;

    List<Integer> horasDisponibles;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View x = inflater.inflate(R.layout.appu_tab_disponibilidad_onel, null);
        injectView(x);
        initUI();
        if(Helper.isConnectedToInternet(getContext()))
        {
            loadPresenter();
        }
        else
        {
            Toast.makeText(getContext(), "No tienes Internet", Toast.LENGTH_LONG).show();
        }

        return x;
    }

    void initUI() {
        horasDisponibles = new ArrayList<>();
        loading = new TransparentProgressDialog(getContext());
        setDateClicked();
        swichCheckListener();
        onClickListener();
        ivContinue.setOnClickListener(singleClick);
        ivClose.setOnClickListener(singleClick);
    }

    void loadPresenter() {
        userPresenter = new UserPresenter();
        userPresenter.addView(this);
        if (!loading.isShowing()) {
            loading.show();
        }
        userPresenter.listAvailableDateHour(Helper.getUserAppPreference(getContext()).getToken(), Helper.getUserAppPreference(getContext()).getId());
    }


    private void onClickListener() {
        singleClick = new SingleClick() {
            @Override
            public void onSingleClick(View v) {
                switch (v.getId()) {
                    case R.id.ivContinue:
                        if(Helper.isConnectedToInternet(getContext()))
                        {
                            showAlert();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "No tienes Internet", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case R.id.ivClose:
                        getActivity().finish();
                        break;
                }
            }
        };
    }

    public  String deleteLast7Characts(String args) {
        String s = args;
        String resultado=s.substring(0, s.length() - 7);
        //   System.out.println(s.substring(0, s.length() - 7));

        return resultado;
    }

    void setDateClicked() {
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                Integer dia = dayOfMonth;
                Integer mes = month + 1;
                Integer anoo = year;

                String diaaa = "";
                if (dia < 10) {
                    diaaa = "0" + dia.toString();
                } else {
                    diaaa = dia.toString();
                }

                String mezzz = "";
                if (mes < 10) {
                    mezzz = "0" + mes.toString();
                } else {
                    mezzz = mes.toString();
                }

                fechaasa = diaaa + "-" + mezzz + "-" + anoo.toString();


                String finallll="";
                String date="";
                SimpleDateFormat dateFormat= new SimpleDateFormat("dd-MM-yyyy");
                try {
                    Date d=dateFormat.parse(fechaasa);
                    date=  DateFormat.getDateInstance(DateFormat.FULL).format(d);
                    String upperString = date.substring(0, 1).toUpperCase() + date.substring(1).toLowerCase();
                    finallll= deleteLast7Characts(upperString).replace(",","");
                }
                catch(Exception e) {
                }

                tvTextFecha.setText(finallll);


                horasDisponibles = new ArrayList<>();

                swOcho.setChecked(false);
                swNueve.setChecked(false);
                swDiez.setChecked(false);
                swOnce.setChecked(false);
                swDoce.setChecked(false);
                swTrece.setChecked(false);
                swCatorce.setChecked(false);
                swQuince.setChecked(false);
                swDiesiseis.setChecked(false);
                swDiesisiete.setChecked(false);
                swDiesiocho.setChecked(false);

                swDiesinueve.setChecked(false);
                swVeinte.setChecked(false);
                swVeintiuno.setChecked(false);
                swVeintidos.setChecked(false);

                for (WsDataAvailableDateHour fecha : dateHours) {
                    if (fechaasa.equals(fecha.getDate_availability())) {
                        for (Integer hora : fecha.getHours_availability()) {
                            if (hora == 8) {
                                swOcho.setChecked(true);
                                horasDisponibles.add(8);
                            }
                            if (hora == 9) {
                                swNueve.setChecked(true);
                                horasDisponibles.add(9);
                            }
                            if (hora == 10) {
                                swDiez.setChecked(true);
                                horasDisponibles.add(10);
                            }
                            if (hora == 11) {
                                swOnce.setChecked(true);
                                horasDisponibles.add(11);
                            }
                            if (hora == 12) {
                                swDoce.setChecked(true);
                                horasDisponibles.add(12);
                            }
                            if (hora == 13) {
                                swTrece.setChecked(true);
                                horasDisponibles.add(13);
                            }
                            if (hora == 14) {
                                swCatorce.setChecked(true);
                                horasDisponibles.add(14);
                            }
                            if (hora == 15) {
                                swQuince.setChecked(true);
                                horasDisponibles.add(15);
                            }
                            if (hora == 16) {
                                swDiesiseis.setChecked(true);
                                horasDisponibles.add(16);
                            }
                            if (hora == 17) {
                                swDiesisiete.setChecked(true);
                                horasDisponibles.add(17);
                            }
                            if (hora == 18) {
                                swDiesiocho.setChecked(true);
                                horasDisponibles.add(18);
                            }



                            if (hora == 19) {
                                swDiesinueve.setChecked(true);
                                horasDisponibles.add(19);
                            }

                            if (hora == 20) {
                                swVeinte.setChecked(true);
                                horasDisponibles.add(20);
                            }

                            if (hora == 21) {
                                swVeintiuno.setChecked(true);
                                horasDisponibles.add(21);
                            }

                            if (hora == 22) {
                                swVeintidos.setChecked(true);
                                horasDisponibles.add(22);
                            }
                        }

                    }
                }
                horasDisponibles = new ArrayList<Integer>(new LinkedHashSet<Integer>(horasDisponibles));

                if(horasDisponibles.size()==0)
                {
                    swOcho.setChecked(false);
                    swNueve.setChecked(false);
                    swDiez.setChecked(false);
                    swOnce.setChecked(false);
                    swDoce.setChecked(false);
                    swTrece.setChecked(false);
                    swCatorce.setChecked(false);
                    swQuince.setChecked(false);
                    swDiesiseis.setChecked(false);
                    swDiesisiete.setChecked(false);
                    swDiesiocho.setChecked(false);

                    swDiesinueve.setChecked(false);
                    swVeinte.setChecked(false);
                    swVeintiuno.setChecked(false);
                    swVeintidos.setChecked(false);
                }


            }
        });
    }


      void showAlert() {
        new AlertDialog.Builder(getContext(), R.style.AlertDialogCustom)
                .setTitle("Guardar disponibilidad")
                .setMessage("¿Esta seguro que desea guardar las horas indicados para el dia " + fechaasa + "?")

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation
                        if (!loading.isShowing()) {
                            loading.show();
                        }

                        WsParameterAgregarDateAvailable wsParameterAgregarDateAvailable= new WsParameterAgregarDateAvailable();
                        wsParameterAgregarDateAvailable.setId_user(Helper.getUserAppPreference(getContext()).getId());
                        wsParameterAgregarDateAvailable.setDate_availability(fechaasa);
                        wsParameterAgregarDateAvailable.setId_type_availability(1);
                        wsParameterAgregarDateAvailable.setFull_name("A Domicilio");
                        wsParameterAgregarDateAvailable.setHours_availability(horasDisponibles);

                        userPresenter.addDateAvailable(Helper.getUserAppPreference(getContext()).getToken(),wsParameterAgregarDateAvailable);
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                //  .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }


    void swichCheckListener() {

        swOcho.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    horasDisponibles.add(8);
                } else {
                    //si se desmarca
                    boolean exist = false;
                    for (int i = 0; i < horasDisponibles.size(); i++) {
                        if (horasDisponibles.get(i) == 8) {
                            horasDisponibles.remove(i);
                            break;
                        }
                    }
                }
               horasDisponibles = new ArrayList<Integer>(new LinkedHashSet<Integer>(horasDisponibles));
            }
        });


        swNueve.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    horasDisponibles.add(9);
                } else {
                    //si se desmarca
                    boolean exist = false;
                    for (int i = 0; i < horasDisponibles.size(); i++) {
                        if (horasDisponibles.get(i) == 9) {
                            horasDisponibles.remove(i);
                            break;
                        }
                    }
                }
                horasDisponibles = new ArrayList<Integer>(new LinkedHashSet<Integer>(horasDisponibles));
            }
        });

        swDiez.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    horasDisponibles.add(10);
                } else {
                    //si se desmarca
                    boolean exist = false;
                    for (int i = 0; i < horasDisponibles.size(); i++) {
                        if (horasDisponibles.get(i) == 10) {
                            horasDisponibles.remove(i);
                            break;
                        }
                    }
                }
                horasDisponibles = new ArrayList<Integer>(new LinkedHashSet<Integer>(horasDisponibles));
            }
        });

        swOnce.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    horasDisponibles.add(11);
                } else {
                    //si se desmarca
                    boolean exist = false;
                    for (int i = 0; i < horasDisponibles.size(); i++) {
                        if (horasDisponibles.get(i) == 11) {
                            horasDisponibles.remove(i);
                            break;
                        }
                    }
                }
                horasDisponibles = new ArrayList<Integer>(new LinkedHashSet<Integer>(horasDisponibles));
            }
        });

        swDoce.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    horasDisponibles.add(12);
                } else {
                    //si se desmarca
                    boolean exist = false;
                    for (int i = 0; i < horasDisponibles.size(); i++) {
                        if (horasDisponibles.get(i) == 12) {
                            horasDisponibles.remove(i);
                            break;
                        }
                    }
                }
                horasDisponibles = new ArrayList<Integer>(new LinkedHashSet<Integer>(horasDisponibles));
            }
        });

        swTrece.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    horasDisponibles.add(13);
                } else {
                    //si se desmarca
                    boolean exist = false;
                    for (int i = 0; i < horasDisponibles.size(); i++) {
                        if (horasDisponibles.get(i) == 13) {
                            horasDisponibles.remove(i);
                            break;
                        }
                    }
                }
                horasDisponibles = new ArrayList<Integer>(new LinkedHashSet<Integer>(horasDisponibles));
            }
        });

        swCatorce.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    horasDisponibles.add(14);
                } else {
                    //si se desmarca
                    boolean exist = false;
                    for (int i = 0; i < horasDisponibles.size(); i++) {
                        if (horasDisponibles.get(i) == 14) {
                            horasDisponibles.remove(i);
                            break;
                        }
                    }
                }
                horasDisponibles = new ArrayList<Integer>(new LinkedHashSet<Integer>(horasDisponibles));
            }
        });

        swQuince.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    horasDisponibles.add(15);
                } else {
                    //si se desmarca
                    boolean exist = false;
                    for (int i = 0; i < horasDisponibles.size(); i++) {
                        if (horasDisponibles.get(i) == 15) {
                            horasDisponibles.remove(i);
                            break;
                        }
                    }
                }
                horasDisponibles = new ArrayList<Integer>(new LinkedHashSet<Integer>(horasDisponibles));
            }
        });

        swDiesiseis.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    horasDisponibles.add(16);
                } else {
                    //si se desmarca
                    boolean exist = false;
                    for (int i = 0; i < horasDisponibles.size(); i++) {
                        if (horasDisponibles.get(i) == 16) {
                            horasDisponibles.remove(i);
                            break;
                        }
                    }
                }
                horasDisponibles = new ArrayList<Integer>(new LinkedHashSet<Integer>(horasDisponibles));
            }
        });

        swDiesisiete.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    horasDisponibles.add(17);
                } else {
                    //si se desmarca
                    boolean exist = false;
                    for (int i = 0; i < horasDisponibles.size(); i++) {
                        if (horasDisponibles.get(i) == 17) {
                            horasDisponibles.remove(i);
                            break;
                        }
                    }
                }
                horasDisponibles = new ArrayList<Integer>(new LinkedHashSet<Integer>(horasDisponibles));
            }
        });

        swDiesiocho.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    horasDisponibles.add(18);
                } else {
                    //si se desmarca
                    boolean exist = false;
                    for (int i = 0; i < horasDisponibles.size(); i++) {
                        if (horasDisponibles.get(i) == 18) {
                            horasDisponibles.remove(i);
                            break;
                        }
                    }
                }
                horasDisponibles = new ArrayList<Integer>(new LinkedHashSet<Integer>(horasDisponibles));
            }
        });


        swDiesinueve.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    horasDisponibles.add(19);
                } else {
                    //si se desmarca
                    boolean exist = false;
                    for (int i = 0; i < horasDisponibles.size(); i++) {
                        if (horasDisponibles.get(i) == 19) {
                            horasDisponibles.remove(i);
                            break;
                        }
                    }
                }
                horasDisponibles = new ArrayList<Integer>(new LinkedHashSet<Integer>(horasDisponibles));
            }
        });

        swVeinte.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    horasDisponibles.add(20);
                } else {
                    //si se desmarca
                    boolean exist = false;
                    for (int i = 0; i < horasDisponibles.size(); i++) {
                        if (horasDisponibles.get(i) == 20) {
                            horasDisponibles.remove(i);
                            break;
                        }
                    }
                }
                horasDisponibles = new ArrayList<Integer>(new LinkedHashSet<Integer>(horasDisponibles));
            }
        });


        swVeintiuno.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    horasDisponibles.add(21);
                } else {
                    //si se desmarca
                    boolean exist = false;
                    for (int i = 0; i < horasDisponibles.size(); i++) {
                        if (horasDisponibles.get(i) == 21) {
                            horasDisponibles.remove(i);
                            break;
                        }
                    }
                }
                horasDisponibles = new ArrayList<Integer>(new LinkedHashSet<Integer>(horasDisponibles));
            }
        });


        swVeintidos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    horasDisponibles.add(22);
                } else {
                    //si se desmarca
                    boolean exist = false;
                    for (int i = 0; i < horasDisponibles.size(); i++) {
                        if (horasDisponibles.get(i) == 22) {
                            horasDisponibles.remove(i);
                            break;
                        }
                    }
                }
                horasDisponibles = new ArrayList<Integer>(new LinkedHashSet<Integer>(horasDisponibles));
            }
        });

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

    public static final String DATE_FORMAT_2 = "dd-MM-yyyy";

    public static String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_2 );
       // dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date today = Calendar.getInstance().getTime();
        return dateFormat.format(today);
    }

    @Override
    public void listAvailableDateHourSuccess(WsResponseAvailableDateHour wsResponseAvailableDateHour) {
        if (loading.isShowing()) {
            loading.dismiss();
        }
        dateHours = new ArrayList<>();

        for (WsDataAvailableDateHour wsDataAvailableDateHour : wsResponseAvailableDateHour.getWsDataAvailableDateHours()) {

            if (wsDataAvailableDateHour.getId_type_availability() == 1) {
                dateHours.add(wsDataAvailableDateHour);
            }
        }


        fechaasa= getCurrentDate();



        String finallll="";
        String date="";
        SimpleDateFormat dateFormat= new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date d=dateFormat.parse(fechaasa);
            date=  DateFormat.getDateInstance(DateFormat.FULL).format(d);
            String upperString = date.substring(0, 1).toUpperCase() + date.substring(1).toLowerCase();
            finallll= deleteLast7Characts(upperString).replace(",","");
        }
        catch(Exception e) {
        }

        tvTextFecha.setText(finallll);



        horasDisponibles = new ArrayList<>();
        for (WsDataAvailableDateHour fecha : dateHours) {
            if (fechaasa.equals(fecha.getDate_availability())) {
                for (Integer hora : fecha.getHours_availability()) {
                    if (hora == 8) {
                        swOcho.setChecked(true);
                        horasDisponibles.add(8);
                    }
                    if (hora == 9) {
                        swNueve.setChecked(true);
                        horasDisponibles.add(9);
                    }
                    if (hora == 10) {
                        swDiez.setChecked(true);
                        horasDisponibles.add(10);
                    }
                    if (hora == 11) {
                        swOnce.setChecked(true);
                        horasDisponibles.add(11);
                    }
                    if (hora == 12) {
                        swDoce.setChecked(true);
                        horasDisponibles.add(12);
                    }
                    if (hora == 13) {
                        swTrece.setChecked(true);
                        horasDisponibles.add(13);
                    }
                    if (hora == 14) {
                        swCatorce.setChecked(true);
                        horasDisponibles.add(14);
                    }
                    if (hora == 15) {
                        swQuince.setChecked(true);
                        horasDisponibles.add(15);
                    }
                    if (hora == 16) {
                        swDiesiseis.setChecked(true);
                        horasDisponibles.add(16);
                    }
                    if (hora == 17) {
                        swDiesisiete.setChecked(true);
                        horasDisponibles.add(17);
                    }
                    if (hora == 18) {
                        swDiesiocho.setChecked(true);
                        horasDisponibles.add(18);
                    }


                    if (hora == 19) {
                        swDiesinueve.setChecked(true);
                        horasDisponibles.add(19);
                    }

                    if (hora == 20) {
                        swVeinte.setChecked(true);
                        horasDisponibles.add(20);
                    }

                    if (hora == 21) {
                        swVeintiuno.setChecked(true);
                        horasDisponibles.add(21);
                    }

                    if (hora == 22) {
                        swVeintidos.setChecked(true);
                        horasDisponibles.add(22);
                    }
                }

            }
        }
        horasDisponibles = new ArrayList<Integer>(new LinkedHashSet<Integer>(horasDisponibles));

        if(horasDisponibles.size()==0)
        {
            swOcho.setChecked(false);
            swNueve.setChecked(false);
            swDiez.setChecked(false);
            swOnce.setChecked(false);
            swDoce.setChecked(false);
            swTrece.setChecked(false);
            swCatorce.setChecked(false);
            swQuince.setChecked(false);
            swDiesiseis.setChecked(false);
            swDiesisiete.setChecked(false);
            swDiesiocho.setChecked(false);

            swDiesinueve.setChecked(false);
            swVeinte.setChecked(false);
            swVeintiuno.setChecked(false);
            swVeintidos.setChecked(false);
        }




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
        if (loading.isShowing()) {
            loading.dismiss();
        }
        Toast.makeText(getContext(), "Se actualizo su disponibilidad", Toast.LENGTH_LONG).show();

        next(MainActivity.class,getContext(),null);
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
