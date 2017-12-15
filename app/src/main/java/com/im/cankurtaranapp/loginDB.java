package com.im.cankurtaranapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilkaymalta on 27.10.2017.
 */

public class loginDB extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "loginDB";
    private static final int DATEBASE_VERSION = 1;
    private static final String LOGIN_TABLE = "login";

    public static final String ROW_ID = "id";
    public static final String ROW_KULLANICI = "kullanici";
    public static final String ROW_SIFRE = "sifre";

    public loginDB(Context context) {
        super(context, DATABASE_NAME, null, DATEBASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sdb=new String();
        sdb="CREATE TABLE " + LOGIN_TABLE + "(" + ROW_ID + " INTEGER PRIMARY KEY , " + ROW_KULLANICI + " TEXT NOT NULL ," + ROW_SIFRE + " TEXT NOT NULL )";
        db.execSQL(sdb);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + LOGIN_TABLE);
        onCreate(db);
    }

    public void VeriEkle(String kullanici, String sifre) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ROW_KULLANICI, kullanici.trim());
        cv.put(ROW_SIFRE, sifre.trim());
        db.insert(LOGIN_TABLE, null, cv);
        db.close();
    }

    public List<String> VeriListele() {
        List<String> veriler = new ArrayList<String>();
        SQLiteDatabase db = this.getWritableDatabase();
        String[] sutunlar = {ROW_ID, ROW_KULLANICI, ROW_SIFRE};
        Cursor cursor = db.query(LOGIN_TABLE, sutunlar, null, null, null, null, null);
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
        String[] verisutunlar = {ROW_ID,  ROW_KULLANICI, ROW_SIFRE };
        Cursor vericursor = veridb.query(LOGIN_TABLE, verisutunlar, ROW_ID+"="+id, null, null, null, null);
        while (vericursor.moveToNext()) {
            veridetay.add(vericursor.getInt(0) + " - " + vericursor.getString(1) + " - " + vericursor.getString(2));
        }
        return veridetay;
    }

    public void VeriSil(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(LOGIN_TABLE, ROW_ID + "=" + id, null);
        db.close();
    }

    public void VeriGuncelle(long id, String adi, String tcno, String dogumtarihi, String telefon, String yakiniadi, String yakinitelefon,
                             String olaytarihi, String olayyeri, String durumu, String kurtarici) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ROW_KULLANICI, adi.trim());
        cv.put(ROW_SIFRE, tcno.trim());
        db.update(LOGIN_TABLE, cv,ROW_ID+"="+id,null);
        db.close();
    }
}
