package com.appledroideirl.appuntomarcafreelancer.presentation.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appledroideirl.appuntomarcafreelancer.R;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsDataRequest;
import com.appledroideirl.appuntomarcafreelancer.domain.model.Pedido;
import com.appledroideirl.appuntomarcafreelancer.presentation.utils.Constants;
import com.appledroideirl.appuntomarcafreelancer.presentation.utils.Helper;
import com.google.rpc.Help;

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
        void onPedidosListDataAdapterClicked(View v, Integer position, String Action, WsDataRequest wsDataRequest);
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

        if (pedido.getTotal_amount() == 98765.43) {

            holder.tvNombreTitulo.setTextColor(ContextCompat.getColor(mContext, R.color.labelColor));

            holder.tvNombreTitulo.setText(pedido.getComment());

            holder.llData.setVisibility(View.GONE);
            holder.llTitulo.setVisibility(View.VISIBLE);
        } else {

            holder.tvNombreTitulo.setTextColor(ContextCompat.getColor(mContext, R.color.tittle_item_reportes));

            holder.llData.setVisibility(View.VISIBLE);
            holder.llTitulo.setVisibility(View.GONE);

            holder.tvSolitudClient.setText("Solicitado por " + pedido.getFull_name_customer());

            if (!pedido.getUrl_image_customer().equals("")) {
                Helper.urlToImageView(pedido.getUrl_image_customer(), holder.ivClientPicture, mContext);
            }
            else
            {
                Helper.urlToImageView("https://avatar-management--avatars.us-west-2.prod.public.atl-paas.net/default-avatar.png", holder.ivClientPicture, mContext);
            }


            holder.tvFecha.setText(pedido.getDate_availability());
            holder.tvNameServicio.setText("Servicio " + "");

            Integer horaaa = pedido.getHour_availability();
            holder.tvHora.setText(horaaa.toString() + ":00");

            String amountFinal = Helper.convertTwoDecimals(pedido.getTotal_amount());

            String amountCupon=Helper.convertTwoDecimals(pedido.getCuponAmount());

            holder.tvCupon.setText("S/" + amountCupon);
            holder.tvTotal.setText("S/" + amountFinal);

            holder.tvComment.setText(pedido.getComment());

            adapter = new SubServicioSolicitudesListDataAdapter(mContext, pedido.getWsDataSales());
            //  holder.rvUserservicios.setHasFixedSize(true);
            holder.rvUserservicios.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
            holder.rvUserservicios.setAdapter(adapter);

            if (pedido.getStatus_sale() == Constants.SOLICITUD_TYPES.ACEPTADO_POR_USUARIO) {
                holder.llBotones.setVisibility(View.GONE);
                holder.ivBotonCheck.setVisibility(View.VISIBLE);
                holder.tvTipo.setText("Aceptado");
            } else {
                holder.llBotones.setVisibility(View.VISIBLE);
                holder.ivBotonCheck.setVisibility(View.GONE);
            }

            if (pedido.getStatus_sale() == Constants.SOLICITUD_TYPES.PENDIENTE) {
                holder.tvTipo.setText("Pendiente");
            }

            if (pedido.getStatus_sale() == Constants.SOLICITUD_TYPES.RECHAZADO_POR_USUARIO) {
                holder.tvTipo.setText("Rechazado");
                holder.llBotones.setVisibility(View.GONE);
            }

            if (pedido.getStatus_sale() == Constants.SOLICITUD_TYPES.PAGADO_POR_CLIENTE) {
                holder.tvTipo.setText("Pagado");
                holder.llBotones.setVisibility(View.GONE);
            }

            if (pedido.getStatus_sale() == Constants.SOLICITUD_TYPES.CANCELADO) {
                holder.tvTipo.setText("Cancelado");
                holder.llBotones.setVisibility(View.GONE);
            }

            holder.tvLugar.setText(pedido.getAddress());

            if (pedido.getLat() != null && pedido.getLng() != null) {
                String coordd = pedido.getLat() + " , " + pedido.getLng();
                holder.tvCoordenadas.setText(coordd);
            }

            if (pedido.getId_type_availability() == 2) {
                holder.tvLugar.setVisibility(View.GONE);
                holder.tvTituloLugar.setVisibility(View.GONE);

                holder.tvCoordenadas.setVisibility(View.GONE);
                holder.tvPosicion.setVisibility(View.GONE);
            }
        }


    }


    @Override
    public int getItemCount() {
        return (null != itemsList ? itemsList.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        protected LinearLayout llAEsconder, llTitulo, llData;
        protected LinearLayout ivBotonVer;
        protected ImageView  ivBotonCheck,ivBotonVerr;
        protected TextView tvSolitudClient;
        protected TextView tvTotal, tvComment;
        protected TextView tvHora;
        protected TextView tvFecha, tvTipo;
        protected TextView tvLugar, tvTituloLugar;
        protected RecyclerView rvUserservicios;
        protected ImageView ivClientPicture;
        protected TextView tvNameServicio, tvNombreTitulo, tvPosicion, tvCoordenadas,tvCupon;

        protected Button btnAceptar;
        protected Button btnDenegar;

        protected LinearLayout llBotones;


        public SingleItemRowHolder(View view) {
            super(view);


            this.tvSolitudClient = (TextView) view.findViewById(R.id.tvSolitudClient);
            this.tvNameServicio = (TextView) view.findViewById(R.id.tvNameServicio);
            this.tvComment = (TextView) view.findViewById(R.id.tvComment);
            this.llAEsconder = (LinearLayout) view.findViewById(R.id.llAEsconder);
            this.ivBotonVer = (LinearLayout) view.findViewById(R.id.ivBotonVer);
            this.ivBotonCheck = (ImageView) view.findViewById(R.id.ivBotonCheck);
            this.ivBotonVerr = (ImageView) view.findViewById(R.id.ivBotonVerr);
            this.rvUserservicios = (RecyclerView) view.findViewById(R.id.rvUserservicios);

            this.tvTotal = (TextView) view.findViewById(R.id.tvTotal);
            this.tvHora = (TextView) view.findViewById(R.id.tvHora);
            this.tvFecha = (TextView) view.findViewById(R.id.tvFecha);
            this.tvLugar = (TextView) view.findViewById(R.id.tvLugar);

            this.tvPosicion = (TextView) view.findViewById(R.id.tvPosicion);
            this.tvCoordenadas = (TextView) view.findViewById(R.id.tvCoordenadas);

            this.tvTipo = (TextView) view.findViewById(R.id.tvTipo);

            this.tvCupon = (TextView) view.findViewById(R.id.tvCupon);

            this.tvTituloLugar = (TextView) view.findViewById(R.id.tvTituloLugar);
            this.ivClientPicture = (ImageView) view.findViewById(R.id.ivClientPicture);

            this.btnAceptar = (Button) view.findViewById(R.id.btnAceptar);
            this.btnDenegar = (Button) view.findViewById(R.id.btnDenegar);

            this.llBotones = (LinearLayout) view.findViewById(R.id.llBotones);
            this.llTitulo = (LinearLayout) view.findViewById(R.id.llTitulo);
            this.llData = (LinearLayout) view.findViewById(R.id.llData);
            this.tvNombreTitulo = (TextView) view.findViewById(R.id.tvNombreTitulo);

            ivBotonVer.setOnClickListener(this);
            btnAceptar.setOnClickListener(this);
            btnDenegar.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            if (view == btnAceptar) {
                mlistener.onPedidosListDataAdapterClicked(view, this.getPosition(), "ACEPT", itemsList.get(this.getPosition()));
            } else {
                if (view == btnDenegar) {
                    mlistener.onPedidosListDataAdapterClicked(view, this.getPosition(), "REFUSE", itemsList.get(this.getPosition()));
                } else {

                    if (view == ivBotonVer) {
                        Integer estadoLinear = llAEsconder.getVisibility();
                        if (estadoLinear == View.VISIBLE) {
                            llAEsconder.setVisibility(View.GONE);
                            ivBotonVerr.setImageResource(R.drawable.appu_go_down_black);

                        } else {
                            llAEsconder.setVisibility(View.VISIBLE);
                            ivBotonVerr.setImageResource(R.drawable.appu_ic_go_up);

                        }
                    }
                }
            }


        }
    }

}
