package com.example.xchange.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.example.xchange.R;
import org.json.JSONException;
import org.json.JSONObject;

public class HomePageActivity extends AppCompatActivity {


    private TextView  actualEuro;
    private RequestQueue mQueue;
    Button readButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);



        readButton = findViewById(R.id.readbutton);
        actualEuro = findViewById(R.id.actualeuro);
        mQueue = Volley.newRequestQueue(this);
        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Toast.makeText(v.getContext(), "button clicked", Toast.LENGTH_SHORT).show();
                readJson();


            }
        });

    }

    private void readJson() {
        final String url = "https://api.exchangeratesapi.io/latest";
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {

                    Log.d("aaa",response.toString());
                    JSONObject object = response.getJSONObject("rates");
                    String value = object.getString("RON");
                    actualEuro.setText(value);
                    Intent i = new Intent(HomePageActivity.this, ExchangeActivity.class);
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
}
