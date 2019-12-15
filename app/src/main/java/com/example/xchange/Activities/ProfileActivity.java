package com.example.xchange.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.xchange.R;

import android.widget.Button;
import android.content.Intent;

public class ProfileActivity extends AppCompatActivity {
    TextView fnameTV,lnameTV,emailTV,birthDateTV;
    Button logoutBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        String email=getIntent().getStringExtra("email");
        fnameTV=findViewById(R.id.firstName);
        lnameTV=findViewById(R.id.lastName);
        emailTV=findViewById(R.id.email);
        birthDateTV=findViewById(R.id.birthDate);
        logoutBtn=findViewById(R.id.LogOutButton);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ProfileActivity.this,LoginActivity.class);
            }
        });


    }
}
