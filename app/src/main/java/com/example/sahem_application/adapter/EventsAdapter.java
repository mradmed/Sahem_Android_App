package com.example.sahem_application.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sahem_application.EventDetailsActivity;
import com.example.sahem_application.Model.Event;
import com.example.sahem_application.R;


import java.util.ArrayList;
import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventViewHolder> {


    List<Event>events=new ArrayList<>();
    Context context;

    public EventsAdapter(Context ct , List<Event> e1){
        context=ct;
        events=e1;
    }



    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.event_row,parent,false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {

        holder.txtName.setText(events.get(position).getEventName());
        holder.txtDescription.setText(events.get(position).getDescription());
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context, EventDetailsActivity.class);
                i.putExtra("idEvent",events.get(position).getIdEvent());
                i.putExtra("eventName",events.get(position).getEventName());
                i.putExtra("description",events.get(position).getDescription());
                i.putExtra("startDate",events.get(position).getStartDate());
                i.putExtra("endDate",events.get(position).getEndDate());
                i.putExtra("adresse",events.get(position).getAddress());
                i.putExtra("location",events.get(position).getLocation());
                i.putExtra("imgEvent",events.get(position).getEventImg());
                i.putExtra("status",events.get(position).getStatus());
                i.putExtra("idOrg",events.get(position).getIdOrg());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        //return names.size();
        return events.size();
    }

    public class EventViewHolder extends RecyclerView.ViewHolder{

        TextView txtName,txtDescription;
        de.hdodenhof.circleimageview.CircleImageView img;
        ConstraintLayout mainLayout;


        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName =itemView.findViewById(R.id.txtName);
            txtDescription=itemView.findViewById(R.id.txtDescption);
            img=itemView.findViewById(R.id.img);
            mainLayout=itemView.findViewById(R.id.mainLayout);
        }
    }
}
