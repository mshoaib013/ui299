package com.example.darkshadow.qskip;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpp extends AppCompatActivity {

    Button signup;
    EditText name,email,password,confirmPassword;
    String e,p,n,cp;
    FirebaseAuth mAuth;
    FirebaseUser user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_upp);

        //firebase

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        final DatabaseReference mDatabase;

        signup = (Button) findViewById(R.id.signupSubmitButton);
        name = (EditText) findViewById(R.id.signupNameField);
        email = (EditText) findViewById(R.id.signupEmailField);
        password = (EditText) findViewById(R.id.signupPassField);
        confirmPassword = (EditText) findViewById(R.id.signupRepeatPassField);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                e = email.getText().toString();
                p = password.getText().toString();
                n = name.getText().toString();
                cp = confirmPassword.getText().toString();

                if(p.equals(cp))
                {
                    signuppp(n,e,p);

                }
                else
                {
                    Toast.makeText(SignUpp.this, "Password don't match.",
                        Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    void signuppp(String name,String email,String pass){
        final SignupController setDefaultUserDataUsingController = new SignupController(name,email,pass);
        mAuth.createUserWithEmailAndPassword(e,p)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("work", "createUserWithEmail:success");
                            {
                                Log.d("work", "createUserWithEmail:success");
                                final DatabaseReference mDatabase;
                                FirebaseUser user = mAuth.getCurrentUser();
                                mDatabase = FirebaseDatabase.getInstance().getReference().child("U").child(user.getUid());
                                mDatabase.setValue(setDefaultUserDataUsingController).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        final Intent intent = new Intent(SignUpp.this, SignInn.class);
                                        startActivity(intent);
                                    }
                                });
                            }

//                            FirebaseUser user = mAuth.getCurrentUser();
//
//                            FirebaseDatabase database = FirebaseDatabase.getInstance();
//                            DatabaseReference uID = database.getReference("User").child(user.getUid()).child("Name");
//                            uID.setValue(user.getUid());
//                            DatabaseReference uName = database.getReference("User").child(user.getUid()).child("Name");
//                            uID.setValue(n);
//                            DatabaseReference myRef = database.getReference("User").child(user.getUid()).child("Email");
//                            myRef.setValue(e);
//                            DatabaseReference pass = database.getReference("User").child(user.getUid()).child("Pass");
//                            pass.setValue(p);
//
//
//                            CountDownTimer timeOutRed;
//                            timeOutRed=new CountDownTimer(3000, 1000) { // adjust the milli seconds here
//                                public void onTick(long millisUntilFinished) {
//
//                                }
//
//                                public void onFinish() {
//                                    Intent intent = new Intent(SignUpp.this, SignInn.class);
//                                    startActivity(intent);
//
//                                }
//                            }.start();

                        } else {
                            Log.w("work", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpp.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
}
