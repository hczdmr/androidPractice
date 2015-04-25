package com.hczdmr.Splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by HaciOzdemir on 21/03/15.
 */
public class Email extends Activity implements View.OnClickListener{
    EditText mail,giris,isim,olay,istek,sonuc;
    String smail,sgiris,sisim,solay,sistek,ssonuc;
    Button mailGonder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email);
        degiskenleriTanimla();
        mailGonder.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        edittextElemanlariniStringeDonustur();
        String emailAdress[] = {smail};
        String mesaj = "Merhaba "
                + sisim
                + " sunu soylemek istiyorum "
                + sgiris
                + ". sadece bu degil ama, senin sunlari yapmandan nefret ediyorum: "
                + solay
                + ", bunlar beni cildirtiyor. Ben de sana sunlari yapmak istiyorum: "
                + sistek
                + " Sonuc olarak: "
                + ssonuc
                + "sanirim yinede seni seviyorum.......";
        Intent mailIntent = new Intent(Intent.ACTION_SEND);
        mailIntent.putExtra(Intent.EXTRA_EMAIL,emailAdress);
        mailIntent.putExtra(Intent.EXTRA_SUBJECT, "nefret mesaji");
        mailIntent.setType("plain/text");
        mailIntent.putExtra(Intent.EXTRA_TEXT,mesaj);
        startActivity(mailIntent);
    }

    private void degiskenleriTanimla(){
        mail = (EditText) findViewById(R.id.editText2);
        giris = (EditText) findViewById(R.id.editText3);
        isim = (EditText) findViewById(R.id.editText4);
        olay = (EditText) findViewById(R.id.editText5);
        istek = (EditText) findViewById(R.id.editText6);
        sonuc = (EditText) findViewById(R.id.editText7);
        mailGonder = (Button) findViewById(R.id.button2);
    }

    private void edittextElemanlariniStringeDonustur() {
        smail = mail.getText().toString();
        sgiris = giris.getText().toString();
        sisim = isim.getText().toString();
        solay = olay.getText().toString();
        sistek = istek.getText().toString();
        ssonuc = sonuc.getText().toString();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
