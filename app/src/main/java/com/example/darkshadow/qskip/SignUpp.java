package com.example.darkshadow.qskip;

import android.content.Intent;
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

public class SignUpp extends AppCompatActivity {

    Button signup;
    EditText name,email,password,confirmPassword;
    String e,p,n,cp;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_upp);

        mAuth = FirebaseAuth.getInstance();
        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

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
                    signuppp();
                }
                else
                {
                    Toast.makeText(SignUpp.this, "Password don't match.",
                        Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    void signuppp(){
        mAuth.createUserWithEmailAndPassword(e,p)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("work", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(SignUpp.this, SignInn.class);
                            startActivity(intent);
                        } else {
                            Log.w("work", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpp.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
}
