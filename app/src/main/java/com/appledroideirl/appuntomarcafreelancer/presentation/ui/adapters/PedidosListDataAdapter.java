package com.appledroideirl.appuntomarcafreelancer.presentation.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appledroideirl.appuntomarcafreelancer.R;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsDataRequest;
import com.appledroideirl.appuntomarcafreelancer.domain.model.Pedido;
import com.appledroideirl.appuntomarcafreelancer.presentation.utils.Constants;
import com.appledroideirl.appuntomarcafreelancer.presentation.utils.Helper;

import java.util.List;

public class PedidosListDataAdapter extends RecyclerView.Adapter<PedidosListDataAdapter.SingleItemRowHolder>
        implements SubServicioSolicitudesListDataAdapter.OnSubServicioSolicitudesListDataAdapterClickListener {

    private List<WsDataRequest> itemsList;
    private Context mContext;
    public OnPedidosListDataAdapterClickListener mlistener;
    SubServicioSolicitudesListDataAdapter adapter;

    public PedidosListDataAdapter(OnPedidosListDataAdapterClickListener mlistener, Context context, List<WsDataRequest> itemsList) {
        this.itemsList = itemsList;
        this.mContext = context;
        this.mlistener = mlistener;
    }

    @Override
    public void onSubServicioSolicitudesListDataAdapterClicked(View v, Integer position) {

    }

    public interface OnPedidosListDataAdapterClickListener {
        void onPedidosListDataAdapterClicked(View v, Integer position, String Action);
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.appu_item_pedidos, null);
        SingleItemRowHolder mh = new SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, int i) {
        WsDataRequest pedido = itemsList.get(i);

        holder.tvSolitudClient.setText("Solicitado por " + pedido.getFull_name_customer());

        Helper.urlToImageView(pedido.getUrl_image_customer(), holder.ivClientPicture, mContext);

        holder.tvFecha.setText(pedido.getDate_availability());
        holder.tvNameServicio.setText("Servicio " + "");

        Integer horaaa = pedido.getHour_availability();
        holder.tvHora.setText(horaaa.toString() + ":00");
        holder.tvLugar.setText(pedido.getAddress());
        holder.tvTotal.setText("S/" + pedido.getTotal_amount().toString());

        adapter = new SubServicioSolicitudesListDataAdapter(mContext, pedido.getWsDataSales());
        //  holder.rvUserservicios.setHasFixedSize(true);
        holder.rvUserservicios.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        holder.rvUserservicios.setAdapter(adapter);

        if (pedido.getStatus_sale() == Constants.SOLICITUD_TYPES.ACEPTADO_POR_USUARIO) {
            holder.llBotones.setVisibility(View.GONE);
        }

    }


    @Override
    public int getItemCount() {
        return (null != itemsList ? itemsList.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        protected LinearLayout llAEsconder;
        protected ImageView ivBotonVer;
        protected TextView tvSolitudClient;
        protected TextView tvTotal;
        protected TextView tvHora;
        protected TextView tvFecha;
        protected TextView tvLugar;
        protected RecyclerView rvUserservicios;
        protected ImageView ivClientPicture;
        protected TextView tvNameServicio;

        protected Button btnAceptar;
        protected Button btnDenegar;

        protected LinearLayout llBotones;


        public SingleItemRowHolder(View view) {
            super(view);


            this.tvSolitudClient = (TextView) view.findViewById(R.id.tvSolitudClient);
            this.tvNameServicio = (TextView) view.findViewById(R.id.tvNameServicio);
            this.llAEsconder = (LinearLayout) view.findViewById(R.id.llAEsconder);
            this.ivBotonVer = (ImageView) view.findViewById(R.id.ivBotonVer);
            this.rvUserservicios = (RecyclerView) view.findViewById(R.id.rvUserservicios);

            this.tvTotal = (TextView) view.findViewById(R.id.tvTotal);
            this.tvHora = (TextView) view.findViewById(R.id.tvHora);
            this.tvFecha = (TextView) view.findViewById(R.id.tvFecha);
            this.tvLugar = (TextView) view.findViewById(R.id.tvLugar);

            this.ivClientPicture = (ImageView) view.findViewById(R.id.ivClientPicture);

            this.btnAceptar = (Button) view.findViewById(R.id.btnAceptar);
            this.btnDenegar = (Button) view.findViewById(R.id.btnDenegar);

            this.llBotones = (LinearLayout) view.findViewById(R.id.llBotones);

            ivBotonVer.setOnClickListener(this);
            btnAceptar.setOnClickListener(this);
            btnDenegar.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            if (view == btnAceptar) {
                mlistener.onPedidosListDataAdapterClicked(view, this.getPosition(), "ACEPT");
            } else {
                if (view == btnDenegar) {
                    mlistener.onPedidosListDataAdapterClicked(view, this.getPosition(), "REFUSE");
                } else {

                    if (view == ivBotonVer) {
                        Integer estadoLinear = llAEsconder.getVisibility();
                        if (estadoLinear == View.VISIBLE) {
                            llAEsconder.setVisibility(View.GONE);
                            ivBotonVer.setImageResource(R.drawable.appu_go_down_black);

                        } else {
                            llAEsconder.setVisibility(View.VISIBLE);
                            ivBotonVer.setImageResource(R.drawable.appu_ic_go_up);

                        }
                    }
                }
            }


        }
    }

}
