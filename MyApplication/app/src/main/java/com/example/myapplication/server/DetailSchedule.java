package com.example.myapplication.server;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TextureView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DetailSchedule extends AppCompatActivity {
    TextView tp,max,min,suggest;
    ImageView imageView;
    String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_schedule);

        anhXa();
        Intent intent=getIntent();
        tp.setText(intent.getStringExtra("address"));
        date=intent.getStringExtra("date");


    }
    public void anhXa(){
        tp=(TextView)findViewById(R.id.textView3);
        max=(TextView)findViewById(R.id.textView5);
        min=(TextView)findViewById(R.id.textView6);
        suggest=(TextView)findViewById(R.id.textView8);
        imageView=(ImageView)findViewById(R.id.imageView2);

    }

}
