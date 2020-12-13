package com.example.sahem_application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EventDetailsActivity  extends AppCompatActivity {
    TextView txtStatus,txtTitle,txtdescrip,OrgName,txtDedline,txtRaised;
    ImageView imgCampaign,imgOrg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        txtStatus=(TextView)findViewById(R.id.txtStatus);
        txtTitle=(TextView)findViewById(R.id.txtTitle);
        txtdescrip=(TextView)findViewById(R.id.txtdescrip);
        txtDedline=(TextView)findViewById(R.id.txtDedline);
        OrgName=(TextView)findViewById(R.id.OrgName);
        txtRaised=(TextView)findViewById(R.id.txtRaised);
        imgCampaign=(ImageView)findViewById(R.id.imgCampaign);
        imgOrg=(ImageView)findViewById(R.id.imgOrg);

        int idEvent =Integer.parseInt(getIntent().getExtras().getString("idEvent"));
        int idOrg =Integer.parseInt(getIntent().getExtras().getString("idOrg"));
        txtTitle.setText(getIntent().getExtras().getString("eventName"));
        txtdescrip.setText(getIntent().getExtras().getString("description"));
        txtDedline.setText(getIntent().getExtras().getString("startDate"));
        OrgName.setText(getIntent().getExtras().getString("endDate"));
        txtRaised.setText(getIntent().getExtras().getString("adresse"));
        //txtStatus.setText(getIntent().getExtras().getString("location"));
        txtStatus.setText(getIntent().getExtras().getString("status"));



    }
    public void BackAction(View v){
        Intent i = new Intent(EventDetailsActivity.this, MainActivity.class);
        startActivity(i);
    }


}
