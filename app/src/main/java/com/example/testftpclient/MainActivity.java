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
//                FTPClient mFtpClient = new FTPClient();
//                mFtpClient.setConnectTimeout(10 * 1000);
//                mFtpClient.connect(InetAddress.getByName(ip));
//                status = mFtpClient.login(userName, pass);
//                Log.e("isFTPConnected", String.valueOf(status));
//
//                FTPClient f = new FTPClient();
//                f.connect(server);
//                f.login(username, password);
//                easyFTP ftp = new easyFTP();
//                ftp.connect("188.247.50.242","","");
//                boolean status=false;
//                status=ftp.setWorkingDirectory("exchange_terminal/Holzunova15/"); // if User say provided any Destination then Set it , otherwise
//                // Upload will be stored on Default /root level on server
//                //InputStream is=getResources().openRawResource(drawable.easyftptest);//??

//                easyFTP ftp = new easyFTP();
//
//                ftp.connect("188.247.50.242","","");
//                String path = getFilesDir() + "/";
//                ftp.downloadFile( "exchange_terminal/Holzunova15/1CtoShop.xml" , path+"1CtoShop.xml" );



                //ftp.uploadFile(path+"1CtiShop1.xml");
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
