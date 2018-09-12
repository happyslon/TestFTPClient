package com.example.testftpclient;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import static com.example.testftpclient.R.*;

public class MainActivity extends AppCompatActivity {
    FTPClient mFtpClient;

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
                if(connectingwithFTP("188.247.50.242","root","zhbr159753")) {
                    publishProgress("Connected");
                }
                File file = new File(getFilesDir() + "/"+"1CtoShop.xml");
                if(downloadSingleFile(mFtpClient, "/exchange_terminal/Holzunova15/1CtoShop.xml", file)){
                    publishProgress("downloaded file");
                }


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
    /**
     *
     * @param ip
     * @param userName
     * @param pass
     */
    public boolean connectingwithFTP(String ip, String userName, String pass) {
        boolean status = false;
        try {
            mFtpClient = new FTPClient();
            mFtpClient.setConnectTimeout(10 * 1000);
            mFtpClient.connect(InetAddress.getByName(ip));
            status = mFtpClient.login(userName, pass);
            Log.e("isFTPConnected", String.valueOf(status));
            if (FTPReply.isPositiveCompletion(mFtpClient.getReplyCode())) {
                mFtpClient.setFileType(FTP.ASCII_FILE_TYPE);
                mFtpClient.enterLocalPassiveMode();
                FTPFile[] mFileArray = mFtpClient.listFiles();
                Log.e("Size", String.valueOf(mFileArray.length));
            }

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return status;
    }
    /**
     * @param ftpClient FTPclient object
     * @param remoteFilePath  FTP server file path
     * @param downloadFile   local file path where you want to save after download
     * @return status of downloaded file
     */
    public boolean downloadSingleFile(FTPClient ftpClient,
                                      String remoteFilePath, File downloadFile) {
        File parentDir = downloadFile.getParentFile();
        if (!parentDir.exists())
            parentDir.mkdir();
        OutputStream outputStream = null;
        try {
            outputStream = new BufferedOutputStream(new FileOutputStream(
                    downloadFile));
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            return ftpClient.retrieveFile(remoteFilePath, outputStream);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}
