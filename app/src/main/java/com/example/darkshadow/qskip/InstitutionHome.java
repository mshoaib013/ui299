package com.example.darkshadow.qskip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InstitutionHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institution_home);

        Button printQRCode = (Button) findViewById(R.id.printQRCodeButton);
        printQRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InstitutionHome.this, InstitutionOption.class);
                startActivity(intent);
            }
        });

    }
}
