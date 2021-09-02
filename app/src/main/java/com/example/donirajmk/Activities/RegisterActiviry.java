package com.example.donirajmk.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.donirajmk.R;
import com.example.donirajmk.Utils.Endpoints;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.donirajmk.Utils.VolleySingleton;

import java.util.HashMap;
import java.util.Map;

public class RegisterActiviry extends AppCompatActivity {
    private EditText nameEt,cityEt,passwordEt,mobileEt;
    private Button register_Button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_activiry);

        nameEt = findViewById(R.id.name);
        cityEt = findViewById(R.id.city);
        passwordEt = findViewById(R.id.password);
        mobileEt = findViewById(R.id.number);
        register_Button = findViewById(R.id.register_button);

        register_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name,city,password,mobile;
                name = nameEt.getText().toString();
                city = cityEt.getText().toString();
                password = passwordEt.getText().toString();
                mobile = mobileEt.getText().toString();
                if(isValid(name,city,password,mobile)){
                    register(name,city,password,mobile);
                }
               //ShowMsg(name + "\n" + city + "\n" + password + "\n" + mobile );
            }
        });
    }

    private void ShowMsg(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private boolean isValid(String name,String city,String password,String mobile){
        if(name.isEmpty()){
            ShowMsg("Името е празно");
            return false;
        }
        else if(city.isEmpty()){
            ShowMsg("Град е задолжително");
            return false;
        }
        else if(mobile.isEmpty()){
            ShowMsg("Внесовте невалиден број.");
            return false;
        }
        return true;
    }

    private void register(final String name, final String city, final String password, final String mobile) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.register_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("Success")) {
                    Toast.makeText(RegisterActiviry.this, response, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActiviry.this, MainActivity.class));
                    RegisterActiviry.this.finish();
                } else {
                    Toast.makeText(RegisterActiviry.this, response, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegisterActiviry.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                Log.d("VOLLEY", error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("city", city);
                params.put("mobile", mobile);
                params.put("password", password);
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }





}

