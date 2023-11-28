package com.example.asus.transkoetaradja.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ASUS on 11/3/2016.
 */
public class DBHelper extends SQLiteOpenHelper{

    public static final String DB_NAME ="TRANS9";
    public static final int DB_VERSION = 1;

    public static final String TABLE_NAME1= "tabel_halte";
    public static final String KEY_IDHALTE= "id";
    public static final String KEY_HALTE= "namaHalte";

    //-------------DEKLARASI UNTUK MEMBUAT TABEL-------------//
    private static final String SCRIPT_CREATE_TABLE =
            "create table " + TABLE_NAME1 + " ("
                    + KEY_IDHALTE + " integer primary key autoincrement, "
                    + KEY_HALTE + " text not null);";

    //-------------DEKLARASI UNTUK MENGHAPUS TABEL-------------//
    private static final String SCRIPT_DELETE_TABLE="DROP TABLE IF EXISTS " + TABLE_NAME1;

    public DBHelper(Context context){
        //BUAT DATABASE JIKA TIDAK ADA
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //BUAT TABEL
        db.execSQL(SCRIPT_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SCRIPT_DELETE_TABLE);
        onCreate(db);
    }
}
