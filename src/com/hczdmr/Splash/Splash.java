package com.hczdmr.Splash;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;

/**
 * Created by HaciOzdemir on 19/03/15.
 */

public class Splash extends Activity {
    MediaPlayer sarki;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        sarki = MediaPlayer.create(Splash.this,R.raw.lazy_song_ringtone);
        SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean music = getPrefs.getBoolean("checkbox",true);
        if(music==true)
            sarki.start();

        // Thread hazırlanıyor
        Thread timer = new Thread() {

            @Override
            public void run() {

                try {
                    // Uygulama 5 saniye aynı ekranda bekliyor.
                    sleep(5000);
                } catch (InterruptedException e) {

                    // Hata yönetimi
                    e.printStackTrace();
                } finally {
                    // Splash ekranindan sonra acilmak istenen sayfa
                    Intent anaEkran = new Intent();
                    anaEkran.setClass(getApplicationContext(), Menu.class);
                    startActivity(anaEkran);

                }

            }
        };

        // Thread başlatılıyor
        timer.start();

    }

    @Override
    protected void onPause() {
        super.onPause();
        sarki.release();
        finish();
    }
}
