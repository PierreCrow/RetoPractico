package com.appledroideirl.appuntomarcafreelancer.presentation.ui.fragments;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.appledroideirl.appuntomarcafreelancer.R;
import com.appledroideirl.appuntomarcafreelancer.presentation.ui.activities.EditProfileActivity;
import com.appledroideirl.appuntomarcafreelancer.presentation.ui.activities.LoginActivity;
import com.appledroideirl.appuntomarcafreelancer.presentation.ui.activities.MisLocalesActivity;
import com.appledroideirl.appuntomarcafreelancer.presentation.ui.dialogfragment.AjustesServicioDialog;

import butterknife.BindView;


public class ProfileBrandFragment extends BaseFragment{

    @BindView(R.id.llCerrarSesion)
    LinearLayout llCerrarSesion;

    @BindView(R.id.llEditarPerfil)
    LinearLayout llEditarPerfil;

    @BindView(R.id.llMisLocales)
    LinearLayout llMisLocales;

    @BindView(R.id.llMisTarjetas)
    LinearLayout llMisTarjetas;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View x = inflater.inflate(R.layout.appu_profile_brand_fragment, null);
        injectView(x);
        initUI();

        return x;
    }


    void initUI()
    {
        llCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next(LoginActivity.class,getContext(),null);
            //    showCerrarSesion();
            }
        });

        llEditarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next(EditProfileActivity.class,getContext(),null);

            }
        });

        llMisLocales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // next(MisLocalesActivity.class,getContext(),null);
            }
        });

        llMisTarjetas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //next(Tar.class,getContext(),null);
            }
        });
    }

    void showCerrarSesion()
    {

     //   AgregarServicioDialog df = new AgregarServicioDialog();
     //   df.show(getFragmentManager(), "");

        AjustesServicioDialog df = new AjustesServicioDialog();
        df.show(getFragmentManager(), "");


       // LogoutDialog df = new LogoutDialog();
       // df.show(getFragmentManager(), "");
    }



}
