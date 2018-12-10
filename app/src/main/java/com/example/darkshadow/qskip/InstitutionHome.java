package com.example.darkshadow.qskip;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class InstitutionHome extends AppCompatActivity {
    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institution_home);
        hide();

        Button printQRCode = (Button) findViewById(R.id.printQRCodeButton);
        printQRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InstitutionHome.this, InstitutionOption.class);
                startActivity(intent);
            }
        });
        Button serv = (Button) findViewById(R.id.institutionHomeService);
        serv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InstitutionHome.this, InstitutionOption.class);
                startActivity(intent);
            }
        });

        Button signout = (Button) findViewById(R.id.institutionSignout);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();

                Intent intent = new Intent(InstitutionHome.this, SignInSignUp.class);
                startActivity(intent);
            }
        });

    }
}
