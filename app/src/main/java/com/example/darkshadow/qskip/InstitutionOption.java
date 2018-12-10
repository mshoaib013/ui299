package com.example.darkshadow.qskip;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InstitutionOption extends AppCompatActivity {
    int b;
    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }
    TextView serviceLeft;
    DatabaseReference mDatabase;
    Button next;
    long total = 0;
    int[] arr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institution_option);
        hide();
        arr = new int[100];

        next = (Button)findViewById(R.id.institutionOptionsNext);



        serviceLeft = (TextView)findViewById(R.id.insttutionOptionServiceLeft);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Org").child("H8CvPTCEHzZo1zKHJMQt39fDwht2").child("que");
        mDatabase.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int a = 0,c = 0,i = 0;
                b = 10000;
                serviceLeft.setText(String.valueOf(dataSnapshot.getChildrenCount()));


                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    QueController queController = snapshot.getValue(QueController.class);
                    a = queController.position;
                    arr[i] = queController.getPosition();
                    if (b>a){
                        b = a;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.child(String.valueOf(b)).removeValue();
            }
        });
    }
}
