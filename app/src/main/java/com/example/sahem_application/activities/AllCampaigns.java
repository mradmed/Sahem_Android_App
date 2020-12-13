package com.example.sahem_application.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.sahem_application.AddCampaign;
import com.example.sahem_application.Model.Campaign;
import com.example.sahem_application.R;
import com.example.sahem_application.adapter.RecyclerViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AllCampaigns extends AppCompatActivity {

    private String URL_JSON = "http://10.0.2.2:3000/campaignslist";
    private JsonArrayRequest ArrayRequest ;
    private RequestQueue requestQueue ;
    private List<Campaign> lstCamp = new ArrayList<>();
    private RecyclerView myrv ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_campaigns);
        Button CreateBtn= findViewById(R.id.createCamp);
        Intent i = getIntent();
        Bundle e= i.getExtras();
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
                Intent newIntent = new Intent(AllCampaigns.this, AddCampaign.class);
                newIntent.putExtra("idUser",id);
                startActivity(newIntent);
            }
        });

        myrv = findViewById(R.id.rv);
        jsoncall();
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


        requestQueue = Volley.newRequestQueue(AllCampaigns.this);
        requestQueue.add(ArrayRequest);
    }



    public void setRvadapter (List<Campaign> lst) {

        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this,lst) ;
        myrv.setLayoutManager(new LinearLayoutManager(this));
        myrv.setAdapter(myAdapter);




    }


}