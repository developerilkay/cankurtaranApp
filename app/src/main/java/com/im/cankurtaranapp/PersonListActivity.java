package com.im.cankurtaranapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilkaymalta on 18.09.2017.
 */

public class PersonListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personlist);


        final ListView listView = (ListView) findViewById(R.id.veriList);

        ListeyiGüncelle();

        listView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                if (v.getId() == R.id.veriList) {
                    AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                    menu.setHeaderTitle(listView.getItemAtPosition(info.position).toString());
                    menu.add(0, 0, 0, "Detayı Göster");
                    menu.add(0, 1, 0, "Sil");
                    menu.add(0, 2, 0, "Düzelt");
                }
            }
        });
    }

    public void ListeyiGüncelle() {
        Veritabanı veritabanı = new Veritabanı(PersonListActivity.this);
        final ListView listView = (ListView) findViewById(R.id.veriList);
        List<String> veriList = veritabanı.VeriListele();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (PersonListActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, veriList);
        listView.setAdapter(adapter);
    }

    public boolean onContextItemSelected(MenuItem item) {
        Veritabanı veritabanı = new Veritabanı(PersonListActivity.this);
        final ListView detayListView = (ListView) findViewById(R.id.veriList);
        boolean donus;
        switch (item.getItemId()) {
            case 0:
                AdapterView.AdapterContextMenuInfo infodetay = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                final String secilidetay = detayListView.getItemAtPosition(infodetay.position).toString();
                String[] dizi = secilidetay.split("-");
                long id = Long.parseLong(dizi[0].trim());
                List<String> veriList = veritabanı.DetayVeriListele(id);
                Bundle bundel = new Bundle();
                bundel.putStringArrayList("dizi", (ArrayList<String>) veriList);
                Intent intent = new Intent(PersonListActivity.this, MagdurEkleActivity.class);
                intent.putExtras(bundel);
                startActivity(intent);
                donus = false;
                break;
            case 1:
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                final String secili = detayListView.getItemAtPosition(info.position).toString();
                AlertDialog.Builder builder = new AlertDialog.Builder(PersonListActivity.this);
                builder.setTitle("Veri Silme");
                builder.setMessage("\"" + secili + "\" adlı veriyi silmek istediğinize emin misiniz?");
                builder.setNegativeButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String[] dizi = secili.split("-");
                        long id = Long.parseLong(dizi[0].trim());
                        Veritabanı veritabanı = new Veritabanı(PersonListActivity.this);
                        veritabanı.VeriSil(id);
                        ListeyiGüncelle();
                    }
                });
                builder.setPositiveButton("Hayır", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                donus = true;
                break;
            case 2:
                AdapterView.AdapterContextMenuInfo infoduzelt = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                final String seciliduzelt = detayListView.getItemAtPosition(infoduzelt.position).toString();
                String[] diziduzelt = seciliduzelt.split("-");
                long idduzelt = Long.parseLong(diziduzelt[0].trim());
                List<String> veriListduzelt = veritabanı.DetayVeriListele(idduzelt);
                Bundle bundelduzelt = new Bundle();
                bundelduzelt.putStringArrayList("dizi", (ArrayList<String>) veriListduzelt);
                Intent intentduzelt = new Intent(PersonListActivity.this, MagdurEkleActivity.class);
                intentduzelt.putExtras(bundelduzelt);
                startActivity(intentduzelt);
                donus = false;
                break;
            default:
                donus = false;
                break;
        }
        return donus;
    }
}
