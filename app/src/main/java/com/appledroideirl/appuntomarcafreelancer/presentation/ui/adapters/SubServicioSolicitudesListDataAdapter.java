package com.appledroideirl.appuntomarcafreelancer.presentation.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.appledroideirl.appuntomarcafreelancer.R;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsDataSale;
import com.appledroideirl.appuntomarcafreelancer.domain.model.Solicitud;
import com.appledroideirl.appuntomarcafreelancer.presentation.utils.Helper;

import java.util.List;

public class SubServicioSolicitudesListDataAdapter extends
        RecyclerView.Adapter<SubServicioSolicitudesListDataAdapter.SingleItemRowHolder> {

    private List<WsDataSale> itemsList;
    private Context mContext;
    public OnSubServicioSolicitudesListDataAdapterClickListener mlistener;
    boolean userHasLocation;

    public SubServicioSolicitudesListDataAdapter(Context context, List<WsDataSale> itemsList) {
        this.itemsList = itemsList;
        this.mContext = context;
        this.mlistener = mlistener;
        this.userHasLocation = userHasLocation;
    }

    public interface OnSubServicioSolicitudesListDataAdapterClickListener {
        void onSubServicioSolicitudesListDataAdapterClicked(View v, Integer position);
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.appu_item_subservicios_pedidos, null);
        SingleItemRowHolder mh = new SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, int i) {
        WsDataSale subServicio = itemsList.get(i);
        holder.tvName.setText(subServicio.getFull_name());
        String amountFinal= Helper.convertTwoDecimals(subServicio.getAmount());

        holder.tvHowMuch.setText("S/"+amountFinal);
    }


    @Override
    public int getItemCount() {
        return (null != itemsList ? itemsList.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView tvName;
        protected TextView tvHowMuch;

        public SingleItemRowHolder(View view) {
            super(view);
            this.tvName = (TextView) view.findViewById(R.id.tvName);
            this.tvHowMuch = (TextView) view.findViewById(R.id.tvHowMuch);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mlistener.onSubServicioSolicitudesListDataAdapterClicked(view, this.getPosition());
        }
    }

}
