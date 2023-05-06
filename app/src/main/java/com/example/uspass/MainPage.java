package com.example.uspass;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainPage extends AppCompatActivity {


    ImageButton profileButton, showPassButton, checkInButton, timeTabelButton, aboutUsButton, contactUsButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);


        profileButton = findViewById(R.id.profile_button);
        showPassButton = findViewById(R.id.showPass);
        checkInButton = findViewById(R.id.checkInBtn);
        timeTabelButton = findViewById(R.id.timetable);
        aboutUsButton = findViewById(R.id.aboutus);
        contactUsButton = findViewById(R.id.contactUsBtn);



        // intent coming from login
        Intent intent = getIntent();

        String nameFromDB = intent.getStringExtra("fullName");
        String addressFromDB = intent.getStringExtra("address");
        String pincodeFromDB = intent.getStringExtra("pincode");
        String mobileFromDB = intent.getStringExtra("phoneNo");
        String emailFromDB = intent.getStringExtra("emailId");
        String passwordFromDB = intent.getStringExtra("passWord");

        String ageFromDB = intent.getStringExtra("age");
        String collegeNameFromDB = intent.getStringExtra("collegeName");
        String passDaysFromDB = intent.getStringExtra("passDays");
        String passTypeFromDB = intent.getStringExtra("passType");
        String oneMarkFromDB = intent.getStringExtra("oneMark");
        String twoMarkFromDB = intent.getStringExtra("twoMark");
        String startPositionFromDB = intent.getStringExtra("startPosition");
        String endPositionFromDB = intent.getStringExtra("endPosition");





        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainPage.this, Profile.class);

                intent.putExtra("mobile", mobileFromDB);
                intent.putExtra("name", nameFromDB);
                intent.putExtra("email", emailFromDB);
                intent.putExtra("password", passwordFromDB);
                intent.putExtra("address", addressFromDB);

                startActivity(intent);
            }
        });

        showPassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainPage.this, ShowPass.class);

                intent.putExtra("fullName", nameFromDB);
                intent.putExtra("address", addressFromDB);
                intent.putExtra("pincode", pincodeFromDB);
                intent.putExtra("phoneNo", mobileFromDB);
                intent.putExtra("emailId", emailFromDB);
                intent.putExtra("passWord", passwordFromDB);

                intent.putExtra("age", ageFromDB);
                intent.putExtra("collegeName", collegeNameFromDB);
                intent.putExtra("passDays", passDaysFromDB);
                intent.putExtra("passType", passTypeFromDB);
                intent.putExtra("oneMark", oneMarkFromDB);
                intent.putExtra("twoMark", twoMarkFromDB);
                intent.putExtra("startPosition", startPositionFromDB);
                intent.putExtra("endPosition", endPositionFromDB);

                startActivity(intent);
            }
        });

        checkInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainPage.this, CheckIn.class);
                intent.putExtra("fullName", nameFromDB);
                intent.putExtra("address", addressFromDB);
                intent.putExtra("pincode", pincodeFromDB);
                intent.putExtra("phoneNo", mobileFromDB);
                intent.putExtra("emailId", emailFromDB);
                intent.putExtra("passWord", passwordFromDB);

                intent.putExtra("age", ageFromDB);
                intent.putExtra("collegeName", collegeNameFromDB);
                intent.putExtra("passDays", passDaysFromDB);
                intent.putExtra("passType", passTypeFromDB);
                intent.putExtra("oneMark", oneMarkFromDB);
                intent.putExtra("twoMark", twoMarkFromDB);
                intent.putExtra("startPosition", startPositionFromDB);
                intent.putExtra("endPosition", endPositionFromDB);

                startActivity(intent);
            }
        });

        timeTabelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainPage.this, TimeTable.class);
                intent.putExtra("startPosition", startPositionFromDB);
                intent.putExtra("endPosition", endPositionFromDB);
                startActivity(intent);
            }
        });


        aboutUsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainPage.this, AboutUs.class));
            }
        });


        contactUsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainPage.this, ContactUs.class));
            }
        });

    }
}