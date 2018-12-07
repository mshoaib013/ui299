package com.example.darkshadow.qskip;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Mode extends AppCompatActivity {
    DatabaseReference mDatabase;
    FirebaseAuth mAuth;
    String waitMessage = "Wait a while";
    TextView waitMessageTextField;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);

        waitMessageTextField = (TextView)findViewById(R.id.userModeWaitingText);

        new CountDownTimer(15000, 500) { // adjust the milli seconds here
            public void onTick(long millisUntilFinished) {

                waitMessage = waitMessage + ".";
                waitMessageTextField.setText(waitMessage);
            }

            public void onFinish() {

            }
        }.start();


        //firebase
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("User").child(currentUser.getUid());
        Log.d("zzzzz", currentUser.getEmail());
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("zzzzz11111", String.valueOf(dataSnapshot.exists()));
                if (dataSnapshot.exists()==true){
                    Intent intent = new Intent(Mode.this, UserHome.class);
                    intent.putExtra("mode", "1");
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(Mode.this, InstitutionHome.class);
                    intent.putExtra("mode", "2");
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        Button userMode = (Button) findViewById(R.id.userModeButton);
        Button institutionMode = (Button) findViewById(R.id.institutionModeButton);

        userMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Mode.this, SignInSignUp.class);
                intent.putExtra("mode", "1");
                startActivity(intent);
            }
        });

        institutionMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Mode.this, InstitutionHome.class);
                intent.putExtra("mode", "2");
                startActivity(intent);
            }
        });

    }
}
