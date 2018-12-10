package com.example.darkshadow.qskip;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpp extends AppCompatActivity {
    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    Button signup;
    EditText name,email,password,confirmPassword,numberOfCounter;
    String e,p,n,cp,message;
    FirebaseAuth mAuth;
    FirebaseUser user;
    Spinner spinner;
    Integer noOfCounter = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_upp);
        hide();


        //Spinner
        spinner = (Spinner) findViewById(R.id.signupSpinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);


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
        numberOfCounter = (EditText) findViewById(R.id.signupNumberOfCounter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {

                String selectedItem = parent.getItemAtPosition(position).toString(); //this is your selected item
                if (selectedItem.equals("Institution")){
                    numberOfCounter.setVisibility(View.VISIBLE);
                }
                else{
                    numberOfCounter.setVisibility(View.GONE);
                }
            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = spinner.getSelectedItem().toString();
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
        if (numberOfCounter.getVisibility()==View.VISIBLE){
            String temp = numberOfCounter.getText().toString();
            noOfCounter = Integer.parseInt(temp);
        }
        final SignupController setDefaultUserDataUsingController = new SignupController(name,email,pass,0);
        final SignupControllerOrg setDefaultOrgDataUsingController = new SignupControllerOrg(name,email,pass,0,0,noOfCounter);

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
                                if (message.equals("User")){
                                    mDatabase = FirebaseDatabase.getInstance().getReference().child("User").child(user.getUid());
                                    mDatabase.setValue(setDefaultUserDataUsingController).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            final Intent intent = new Intent(SignUpp.this, SignInn.class);
                                            startActivity(intent);
                                        }
                                    });
                                }
                                else {
                                    mDatabase = FirebaseDatabase.getInstance().getReference().child("Org").child(user.getUid());
                                    mDatabase.setValue(setDefaultOrgDataUsingController).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            final Intent intent = new Intent(SignUpp.this, SignInn.class);
                                            startActivity(intent);
                                        }
                                    });
                                }
//                                mDatabase = FirebaseDatabase.getInstance().getReference().child("U").child(user.getUid());
                            }
                        } else {
                            Log.w("work", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpp.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}