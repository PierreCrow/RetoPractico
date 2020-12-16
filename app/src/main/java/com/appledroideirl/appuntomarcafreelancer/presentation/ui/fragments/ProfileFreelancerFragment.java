package com.appledroideirl.appuntomarcafreelancer.presentation.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.appledroideirl.appuntomarcafreelancer.R;
import com.appledroideirl.appuntomarcafreelancer.presentation.ui.activities.EditProfileActivity;
import com.appledroideirl.appuntomarcafreelancer.presentation.ui.activities.LoginActivity;
import com.appledroideirl.appuntomarcafreelancer.presentation.ui.activities.MiCuentaBancariaActivity;
import com.appledroideirl.appuntomarcafreelancer.presentation.ui.activities.MisLocalesActivity;
import com.appledroideirl.appuntomarcafreelancer.presentation.ui.dialogfragment.LogoutDialog;
import com.appledroideirl.appuntomarcafreelancer.presentation.utils.Helper;
import com.appledroideirl.appuntomarcafreelancer.presentation.utils.SingleClick;

import butterknife.BindView;


public class ProfileFreelancerFragment extends BaseFragment {

    @BindView(R.id.ivUserImage)
    ImageView ivUserImage;

    @BindView(R.id.tvName)
    TextView tvName;

    @BindView(R.id.tvRate)
    TextView tvRate;

    @BindView(R.id.llMiCuentaBancaria)
    LinearLayout llMiCuentaBancaria;

    @BindView(R.id.llLogout)
    LinearLayout llLogout;

    @BindView(R.id.llEditarPerfil)
    LinearLayout llEditarPerfil;

    @BindView(R.id.llMisLocales)
    LinearLayout llMisLocales;

    @BindView(R.id.viewlocales)
    View viewlocales;

    @BindView(R.id.tvLocalDireccion)
    TextView tvLocalDireccion;



    SingleClick singleClick;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View x = inflater.inflate(R.layout.appu_profile_freelancer_fragment, null);
        injectView(x);
        initUI();

        return x;
    }


    void initUI() {

        if (Helper.getUserAppPreference(getContext()).getType_user().equals("FREELANCER")) {
            tvLocalDireccion.setText("Mis direcciones");

          //  llMisLocales.setVisibility(View.GONE);
          //  viewlocales.setVisibility(View.GONE);
        }
        else
        {
            tvLocalDireccion.setText("Mis locales");
        }


        Helper.urlToImageView(Helper.getUserAppPreference(getContext()).getPhoto(), ivUserImage, getContext());
        tvName.setText(Helper.getUserAppPreference(getContext()).getFull_name());
        Double rateee=Helper.getUserAppPreference(getContext()).getAvg_rate();
        String califf=Helper.convertTwoDecimals(rateee);
        tvRate.setText(califf);

        onClickListener();
        llMiCuentaBancaria.setOnClickListener(singleClick);
        llLogout.setOnClickListener(singleClick);
        llEditarPerfil.setOnClickListener(singleClick);
        llMisLocales.setOnClickListener(singleClick);
    }

    void showCerrarSesion() {
        LogoutDialog df = new LogoutDialog();
        df.show(getFragmentManager(), "");
    }


    private void onClickListener() {
        singleClick = new SingleClick() {
            @Override
            public void onSingleClick(View v) {
                switch (v.getId()) {
                    case R.id.llEditarPerfil:
                        next(EditProfileActivity.class, getContext(), null);
                        break;
                    case R.id.llMiCuentaBancaria:
                        next(MiCuentaBancariaActivity.class, getContext(), null);
                        break;
                    case R.id.llLogout:
                        showCerrarSesion();
                        break;
                    case R.id.llMisLocales:
                        next(MisLocalesActivity.class, getContext(), null);
                        break;

                }
            }
        };
    }


}
