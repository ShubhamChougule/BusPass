package com.example.uspass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Profile extends AppCompatActivity {


    TextView profileMobile, profileName, profileAddress, profileEmail, profilePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        profileMobile = findViewById(R.id.userMobile);
        profileName = findViewById(R.id.username);
        profileAddress = findViewById(R.id.useraddress);
        profileEmail = findViewById(R.id.useremail);
        profilePassword = findViewById(R.id.userpasword);


        adddetails();

    }

    public void adddetails() {
        // intent coming from login
        Intent intent = getIntent();
        String mobileFromDB = intent.getStringExtra("mobile");
        String nameFromDB = intent.getStringExtra("name");
        String emailFromDB = intent.getStringExtra("email");
        String passwordFromDB = intent.getStringExtra("password");
        String addressFromDB = intent.getStringExtra("address");

        profileMobile.setText(mobileFromDB);
        profileName.setText(nameFromDB);
        profileAddress.setText(addressFromDB);
        profileEmail.setText(emailFromDB);
        profilePassword.setText(passwordFromDB);
    }
}