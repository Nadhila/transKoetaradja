package com.example.asus.transkoetaradja;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class TimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);

        String bus = getIntent().getStringExtra(BusActivity.NAMA_BUS);
        TextView tv = (TextView) findViewById(R.id.listView3);
        tv.setText(bus);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
