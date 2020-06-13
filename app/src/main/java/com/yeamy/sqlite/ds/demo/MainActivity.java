package com.yeamy.sqlite.ds.demo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final TextView tv = new TextView(this);
        setContentView(tv);

        new AsyncTask<Context, String, String>() {

            @Override
            protected String doInBackground(Context[] contexts) {
                Database db = new Database(contexts[0]);
                return db.get();
            }

            @Override
            protected void onPostExecute(String s) {
                tv.setText(s);
            }
        }.execute(getApplicationContext());

    }

}
