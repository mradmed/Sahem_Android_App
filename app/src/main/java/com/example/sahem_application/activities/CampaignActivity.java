package com.example.sahem_application.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sahem_application.R;

public class CampaignActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign);
        ImageView iv = findViewById(R.id.item_book_img);
        TextView descTV= findViewById(R.id.descriptiontv);
        TextView titleTv= findViewById(R.id.item_book_title);
        Intent i = getIntent();
        Bundle e = i.getExtras();
        System.out.println(e.getString("campaign_description"));
        descTV.setText(e.getString("campaign_description"));
        titleTv.setText(e.getString("campaign_name"));
        Glide.with(this).load("http://10.0.2.2:3000/profile/"+e.getString("campaign_img")).into(iv);


    }
}