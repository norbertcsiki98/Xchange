package com.example.xchange.Activities;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.xchange.R;

public class ExchangeActivity extends AppCompatActivity {

    EditText valueEdittext, resultEdittext;
    Button eurotoronButton, rontoeuroButton;
    String actualvalue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);

        valueEdittext = findViewById(R.id.value);
        resultEdittext = findViewById(R.id.resultedittext);
        eurotoronButton = findViewById(R.id.eurotoron);
        rontoeuroButton = findViewById(R.id.rontoeuro);


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                actualvalue = null;
            } else {
                actualvalue = extras.getString("STRING_I_NEED");
            }
        } else {
            actualvalue = (String) savedInstanceState.getSerializable("STRING_I_NEED");
        }
        Log.d("HELLO", actualvalue);

        eurotoronButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String finalvalue = valueEdittext.getText().toString();
                Double finalvalueD = Double.parseDouble(finalvalue);
                Double finalactualvalueD = Double.parseDouble(actualvalue);
                final Double sum = finalvalueD * finalactualvalueD;
                resultEdittext.setText(String.valueOf(sum));
            }
        });

        rontoeuroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String finalvalue = valueEdittext.getText().toString();
                Double finalvalueD = Double.parseDouble(finalvalue);
                Double finalactualvalueD = Double.parseDouble(actualvalue);
                final Double sum = finalvalueD / finalactualvalueD;
                resultEdittext.setText(String.valueOf(sum));



            }
        });


    }
}
