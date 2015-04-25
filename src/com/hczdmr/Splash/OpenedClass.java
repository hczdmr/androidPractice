package com.hczdmr.Splash;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by HaciOzdemir on 22/03/15.
 */
public class OpenedClass extends Activity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener{
    TextView question,test;
    Button returnData;
    RadioGroup selectionList;
    String ekmegiAl,cevap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send);
        degiskenleriTanimla();
        SharedPreferences getData = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String et = getData.getString("name","hczdmr");
        String values = getData.getString("list","4");
        if(values.contentEquals("1")){
            question.setText(et);
        }
        //Bundle sepet = getIntent().getExtras();
        //ekmegiAl = sepet.getString("key");
        //question.setText(ekmegiAl);
    }
    private void degiskenleriTanimla(){
        question = (TextView) findViewById(R.id.textView9);
        test = (TextView) findViewById(R.id.textView10);
        returnData = (Button) findViewById(R.id.bReturn);
        returnData.setOnClickListener(this);
        selectionList = (RadioGroup) findViewById(R.id.answers);
        selectionList.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent back = new Intent();
        Bundle backpack = new Bundle();
        backpack.putString("cevap",cevap);
        back.putExtras(backpack);
        setResult(RESULT_OK,back);
        finish();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.radioButton:
                cevap = "Hakli olabilirsin";
                break;
            case R.id.radioButton2:
                cevap = "Kesinlikle haklisin";
                break;
            case R.id.radioButton3:
                cevap = "ikisi de diyorsun";
                break;
        }
        test.setText(cevap);
    }
}
