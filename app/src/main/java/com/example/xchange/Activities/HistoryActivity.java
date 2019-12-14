package com.example.xchange.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.xchange.R;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;


public class HistoryActivity extends AppCompatActivity {


    private TextView act_euro_val;
    private Button Generate_rates_button;
    private DatePicker simpleDatePicker;
    private ArrayList<String> values_from_response = new ArrayList<>();
    //private RequestQueue mQueue;
    String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Generate_rates_button = (Button) findViewById(R.id.generate);
        simpleDatePicker = (DatePicker) findViewById(R.id.simpleDatePicker);
        //simpleDatePicker.setSpinnersShown(false);
        //mQueue = Volley.newRequestQueue(getApplicationContext());


        Generate_rates_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                try {
//                   extract_euro_values();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

                // SENDING DATA LIST FOR HISTORY LINE CHART

                Intent Chart_Intent = new Intent(HistoryActivity.this, NotificationsActivity.class);
                Chart_Intent.putExtra("Value_Set", values_from_response);
                startActivity(Chart_Intent);
            }
        });
    }

// CREATING THE REQUEST

    public void Build_Date_json(String data) throws IOException {

        String url = "https://api.exchangeratesapi.io/" + data;

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .header("Content-Type", "application/json")
                .build();


        client.newCall(request).enqueue(new Callback() {

            public void onFailure(Request request, IOException e) {
                String mMessage = e.getMessage().toString();
                Log.w("failure Response", mMessage);
                Toast.makeText(HistoryActivity.this, mMessage, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                final String mMessage = response.toString();


                HistoryActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(HistoryActivity.this, mMessage, Toast.LENGTH_SHORT).show();
                        try {
                            JSONObject obj = new JSONObject(mMessage);
                            JSONObject rates_obj = obj.getJSONObject("rates");
                            value = rates_obj.getString("RON");
                            values_from_response.add(value);

                        } catch (JSONException error) {
                            error.printStackTrace();
                        }

                    }
                });
            }
        });
    }

// SAVING EURO VALUES BY YEAR & MONTHS

    public void extract_euro_values() {
        int chosed_year = simpleDatePicker.getYear() + 1;
        int actual_year = Calendar.getInstance().get(Calendar.YEAR) + 1;
        int actual_month = Calendar.getInstance().get(Calendar.MONTH) + 1;

        for (int year = chosed_year; year <= actual_year; year++) {
            for (int month = 1; month <= 12; month++) {
                if (year == actual_year && month > actual_month) {
                    break;
                }
                String str_year = Integer.toString(year);
                String str_month = Integer.toString(year);
                String data = str_year + "-" + str_month + "-1";
                //Build_Date_json(data);
            }
        }
    }
}
