package com.example.sahem_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class Registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.roles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        Button btnRegister=(Button)findViewById(R.id.btnlogin);

        EditText email=(EditText)findViewById(R.id.email);
        EditText username=(EditText)findViewById(R.id.username);
        EditText password=(EditText)findViewById(R.id.password);
        EditText confirmPassword=(EditText)findViewById(R.id.confPassword);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    try {
                        RequestQueue requestQueue = Volley.newRequestQueue(Registration.this);
                        JSONObject jsonBody = new JSONObject();
                        jsonBody.put("userName", username.getText().toString());
                        jsonBody.put("email", email.getText().toString());
                        jsonBody.put("role", spinner.getSelectedItem().toString());

                        jsonBody.put("password", password.getText().toString());


                        final String mRequestBody = jsonBody.toString();

                        String url = "http://10.0.2.2:3000/register";
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.i("LOG_RESPONSE", response);
                                Toast toast = Toast.makeText(Registration.this, "Registration Done !", Toast.LENGTH_SHORT);
                                toast.show();
                                Intent intent = new Intent(Registration.this, Login.class);
                                startActivity(intent);

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("LOG_RESPONSE", error.toString());
                                Toast toast = Toast.makeText(Registration.this, "Register Failed !", Toast.LENGTH_SHORT);
                                toast.show();


                            }
                        }) {
                            @Override
                            public String getBodyContentType() {
                                return "application/json; charset=utf-8";
                            }

                            @Override
                            public byte[] getBody() throws AuthFailureError {
                                try {
                                    return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
                                } catch (UnsupportedEncodingException uee) {
                                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, "utf-8");
                                    return null;
                                }
                            }

                            @Override
                            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                                String responseString = "";
                                if (response != null) {
                                    responseString = String.valueOf(response.statusCode);
                                }
                                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                            }
                        };

                        requestQueue.add(stringRequest);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }




        });


    }

    public void BackAction(View v){
        Intent i = new Intent(Registration.this, Login.class);
        startActivity(i);
    }
}