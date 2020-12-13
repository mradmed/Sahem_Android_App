package com.example.sahem_application;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.sahem_application.Model.Event;
import com.example.sahem_application.adapter.EventsAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EventsFragement extends Fragment {

    AutoCompleteTextView cbxCategory;
    private static final  String[] Catg=new String[]{"Medical","Social","Food"};
    RecyclerView recyclerView;
    ImageView imgCateg;
    private List<Event> events=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragement_events,container,false);

        cbxCategory=(AutoCompleteTextView) view.findViewById(R.id.txtCat);
        imgCateg=(ImageView) view.findViewById(R.id.imgCbx);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_dropdown_item_1line,Catg);
        cbxCategory.setAdapter(adapter);
        imgCateg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                cbxCategory.showDropDown();
            }
        });
        AllEvents(view);

        return view;
    }
    private void AllEvents(View v){

        String url = "http://10.0.2.2:3000/events";
        List<Event> events=new ArrayList<>();
        /*List<String> names=new ArrayList<>();
        List<String> descriptions=new ArrayList<>();
        List<String>images=new ArrayList<>();*/
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                Log.d("Response", response.toString());
                // Parsing json
                for (int i = 0; i < response.length(); i++) {
                    try {

                        JSONObject jsonObject=response.getJSONObject(i);
                        String idEvent=jsonObject.getString("idEvent");
                        String idOrg=jsonObject.getString("idOrg");
                        String eventName=jsonObject.getString("eventName");
                        String description=jsonObject.getString("description");
                        String startDate=jsonObject.getString("startDate");
                        String endDate=jsonObject.getString("endDate");
                        String address=jsonObject.getString("address");
                        String location=jsonObject.getString("location");
                        String eventBudget=jsonObject.getString("eventBudget");
                        String eventImg=jsonObject.getString("eventImg");
                        String nbSponsors=jsonObject.getString("nbSponsors");
                        String status=jsonObject.getString("status");
                        Event event=new Event(idEvent,idOrg,eventName,description,startDate,endDate,address,location,
                                eventBudget,eventImg,nbSponsors,status);
                        System.out.println(event.toString());
                        if(events.add(event)){System.out.println("add true");}else{System.out.println("add false");}

                        /*names.add(eventName);
                        descriptions.add(description);
                        images.add(eventImg);*/



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                /*System.out.println("test:"+names.get(0));
                System.out.println("name size:"+names.size());*/
                //RecycleView
                recyclerView=(RecyclerView) v.findViewById(R.id.listEvents);
                EventsAdapter eventsAdapter=new EventsAdapter(getContext(),events);
                recyclerView.setAdapter(eventsAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error response", error.toString());
                // hidePDialog();
            }
        });

        requestQueue.add(jsonArrayRequest);

    }


}
