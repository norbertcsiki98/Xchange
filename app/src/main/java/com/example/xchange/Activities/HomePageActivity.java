package com.example.xchange.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;



import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.xchange.Activities.Menu.MenuView;
import com.example.xchange.R;
import org.json.JSONException;
import org.json.JSONObject;

public class HomePageActivity extends AppCompatActivity {


    private TextView  actualEuro;
    private RequestQueue mQueue;
    Button readButton,exchangeNowBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);



        readButton = findViewById(R.id.readbutton);
        exchangeNowBtn=findViewById(R.id.gotoexhange);
        actualEuro = findViewById(R.id.actualeuro);
        mQueue = Volley.newRequestQueue(this);
        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Toast.makeText(v.getContext(), "button clicked", Toast.LENGTH_SHORT).show();
                readJson1();


            }
        });


        exchangeNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readJson();
            }
        });

    }

    //Jump to exchange activity by pressing the exchange now button

    private void readJson() {
        final String url = "https://api.exchangeratesapi.io/latest";
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {
                    String email = getIntent().getStringExtra("email");
                    JSONObject object = response.getJSONObject("rates");
                    String value = object.getString("RON");
                    actualEuro.setText(value);
                    SharedPreferences preferences=getSharedPreferences("CONTAINER",MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("ACT_VAL",value);
                    editor.commit();
                    Intent intent = new Intent(HomePageActivity.this, MenuView.class);
                    intent.putExtra("email",email);
                    Intent i = new Intent(HomePageActivity.this, ExchangeActivity.class);
                    i.putExtra("email",email);
                    i.putExtra("STRING_I_NEED", value);
                    startActivity(i);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });


        mQueue.add(request);
    }

    //Extract the actual euro value from API

    private void readJson1() {
        final String url = "https://api.exchangeratesapi.io/latest";
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {

                    JSONObject object = response.getJSONObject("rates");
                    String value = object.getString("RON");
                    actualEuro.setText(value);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });


        mQueue.add(request);
    }
}
