package com.hczdmr.Splash;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.*;

/**
 * Created by HaciOzdemir on 24/04/15.
 */
public class InternalData extends Activity implements View.OnClickListener{
    EditText sharedData;
    TextView dataResults;
    FileOutputStream fos;
    String FILENAME = "internalString";
    FileInputStream fis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharedpreferences);
        setupVariables();
    }

    private void setupVariables() {
        sharedData = (EditText)findViewById(R.id.etSharedPref);
        dataResults = (TextView) findViewById(R.id.tvResult);
        Button save = (Button) findViewById(R.id.bSave);
        Button load = (Button) findViewById(R.id.bLoad);
        save.setOnClickListener(this);
        load.setOnClickListener(this);
        try {
            fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bSave:
                String data = sharedData.getText().toString();
                //Saving data via file
             /*   File f = new File(FILENAME);
                try {
                    fos = new FileOutputStream(f);
                    //write some data
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                */
                try {
                    fos = openFileOutput(FILENAME,Context.MODE_PRIVATE);
                    fos.write(data.getBytes());
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.bLoad:
                //Bu sekilde yaptik sonra bunu Asynctask icine tasiyoruz.
               /* String collected = null;
                try {
                    fis = openFileInput(FILENAME);
                    byte[] dataArray = new byte[fis.available()];
                    while (fis.read(dataArray) != -1){
                        collected = new String(dataArray);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        fis.close();
                        dataResults.setText(collected);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }*/
                new loadSomeStuff().execute(FILENAME);
                break;
        }
    }
    public class loadSomeStuff extends AsyncTask<String,Integer,String>{
        ProgressDialog dialog;

           //Birinci String execute icine paslanan deger tipi
          //Burada Tip1 doInBackground metoduna verilecek parametrelerin tipini ya da sınıfını belirler (örnekte Void).
         // Tip2 doInBackground metodunun işleyişi sırasında onProgressUpdate metoduna paslanacak değişkenin tipini ya da sınıfını belirtir.
        // Tip3 ise onPostExecute metoduna verilen değişkendir ve aynı zamanda doInBackground metodunun return tipidir.

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //setting up something
            dialog = new ProgressDialog(InternalData.this);
            dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            dialog.setMax(100);
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String collected = null;

            for(int i=0; i<20; i++){
                publishProgress(5);
                try {
                    Thread.sleep(88);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            try {
                fis = openFileInput(FILENAME);
                byte[] dataArray = new byte[fis.available()];
                while (fis.read(dataArray) != -1){
                    collected = new String(dataArray);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    fis.close();
                    return collected;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            // 5 er 5 er ilerleticek 100 e kadar.
            dialog.incrementProgressBy(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dialog.dismiss();
            // doinbackgrounddan donen collected burada s parametresiyle aliniyor.
            dataResults.setText(s);
        }
    }
}
