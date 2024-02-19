package com.example.basiclogintoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basiclogintoapp.Model.Case;
import com.example.basiclogintoapp.adapter.CaseAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CaseManagementSystem extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CaseAdapter caseAdapter;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_management_system);
        FirebaseApp.initializeApp(this);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference().child("cases");

        // Set up FirebaseRecyclerOptions
        FirebaseRecyclerOptions<Case> options = new FirebaseRecyclerOptions.Builder<Case>()
                .setQuery(databaseReference, Case.class)
                .build();

        // Initialize Adapter
        caseAdapter = new CaseAdapter(options);
        recyclerView.setAdapter(caseAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        caseAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        caseAdapter.stopListening();
    }
}
