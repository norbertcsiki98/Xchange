package com.example.xchange.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import com.example.xchange.Database.FirebaseDatabaseManager;
import com.example.xchange.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getCanonicalName();

    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    DatabaseReference databaseReference=firebaseDatabase.getReference("Users");

    private Button datePickerButton;
    private TextView birthDateView,birthDateViewL;
    private EditText fname;
    private EditText lname;
    private EditText email;
    private EditText password;
    private EditText confpassword;
    private Button registerBtn;
    private String birthDate;


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
            if(checkDataEntered()==true) {
                addUser();
                Intent profintent = new Intent(RegisterActivity.this, ProfileActivity.class);
                startActivity(profintent);
            }
        }
    });

    }

    // insert user data to database
    public void addUser()
    {
        fname=findViewById(R.id.firstName);
        String fnameS=fname.getText().toString();
        lname=findViewById(R.id.lastName);
        String lnameS=lname.getText().toString();
        email=findViewById(R.id.email);
        String emailS=email.getText().toString();
        password=findViewById(R.id.password);
        String passwordS=password.getText().toString();
        birthDateViewL=findViewById(R.id.birthDate);
        String birthS=birthDateViewL.getText().toString();
        FirebaseDatabaseManager fm=new FirebaseDatabaseManager(fnameS,lnameS,emailS,passwordS,birthS);
        databaseReference.push().setValue(fm);
        Toast.makeText(this,"Insert to database",Toast.LENGTH_SHORT).show();

    }

    //check data entered
    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    boolean isEmptyT(TextView text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }


    boolean checkDataEntered() {
        boolean dataValidation=true;
        if (isEmpty(fname)) {
            fname.setError("First name is required!");
            dataValidation=false;
        }

        if (isEmpty(lname)) {
            lname.setError("Last name is required!");
            dataValidation=false;
        }

        if (isEmail(email) == false) {
            email.setError("Enter valid email!");
            dataValidation=false;
        }

        if (isEmpty(password)) {
            password.setError("Password is required!");
            dataValidation=false;
        }
        if (isEmpty(confpassword)) {
            confpassword.setError("Confirm password is required!");
            dataValidation=false;
        }
        if (isEmptyT(birthDateView)) {
            datePickerButton.setError("Birth date is required!");
            dataValidation=false;
        }


        return dataValidation;
    }
}
