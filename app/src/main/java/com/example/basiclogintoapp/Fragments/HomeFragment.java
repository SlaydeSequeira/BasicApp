package com.example.basiclogintoapp.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.basiclogintoapp.R;
import com.example.basiclogintoapp.adapter.RecyclerAdapter;
import com.example.basiclogintoapp.adapter.RecyclerAdapter1;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeFragment extends Fragment {
    String[] data1 = new String[100];
    String[] data2 = new String[100];
    String[] data3 = new String[100];

    String[] Image = new String[100];
    String[] data4 = new String[100];
    String[] data5 = new String[100];
    String[] data6 = new String[100];

    String[] Image2 = new String[100];


    int count=0;
    int count1=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        RecyclerView recyclerView1 = view.findViewById(R.id.recyclerView1);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference("Vposts");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String temp=  snapshot.child("count").getValue().toString();
                int count = Integer.parseInt(temp);
                for(int i=0;i<count;i++)
                {
                     String temp2=String.valueOf(i);
                    data1[i] = String.valueOf(snapshot.child(temp2).child("data1").getValue());
                    data2[i] = String.valueOf(snapshot.child(temp2).child("data2").getValue());
                    data3[i] = String.valueOf(snapshot.child(temp2).child("data3").getValue());
                    Image[i] = String.valueOf(snapshot.child(temp2).child("img").getValue());
                }
                RecyclerAdapter adapter = new RecyclerAdapter(getContext(), data1,count,data2,data3,Image);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        DatabaseReference myRef1 = firebaseDatabase.getReference("Vposts");
        myRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String temp=  snapshot.child("count").getValue().toString();
                int count1 = Integer.parseInt(temp);
                for(int i=0;i<count1;i++)
                {
                    String temp2=String.valueOf(i);
                    data4[i] = String.valueOf(snapshot.child(temp2).child("data1").getValue());
                    data5[i] = String.valueOf(snapshot.child(temp2).child("data2").getValue());
                    data6[i] = String.valueOf(snapshot.child(temp2).child("data3").getValue());
                    Image2[i] = String.valueOf(snapshot.child(temp2).child("img").getValue());
                }

                LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext());
                layoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView1.setLayoutManager(layoutManager1);
                recyclerView1.setNestedScrollingEnabled(false);
                RecyclerAdapter1 adapter1 = new RecyclerAdapter1(getContext(), data4,count1,data5,data6,Image2);
                recyclerView1.setAdapter(adapter1);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



return view;

    }
}

