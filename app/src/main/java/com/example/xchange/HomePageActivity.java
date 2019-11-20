package com.example.xchange;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import java.util.Currency;
import java.util.Locale;

public class HomePageActivity extends AppCompatActivity {


    EditText actEur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        actEur = findViewById(R.id.actualeuro);
        //String euro = Currency.getInstance(Locale.GERMANY).getCurrencyCode();
        //actEur.setText(euro);

    }
}
