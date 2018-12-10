package com.example.darkshadow.qskip;

import android.app.Activity;
import android.app.Notification;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blikoon.qrcodescanner.QrCodeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UserHome extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    FirebaseAuth mAuth;
    LinearLayout viewOne;
    SignupControllerOrg org;
    LinearLayout viewTwo;
    int aa;
    boolean oneTime = true;
    int max,decMax[];
    private static final int REQUEST_CODE_QR_SCAN = 101;
    Bitmap bitmap;
    Integer totalInQ;
    String uid;
    long temp;
    FirebaseUser user;
    int i = 0,myPosition;
    int arr[];
    DatabaseReference database;
    DatabaseReference mDatabase,mmDatabase;
    TextView currentPosition,estimatedTime;
    Date firstTime,lastTime,estimateTime;
    Date d=new Date();
    Vibrator v;
    SimpleDateFormat sdf=new SimpleDateFormat("hh:mm a");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        hide();
        arr = new int[100];
        decMax = new int[100];
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Org");
        mmDatabase = FirebaseDatabase.getInstance().getReference().child("Org");

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.beep);
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);







        setContentView(R.layout.activity_user_home);
        currentPosition = (TextView)findViewById(R.id.userHomeCurrentPosition);
        estimatedTime = (TextView)findViewById(R.id.userHomeEstimatedTime);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        final Button login = (Button)findViewById(R.id.scanQrCodeButton);
        Button scanQrCode = (Button)findViewById(R.id.scanQrCodeButton);
        viewOne = findViewById(R.id.view1);
        viewTwo = findViewById(R.id.view2);
        Button cancelAppointment = (Button) findViewById(R.id.cancelAppointmentButton);
        uid = mAuth.getUid();

        scanQrCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        Intent i = new Intent(UserHome.this,QrCodeActivity.class);
                        startActivityForResult( i,REQUEST_CODE_QR_SCAN);
            }
        });

        //update always position
        firstTime = Calendar.getInstance().getTime();
        lastTime = firstTime;

        mmDatabase.child("H8CvPTCEHzZo1zKHJMQt39fDwht2").child("que").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                totalInQ = (int) (long) dataSnapshot.getChildrenCount();
                Log.d("zztotalINQ", String.valueOf(totalInQ));
                //fixed position not changing on data change
                if (oneTime == true){
                    Log.d("zz", String.valueOf(oneTime));
                    myPosition = totalInQ;
                    temp = myPosition;
                    oneTime = false;
                    Log.d("zz", String.valueOf(oneTime));

                    Log.d("zz", String.valueOf(myPosition));
                }
                //check if decrease
                if (dataSnapshot.getChildrenCount()<temp){
                    myPosition = myPosition-1;
                }
                else {
                    temp = dataSnapshot.getChildrenCount();
                }


                    currentPosition.setText("Current Position : "+ totalInQ);
                    estimatedTime.setText("Estimated Time : "+ myPosition);

                if (totalInQ<5){
                    currentPosition.setTextColor(Color.BLUE);
                    estimatedTime.setTextColor(Color.BLUE);

                    // Vibrate for 500 milliseconds
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        //deprecated in API 26
                        v.vibrate(500);
                    }
                }
//                    mp.start();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    void setQue(){
        i=10;
        final QueController setQueController = new QueController(uid,totalInQ);
        mDatabase.child("H8CvPTCEHzZo1zKHJMQt39fDwht2").child("que").child(String.valueOf(totalInQ+1)).setValue(setQueController).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
//                currentPosition.setText("Current Position : "+totalInQ);
//                estimatedTime.setText("Estimated Time : "+totalInQ/2);
//                mDatabase.
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode != Activity.RESULT_OK)
        {
            if(data==null)
                return;
            //Getting the passed result
            String result = data.getStringExtra("com.blikoon.qrcodescanner.error_decoding_image");
            if( result!=null)
            {
                AlertDialog alertDialog = new AlertDialog.Builder(UserHome.this).create();
                alertDialog.setTitle("Scan Error");
                alertDialog.setMessage("QR Code could not be scanned");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
            return;

        }
        if(requestCode == REQUEST_CODE_QR_SCAN)
        {
            if(data==null)
                return;
            //Getting the passed result
            String result = data.getStringExtra("com.blikoon.qrcodescanner.got_qr_scan_relult");
            AlertDialog alertDialog = new AlertDialog.Builder(UserHome.this).create();
            alertDialog.setTitle("Scan result");
            alertDialog.setMessage(result);
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();

            if (result.equals("H8CvPTCEHzZo1zKHJMQt39fDwht2")){
                viewOne.setVisibility(View.GONE);
                viewTwo.setVisibility(View.VISIBLE);
                setQue();
            }

        }
    }

    public int largest(){

        for (int counter = 1; counter < arr.length; counter++)
        {
            if (arr[counter] > max)
            {
                max = decMax[counter];
            }
        }

        return max;
    }
}
