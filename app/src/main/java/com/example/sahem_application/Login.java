package com.example.sahem_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sahem_application.activities.AllCampaigns;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    EditText edit_email, edit_password;

    TextView txtRegister, txtForgetPwd;
    Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edit_email = (EditText) findViewById(R.id.username);
        edit_password = (EditText) findViewById(R.id.password);


        txtRegister = (TextView) findViewById(R.id.txtReg);
        txtForgetPwd = (TextView) findViewById(R.id.txtForgetPwd);
        btnLogin = (Button) findViewById(R.id.login);

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Registration.class);
                startActivity(i);
            }
        });

        txtForgetPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Forgetpassword.class);
                startActivity(i);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginaction(v);
            }
        });




    }

    public void loginaction(View view){



            RequestQueue requestQueue = Volley.newRequestQueue(Login.this);

            String url = "http://10.0.2.2:3000/login";

        Map<String, String> params = new HashMap();
        params.put("email", edit_email.getText().toString());
        params.put("password", edit_password.getText().toString());

        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.i("message",response.getString("message"));
                    if(response.getInt("code") == 200){
                        edit_email.setText("");
                        edit_password.setText("");
                        Intent i = new Intent(Login.this, MainActivity.class);
                        i.putExtra("Role",response.getString("role"));
                        i.putExtra("idUser",response.getInt("id"));
                        startActivity(i);
                    }
                    else{
                        Toast toast = Toast.makeText(Login.this, response.getString("message"), Toast.LENGTH_SHORT);
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




}