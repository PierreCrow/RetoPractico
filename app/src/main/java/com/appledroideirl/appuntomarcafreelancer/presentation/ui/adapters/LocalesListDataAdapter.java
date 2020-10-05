package com.appledroideirl.appuntomarcafreelancer.presentation.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.appledroideirl.appuntomarcafreelancer.R;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsDataLocales;
import com.appledroideirl.appuntomarcafreelancer.domain.model.Reporte;

import java.util.List;

public class LocalesListDataAdapter extends RecyclerView.Adapter<LocalesListDataAdapter.SingleItemRowHolder> {

    private List<WsDataLocales> itemsList;
    private Context mContext;
    public OnLocalesListDataAdapterClickListener mlistener;

    public LocalesListDataAdapter(OnLocalesListDataAdapterClickListener mlistener, Context context, List<WsDataLocales> itemsList) {
        this.itemsList = itemsList;
        this.mContext = context;
        this.mlistener = mlistener;
    }

    public interface OnLocalesListDataAdapterClickListener {
        void onLocalesListDataAdapterClicked(View v, Integer position);
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.appu_item_mis_locales, null);
        SingleItemRowHolder mh = new SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, int i) {
        WsDataLocales reporte = itemsList.get(i);

        String titulo=reporte.getFull_name();
        String direccion=reporte.getAddress();
        holder.tvnameLocal.setText(titulo);
        holder.tvadressLocal.setText(direccion);
    }


    @Override
    public int getItemCount() {
        return (null != itemsList ? itemsList.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView   tvnameLocal,tvadressLocal;

        public SingleItemRowHolder(View view) {
            super(view);

            this.tvnameLocal = (TextView) view.findViewById(R.id.tvnameLocal);
            this.tvadressLocal = (TextView) view.findViewById(R.id.tvadressLocal);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mlistener.onLocalesListDataAdapterClicked(view, this.getPosition());
        }
    }

}
