package com.example.xchange;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
public class RegisterActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getCanonicalName();

    private Button datePickerButton;
    private TextView birthDateView;
    private EditText fname;
    private EditText lname;
    private EditText email;
    private EditText password;
    private EditText confpassword;
    private Button registerBtn;
    private String birthDate;
    private String firstnameS,lastnameS,emailS,passwordS,birthdateS;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fname=findViewById(R.id.firstName);
        lname=findViewById(R.id.lastName);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        confpassword=findViewById(R.id.confirmPassword);
        birthDateView=findViewById(R.id.birthDate);
        registerBtn=findViewById(R.id.registerButton);
        db=new DatabaseHelper(this);
        datePickerButton=findViewById(R.id.date_picker);
        datePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(v.getContext());
                dialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        birthDate = year + "." + month + "." + dayOfMonth;
                        Log.d(TAG, "Date: " + birthDate);
                        birthDateView.setText(birthDate);
                    }
                });
                dialog.show();
            }
        });
        registerBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            firstnameS=fname.getText().toString();
            lastnameS=lname.getText().toString();
            emailS=email.getText().toString();
            passwordS=password.getText().toString();
            birthdateS=birthDateView.getText().toString();
            db.insertUser(firstnameS,lastnameS,emailS,passwordS,birthdateS);
            Intent profintent=new Intent(RegisterActivity.this,ProfileActivity.class);
            startActivity(profintent);
        }
    });

    }

}
