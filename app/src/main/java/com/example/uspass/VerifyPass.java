package com.example.uspass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class VerifyPass extends AppCompatActivity {

    Button goPassButton, comePassButton;

    Button searchButton;
    EditText mobileNumberTextView;

    String UserFinalNumber;

    TextView profileName , profilePassPlanType, profilePassRemDays, goText, comeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_pass);

        goPassButton = findViewById(R.id.goButton);
        comePassButton = findViewById(R.id.comeButton);
        searchButton = findViewById(R.id.srchButton);
        mobileNumberTextView = findViewById(R.id.userNumber);


        profileName = findViewById(R.id.username);
        profilePassPlanType = findViewById(R.id.passPlan);
        profilePassRemDays = findViewById(R.id.remDaysPass);
        goText = findViewById(R.id.goText);
        comeText = findViewById(R.id.comeText);

        makeButtonsDisable();


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!validateMobileNumber()) {

                } else {
                    verifyUser();
                }

            }
        });





    }









    private void makeButtonsVisible() {
        goPassButton.setClickable(true);
        comePassButton.setClickable(true);
    }
    private void makeButtonsDisable() {
        goPassButton.setClickable(false);
        comePassButton.setClickable(false);
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

                    UserFinalNumber = mobileNumber;
                    addDetails();

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
                String goFromDB = snapshot.child(UserFinalNumber).child("oneMark").getValue(String.class);
                String comFromDB = snapshot.child(UserFinalNumber).child("twoMark").getValue(String.class);

                // adding details

                profileName.setText(nameFromDB);
                profilePassPlanType.setText(passPlanFromDB);
                profilePassRemDays.setText(remDaysFromDB);
                goText.setText(goFromDB);
                comeText.setText(comFromDB);


                // decide whether user is able to use service or not

                String days = snapshot.child(UserFinalNumber).child("passDays").getValue(String.class);
                days = days.trim();


                if(days.equals("0")) {
                    Toast.makeText(VerifyPass.this, "User Pass Limit exhausted", Toast.LENGTH_LONG).show();
                    makeButtonsDisable();

                } else {

                    if(goFromDB.equals("Not Marked")) {
                        goPassButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                reference.child(UserFinalNumber).child("oneMark").setValue("Marked");
                                goPassButton.setClickable(false);
                                goText.setText("Marked");
                            }
                        });
                    } else {
                        goPassButton.setClickable(false);
                    }


                    if(comFromDB.equals("Not Marked")) {
                        String finalDays = days;
                        comePassButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                reference.child(UserFinalNumber).child("twoMark").setValue("Marked");
                                comePassButton.setClickable(false);
                                comeText.setText("Marked");
                                int D = Integer.parseInt(finalDays);
                                D--;

                                String text = new String(String.valueOf(D));
                                reference.child(UserFinalNumber).child("passDays").setValue(text);
                                profilePassRemDays.setText(text);

                            }
                        });
                    } else {
                        comePassButton.setClickable(false);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


}