package com.example.uspass;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class AdminHome extends AppCompatActivity {

    TextView adminName;
    ImageButton addPassButton, verifyPassButton;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        addPassButton = findViewById(R.id.addPass);
        verifyPassButton = findViewById(R.id.verify);

        adminName = findViewById(R.id.admin_title);
        Intent intent = getIntent();
        String adminNameFromDB = intent.getStringExtra("name");
        adminName.setText("Welcome Admin "+ adminNameFromDB);

        addPassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(AdminHome.this, AddPass.class);
                startActivity(intent);
            }
        });
        verifyPassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(AdminHome.this, VerifyPass.class);
                startActivity(intent);

            }
        });




    }
}