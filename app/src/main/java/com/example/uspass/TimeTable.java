package com.example.uspass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class TimeTable extends AppCompatActivity {

    TextView start, end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);

        start = findViewById(R.id.txt_start);
        end = findViewById(R.id.txt_end);

        Intent intent = getIntent();

        String startPositionFromDB = intent.getStringExtra("startPosition");
        String endPositionFromDB = intent.getStringExtra("endPosition");

        start.setText(startPositionFromDB);
        end.setText(endPositionFromDB);
    }
}