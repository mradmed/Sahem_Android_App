package com.example.sahem_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ResetPassword extends AppCompatActivity {


    public void ResetPass(String email,String password){
        RequestQueue requestQueue = Volley.newRequestQueue(ResetPassword.this);

        String url = "http://10.0.2.2:3000/user/password";

        Map<String, String> params = new HashMap();
        params.put("email", email);
        params.put("password",password);


        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.PUT, url, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    if(response.getInt("code") == 200){

                        Toast toast = Toast.makeText(ResetPassword.this, "Password Reset", Toast.LENGTH_SHORT);
                        toast.show();
                        Intent i = new Intent(ResetPassword.this, Login.class);

                        startActivity(i);

                    }
                    else{
                        Toast toast = Toast.makeText(ResetPassword.this, "Error", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                //TODO: handle failure
            }
        });
        requestQueue.add(jsonRequest);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        Intent i = getIntent();
        Bundle extras = i.getExtras();
        String email = extras.getString("email");

        EditText pass1= findViewById(R.id.password1);
        EditText pass2= findViewById(R.id.password2);

        Button btnNext = findViewById(R.id.btnContinue);


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ps1= pass1.getText().toString();
                String ps2= pass2.getText().toString();
                if(ps1.equals(ps2)){
                    ResetPass(email,ps1);
                }
                else {
                    Toast toast = Toast.makeText(ResetPassword.this, "Passwords Don't match", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }


}