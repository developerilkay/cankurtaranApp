package com.im.cankurtaranapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilkaymalta on 19.09.2017.
 */

public class MagdurEkleActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magdurekle);

         new Veritabanı(MagdurEkleActivity.this);
        final EditText adi = (EditText) findViewById(R.id.adi);
        final EditText tcno =(EditText) findViewById(R.id.tcno);
        final EditText dogumtarihi=(EditText) findViewById(R.id.dogumtarihi);
        final EditText telefon = (EditText) findViewById(R.id.phone);
        final EditText yakiniadi=(EditText) findViewById(R.id.yakiniadi);
        final EditText yakinitelefon=(EditText) findViewById(R.id.yakiniphone);
        final EditText olaytarihi=(EditText) findViewById(R.id.olaytarihi);
        final EditText olayyeri=(EditText) findViewById(R.id.olayyeri);
        final EditText durumu =(EditText) findViewById(R.id.durumu);
        final EditText kurtarici=(EditText) findViewById(R.id.kurtarici);

        Button ekle = (Button) findViewById(R.id.btnekle);
        Button listele = (Button) findViewById(R.id.btnlistele);
        Button duzenle = (Button) findViewById(R.id.btnduzenle);
        ekle.setEnabled(true);
        duzenle.setEnabled(false);
        ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Veritabanı veritabanı = new Veritabanı(MagdurEkleActivity.this);
                veritabanı.VeriEkle(adi.getText().toString(),tcno.getText().toString(),dogumtarihi.getText().toString(),
                        telefon.getText().toString(),yakiniadi.getText().toString(),yakinitelefon.getText().toString(),
                        olaytarihi.getText().toString(),olayyeri.getText().toString(),durumu.getText().toString(),
                        kurtarici.getText().toString());


                Toast.makeText(getApplicationContext(), "Veri başarıyla kaydedildi.", Toast.LENGTH_LONG).show();
                //Toast.LENGTH_LONG yerine 2000 girersek 2 sn gösterecektir.
                adi.setText("");
                tcno.setText("");
                dogumtarihi.setText("");
                telefon.setText("");
                yakiniadi.setText("");
                yakinitelefon.setText("");
                olaytarihi.setText("");
                olayyeri.setText("");
                durumu.setText("");
                kurtarici.setText("");
            }
        });

        listele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MagdurEkleActivity.this, PersonListActivity.class));

            }
        });
        duzenle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle exras = getIntent().getExtras();
                if (exras != null) {
                    List<String> veri = exras.getStringArrayList("dizi");
                    String[] dizi = veri.get(0).toString().split("-");
                    Veritabanı veritabanı = new Veritabanı(MagdurEkleActivity.this);
                    veritabanı.VeriGuncelle(Long.parseLong(dizi[0].trim()), adi.getText().toString(), tcno.getText().toString(), dogumtarihi.getText().toString(),
                            telefon.getText().toString(), yakiniadi.getText().toString(), yakinitelefon.getText().toString(),
                            olaytarihi.getText().toString(), olayyeri.getText().toString(), durumu.getText().toString(),
                            kurtarici.getText().toString());


                    Toast.makeText(getApplicationContext(), "Veri başarıyla güncellendi.", Toast.LENGTH_LONG).show();
                    //Toast.LENGTH_LONG yerine 2000 girersek 2 sn gösterecektir.
                    adi.setText("");
                    tcno.setText("");
                    dogumtarihi.setText("");
                    telefon.setText("");
                    yakiniadi.setText("");
                    yakinitelefon.setText("");
                    olaytarihi.setText("");
                    olayyeri.setText("");
                    durumu.setText("");
                    kurtarici.setText("");
                }
            }
        });

        Bundle exras = getIntent().getExtras();
        if (exras != null) {
            List<String> veri = exras.getStringArrayList("dizi");
            String[] dizi = veri.get(0).toString().split("-");
            if (veri != null) {
                //Toast.makeText(getApplicationContext(), veri.get(0).toString(), Toast.LENGTH_LONG).show();
                adi.setText(dizi[1].trim());
                tcno.setText(dizi[2].trim());
                dogumtarihi.setText(dizi[3].trim());
                telefon.setText(dizi[4].trim());
                yakiniadi.setText(dizi[5].trim());
                yakinitelefon.setText(dizi[6].trim());
                olaytarihi.setText(dizi[7].trim());
                olayyeri.setText(dizi[8].trim());
                durumu.setText(dizi[9].trim());
                kurtarici.setText(dizi[10].trim());
                ekle.setEnabled(false);
                duzenle.setEnabled(true);
            }
        }

        Bundle detayexras = getIntent().getExtras();
        if (detayexras != null) {
            String[] detayveri = detayexras.getStringArray("detaydizi");
            if (detayveri != null) {
                adi.setText(detayveri[1]);
                tcno.setText(detayveri[2]);
                telefon.setText(detayveri[3]);
            }
        }

    }
}
