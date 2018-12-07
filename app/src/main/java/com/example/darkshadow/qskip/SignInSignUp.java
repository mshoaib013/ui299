package com.example.darkshadow.qskip;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInSignUp extends AppCompatActivity {

    FirebaseAuth mAuth;
    String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_sign_up);

        Button signup = (Button) findViewById(R.id.signupButton);
        Button signIn = (Button) findViewById(R.id.signInButton);
        Button federated = (Button) findViewById(R.id.federatedButton);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInSignUp.this, SignUpp.class);
                intent.putExtra("mode", message);
                startActivity(intent);
            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInSignUp.this, SignInn.class);
                startActivity(intent);
            }
        });
        federated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        String s;
        Intent intent=getIntent();
//        message = getIntent().getExtras().getString("mode");
//        Log.d("getextra", message);


    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser!=null){
            Intent intent = new Intent(SignInSignUp.this, UserHome.class);
            startActivity(intent);
        }
    }
}
