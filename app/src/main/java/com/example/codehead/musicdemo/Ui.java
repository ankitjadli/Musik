package com.example.codehead.musicdemo;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Ui extends AppCompatActivity{

    private static final int MY_PERMISSIONS_REQUEST = 1;
    private Button mSongButton;
    private Button mArtistButton;
    private RelativeLayout ui;
    private String timer;
    private Date time;
    private TextView timetext;


    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui);
        requestPermission();
        timer = "sunny";
        ui = (RelativeLayout) findViewById(R.id.uiback) ;
        mSongButton=findViewById(R.id.song_button);
        mArtistButton=findViewById(R.id.artist_button);

        testtime();


        mSongButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Ui.this,MainActivity.class);
                startActivity(intent);
            }

        });
        mArtistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Ui.this,Artists.class);
                startActivity(intent);
            }
        });



    }

    private void testtime() {
        Date d=new Date();
        SimpleDateFormat sdf= new SimpleDateFormat("HH");
        String currentDateTimeString = sdf.format(d);
        String timezoneone = "19";
        String timezonetwo = "7";
        int resulttimezone2 = currentDateTimeString.compareTo(timezonetwo);
        int resulttimezone1 = currentDateTimeString.compareTo(timezoneone);

        if(resulttimezone1 == 0)
            dark();
        else if(resulttimezone1 < 0 && resulttimezone2 > 0) {
            sun();
        }
        else if(resulttimezone1 < 0 && resulttimezone2 < 0) {
            dark();
        }
        else
            dark();


    }

    private void sun()
    {
        mSongButton.setBackgroundResource(R.drawable.tap);
        mArtistButton.setBackgroundResource(R.drawable.taap);
        ui.setBackgroundResource(R.drawable.back);
    }
    private void dark()
    {
        mSongButton.setBackgroundResource(R.drawable.darksongs);
        mArtistButton.setBackgroundResource(R.drawable.darkartist);
        ui.setBackgroundResource(R.drawable.homeblack);
    }













    private void requestPermission(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                AlertDialog.Builder builder = new AlertDialog.Builder(Ui.this);
                builder.setTitle("Need Storage Permission");
                builder.setMessage("This app needs storage permission.");
                builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        ActivityCompat.requestPermissions(Ui.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST);
                    }
                });
                builder.setNegativeButton("Deny", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();

            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST);

                // MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }
    }


}

