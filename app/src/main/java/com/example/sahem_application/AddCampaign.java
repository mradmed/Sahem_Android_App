package com.example.sahem_application;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.sahem_application.activities.AllCampaigns;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class AddCampaign extends AppCompatActivity {


    private static final int PICK_IMAGE = 1;
    Bitmap mBitmap;
    ApiService apiService;
    String UploadedImage;
    EditText CampaignName,Description,endDate,TargetTF;


    Uri imageUri;





    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_campaign);
         CampaignName = findViewById(R.id.cmpName);
         Description = findViewById(R.id.desc);
         endDate = findViewById(R.id.cmpEnd);
         TargetTF = findViewById(R.id.targetTv);




        ImageView iv = findViewById(R.id.pickimage);
        initRetrofitClient();
        Button UpBt = findViewById(R.id.campUpload);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(gallery, "Select Picture"), PICK_IMAGE);
              /*  Toast toast = Toast.makeText(AddCampaign.this, "Working", Toast.LENGTH_SHORT);
                toast.show();*/
            }
        });

        UpBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBitmap != null){

                    CreateUpload();
                }

                else {
                    Toast.makeText(getApplicationContext(), "Bitmap is null. Try again", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void initRetrofitClient() {
        OkHttpClient client = new OkHttpClient.Builder().build();

        apiService = new Retrofit.Builder().baseUrl("http://10.0.2.2:3000").client(client).build().create(ApiService.class);
    }


    private void multipartImageUpload() {
        try {
            File filesDir = getApplicationContext().getFilesDir();
            File file = new File(filesDir, "image" + ".png");

            OutputStream os;
            try {
                os = new FileOutputStream(file);
                mBitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
                os.flush();
                os.close();
            } catch (Exception e) {
                Log.e(getClass().getSimpleName(), "Error writing bitmap", e);
            }

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            mBitmap.compress(Bitmap.CompressFormat.PNG, 0, bos);
            byte[] bitmapdata = bos.toByteArray();


            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();


            RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
            MultipartBody.Part body = MultipartBody.Part.createFormData("upload", file.getName(), reqFile);
            RequestBody name = RequestBody.create(MediaType.parse("text/plain"), "upload");

            Call<ResponseBody> req = apiService.postImage(body, name);
            req.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {


                    if (response.code() == 200) {
                        Toast.makeText(getApplicationContext(), "Uploaded Successfully", Toast.LENGTH_SHORT).show();

                        try {
                            UploadedImage =response.body().string();
                            System.out.println("Uploaded Image name :"+ UploadedImage);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                    Toast.makeText(getApplicationContext(), response.code() + " ", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                    Toast.makeText(getApplicationContext(), "Request failed", Toast.LENGTH_SHORT).show();
                    t.printStackTrace();
                }
            });


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            final InputStream imageStream;
            try {
                imageStream = getContentResolver().openInputStream(imageUri);
                 mBitmap = BitmapFactory.decodeStream(imageStream);

                EditText imgName = findViewById(R.id.img);
                imgName.setText(imageUri.getPath());
                imgName.setEnabled(false);
                multipartImageUpload();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }



        }

    else {
        Toast.makeText(AddCampaign.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
    }

    }


    public void CreateUpload(){

        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);

        RequestQueue requestQueue = Volley.newRequestQueue(AddCampaign.this);

        String url = "http://10.0.2.2:3000/campaign";

        Map<String, String> params = new HashMap();
        params.put("imgURL", UploadedImage);
        params.put("idOrg","1");
        params.put("cmpName",CampaignName.getText().toString());
        params.put("description",Description.getText().toString());
        params.put("target",TargetTF.getText().toString());
        params.put("raised","0");
        params.put("nbDons","0");
        params.put("creationDate",formattedDate);
        params.put("endDate",endDate.getText().toString());
        params.put("status","Running");


        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    if(response.getInt("code") == 200){



                    }
                    else{
                        Toast toast = Toast.makeText(AddCampaign.this, "Failed", Toast.LENGTH_SHORT);
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


