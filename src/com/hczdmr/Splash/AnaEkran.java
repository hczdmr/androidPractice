package com.hczdmr.Splash;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AnaEkran extends Activity implements View.OnClickListener{
    Button add,subtract;
    TextView tv;
    int number=0;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        degiskenleriTanimla();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bAddOne:
                ++number;
                tv.setText("Your total is "+Integer.toString(number));
                break;
            case R.id.bSubtractOne:
                --number;
                tv.setText("Your total is "+Integer.toString(number));
                break;
        }
    }
    private void degiskenleriTanimla() {
        add = (Button) findViewById(R.id.bAddOne);
        subtract = (Button) findViewById(R.id.bSubtractOne);
        tv = (TextView) findViewById(R.id.tvNumber);
        add.setOnClickListener(this);
        subtract.setOnClickListener(this);
    }
}
