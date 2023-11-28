package com.example.asus.transkoetaradja;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.asus.transkoetaradja.Helper.DBHelper;

import java.util.ArrayList;

public class HalteActivity extends AppCompatActivity {
    private ListView listView;
    ListAdapter adapter;
    ArrayList<String> records;
    DBHelper dbhelper;
    public static final String TITIK_HALTE1 = "";
    public static final String TITIK_HALTE2 = "";
    public static final String NAMA_HALTE = "";
    public static final int DETAIL_REQUEST_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halte);
        listView=(ListView)findViewById(R.id.bisa3);
        records=new ArrayList<String>();

        adapter=new ListAdapter(this,R.layout.item_list,R.id.output,records);
        listView.setAdapter(adapter);
        dbhelper=new DBHelper(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {

                String halte = (String) parent.getItemAtPosition(i);
                Toast.makeText(HalteActivity.this, ""+halte , Toast.LENGTH_LONG).show();

                if(i == 0){
                      String latitude = "5.566280";
                      String langitude = "95.366630";
                      Intent s = new Intent(view.getContext(),MapsActivity.class);
                      s.putExtra(TITIK_HALTE1,latitude);
                      s.putExtra(TITIK_HALTE2,langitude);
                      s.putExtra(NAMA_HALTE,halte);
                      startActivityForResult(s, DETAIL_REQUEST_CODE);
                }

                if(i == 1){
                    String latitude = "-6.903932";
                    String langitude = "107.608629";
                    Intent t = new Intent(view.getContext(),MapsActivity.class);
                    t.putExtra(TITIK_HALTE1,latitude);
                    t.putExtra(TITIK_HALTE2,langitude);
                    t.putExtra(NAMA_HALTE,halte);
                    startActivityForResult(t, DETAIL_REQUEST_CODE);
                }
            }
        });
    }

    public void onStart(){
        super.onStart();
        readSampleData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }


    public void readSampleData(){
        //MEMBUKA DATABASE
        SQLiteDatabase database=dbhelper.getReadableDatabase();
        String sql="SELECT * FROM "+ DBHelper.TABLE_NAME1;

        //MEMBUAT KURSOR UNTUK MEMBUKA DATABASE
        Cursor c=database.rawQuery(sql,null);
        String halte;
        int id;
        if(c.getCount()>0)
            while(c.moveToNext()){
                id=c.getInt(c.getColumnIndex(DBHelper.KEY_IDHALTE));
                halte=c.getString(c.getColumnIndex(DBHelper.KEY_HALTE));

                String item=id+"_"+halte;
                records.add(item);
            }
        //notify listview of dataset changed
        adapter.notifyDataSetChanged();
    }
}
