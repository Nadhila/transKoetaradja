package com.example.asus.transkoetaradja;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class BusActivity extends AppCompatActivity {
    public static final String NAMA_BUS = "";
    public static final int DETAIL_REQUEST_CODE = 1001;

    String[] BUS = {
            "BUS 1",
            "BUS 2",
            "BUS 3",
            "BUS 4",
            "BUS 5",
            "BUS 6",
            "BUS 7",
            "BUS 8",
            "BUS 9",
            "BUS 10",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);

        String halte = getIntent().getStringExtra(ScheduleActivity.NAMA_HALTE);
        TextView tv = (TextView) findViewById(R.id.listView2);
        tv.setText(halte);

        ListView listView=(ListView)findViewById(R.id.bisa2);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,BUS);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String bus = (String) adapterView.getItemAtPosition(i);
                Toast.makeText(view.getContext(),""+bus,Toast.LENGTH_SHORT).show();

                if(i == 0){
                    Intent s = new Intent(view.getContext(),TimeActivity.class);
                    s.putExtra(NAMA_BUS,bus);
                    startActivityForResult(s, DETAIL_REQUEST_CODE);
                }

            }
        });

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
