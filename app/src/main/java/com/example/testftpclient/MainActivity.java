package com.example.testftpclient;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.adeel.library.easyFTP;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    class uploadTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                easyFTP ftp = new easyFTP();
                ftp.connect("address","username","password");
                boolean status=false;
                status=ftp.setWorkingDirectory("Files/Uploads/Images"); // if User say provided any Destination then Set it , otherwise
                // Upload will be stored on Default /root level on server
                InputStream is=getResources().openRawResource(+R.drawable.easyftptest);//??
                ftp.uploadFile(is,"test.png");
                return new String("Upload Successful");
            }catch (Exception e){
                String t="Failure : " + e.getLocalizedMessage();
                return t;
            }
        }
    }
}
