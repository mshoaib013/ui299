package com.example.darkshadow.qskip;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInn extends AppCompatActivity {
    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }
    EditText emaill,passs;
    Button signin;
    EditText email,pass;
    String e,p;

    FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_inn);
        mAuth = FirebaseAuth.getInstance();
        hide();


        mAuth = FirebaseAuth.getInstance();
        signin = (Button) findViewById(R.id.signinSubmitButton);
        email = (EditText) findViewById(R.id.signinEmailField);
        pass = (EditText) findViewById(R.id.signinPassField);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                e = email.getText().toString();
                p = pass.getText().toString();
                signin();

            }
        });

    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }
    void signin(){
        mAuth.signInWithEmailAndPassword(e, p)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(SignInn.this, e+p ,
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignInn.this, Mode.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(SignInn.this, e+p+" not working" ,
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }
}
