package com.hczdmr.Splash;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.*;

import java.io.*;

/**
 * Created by HaciOzdemir on 24/04/15.
 */
public class ExternalData extends Activity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    private TextView canWrite,canRead;
    private String state;
    boolean canW,canR;
    Spinner spin;
    String[] paths = {"Musics", "Pictures", "Downloads"};
    File path = null;
    File file = null;
    EditText saveFile;
    Button confirm,save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.externaldata);
        canWrite = (TextView) findViewById(R.id.tvCanWrite);
        canRead = (TextView) findViewById(R.id.tvCanRead);
        confirm = (Button) findViewById(R.id.bConfirmSaveAs);
        save = (Button) findViewById(R.id.bSaveFile);
        saveFile = (EditText) findViewById(R.id.etSaveAs);
        confirm.setOnClickListener(this);
        save.setOnClickListener(this);
        checkState();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, paths);
        spin = (Spinner) findViewById(R.id.spinner);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(this);
    }

    private void checkState() {
        state = Environment.getExternalStorageState();
        if(state.equals(Environment.MEDIA_MOUNTED)){
            canWrite.setText("true");
            canRead.setText("true");
            canW = canR = true;
        }else if(state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)){
            canWrite.setText("false");
            canRead.setText("true");
            canW = false;
            canR = true;
        }else {
            canWrite.setText("false");
            canRead.setText("false");
            canW = canR = false;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Travis  boyle yapti ama gerek yok bence position zaten var.
        //int pos = spin.getSelectedItemPosition();
        switch (position){
            case 0:
                path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
                break;
            case 1:
                path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                break;
            case 2:
                path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bConfirmSaveAs:
                save.setVisibility(View.VISIBLE);
                break;
            case R.id.bSaveFile:
                String f = saveFile.getText().toString();
                file = new File(path,f);
                if((canR && canW) == true){

                    path.mkdirs();

                    try {
                        InputStream is = getResources().openRawResource(R.raw.explosion);
                        OutputStream os = new FileOutputStream(file);
                        byte[] data = new byte[is.available()];
                        is.read(data);
                        os.write(data);
                        is.close();
                        os.close();

                        Toast t = Toast.makeText(this,"File has been saved.",Toast.LENGTH_LONG);
                        t.show();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }
}
