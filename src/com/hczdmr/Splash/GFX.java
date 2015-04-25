package com.hczdmr.Splash;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;

/**
 * Created by HaciOzdemir on 12/04/15.
 */
public class GFX extends Activity{
    HaciOzdemir myView;
    PowerManager.WakeLock wL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        PowerManager pM = (PowerManager)getSystemService(Context.POWER_SERVICE);
        wL = pM.newWakeLock(PowerManager.FULL_WAKE_LOCK,"uyanikKal");

        super.onCreate(savedInstanceState);
        wL.acquire();
        myView = new HaciOzdemir(this);
        setContentView(myView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        wL.release();
    }
}
