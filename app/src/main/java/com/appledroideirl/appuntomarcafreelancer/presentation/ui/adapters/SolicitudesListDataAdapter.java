package com.appledroideirl.appuntomarcafreelancer.presentation.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.appledroideirl.appuntomarcafreelancer.R;
import com.appledroideirl.appuntomarcafreelancer.domain.model.Solicitud;

import java.util.List;

public class SolicitudesListDataAdapter extends RecyclerView.Adapter<SolicitudesListDataAdapter.SingleItemRowHolder> {

    private List<Solicitud> itemsList;
    private Context mContext;
    public OnSolicitudesListDataAdapterClickListener mlistener;
    boolean userHasLocation;

    public SolicitudesListDataAdapter(OnSolicitudesListDataAdapterClickListener mlistener, Context context, List<Solicitud> itemsList, boolean userHasLocation) {
        this.itemsList = itemsList;
        this.mContext = context;
        this.mlistener = mlistener;
        this.userHasLocation = userHasLocation;
    }

    public interface OnSolicitudesListDataAdapterClickListener {
        void onSolicitudesListDataAdapterClicked(View v, Integer position);
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.appu_item_mis_solicitudes, null);
        SingleItemRowHolder mh = new SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, int i) {
        Solicitud solicitud = itemsList.get(i);
    }


    @Override
    public int getItemCount() {
        return (null != itemsList ? itemsList.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public SingleItemRowHolder(View view) {
            super(view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mlistener.onSolicitudesListDataAdapterClicked(view, this.getPosition());
        }
    }

}
