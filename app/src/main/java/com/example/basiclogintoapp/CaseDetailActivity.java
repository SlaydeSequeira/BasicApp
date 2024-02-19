package com.example.basiclogintoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class CaseDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_detail);

        // Retrieve case details from Intent
        Intent intent = getIntent();
        String caseTitle = intent.getStringExtra("caseTitle");
        String caseDescription = intent.getStringExtra("caseDescription");
        String caseStatus = intent.getStringExtra("caseStatus");

        // Display case details in UI elements
        TextView titleTextView = findViewById(R.id.detailTitleTextView);
        TextView descriptionTextView = findViewById(R.id.detailDescriptionTextView);
        ProgressBar progressBar = findViewById(R.id.detailProgressBar);

        titleTextView.setText(caseTitle);
        descriptionTextView.setText(caseDescription);

        // Set ProgressBar based on the status
        int progress = calculateProgress(convertStatusToInt(caseStatus));
        progressBar.setProgress(progress);
    }

    private int calculateProgress(int status) {
        switch (status) {
            case 1:
                return 25;
            case 2:
                return 50;
            case 3:
                return 75;
            case 4:
                return 100;
            default:
                return 0;
        }
    }

    private int convertStatusToInt(String status) {
        try {
            return Integer.parseInt(status);
        } catch (NumberFormatException e) {
            // Handle the case where the status is not a valid integer
            e.printStackTrace();
            return 0; // or another default value
        }
    }
}
