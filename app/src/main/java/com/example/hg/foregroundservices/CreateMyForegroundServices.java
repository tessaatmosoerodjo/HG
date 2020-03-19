package com.example.hg.foregroundservices;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.hg.R;

public class CreateMyForegroundServices extends AppCompatActivity implements View.OnClickListener {

    private Button start;
    private Button stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_my_foreground_services);


        //getting buttons from xml
        start = findViewById(R.id.start);
        stop = findViewById(R.id.stop);

        //attaching onclicklistener to buttons
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
    }

//    public void startService(View v) {
//        Intent startIntent = new Intent(this, MyService.class);
//        startIntent.setAction("start");
//        startService(startIntent);
//    }
//
//    public void stopService(View v) {
//        Intent stopIntent = new Intent(this, MyService.class);
//        stopIntent.setAction("stop");
//        stopService(stopIntent);
//    }


    @Override
    public void onClick(View view) {
        if (view == start) {
            //start the service here
            startService(new Intent(this, MyService.class));
        } else if (view == stop) {
            //stop the service here
            stopService(new Intent(this, MyService.class));
        }
    }







}
