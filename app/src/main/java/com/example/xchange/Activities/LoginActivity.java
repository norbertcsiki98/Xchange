package com.example.xchange.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.xchange.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void goToRegister(View view)
    {
        Intent intent=new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void goToHomePage(View view)
    {
        Intent intent=new Intent(this, HomePageActivity.class);
        startActivity(intent);
    }



}
