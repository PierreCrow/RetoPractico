package com.appledroideirl.appuntomarcafreelancer.presentation.ui.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.appledroideirl.appuntomarcafreelancer.R;
import com.appledroideirl.appuntomarcafreelancer.domain.model.Service;

import java.util.List;

public class ServiciosHomeListDataAdapter extends RecyclerView.Adapter<ServiciosHomeListDataAdapter.SingleItemRowHolder> {

    private List<Service> itemsList;
    private Context mContext;
    public OnServiciosHomeListDataAdapterClickListener mlistener;
    boolean userHasLocation;

    public ServiciosHomeListDataAdapter(OnServiciosHomeListDataAdapterClickListener mlistener, Context context, List<Service> itemsList, boolean userHasLocation) {
        this.itemsList = itemsList;
        this.mContext = context;
        this.mlistener = mlistener;
        this.userHasLocation = userHasLocation;
    }

    public interface OnServiciosHomeListDataAdapterClickListener {
        void onServiciosHomeListDataAdapterClicked(View v, Integer position);
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.appu_item_user_servicios_home, null);
        SingleItemRowHolder mh = new SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, int i) {
        Service service = itemsList.get(i);

        if(service.getFile_image().equals("NUEVO"))
        {
            holder.ivIcon.setImageResource(R.drawable.appu_ic_plus);
            holder.tvName.setText("");
            holder.llService.setBackgroundColor(mContext.getResources().getColor(R.color.white));

        }
        else
        {
            if(service.getEnable()==1)
            {

                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)holder.ivIcon.getLayoutParams();
                params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                holder.ivIcon.setLayoutParams(params);

                byte[] decodedString = Base64.decode(service.getFile_image(), Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                Drawable d = new BitmapDrawable(mContext.getResources(), decodedByte);
                holder.ivIcon.setImageDrawable(d);
                holder.tvName.setText(service.getFull_name());
                holder.llService.setBackgroundColor(Color.parseColor(service.getColor()));
            }

        }

    }


    @Override
    public int getItemCount() {
        return (null != itemsList ? itemsList.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected LinearLayout llService;
        protected ImageView ivIcon;
        protected TextView tvName;

        public SingleItemRowHolder(View view) {
            super(view);

            this.llService = (LinearLayout) view.findViewById(R.id.llService);
            this.ivIcon = (ImageView) view.findViewById(R.id.ivIcon);
            this.tvName = (TextView) view.findViewById(R.id.tvName);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mlistener.onServiciosHomeListDataAdapterClicked(view, this.getPosition());
        }
    }

}
