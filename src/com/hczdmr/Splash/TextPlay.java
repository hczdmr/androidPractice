package com.hczdmr.Splash;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Random;

/**
 * Created by HaciOzdemir on 20/03/15.
 */
public class TextPlay extends Activity implements View.OnClickListener{
    //bir degiskeni final tanimlamakla burada tanimlamak ayni seydir.
    Button dene;
    ToggleButton sec;
    EditText input;
    TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text);

        degiskenleriTanimla();
        sec.setOnClickListener(this);
        dene.setOnClickListener(this);
    }
    private void degiskenleriTanimla(){
        dene = (Button) findViewById(R.id.button);
        sec = (ToggleButton) findViewById(R.id.toggleButton);
        input = (EditText) findViewById(R.id.editText);
        result = (TextView) findViewById(R.id.textView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                String str = input.getText().toString();
                result.setText(str);
                if(str.contentEquals("left")){
                    result.setGravity(Gravity.LEFT);
                }else if(str.contentEquals("center")) {
                    result.setGravity(Gravity.CENTER);
                }else if(str.contentEquals("right")) {
                    result.setGravity(Gravity.RIGHT);
                }else if(str.contentEquals("blue")) {
                    result.setTextColor(Color.BLUE);
                }else if(str.contains("WTF")) {
                    Random crazy = new Random();
                    result.setText("WTF!!!!");
                    result.setTextSize(crazy.nextInt(75));
                    result.setTextColor(Color.rgb(crazy.nextInt(265),crazy.nextInt(265),crazy.nextInt(265)));
                    switch(crazy.nextInt(3)) {
                        case 0:
                            result.setGravity(Gravity.LEFT);
                            break;
                        case 1:
                            result.setGravity(Gravity.CENTER);
                            break;
                        case 2:
                            result.setGravity(Gravity.RIGHT);
                            break;
                    }
                }else {
                    result.setText("invalid");
                    result.setGravity(Gravity.CENTER);
                    result.setTextColor(Color.WHITE);
                }
                break;
            case R.id.toggleButton:
                if(sec.isChecked()){
                    input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }else{
                    input.setInputType(InputType.TYPE_CLASS_TEXT);
                }
                break;
        }
    }
}
