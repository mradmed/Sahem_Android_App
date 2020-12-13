package com.example.sahem_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CodeVerifpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_verifpage);
        EditText nb1= (EditText) findViewById(R.id.nb1);
        EditText nb2= (EditText) findViewById(R.id.nb2);
        EditText nb3= (EditText) findViewById(R.id.nb3);
        EditText nb4= (EditText) findViewById(R.id.nb4);
        Button btnnext = (Button) findViewById(R.id.btnContinue);
        Intent i = getIntent();
        Bundle extras = i.getExtras();

        String vcode  = extras.getString("code");
        String email = extras.getString("email");


        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inptcode = nb1.getText().toString()+nb2.getText().toString()+nb3.getText().toString()+nb4.getText().toString();


                if( vcode.equals(inptcode)) {
                    Intent i = new Intent(CodeVerifpage.this, ResetPassword.class);
                    i.putExtra("email",email);
                    startActivity(i);
                }
                else
                {
                    Toast toast = Toast.makeText(CodeVerifpage.this, "Incorrect", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }
}