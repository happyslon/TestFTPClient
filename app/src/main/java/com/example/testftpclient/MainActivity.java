package com.example.testftpclient;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import java.io.InputStream;

import static com.example.testftpclient.R.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        Button btnStart = findViewById(id.btn_start);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new UploadTask().execute();
            }
        });
    }

    class UploadTask extends AsyncTask<Void, String, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {

                publishProgress("Upload Successful");


            }catch (Exception e){
                publishProgress("Failure : " + e.toString());

            }

            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            if(values != null && values.length != 0){
                addLog(values[0]);
            }
        }

    }

    private void addLog(String values) {
        Toast.makeText(this, values, Toast.LENGTH_SHORT).show();
    }
}
