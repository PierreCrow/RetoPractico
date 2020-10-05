package com.appledroideirl.appuntomarcafreelancer.presentation.ui.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.appledroideirl.appuntomarcafreelancer.R;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.parameter.WsParameterAddSubService;
import com.appledroideirl.appuntomarcafreelancer.data.datasource.cloud.model.user.response.WsDataSale;
import com.appledroideirl.appuntomarcafreelancer.domain.model.Service;
import com.appledroideirl.appuntomarcafreelancer.domain.model.SubService;
import com.appledroideirl.appuntomarcafreelancer.presentation.ui.dialogfragment.AjustesServicioDialog;

import java.util.List;

public class EscogeSubServicioListDataAdapter extends
        RecyclerView.Adapter<EscogeSubServicioListDataAdapter.SingleItemRowHolder> {

    private List<SubService> itemsList;
    private Context mContext;
    public OnEscogeSubServicioListDataAdapterClickListener mlistener;


    public EscogeSubServicioListDataAdapter(OnEscogeSubServicioListDataAdapterClickListener mlistener, Context context, List<SubService> itemsList) {
        this.itemsList = itemsList;
        this.mContext = context;
        this.mlistener = mlistener;
    }

    public interface OnEscogeSubServicioListDataAdapterClickListener {
        void onEscogeSubServicioListDataAdapterClicked(View v, Integer position,boolean add,Double price);
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.appu_item_escoge_subservicios, null);
        SingleItemRowHolder mh = new SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, int i) {
        SubService subServicio = itemsList.get(i);
        holder.tvName.setText(subServicio.getFull_name());
        if (subServicio.getEnable() == 1) {
            holder.chbCheck.setChecked(true);
        } else {
            holder.chbCheck.setChecked(false);
        }
        holder.etPrice.setText(subServicio.getCharge());

        textChangeListenr(holder.etPrice,subServicio.getId());

    }


    @Override
    public int getItemCount() {
        return (null != itemsList ? itemsList.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView tvName;
        protected EditText etPrice;
        protected CheckBox chbCheck;

        public SingleItemRowHolder(View view) {
            super(view);
            this.tvName = (TextView) view.findViewById(R.id.tvName);
            this.etPrice = (EditText) view.findViewById(R.id.etPrice);
            this.chbCheck = (CheckBox) view.findViewById(R.id.chbCheck);
            chbCheck.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            Double precioo=Double.parseDouble(etPrice.getText().toString());

            boolean acvtivado=false;

            if(view==chbCheck)
            {
                acvtivado=chbCheck.isChecked();
            }

            if(acvtivado)
            {
                mlistener.onEscogeSubServicioListDataAdapterClicked(view, this.getPosition(),true,precioo);
            }
            else
            {
                mlistener.onEscogeSubServicioListDataAdapterClicked(view, this.getPosition(),false,precioo);
            }

        }
    }


    void textChangeListenr(EditText editText,Integer idd)
    {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }
            @Override
            public void onTextChanged(final CharSequence s, int start, int before,
                                      int count) {

            }
            @Override
            public void afterTextChanged(final Editable s) {
                //avoid triggering event when text is too short
                if (s.toString().equals("")) {
                }
                else
                {
                    for(int i=0;i< AjustesServicioDialog.subServicesToAdd.size();i++)
                    {
                        if(idd==AjustesServicioDialog.subServicesToAdd.get(i).getId_sub_service())
                        {
                            AjustesServicioDialog.subServicesToAdd.get(i).setCharge(Double.parseDouble(s.toString()));
                        }
                    }
                }



            }
        });
    }

}
