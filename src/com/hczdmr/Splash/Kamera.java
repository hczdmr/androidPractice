package com.hczdmr.Splash;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by HaciOzdemir on 21/03/15.
 */
public class Kamera extends Activity implements View.OnClickListener{
    ImageView iv;
    ImageButton ib;
    Button b;
    Intent i;
    int kameraResult = 0;
    Bitmap bm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo);
        degiskenTanimla();
        //InputStream is = getResources().openRawResource(R.drawable.icon_camera);
        //bm = BitmapFactory.decodeStream(is);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fotoAl:
                i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i,kameraResult);
                break;
            case R.id.arkaplanYap:
                try {
                    getApplicationContext().setWallpaper(bm);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
    private void degiskenTanimla() {
        iv = (ImageView) findViewById(R.id.imageView);
        ib = (ImageButton) findViewById(R.id.fotoAl);
        b = (Button) findViewById(R.id.arkaplanYap);
        ib.setOnClickListener(this);
        b.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            bm = (Bitmap) extras.get("data");
            iv.setImageBitmap(bm);
        }
    }
}
