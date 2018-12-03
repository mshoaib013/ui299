package com.example.darkshadow.qskip;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InstitutionOption extends AppCompatActivity {
    TextView serviceLeft;
    DatabaseReference mDatabase;
    Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institution_option);

        next = (Button)findViewById(R.id.institutionOptionsNext);



        serviceLeft = (TextView)findViewById(R.id.insttutionOptionServiceLeft);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Org").child("Pjl68etfWfZzzh9bHZJTAGKQZCv1").child("que");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                serviceLeft.setText(String.valueOf(dataSnapshot.getChildrenCount()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.child("").removeValue();
            }
        });

    }
}
