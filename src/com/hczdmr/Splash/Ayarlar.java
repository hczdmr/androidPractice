package com.hczdmr.Splash;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by HaciOzdemir on 10/04/15.
 */
public class Ayarlar extends PreferenceActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref);
    }
}
