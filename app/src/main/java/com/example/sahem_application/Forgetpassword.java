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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

public class Forgetpassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);

        Button btnContinue = (Button) findViewById(R.id.btnContinue);
        EditText email_text = (EditText) findViewById(R.id.email);

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VerifAction(email_text.getText().toString());
            }
        });

    }
    public void BackAction(View v){
        Intent i = new Intent(Forgetpassword.this, Login.class);
        startActivity(i);
    }

    String randomString(int len){
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(len);
        for(int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

    public void SendCode(String email){
        RequestQueue requestQueue = Volley.newRequestQueue(Forgetpassword.this);

        String url = "http://10.0.2.2:3000/reset";
        String vcode = randomString(4);
        Map<String, String> params = new HashMap();
        params.put("to", email);
        params.put("subject","Reset password");
        params.put("text","This is your reset code : "+vcode);

        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    if(response.getInt("code") == 200){

                        Toast toast = Toast.makeText(Forgetpassword.this, "Email Sent !", Toast.LENGTH_SHORT);
                        toast.show();
                        Intent i = new Intent(Forgetpassword.this, CodeVerifpage.class);
                        i.putExtra("code",vcode);
                        i.putExtra("email",email);
                        startActivity(i);

                    }
                    else{
                        Toast toast = Toast.makeText(Forgetpassword.this, "Error", Toast.LENGTH_SHORT);
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





    public void VerifAction(String email){



        RequestQueue requestQueue = Volley.newRequestQueue(Forgetpassword.this);

        String url = "http://10.0.2.2:3000/verifmail";

        Map<String, String> params = new HashMap();
        params.put("email", email);


        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.i("message",response.getString("message"));
                    if(response.getInt("code") == 200){
                        SendCode(email);

                    }
                    else{
                        Toast toast = Toast.makeText(Forgetpassword.this, response.getString("message"), Toast.LENGTH_SHORT);
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