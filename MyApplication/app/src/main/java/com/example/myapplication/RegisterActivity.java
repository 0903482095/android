package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.model.UserDTO;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import static android.widget.Toast.LENGTH_SHORT;

public class RegisterActivity extends AppCompatActivity {


    EditText username,email,date,address;
    Button btn;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        anhXa();

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,MainActivity.class));
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallRest();
            }
        });
    }

    public void CallRest() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.6.104:8080/register";

        JsonObjectRequest objectRequest = null;
        UserDTO user=new UserDTO(username.getText().toString().trim(),email.getText().toString().trim(),address.getText().toString().trim(),date.getText().toString().trim());

        try {
            objectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(new Gson().toJson(user)),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(RegisterActivity.this,"Đăng kí thành công",LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(RegisterActivity.this,"Đăng kí thành công",LENGTH_SHORT).show();
                    try {
                        Thread.sleep(2000);
                        startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        queue.add(objectRequest);
    }
    public void anhXa(){
        username=(EditText) findViewById(R.id.editText3);
        email=(EditText) findViewById(R.id.editText4);
        date=(EditText) findViewById(R.id.editText5);
        address=(EditText) findViewById(R.id.editText6);
        btn=(Button) findViewById(R.id.button2);
        textView=(TextView) findViewById(R.id.textView4);
    }
}
