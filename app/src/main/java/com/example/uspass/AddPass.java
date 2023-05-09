package com.example.uspass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AddPass extends AppCompatActivity {

    Button searchButton, oneDayPassButton, oneWeekPassButton, oneMonthPassButton;
    EditText mobileNumberTextView;
    TextView profileMobile, profileName , profilePassPlanType, profilePassRemDays;
    String UserFinalNumber;

    LinearLayout addPass;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pass);

        searchButton = findViewById(R.id.srchButton);
        oneDayPassButton = findViewById(R.id.dayButton);
        oneWeekPassButton = findViewById(R.id.weekButton);
        oneMonthPassButton = findViewById(R.id.monthButton);
        mobileNumberTextView = findViewById(R.id.userNumber);


        profileMobile = findViewById(R.id.number);
        profileName = findViewById(R.id.username);
        profilePassPlanType = findViewById(R.id.passPlan);
        profilePassRemDays = findViewById(R.id.remDaysPass);


        addPass = findViewById(R.id.panel_add_pass);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!validateMobileNumber()) {

                } else {
                    verifyUser();
                }

            }
        });

        oneDayPassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeButtonsDisable();
                addPass("1 DAY PASS", "1");
            }
        });

        oneWeekPassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeButtonsDisable();
                addPass("1 WEEK PASS", "7");
            }
        });

        oneMonthPassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeButtonsDisable();
                addPass("1 MONTH PASS", "30");
            }
        });
    }

    public void addPass(String passType, String count) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");

        reference.child(UserFinalNumber).child("passDays").setValue(count);
        reference.child(UserFinalNumber).child("passType").setValue(passType);

        addDetails();
    }

    public boolean validateMobileNumber() {
        String input = mobileNumberTextView.getText().toString();
        if(input.length() == 0) {
            mobileNumberTextView.setError("user Phone Number cannot be empty !");
            return false;
        } else if(input.length() < 10 || input.length() > 10) {
            mobileNumberTextView.setError("Please Enter Correct Phone Number");
            return false;
        } else {
            mobileNumberTextView.setError(null);
            return true;
        }
    }


    public void verifyUser() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        String mobileNumber = mobileNumberTextView.getText().toString().trim();

        Query userCheckQuery = reference.orderByChild("phoneNo").equalTo(mobileNumber);

        userCheckQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    mobileNumberTextView.setError(null);

                    String days = snapshot.child(mobileNumber).child("passDays").getValue(String.class);
                    days = days.trim();


                    UserFinalNumber = mobileNumber;


                    if(days.equals("0")) {
                        Toast.makeText(AddPass.this, "select appropriate pass", Toast.LENGTH_LONG).show();
                        makeButtonsVisible();
                        addDetails();
                    } else {
                        Toast.makeText(AddPass.this, "User already using pass service", Toast.LENGTH_LONG).show();
                        addDetails();
                        makeButtonsDisable();
                    }

                } else {
                    mobileNumberTextView.setError("User Not Exist");
                    mobileNumberTextView.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void addDetails() {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query userCheckQuery = reference.orderByChild("phoneNo").equalTo(UserFinalNumber);

        userCheckQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String nameFromDB = snapshot.child(UserFinalNumber).child("fullName").getValue(String.class);
                String passPlanFromDB = snapshot.child(UserFinalNumber).child("passType").getValue(String.class);
                String remDaysFromDB = snapshot.child(UserFinalNumber).child("passDays").getValue(String.class);

                // adding details

                profileName.setText(nameFromDB);
                profileMobile.setText(UserFinalNumber);
                profilePassPlanType.setText(passPlanFromDB);
                profilePassRemDays.setText(remDaysFromDB);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void makeButtonsVisible() {
        addPass.setVisibility(View.VISIBLE);
    }
    private void makeButtonsDisable() {
        addPass.setVisibility(View.INVISIBLE);
    }
}