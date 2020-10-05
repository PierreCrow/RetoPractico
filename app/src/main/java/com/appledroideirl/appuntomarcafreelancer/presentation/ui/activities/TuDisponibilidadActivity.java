package com.appledroideirl.appuntomarcafreelancer.presentation.ui.activities;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.FrameLayout;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.appledroideirl.appuntomarcafreelancer.R;
import com.appledroideirl.appuntomarcafreelancer.presentation.ui.fragments.TabDisponibilidadFragment;
import com.appledroideirl.appuntomarcafreelancer.presentation.ui.fragments.TabDisponibilidadOneFragment;
import com.appledroideirl.appuntomarcafreelancer.presentation.utils.Helper;

public class TuDisponibilidadActivity extends BaseActivity {

    FrameLayout containerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appu_tu_disponibilidad_activity);

        containerView = (FrameLayout) findViewById(R.id.containerdisponibilidad);


        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        injectView();
        initUi();
       // loadTabHomeFragment();
    }

    void initUi() {

        if(Helper.getUserAppPreference(getApplicationContext()).getType_user().equals("MARCA"))
        {
            loadTabHomeFragment();
        }
        else
        {
            loadTabOneFragment();
        }

    }


    void loadTabHomeFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        TabDisponibilidadFragment homeFragment = new TabDisponibilidadFragment();
        fragmentTransaction.replace(R.id.containerdisponibilidad, homeFragment);
        fragmentTransaction.commit();
    }

    void loadTabOneFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        TabDisponibilidadOneFragment homeFragment = new TabDisponibilidadOneFragment();
        fragmentTransaction.replace(R.id.containerdisponibilidad, homeFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {

    }

}