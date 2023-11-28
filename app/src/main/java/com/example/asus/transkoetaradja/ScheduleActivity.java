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

public class ScheduleActivity extends AppCompatActivity {

    private ListView listView;
    ListAdapter adapter;
    ArrayList<String> records;
    DBHelper dbhelper;
    public static final String NAMA_HALTE = "";
    public static final int DETAIL_REQUEST_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        listView=(ListView)findViewById(R.id.bisa);
        records=new ArrayList<String>();

        adapter=new ListAdapter(this,R.layout.item_list,R.id.output,records);
        listView.setAdapter(adapter);
        dbhelper=new DBHelper(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {

                String halte = (String) parent.getItemAtPosition(i);
                Toast.makeText(ScheduleActivity.this, ""+halte , Toast.LENGTH_LONG).show();

                if(i == 0){
                    Intent s = new Intent(view.getContext(),BusActivity.class);
                    s.putExtra(NAMA_HALTE,halte);
                    startActivityForResult(s, DETAIL_REQUEST_CODE);
                }
            }
        });
    }

    public void onStart(){
        super.onStart();
        insertSampleData();
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

    //MEMASUKKAN SAMPLE DATA
    public void insertSampleData() {
        try {
            //get a writable database
            SQLiteDatabase database = dbhelper.getWritableDatabase();

            database.execSQL("INSERT OR REPLACE INTO tabel_halte(namaHalte)"
                    + " values ('HALTE APK KEUDAH')");
            database.execSQL("INSERT OR REPLACE INTO tabel_halte(namaHalte)"
                    + " values ('HALTE SP.KEURAMAT')");
            database.execSQL("INSERT OR REPLACE INTO tabel_halte(namaHalte)"
                    + " values ('HALTE DPN.HOTEL MEKAH')");
            database.execSQL("INSERT OR REPLACE INTO tabel_halte(namaHalte)"
                    + " values ('HALTE DPN. DSI')");
//            database.execSQL("INSERT INTO tabel_halte(namaHalte)"
//                    + " values ('HALTE DPN.RS. U'BUDIYAH')");
//        database.execSQL("INSERT INTO tabel_halte(namaHalte)"
//                + " values ('HALTE BPN')");
//        database.execSQL("INSERT INTO tabel_halte(namaHalte)"
//                + " values ('HALTE TEKNIK')");
//        database.execSQL("INSERT INTO tabel_halte(namaHalte)"
//                + " values ('HALTE KEDOKTERAN')");
//        database.execSQL("INSERT INTO tabel_halte(namaHalte)"
//                + " values ('HALTE MESJID JAMIK')");
//        database.execSQL("INSERT INTO tabel_halte(namaHalte)"
//                + " values ('HALTE UIN')");
//        database.execSQL("INSERT INTO tabel_halte(namaHalte)"
//                + " values ('HALTE LAMNYONG')");
//        database.execSQL("INSERT INTO tabel_halte(namaHalte)"
//                + " values ('HALTE SP.MESRA')");
//        database.execSQL("INSERT INTO tabel_halte(namaHalte)"
//                + " values ('HALTE PRADA')");
//        database.execSQL("INSERT INTO tabel_halte(namaHalte)"
//                + " values ('HALTE DPN.DKKA')");
//        database.execSQL("INSERT INTO tabel_halte(namaHalte)"
//                + " values ('HALTE RSUDZA')");
//        database.execSQL("INSERT INTO tabel_halte(namaHalte)"
//                + " values ('HALTE SP.GP.KUTA ALAM')");
//        database.execSQL("INSERT INTO tabel_halte(namaHalte)"
//                + " values ('HALTE PEUNAYONG')");
        }
        catch (SQLException sqe) {
            Log.e("fetchAllItems", "FAILED: " + sqe.getMessage() + " allData = ");
        }
    }

}
