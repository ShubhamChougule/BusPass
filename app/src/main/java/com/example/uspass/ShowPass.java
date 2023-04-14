package com.example.uspass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ShowPass extends AppCompatActivity {

    TextView name, phone, address, passplan, passdays, startplace, endplace, currDate;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_pass);

        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        address = findViewById(R.id.address);
        passplan = findViewById(R.id.passplan);
        passdays = findViewById(R.id.passdays);
        startplace = findViewById(R.id.startplace);
        endplace = findViewById(R.id.endplace);

        currDate = findViewById(R.id.d_date);


        SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
        Date todayDate = new Date();
        String thisDate = currentDate.format(todayDate);
        currDate.setText("Pass Viewed Date : " + thisDate);

        setUserData();




    }

    public void setUserData() {
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


        name.setText("Name : " + nameFromDB);
        phone.setText("Mobile Number : " + mobileFromDB);
        address.setText("Address : " + addressFromDB);
        passplan.setText("Pass Plan : " + passTypeFromDB);
        passdays.setText("Remaining Days : " + passDaysFromDB);
        startplace.setText("From : " + startPositionFromDB);
        endplace.setText("To : " + endPositionFromDB);


    }
}