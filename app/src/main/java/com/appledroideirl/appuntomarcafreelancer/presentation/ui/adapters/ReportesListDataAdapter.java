package com.appledroideirl.appuntomarcafreelancer.presentation.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.appledroideirl.appuntomarcafreelancer.R;
import com.appledroideirl.appuntomarcafreelancer.domain.model.Reporte;

import java.util.List;

public class ReportesListDataAdapter extends RecyclerView.Adapter<ReportesListDataAdapter.SingleItemRowHolder> {

    private List<Reporte> itemsList;
    private Context mContext;
    public OnReportesListDataAdapterClickListener mlistener;
    boolean userHasLocation;

    public ReportesListDataAdapter(OnReportesListDataAdapterClickListener mlistener, Context context, List<Reporte> itemsList, boolean userHasLocation) {
        this.itemsList = itemsList;
        this.mContext = context;
        this.mlistener = mlistener;
        this.userHasLocation = userHasLocation;
    }

    public interface OnReportesListDataAdapterClickListener {
        void onReportesListDataAdapterClicked(View v, Integer position);
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.appu_item_reportes, null);
        SingleItemRowHolder mh = new SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, int i) {
        Reporte reporte = itemsList.get(i);

        String titulo=reporte.getTittle();
        holder.tvTittle.setText(titulo);

        if(titulo.equals("Ingresos"))
        {
            holder.tvSubTittle.setText("Este mes has ganado S/"+reporte.getValue().toString());
            holder.ivReporte.setImageResource(R.drawable.appu_ic_ingresos);
        }

        if(titulo.equals("Servicios Completados"))
        {
            Integer valor=Integer.valueOf(reporte.getValue().intValue());
            holder.tvSubTittle.setText("En este mes tienes "+valor.toString()+" servicios completados");
            holder.ivReporte.setImageResource(R.drawable.appu_ic_completados);
        }

        if(titulo.equals("Servicios Rechazados"))
        {
            Integer valor=Integer.valueOf(reporte.getValue().intValue());
            holder.tvSubTittle.setText("En este mes tienes "+valor.toString()+" servicios rechazados");
            holder.ivReporte.setImageResource(R.drawable.appu_ic_rechazados);
        }

    }


    @Override
    public int getItemCount() {
        return (null != itemsList ? itemsList.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView   tvTittle,tvSubTittle;
        protected ImageView ivReporte;

        public SingleItemRowHolder(View view) {
            super(view);

            this.tvTittle = (TextView) view.findViewById(R.id.tvTittle);
            this.tvSubTittle = (TextView) view.findViewById(R.id.tvSubTittle);
            this.ivReporte = (ImageView) view.findViewById(R.id.ivReporte);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mlistener.onReportesListDataAdapterClicked(view, this.getPosition());
        }
    }

}
