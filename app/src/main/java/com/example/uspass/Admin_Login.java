package com.example.uspass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Admin_Login extends AppCompatActivity {

    TextView adminUserId, adminPassword;
    Button adminLoginButton;

    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);


        adminLoginButton = findViewById(R.id.adminLogin);
        adminUserId = findViewById(R.id.admin_userid);
        adminPassword = findViewById(R.id.admin_password);


        adminLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validateAdminUserId() | !validateAdminPassword()) {

                } else {
                    verifyAdmin();
                }
            }
        });

    }


    public boolean validateAdminUserId() {
        if(adminUserId.getText().toString().isEmpty()) {
            adminUserId.setError("Admin User ID cannot be empty");
            return false;
        } else {
            adminUserId.setError(null);
            return true;
        }
    }

    public boolean validateAdminPassword() {
        if(adminPassword.getText().toString().isEmpty()) {
            adminPassword.setError("Password cannot be empty");
            return false;
        } else {
            adminPassword.setError(null);
            return true;
        }
    }


    public void verifyAdmin() {
        String adminUserIdString = adminUserId.getText().toString().trim();
        String adminUserPassString = adminPassword.getText().toString().trim();


        database = FirebaseDatabase.getInstance();
        reference = database.getReference("admin");

        Query checkUserDatabase = reference.orderByChild("adminUserId").equalTo(adminUserIdString);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {

                    adminUserId.setError(null);
                    String adminPasswordFromDB = snapshot.child(adminUserIdString).child("adminPassword").getValue(String.class);

                    if(adminPasswordFromDB.equals(adminUserPassString)) {
                        adminPassword.setError(null);

                        Toast.makeText(Admin_Login.this, "Login Successfully", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(Admin_Login.this, AdminHome.class);
                        String AdminName = snapshot.child(adminUserIdString).child("adminName").getValue(String.class);
                        intent.putExtra("name", AdminName);

                        startActivity(intent);
                        finish();

                    } else {
                        adminPassword.setError("Password is incorrect !!");
                        adminPassword.requestFocus();
                    }
                } else {
                    adminUserId.setError("Admin does not exists");
                    adminUserId.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}