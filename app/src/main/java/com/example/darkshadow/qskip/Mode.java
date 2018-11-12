package com.example.darkshadow.qskip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Mode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);
        Button userMode = (Button) findViewById(R.id.userModeButton);
        Button institutionMode = (Button) findViewById(R.id.institutionModeButton);

        userMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Mode.this, SignInSignUp.class);
                startActivity(intent);
            }
        });

        institutionMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Mode.this, SignInSignUp.class);
                startActivity(intent);
            }
        });

    }
}
