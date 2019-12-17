package com.example.xchange.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.xchange.Database.DatabaseHelper;
import com.example.xchange.R;


public class LoginActivity extends AppCompatActivity {
    private EditText emailET,passwordET;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailET=findViewById(R.id.email);
        passwordET=findViewById(R.id.password);


    }

    // User can create a new acount

    public void goToRegister(View view)
    {
        Intent intent=new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    // If data introduced is correct and it's available , user can view the application

    public void goToHomePage(View view)
    {
        db=new DatabaseHelper(this);
        String email = emailET.getText().toString();
        String password = passwordET.getText().toString();
        Boolean CheckEmailPassword = db.checkLogin(email, password);
        if (checkDataEntered() == true) {
            if (CheckEmailPassword == true) {

                SharedPreferences preferences=getSharedPreferences("CONTAINER",MODE_PRIVATE);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putString("EMAIL",email);
                editor.commit();

                Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
                intent.putExtra("email",email);
                startActivity(intent);
                //Toast.makeText(getContext(),"Successfully login",Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(LoginActivity.this, "Wrong email or password", Toast.LENGTH_SHORT).show();
            }

        }

    }

    // Check for existence

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    // Validating data

    public boolean checkDataEntered()
    {
        boolean dataValidation=true;
        if (isEmpty(emailET)) {
            emailET.setError("Email is required!");
            dataValidation=false;
        }

        if (isEmpty(passwordET)) {
            passwordET.setError("Password is required!");
            dataValidation=false;
        }
        return dataValidation;
    }
}
