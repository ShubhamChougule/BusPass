package com.example.uspass;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    Button signUp, bck;
    EditText et1, et2, et3, et4, et5, et6, et7, et8, et9, et10;
    FirebaseDatabase database;
    DatabaseReference reference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        signUp = (Button) findViewById(R.id.bt_signUp);
        bck = (Button) findViewById(R.id.bt_home);
        et1 = (EditText) findViewById(R.id.et_fn);
        et2 = (EditText) findViewById(R.id.et_address);
        et3 = (EditText) findViewById(R.id.et_pincode);
        et4 = (EditText) findViewById(R.id.et_phn);
        et5 = (EditText) findViewById(R.id.et_email);
        et6 = (EditText) findViewById(R.id.et_pass);
        et7 = (EditText) findViewById(R.id.et_date);
        et8 = (EditText) findViewById(R.id.et_clg);
        et9 = (EditText) findViewById(R.id.et_startLocation);
        et10 = (EditText) findViewById(R.id.et_endLocation);






        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(check()) {
                    Toast.makeText(RegisterActivity.this, "Please Fill All Fields !!", Toast.LENGTH_LONG).show();
                    return;
                }


                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");

                String fullName = et1.getText().toString();
                String address = et2.getText().toString();
                String pinCode = et3.getText().toString();
                String phoneNo = et4.getText().toString();
                String emailId = et5.getText().toString();
                String password = et6.getText().toString();
                String age = et7.getText().toString();
                String college = et8.getText().toString();
                String startPosition = et9.getText().toString();
                String endPosition = et10.getText().toString();


                HelperClass helperClass = new HelperClass(fullName, address,  pinCode,  phoneNo,  emailId,  password,  age,  college,
                                                  "0", "not purchased yet", "Not Marked", "Not Marked", startPosition, endPosition);
                reference.child(phoneNo).setValue(helperClass);

                Toast.makeText(RegisterActivity.this, "You have signup successfully", Toast.LENGTH_SHORT).show();


                // next activity
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
















        bck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(RegisterActivity.this, LandingPage.class);
                startActivity(it);
            }
        });


    }


    public boolean check() {
        if(et1.getText().toString().isEmpty() ||
                et2.getText().toString().isEmpty() ||
                et3.getText().toString().isEmpty() ||
                et4.getText().toString().isEmpty() ||
                et5.getText().toString().isEmpty() ||
                et6.getText().toString().isEmpty() ||
                et7.getText().toString().isEmpty() ||
                et8.getText().toString().isEmpty() ||
                et9.getText().toString().isEmpty() ||
                et10.getText().toString().isEmpty()) {
            return true;
        }

        return false;
    }
}