package com.example.xchange.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.xchange.Database.DatabaseHelper;
import com.example.xchange.R;

import android.widget.Button;
import android.content.Intent;

import java.util.ArrayList;

import static com.example.xchange.Database.DatabaseHelper.COLUMN_EMAIL;
import static com.example.xchange.Database.DatabaseHelper.TABLE_NAME;

public class ProfileActivity extends AppCompatActivity {
    TextView fnameTV, lnameTV, emailTV, birthDateTV;
    Button logoutBtn;
    DatabaseHelper db = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        String email = getIntent().getStringExtra("email");
        fnameTV = findViewById(R.id.firstName);
        lnameTV = findViewById(R.id.lastName);
        emailTV = findViewById(R.id.email);
        birthDateTV = findViewById(R.id.birthDate);
        logoutBtn = findViewById(R.id.LogOutButton);


        SharedPreferences preferences = getSharedPreferences("CONTAINER", MODE_PRIVATE);
        String Activ_email = preferences.getString("EMAIL", "email");

        ArrayList<String> datas = new ArrayList<>();
        datas = db.getDatas(Activ_email);
        fnameTV.setText(datas.get(0));
        lnameTV.setText(datas.get(1));
        emailTV.setText(datas.get(2));
        birthDateTV.setText(datas.get(3));

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Logout_intent = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(Logout_intent);
            }
        });


    }
}
