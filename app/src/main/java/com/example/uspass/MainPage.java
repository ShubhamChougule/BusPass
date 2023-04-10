package com.example.uspass;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainPage extends AppCompatActivity {


    ImageButton profileButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);


        profileButton = (ImageButton) findViewById(R.id.profile_button);



        // intent coming from login
        Intent intent = getIntent();
        String mobileFromDB = intent.getStringExtra("mobile");
        String nameFromDB = intent.getStringExtra("name");
        String emailFromDB = intent.getStringExtra("email");
        String passwordFromDB = intent.getStringExtra("password");
        String addressFromDB = intent.getStringExtra("address");


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

    }
}