package com.appledroideirl.appuntomarcafreelancer.presentation.ui.activities;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.appledroideirl.appuntomarcafreelancer.R;

import butterknife.BindView;

public class CompletarDatosBrandActivity extends BaseActivity {

    @BindView(R.id.ivClose)
    ImageView ivClose;

    @BindView(R.id.ivContinue)
    ImageView ivContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appu_completar_datos_brand_activity);

        injectView();
        initUi();
        preventKeyboard();
    }

    void initUi() {
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        ivContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                next(MainActivity.class, null);

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    void preventKeyboard() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

}