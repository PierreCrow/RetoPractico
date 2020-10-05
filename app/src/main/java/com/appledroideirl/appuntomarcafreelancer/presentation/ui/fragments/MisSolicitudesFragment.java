package com.appledroideirl.appuntomarcafreelancer.presentation.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appledroideirl.appuntomarcafreelancer.R;
import com.appledroideirl.appuntomarcafreelancer.domain.model.Solicitud;
import com.appledroideirl.appuntomarcafreelancer.presentation.ui.adapters.SolicitudesListDataAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MisSolicitudesFragment extends BaseFragment implements SolicitudesListDataAdapter.OnSolicitudesListDataAdapterClickListener {


    @BindView(R.id.rv_solicitudes)
    RecyclerView rv_solicitudes;


    SolicitudesListDataAdapter adapter;
    SolicitudesListDataAdapter.OnSolicitudesListDataAdapterClickListener mlistener;

    List<Solicitud> freelancers;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View x = inflater.inflate(R.layout.appu_mis_solicitudes, null);
        injectView(x);

        cargaTodo();
        return x;
    }

    void cargaTodo() {
        mlistener = this;

        freelancers = new ArrayList<>();


        adapter = new SolicitudesListDataAdapter(mlistener, getContext(), freelancers, true);
        rv_solicitudes.setHasFixedSize(true);
        //  rv_solicitudes.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rv_solicitudes.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rv_solicitudes.setAdapter(adapter);

    }


    @Override
    public void onSolicitudesListDataAdapterClicked(View v, Integer position) {

    }
}
