package com.example.messpoll.lunch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.messpoll.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ArharDalList extends AppCompatActivity {

    ListView listView;

    ArrayList<String> arharDalHaters;

    ArrayAdapter arrayAdapter;

    FirebaseDatabase firebaseDatabase;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arhar_dal_list);


        listView = findViewById(R.id.listView);

        arharDalHaters = new ArrayList<>();

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, arharDalHaters);

        firebaseAuth = FirebaseAuth.getInstance();

        firebaseDatabase = FirebaseDatabase.getInstance();

        firebaseDatabase.getReference().child("Lunch").child("arharDal").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                String hater = snapshot.child("Hater").getValue(String.class);

                arharDalHaters.add(hater);

                listView.setAdapter(arrayAdapter);

                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}