package com.hczdmr.Splash;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.SQLException;

/**
 * Created by HaciOzdemir on 26/04/15.
 */
public class SQLiteExample extends Activity implements View.OnClickListener {
    Button sqlUpdate,sqlView,sqlModify,sqlGetInfo,sqlDelete;
    EditText sqlName,sqlHotness,sqlRow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqliteexample);
        sqlUpdate = (Button) findViewById(R.id.bSQLUpdate);
        sqlView = (Button) findViewById(R.id.bSQLOpenView);
        sqlName = (EditText) findViewById(R.id.etSQLName);
        sqlHotness = (EditText) findViewById(R.id.etSQLHotness);
        sqlUpdate.setOnClickListener(this);
        sqlView.setOnClickListener(this);

        sqlModify = (Button) findViewById(R.id.bSQLModify);
        sqlGetInfo = (Button) findViewById(R.id.bSQLInfo);
        sqlDelete = (Button) findViewById(R.id.bSQLDelete);
        sqlRow = (EditText) findViewById(R.id.etSQLRow);
        sqlModify.setOnClickListener(this);
        sqlGetInfo.setOnClickListener(this);
        sqlDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bSQLUpdate:
                boolean didItWorks = true;
                try {
                    String name = sqlName.getText().toString();
                    String hotness = sqlHotness.getText().toString();
                    HotOrNot entry = new HotOrNot(SQLiteExample.this);
                    entry.open();
                    entry.createEntry(name, hotness);
                    entry.close();
                }catch (Exception e){
                    didItWorks = false;
                    String error = e.toString();
                    Dialog d = new Dialog(this);
                    d.setTitle("Dang it!");
                    TextView t = new TextView(this);
                    t.setText(error);
                    d.setContentView(t);
                    d.show();
                }finally {
                    if(didItWorks) {
                        Dialog d = new Dialog(this);
                        d.setTitle("Heck Yeah!");
                        TextView t = new TextView(this);
                        t.setText("Success!");
                        d.setContentView(t);
                        d.show();
                    }
                }
                break;
            case R.id.bSQLOpenView:
                Intent i = new Intent("android.intent.action.SQLVIEW");
                startActivity(i);
                break;
            case R.id.bSQLModify:
                String mName = sqlName.getText().toString();
                String mHotness = sqlHotness.getText().toString();
                String sRow = sqlRow.getText().toString();
                long lRow = Long.parseLong(sRow);
                HotOrNot ex = new HotOrNot(this);
                try {
                    ex.open();
                    ex.updateEntry(lRow,mName,mHotness);
                    ex.close();
                } catch (SQLException e) {
                    String error = e.toString();
                    Dialog d = new Dialog(this);
                    d.setTitle("Dang it!");
                    TextView t = new TextView(this);
                    t.setText(error);
                    d.setContentView(t);
                    d.show();
                }

                break;
            case R.id.bSQLInfo:
                String s = sqlRow.getText().toString();
                long l = Long.parseLong(s);
                HotOrNot hon = new HotOrNot(this);
                try {
                    hon.open();
                    String returnedName = hon.getName(l);
                    String returnedHotness = hon.getHotness(l);
                    hon.close();
                    sqlName.setText(returnedName);
                    sqlHotness.setText(returnedHotness);
                } catch (SQLException e) {
                    String error = e.toString();
                    Dialog d = new Dialog(this);
                    d.setTitle("Dang it!");
                    TextView t = new TextView(this);
                    t.setText(error);
                    d.setContentView(t);
                    d.show();
                }
                break;
            case R.id.bSQLDelete:
                String sRow1 = sqlRow.getText().toString();
                long lRow1 = Long.parseLong(sRow1);
                HotOrNot ex1 = new HotOrNot(this);
                try {
                    ex1.open();
                    ex1.deleteEntry(lRow1);
                    ex1.close();
                } catch (SQLException e) {
                    String error = e.toString();
                    Dialog d = new Dialog(this);
                    d.setTitle("Dang it!");
                    TextView t = new TextView(this);
                    t.setText(error);
                    d.setContentView(t);
                    d.show();
                }
                break;
        }
    }
}
