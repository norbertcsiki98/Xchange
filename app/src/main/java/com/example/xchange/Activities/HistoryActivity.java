package com.example.xchange.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.xchange.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class HistoryActivity extends AppCompatActivity {


    int i = 0;
    private Button Generate_rates_button;
    private DatePicker simpleDatePicker;
    private RequestQueue mQueue;
    String value;
    private ArrayList<String> get_responses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Generate_rates_button = (Button) findViewById(R.id.generate);
        simpleDatePicker = (DatePicker) findViewById(R.id.simpleDatePicker);
        mQueue = Volley.newRequestQueue(getApplicationContext());

        // Sending actual euro values to Notification activity

        Generate_rates_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                new Thread(new Runnable() {
                    public void run() {
                        // a potentially time consuming task
                        extract_euro_values();
                    }
                }).start();

                ArrayList<String> Vals = new ArrayList<>();
                Vals = get_responses;
                Vals.removeAll(Collections.singleton(null));

                if (i == 2) {

                    SharedPreferences preferences = getSharedPreferences("CONTAINER", MODE_PRIVATE);
                    String act_val_euro = preferences.getString("ACT_VAL", "val");
                    String email = preferences.getString("EMAIL", "email");
                    //sendEmail(act_val_euro,email);
                    Intent Chart_Intent = new Intent(HistoryActivity.this, NotificationsActivity.class);
                    Chart_Intent.putExtra("Value_Set", Vals);
                    startActivity(Chart_Intent);

                }
            }
        });
    }


    // Building the request (URL) for a new value

    private void Build_Date_json(String date) {
        String url = "https://api.exchangeratesapi.io/" + date;
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            public void onResponse(JSONObject response) {

                try {

                    JSONObject object = response.getJSONObject("rates");
                    value = object.getString("RON");
                    get_responses.add(value);

                } catch (JSONException e) {

                    e.printStackTrace();

                }

            }
        }, new Response.ErrorListener() {
            @Override

            public void onErrorResponse(VolleyError volleyError) {

                String message = null;
                if (volleyError instanceof NetworkError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                } else if (volleyError instanceof ServerError) {
                    message = "The server could not be found. Please try again after some time!!";
                } else if (volleyError instanceof AuthFailureError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                } else if (volleyError instanceof ParseError) {
                    message = "Parsing error! Please try again after some time!!";
                } else if (volleyError instanceof NoConnectionError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                } else if (volleyError instanceof TimeoutError) {
                    message = "Connection TimeOut! Please check your internet connection.";
                }
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            }
        });

        mQueue.add(request);

    }

    // Generate the new date and passing it to Build function

    public void extract_euro_values() {

        int chosed_year = simpleDatePicker.getYear() + 1;
        int actual_year = Calendar.getInstance().get(Calendar.YEAR) + 1;
        int actual_month = Calendar.getInstance().get(Calendar.MONTH) + 1;

        for (int year = chosed_year; year < actual_year; year++) {
            for (int month = 1; month <= 12; month++) {
                if (year == actual_year && month > actual_month) {
                    break;
                }
                String str_year = Integer.toString(year);
                String str_month = Integer.toString(month);
                String data = str_year + "-" + str_month + "-1";
                Build_Date_json(data);
            }
        }
    }

    protected void sendEmail(String value, String email) {
        Log.i("Send email", "NEW VALUE : " + value);
        String[] TO = {""};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "NEW VALUE DETECTED");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "The new value of euro is " + value);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(HistoryActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

}


