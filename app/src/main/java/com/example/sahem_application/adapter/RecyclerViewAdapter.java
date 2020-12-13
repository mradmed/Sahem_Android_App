package com.example.sahem_application.adapter;

import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.sahem_application.Model.Campaign;
import com.example.sahem_application.R;
import com.example.sahem_application.activities.CampaignActivity;


import java.util.List;



public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext ;
    private List<Campaign> mData ;
    RequestOptions option;


    public RecyclerViewAdapter(Context mContext, List<Campaign> mData) {
        this.mContext = mContext;
        this.mData = mData;

        // Request option for Glide
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.activity_campaign_row,parent,false) ;
        final MyViewHolder viewHolder = new MyViewHolder(view) ;
        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(mContext, CampaignActivity.class);
                i.putExtra("campaign_name",mData.get(viewHolder.getAdapterPosition()).getCmpName());
                i.putExtra("campaign_description",mData.get(viewHolder.getAdapterPosition()).getDescription());
                i.putExtra("campaign_status",mData.get(viewHolder.getAdapterPosition()).getStatus());
                i.putExtra("campaign_target",mData.get(viewHolder.getAdapterPosition()).getTarget());
                i.putExtra("campaign_raised",mData.get(viewHolder.getAdapterPosition()).getRaised());
                i.putExtra("campaign_nbdons",mData.get(viewHolder.getAdapterPosition()).getNbDons());
                i.putExtra("campaign_img",mData.get(viewHolder.getAdapterPosition()).getImgUrl());
                i.putExtra("campaign_idCmp",mData.get(viewHolder.getAdapterPosition()).getIdCmp());
                i.putExtra("campaign_idOrg",mData.get(viewHolder.getAdapterPosition()).getIdOrg());
                i.putExtra("campaign_endDate",mData.get(viewHolder.getAdapterPosition()).getEndDate());
                i.putExtra("campaign_creationDate",mData.get(viewHolder.getAdapterPosition()).getCreationDate());

                mContext.startActivity(i);

            }
        });




        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.tv_name.setText(mData.get(position).getCmpName());
        holder.tv_status.setText(mData.get(position).getStatus());

        holder.pb_raised.setProgress((int)mData.get(position).getRaised());
        holder.pb_raised.setMax((int)mData.get(position).getTarget());
        holder.tv_category.setText("Charity");

        // Load Image from the internet and set it into Imageview using Glide

        Glide.with(mContext).load("http://10.0.2.2:3000/profile/"+mData.get(position).getImgUrl()).apply(option).into(holder.img_thumbnail);



    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name ;
        TextView tv_status ;
        ProgressBar pb_raised;
        TextView tv_category;
        ImageView img_thumbnail;
        LinearLayout view_container;





        public MyViewHolder(View itemView) {
            super(itemView);

            view_container = itemView.findViewById(R.id.container);
            tv_name = itemView.findViewById(R.id.cmpNameTV);
            tv_category = itemView.findViewById(R.id.catTV);
            tv_status = itemView.findViewById(R.id.statusTV);
            pb_raised = itemView.findViewById(R.id.Progressbar);
            img_thumbnail = itemView.findViewById(R.id.thumbnail);

        }
    }

}

