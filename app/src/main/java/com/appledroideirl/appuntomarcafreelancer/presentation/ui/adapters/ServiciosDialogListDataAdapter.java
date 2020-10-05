package com.appledroideirl.appuntomarcafreelancer.presentation.ui.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.appledroideirl.appuntomarcafreelancer.R;
import com.appledroideirl.appuntomarcafreelancer.domain.model.Service;

import java.util.List;

public class ServiciosDialogListDataAdapter extends RecyclerView.Adapter<ServiciosDialogListDataAdapter.SingleItemRowHolder> {

    private List<Service> itemsList;
    private Context mContext;
    public OnServiciosDialogListDataAdapterClickListener mlistener;
    boolean userHasLocation;

    public ServiciosDialogListDataAdapter(OnServiciosDialogListDataAdapterClickListener mlistener, Context context, List<Service> itemsList, boolean userHasLocation) {
        this.itemsList = itemsList;
        this.mContext = context;
        this.mlistener = mlistener;
        this.userHasLocation = userHasLocation;
    }

    public interface OnServiciosDialogListDataAdapterClickListener {
        void onServiciosDialogListDataAdapterClicked(View v, Integer position,boolean add);
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.appu_item_servicios_dialog, null);
        SingleItemRowHolder mh = new SingleItemRowHolder(v);
        return mh;
    }

    interface OnItemCheckListener {
        void onItemCheck(Service item);
        void onItemUncheck(Service item);
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, int i) {
        Service service = itemsList.get(i);

        if(!service.getFile_image().equals("NUEVO"))
        {
            byte[] decodedString = Base64.decode(service.getFile_image(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            Drawable d = new BitmapDrawable(mContext.getResources(), decodedByte);
            holder.ivIcon.setImageDrawable(d);

            holder.llService.setBackgroundColor(Color.parseColor(service.getColor()));
            holder.rbService.setText(service.getFull_name());

            if (service.getEnable() == 1) {
                holder.rbService.setChecked(true);
            } else {
                holder.rbService.setChecked(false);
            }

        }

    }


    @Override
    public int getItemCount() {
        return (null != itemsList ? itemsList.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected CheckBox rbService;
        protected LinearLayout llService;
        protected ImageView ivIcon;

        View itemView;

        public SingleItemRowHolder(View view) {
            super(view);

            this.rbService = (CheckBox) view.findViewById(R.id.rbService);
            this.ivIcon = (ImageView) view.findViewById(R.id.ivIcon);
            this.llService = (LinearLayout) view.findViewById(R.id.llService);

            rbService.setOnClickListener(this);
        }

        public void setOnClickListener(View.OnClickListener onClickListener) {
            itemView.setOnClickListener(onClickListener);
        }

        @Override
        public void onClick(View view) {

            boolean anadir=false;

            boolean acvtivado=false;

            if(view==rbService)
            {
                acvtivado=rbService.isChecked();
            }

            if(acvtivado)
            {
                mlistener.onServiciosDialogListDataAdapterClicked(view, this.getPosition(),true);
            }
            else
            {
                mlistener.onServiciosDialogListDataAdapterClicked(view, this.getPosition(),false);
            }


/*
            if(rbService.isChecked())
            {
                anadir=true;
            }
            else
            {
                anadir=false;
            }
            mlistener.onServiciosDialogListDataAdapterClicked(view, this.getPosition(),anadir);
            */


        }
    }

}
