package com.im.cankurtaranapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilkaymalta on 14.09.2017.
 */


public class Veritabanı extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "veritabanı";
    private static final int DATEBASE_VERSION = 1;
    private static final String KISILER_TABLE = "kisiler";

    public static final String ROW_ID = "id";
    public static final String ROW_ADI = "adi";
    public static final String ROW_TCNO = "tcno";
    public static final String ROW_DOGUMTARIHI = "dogumtarihi";
    public static final String ROW_TELEFON = "telefon";
    public static final String ROW_YAKINIADI = "yakiniadi";
    public static final String ROW_YAKINITELEFON = "yakinitelefon";
    public static final String ROW_OLAYTARIHI = "olaytarihi";
    public static final String ROW_OLAYYERI = "olayyeri";
    public static final String ROW_DURUMU = "durumu";
    public static final String ROW_KURTARICI = "kurtarici";

    public Veritabanı(Context context) {
        super(context, DATABASE_NAME, null, DATEBASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sdb=new String();
        sdb="CREATE TABLE " + KISILER_TABLE + "(" + ROW_ID + " INTEGER PRIMARY KEY , " + ROW_ADI + " TEXT NOT NULL ," + ROW_TCNO + " TEXT NOT NULL ," + ROW_DOGUMTARIHI + " TEXT NOT NULL ," + ROW_TELEFON + " TEXT NOT NULL ," + ROW_YAKINIADI + " TEXT NOT NULL ," + ROW_YAKINITELEFON + " TEXT NOT NULL ," + ROW_OLAYTARIHI + " TEXT NOT NULL ," + ROW_OLAYYERI + " TEXT NOT NULL ," + ROW_DURUMU + " TEXT NOT NULL ," + ROW_KURTARICI + " TEXT NOT NULL)";
        db.execSQL(sdb);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + KISILER_TABLE);
        onCreate(db);
    }

    public void VeriEkle(String adi, String tcno, String dogumtarihi, String telefon, String yakiniadi, String yakinitelefon,
                         String olaytarihi, String olayyeri, String durumu, String kurtarici) {
        //SQLiteDatabase db = this.getReadableDatabase();
        DatabaseReference dbRef =
        ContentValues cv = new ContentValues();
        cv.put(ROW_ADI, adi.trim());
        cv.put(ROW_TCNO, tcno.trim());
        cv.put(ROW_DOGUMTARIHI, dogumtarihi.trim());
        cv.put(ROW_TELEFON, telefon.trim());
        cv.put(ROW_YAKINIADI, yakiniadi.trim());
        cv.put(ROW_YAKINITELEFON, yakinitelefon.trim());
        cv.put(ROW_OLAYTARIHI, olaytarihi.trim());
        cv.put(ROW_OLAYYERI, olayyeri.trim());
        cv.put(ROW_DURUMU, durumu.trim());
        cv.put(ROW_KURTARICI, kurtarici.trim());
        //db.insert(KISILER_TABLE, null, cv);
        //db.close();
    }

    public List<String> VeriListele() {
        List<String> veriler = new ArrayList<String>();
        SQLiteDatabase db = this.getWritableDatabase();
        String[] sutunlar = {ROW_ID, ROW_ADI, ROW_TCNO, ROW_DOGUMTARIHI, ROW_TELEFON, ROW_YAKINIADI,
                ROW_YAKINITELEFON, ROW_OLAYTARIHI, ROW_OLAYYERI, ROW_DURUMU, ROW_KURTARICI};
        Cursor cursor = db.query(KISILER_TABLE, sutunlar, null, null, null, null, null);
        while (cursor.moveToNext()) {
            veriler.add(cursor.getInt(0) + "  -  " + cursor.getString(1)
            );
        /*while (cursor.moveToNext()) {
            veriler.add(cursor.getInt(0) + "  -  " + cursor.getString(2) + "  -  " + cursor.getString(1)
                    + "  -  " + cursor.getString(4)
            );*/
        }
        return veriler;

    }
    public List<String> DetayVeriListele(long id) {
        List<String> veridetay = new ArrayList<String>();
        SQLiteDatabase veridb = this.getWritableDatabase();
        String[] verisutunlar = {ROW_ID, ROW_ADI, ROW_TCNO, ROW_DOGUMTARIHI, ROW_TELEFON, ROW_YAKINIADI,
                ROW_YAKINITELEFON,ROW_OLAYTARIHI,ROW_OLAYYERI, ROW_DURUMU, ROW_KURTARICI};
        Cursor vericursor = veridb.query(KISILER_TABLE, verisutunlar, ROW_ID+"="+id, null, null, null, null);
        while (vericursor.moveToNext()) {
            veridetay.add(vericursor.getInt(0) + " - " + vericursor.getString(1) + " - " + vericursor.getString(2)
                    + " - " + vericursor.getString(3) + " - " + vericursor.getString(4) + " - " + vericursor.getString(5)
                    + " - " + vericursor.getString(6) + " - " + vericursor.getString(7) + " - " + vericursor.getString(8)
                    + " - " + vericursor.getString(9) + " - " + vericursor.getString(10));
        }
        return veridetay;
    }

    public void VeriSil(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(KISILER_TABLE, ROW_ID + "=" + id, null);
        db.close();
    }

    public void VeriGuncelle(long id, String adi, String tcno, String dogumtarihi, String telefon, String yakiniadi, String yakinitelefon,
                         String olaytarihi, String olayyeri, String durumu, String kurtarici) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ROW_ADI, adi.trim());
        cv.put(ROW_TCNO, tcno.trim());
        cv.put(ROW_DOGUMTARIHI, dogumtarihi.trim());
        cv.put(ROW_TELEFON, telefon.trim());
        cv.put(ROW_YAKINIADI, yakiniadi.trim());
        cv.put(ROW_YAKINITELEFON, yakinitelefon.trim());
        cv.put(ROW_OLAYTARIHI, olaytarihi.trim());
        cv.put(ROW_OLAYYERI, olayyeri.trim());
        cv.put(ROW_DURUMU, durumu.trim());
        cv.put(ROW_KURTARICI, kurtarici.trim());
        db.update(KISILER_TABLE, cv,ROW_ID+"="+id,null);
        db.close();
    }
}


