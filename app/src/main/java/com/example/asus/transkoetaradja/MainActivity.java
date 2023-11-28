package com.example.asus.transkoetaradja;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button schedule1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnSchedule(View view) {
        Intent scheduleIntent = new Intent(this, ScheduleActivity.class);
        startActivity(scheduleIntent);
    }

    public void btnTracking(View view) {
        Intent trackingIntent = new Intent(this, tracking.class);
        startActivity(trackingIntent);
    }

    public void btnHalte(View view) {
        Intent halteIntent = new Intent(this, HalteActivity.class);
        startActivity(halteIntent);
    }
}
