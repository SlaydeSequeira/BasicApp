package com.example.basiclogintoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.basiclogintoapp.adapter.RecyclerAdapter;
import com.example.basiclogintoapp.adapter.RecyclerAdapter1;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.*;

public class PendingCases extends AppCompatActivity {
    String[] data1 = new String[100];
    String[] data2 = new String[100];
    String[] data3 = new String[100];

    String[] Image = new String[100];
    String[] data4 = new String[100];
    String[] data5 = new String[100];
    String[] data6 = new String[100];
    String[] data7 = new String[100];
    String[] data8 = new String[100];
    String[] data9 = new String[100];


    String[] Image2 = new String[100];
    String x = "hotel";
    CardView c1, c2, c3;

    int count = 0;
    int count1 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_cases);
        getSupportActionBar().hide();
        RecyclerView recyclerView1 = findViewById(R.id.recyclerView1);
        c1 = findViewById(R.id.c1);
        c2 = findViewById(R.id.c2);
        c3 = findViewById(R.id.c3);

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x = "restaurant";
                resetpost();
            }
        });

        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x = "hotel";
                resetpost();
            }
        });

        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x = "site";
                resetpost();
            }
        });

        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this);
        layoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView1.setLayoutManager(layoutManager1);
        recyclerView1.setNestedScrollingEnabled(false);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference("Vposts");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String temp = snapshot.child("count").getValue().toString();
                int count = Integer.parseInt(temp);
                for (int i = 0; i < count; i++) {
                    String temp2 = String.valueOf(i);
                    data1[i] = String.valueOf(snapshot.child(temp2).child("name").getValue());
                    data2[i] = String.valueOf(snapshot.child(temp2).child("description").getValue());
                    data3[i] = String.valueOf(snapshot.child(temp2).child("rev").getValue());
                    Image[i] = String.valueOf(snapshot.child(temp2).child("img").getValue());
                }
                RecyclerAdapter adapter = new RecyclerAdapter(getApplicationContext(), data1, count, data2, data3, Image);
                // recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        DatabaseReference myRef1 = firebaseDatabase.getReference(x);
        myRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String temp = snapshot.child("count").getValue().toString();
                int count1 = Integer.parseInt(temp);
                for (int i = 0; i < count1; i++) {
                    String temp2 = String.valueOf(i);
                    data4[i] = String.valueOf(snapshot.child(temp2).child("name").getValue());
                    data5[i] = String.valueOf(snapshot.child(temp2).child("description").getValue());
                    data7[i] = String.valueOf(snapshot.child(temp2).child("verified").getValue());
                    data8[i] = String.valueOf(snapshot.child(temp2).child("x").getValue());
                    data9[i] = String.valueOf(snapshot.child(temp2).child("y").getValue());
                    data8[i]="Latitude: "+data8[i] +" Longitude: "+data9[i];
                    DataSnapshot revSnapshot = snapshot.child(temp2).child("rev");

                    int totalStars = 0;
                    int totalPeople = 0;

                    for (DataSnapshot ratingSnapshot : revSnapshot.getChildren()) {
                        String rating = ratingSnapshot.getKey();
                        String countString = String.valueOf(ratingSnapshot.getValue());

                        try {
                            int stars = Integer.parseInt(rating);
                            int people = Integer.parseInt(countString);

                            totalStars += stars * people;
                            totalPeople += people;
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                    }

                    if (totalPeople > 0) {
                        double averageStars = (double) totalStars / totalPeople;
                        data6[i] = String.valueOf(averageStars);
                    } else {
                        data6[i] = "0.0";
                    }
                    Image2[i] = String.valueOf(snapshot.child(temp2).child("img").getValue());
                }

                RecyclerAdapter1 adapter1 = new RecyclerAdapter1(getApplicationContext(), data4, count1, data5, data8, Image2, x, data7);
                recyclerView1.setAdapter(adapter1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void resetpost() {
        RecyclerView recyclerView1 = findViewById(R.id.recyclerView1);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef1 = firebaseDatabase.getReference(x);

        myRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String temp = snapshot.child("count").getValue().toString();
                int count1 = Integer.parseInt(temp);
                for (int i = 0; i < count1; i++) {
                    String temp2 = String.valueOf(i);
                    data4[i] = String.valueOf(snapshot.child(temp2).child("name").getValue());
                    data5[i] = String.valueOf(snapshot.child(temp2).child("description").getValue());
                    data7[i] = String.valueOf(snapshot.child(temp2).child("verified").getValue());
                    DataSnapshot revSnapshot = snapshot.child(temp2).child("rev");

                    int totalStars = 0;
                    int totalPeople = 0;

                    for (DataSnapshot ratingSnapshot : revSnapshot.getChildren()) {
                        String rating = ratingSnapshot.getKey();
                        String countString = String.valueOf(ratingSnapshot.getValue());

                        try {
                            int stars = Integer.parseInt(rating);
                            int people = Integer.parseInt(countString);

                            totalStars += stars * people;
                            totalPeople += people;
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                    }

                    if (totalPeople > 0) {
                        double averageStars = (double) totalStars / totalPeople;
                        data6[i] = String.valueOf(averageStars);
                    } else {
                        data6[i] = "0.0";
                    }

                    Image2[i] = String.valueOf(snapshot.child(temp2).child("img").getValue());
                }

                LinearLayoutManager layoutManager1 = new LinearLayoutManager(getApplicationContext());
                layoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView1.setLayoutManager(layoutManager1);
                recyclerView1.setNestedScrollingEnabled(false);

                RecyclerAdapter1 adapter1 = new RecyclerAdapter1(getApplicationContext(), data4, count1, data5, data6, Image2, x, data7);
                recyclerView1.setAdapter(adapter1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
