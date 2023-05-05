package com.example.uspass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CheckIn extends AppCompatActivity {

    TextView checkInStatus, welcome;
    Button markCheckInButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);
        
        checkInStatus = findViewById(R.id.checkInstatus);
        welcome = findViewById(R.id.welcome);
        markCheckInButton = findViewById(R.id.markCheckIn);



        Intent intent = getIntent();

        String nameFromDB = intent.getStringExtra("fullName");
        String oneMarkFromDB = intent.getStringExtra("oneMark");
        String twoMarkFromDB = intent.getStringExtra("twoMark");
        String mobileFromDB = intent.getStringExtra("phoneNo");
        
        welcome.setText("Hello " + nameFromDB);

        markCheckInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
                reference.child(mobileFromDB).child("oneMark").setValue("Not Marked");
                reference.child(mobileFromDB).child("twoMark").setValue("Not Marked");

                Toast.makeText(CheckIn.this, "Your CheckIn is Completed", Toast.LENGTH_LONG).show();
                checkInStatus.setText("Now you can use your pass");
            }

        });


        
        if(oneMarkFromDB.equals("Marked") && twoMarkFromDB.equals("Not Marked")) {
            checkInStatus.setText("Your -> is Marked only");
        } else if(oneMarkFromDB.equals("Not Marked") && twoMarkFromDB.equals("Marked")) {
            checkInStatus.setText("Your <- is Marked only");
        }else if (oneMarkFromDB.equals("Not Marked") && twoMarkFromDB.equals("Not Marked")) {
            checkInStatus.setText("You Haven't Used Your Pass Yet");
            markCheckInButton.setOnClickListener(null);
        } else {
            checkInStatus.setText("Please check in for today..");
        }



    }
}