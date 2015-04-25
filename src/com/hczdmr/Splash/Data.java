package com.hczdmr.Splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by HaciOzdemir on 22/03/15.
 */
public class Data extends Activity implements View.OnClickListener{
    Button start,startFor;
    EditText edit;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get);
        degiskenleriTanimla();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button3:
                String ekmek = edit.getText().toString();
                Bundle sepet = new Bundle();
                sepet.putString("key",ekmek);
                Intent i = new Intent(Data.this,OpenedClass.class);
                i.putExtras(sepet);
                startActivity(i);
                break;
            case R.id.button4:
                Intent a = new Intent(Data.this,OpenedClass.class);
                startActivityForResult(a,0);
                break;
        }

    }
    private void degiskenleriTanimla() {
        start = (Button) findViewById(R.id.button3);
        startFor = (Button) findViewById(R.id.button4);
        edit = (EditText) findViewById(R.id.editText8);
        tv = (TextView) findViewById(R.id.textView8);
        start.setOnClickListener(this);
        startFor.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Bundle sepet = data.getExtras();
            String s = sepet.getString("cevap");
            tv.setText(s);
        }
    }
}
