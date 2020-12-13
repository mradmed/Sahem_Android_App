package com.example.sahem_application;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.sahem_application.Model.Campaign;
import com.example.sahem_application.activities.AllCampaigns;
import com.example.sahem_application.adapter.RecyclerViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CampaignsFragement extends Fragment {

    private String URL_JSON = "http://10.0.2.2:3000/campaignslist";
    private JsonArrayRequest ArrayRequest ;
    private RequestQueue requestQueue ;
    private List<Campaign> lstCamp = new ArrayList<>();
    private RecyclerView myrv ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.fragement_campaigns,container,false);
        Button CreateBtn= view.findViewById(R.id.createCamp);

        Bundle e= getArguments();
        String role= e.getString("Role");
        int id = e.getInt("idUser");
        if(role.equals("Association"))
        {
            CreateBtn.setVisibility(View.VISIBLE);
        }
        else{
            CreateBtn.setVisibility(View.INVISIBLE);
        }

        CreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(getContext(), AddCampaign.class);
                newIntent.putExtra("idUser",id);
                startActivity(newIntent);
            }
        });

        myrv = view.findViewById(R.id.rv);
        jsoncall();
         return view;
    }


    public void jsoncall() {


        ArrayRequest = new JsonArrayRequest(URL_JSON, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject;


                for (int i = 0 ; i<response.length();i++) {

                    //Toast.makeText(getApplicationContext(),String.valueOf(i),Toast.LENGTH_SHORT).show();

                    try {

                        jsonObject = response.getJSONObject(i);
                        Campaign c = new Campaign();
                        c.setIdCmp(jsonObject.getInt("idCmp"));
                        c.setIdOrg(jsonObject.getInt("idOrg"));
                        c.setCmpName(jsonObject.getString("cmpName"));
                        c.setStatus(jsonObject.getString("status"));
                        c.setDescription(jsonObject.getString("description"));
                        c.setImgUrl(jsonObject.getString("imgURL"));
                        c.setEndDate(jsonObject.getString("endDate"));
                        c.setCreationDate(jsonObject.getString("creationDate"));
                        c.setRaised(jsonObject.getDouble("raised"));
                        c.setTarget(jsonObject.getDouble("target"));
                        c.setNbDons(jsonObject.getInt("nbDons"));


                        //Toast.makeText(MainActivity.this,anime.toString(),Toast.LENGTH_SHORT).show();
                        lstCamp.add(c);
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


                //Toast.makeText(AllCampaigns.this,"Size of Liste "+String.valueOf(lstCamp.size()),Toast.LENGTH_SHORT).show();


                setRvadapter(lstCamp);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(ArrayRequest);
    }



    public void setRvadapter (List<Campaign> lst) {

        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(getContext(),lst) ;
        myrv.setLayoutManager(new LinearLayoutManager(getContext()));
        myrv.setAdapter(myAdapter);




    }
}
