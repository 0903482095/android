package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.model.ScheduleDTO;
import com.example.myapplication.model.UserDTO;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import static android.widget.Toast.LENGTH_SHORT;

public class EditSchedule extends AppCompatActivity {
    EditText address,date;
    Button done;
    ScheduleDTO scheduleDTO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_schedule);

        anhXa();
        Intent intent=getIntent();

        scheduleDTO =  new ScheduleDTO(intent.getIntExtra("id",0),intent.getStringExtra("address"),intent.getStringExtra("date") ,intent.getIntExtra("idUser",0));

        address.setText(scheduleDTO.getAddress());
        date.setText(scheduleDTO.getDate());

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallRest();
            }
        });


    }
    public void CallRest(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.6.104:8080/updateSchedule";

        JsonObjectRequest objectRequest = null;
        ScheduleDTO schedule=new ScheduleDTO(scheduleDTO.getId(),address.getText().toString().trim(),date.getText().toString().trim(),scheduleDTO.getId_user());

        try {
            objectRequest = new JsonObjectRequest(Request.Method.PUT, url, new JSONObject(new Gson().toJson(schedule)),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            startActivity(new Intent(EditSchedule.this,ScheduleActivity.class));
                            finish();
                            Toast.makeText(EditSchedule.this,"update thất bại",LENGTH_SHORT).show();

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    startActivity(new Intent(EditSchedule.this,ScheduleActivity.class));
                    Toast.makeText(EditSchedule.this,"update thành công",LENGTH_SHORT).show();
                }
            }
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        queue.add(objectRequest);
    }

    public void anhXa(){
        address=(EditText)findViewById(R.id.editText7);
        date=(EditText)findViewById(R.id.editText8);
        done=(Button)findViewById(R.id.button3);
    }

}
