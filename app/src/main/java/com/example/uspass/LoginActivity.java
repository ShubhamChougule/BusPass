package com.example.uspass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    EditText mobileNumber, password;
    Button homeButton, loginButton;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        homeButton = (Button) findViewById(R.id.bt_login_home);
        loginButton = (Button) findViewById(R.id.bt_login_loginOk);
        mobileNumber = (EditText) findViewById(R.id.et_login_mobileNo);
        password = (EditText) findViewById(R.id.et_login_pass);



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validateMobileNumber() | !validatePassWord()) {

                } else {
                    checkUser();
                }
            }
        });





        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, LandingPage.class));
                finish();
            }
        });
    }


    public boolean validateMobileNumber() {
        if(mobileNumber.getText().toString().isEmpty()) {
            mobileNumber.setError("Mobile number cannot be empty!");
            return false;
        } else {
            mobileNumber.setError(null);
            return true;
        }
    }

    public boolean validatePassWord() {
        if(password.getText().toString().isEmpty()) {
            password.setError("PassWord number cannot be empty!");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }

    public void checkUser() {
        String userMobileNumber = mobileNumber.getText().toString().trim();
        String userPassword = password.getText().toString().trim();


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("users");

        Query checkUserDatabase = reference.orderByChild("phoneNo").equalTo(userMobileNumber);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    mobileNumber.setError(null);
                    String passWordFromDB = snapshot.child(userMobileNumber).child("passWord").getValue(String.class);

                    if(passWordFromDB.equals(userPassword)) {
                        password.setError(null);

                        Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(LoginActivity.this, MainPage.class);


                        String nameFromDB = snapshot.child(userMobileNumber).child("fullName").getValue(String.class);
                        String addressFromDB = snapshot.child(userMobileNumber).child("address").getValue(String.class);
                        String pincodeFromDB = snapshot.child(userMobileNumber).child("pincode").getValue(String.class);
                        String phoneFromDB = snapshot.child(userMobileNumber).child("phoneNo").getValue(String.class);
                        String emailFromDB = snapshot.child(userMobileNumber).child("emailId").getValue(String.class);
                        String passFromDB = snapshot.child(userMobileNumber).child("passWord").getValue(String.class);
                        String ageFromDB = snapshot.child(userMobileNumber).child("age").getValue(String.class);
                        String collegeNameFromDB = snapshot.child(userMobileNumber).child("collegeName").getValue(String.class);
                        String passDaysFromDB = snapshot.child(userMobileNumber).child("passDays").getValue(String.class);
                        String passTypeFromDB = snapshot.child(userMobileNumber).child("passType").getValue(String.class);
                        String oneMarkFromDB = snapshot.child(userMobileNumber).child("oneMark").getValue(String.class);
                        String twoMarkFromDB = snapshot.child(userMobileNumber).child("twoMark").getValue(String.class);
                        String startPositionFromDB = snapshot.child(userMobileNumber).child("startPosition").getValue(String.class);
                        String endPositionFromDB = snapshot.child(userMobileNumber).child("endPosition").getValue(String.class);



                        intent.putExtra("fullName", nameFromDB);
                        intent.putExtra("address", addressFromDB);
                        intent.putExtra("pincode", pincodeFromDB);
                        intent.putExtra("phoneNo", phoneFromDB);
                        intent.putExtra("emailId", emailFromDB);
                        intent.putExtra("passWord", passFromDB);

                        intent.putExtra("age", ageFromDB);
                        intent.putExtra("collegeName", collegeNameFromDB);
                        intent.putExtra("passDays", passDaysFromDB);
                        intent.putExtra("passType", passTypeFromDB);
                        intent.putExtra("oneMark", oneMarkFromDB);
                        intent.putExtra("twoMark", twoMarkFromDB);
                        intent.putExtra("startPosition", startPositionFromDB);
                        intent.putExtra("endPosition", endPositionFromDB);

                        startActivity(intent);

                    } else {
                        password.setError("Incorrect Password");
                        password.requestFocus();
                    }
                } else {
                    mobileNumber.setError("User does not exists !");
                    mobileNumber.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }




}