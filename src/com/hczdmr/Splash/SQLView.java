package com.hczdmr.Splash;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.sql.SQLException;

/**
 * Created by HaciOzdemir on 26/04/15.
 */
public class SQLView extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlview);
        TextView tv = (TextView) findViewById(R.id.tvSQLinfo);
        HotOrNot info = new HotOrNot(this);
        try {
            info.open();
            String data = info.getData();
            info.close();
            tv.setText(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
