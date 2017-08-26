package com.example.xiaojun.lingdaotanchuang.DownloadService;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.os.ResultReceiver;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Administrator on 2016/7/6 0006.
 */
public class DownloadService extends IntentService {
    public static final int UPDATE_PROGRESS = 8344;
    public  String ididid;

    public DownloadService() {
        super("DownloadService");
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        String urlToDownload = intent.getStringExtra("url");
        ResultReceiver receiver = intent.getParcelableExtra("receiver");
        ididid=intent.getStringExtra("urlName");
        Log.d("DownloadService", "获取的idid"+ididid);
        String nameType=intent.getStringExtra("nameType");

        Log.d("DownloadService", "下载地址"+urlToDownload);
        try {
            URL url = new URL(urlToDownload);
            URLConnection connection = url.openConnection();
            connection.connect();
            // this will be useful so that you can show a typical 0-100% progress bar
            int fileLength = connection.getContentLength();
            // download the file
            InputStream input = new BufferedInputStream(connection.getInputStream());
            OutputStream output = new FileOutputStream(Environment.getExternalStorageDirectory()+ File.separator+"linhefile"+File.separator+ididid+"."+nameType);
            byte data[] = new byte[1024];
            long total = 0;
            int count;
            while ((count = input.read(data)) != -1) {
                total += count;
                // publishing the progress....
                Bundle resultData = new Bundle();
                resultData.putInt("progress" ,(int) (total * 100 / fileLength));
                receiver.send(UPDATE_PROGRESS, resultData);
                output.write(data, 0, count);

            }
            output.flush();
            output.close();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bundle resultData = new Bundle();

        resultData.putString("ididid2" ,ididid);
        resultData.putInt("progress" ,100);
        Log.d("DownloadService", "下载的idid"+ididid);

        receiver.send(UPDATE_PROGRESS, resultData);

    }
}