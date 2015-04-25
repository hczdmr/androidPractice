package com.hczdmr.Splash;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.concurrent.ExecutionException;

/**
 * Created by HaciOzdemir on 20/03/15.
 */
public class Menu extends ListActivity{
    String siniflar[] = { "AnaEkran", "TextPlay", "Email", "Kamera", "Data", "GFX", "GFXSurface", "SoundStuff", "Slider", "Tabs", "SimpleBrowser", "Flipper", "SharedPrefs", "InternalData"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setListAdapter(new ArrayAdapter<String>(Menu.this, android.R.layout.simple_list_item_1, siniflar));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String tik = siniflar[position];
        try {
            Class sinif = Class.forName("com.hczdmr.Splash." + tik);
            Intent gonder = new Intent(Menu.this, sinif);
            startActivity(gonder);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.cool_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.aboutUs:
                Intent hakkimizda = new Intent("android.intent.action.HAKKIMIZDA");
                startActivity(hakkimizda);
                break;
            case R.id.ayarlar:
                Intent ayarlar = new Intent("android.intent.action.AYARLAR");
                startActivity(ayarlar);
                break;
            case R.id.exit:
                finish();
                break;
        }
        return true;
    }
}
