package com.example.ray.teachlink;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ray on 2016/12/14.
 */

public class WebData {
    private URL url=null;
    private Handler mHandler;
    public WebData(URL url,Handler mHandler){
        this.url=url;
        this.mHandler=mHandler;
    }
    public void getData(){
        new Thread(new Runnable() {
            public void run() {

                HttpURLConnection urlConnection = null;
                try {
                    //url = new URL("http://192.168.0.22/");
                    urlConnection = (HttpURLConnection) url.openConnection();
                    Log.d("Server conn status!!!", String.valueOf(urlConnection.getResponseCode()));
                    InputStream dataStream = new BufferedInputStream(urlConnection.getInputStream());
                    InputStreamReader streamReader = new InputStreamReader(dataStream,"UTF-8");
                    BufferedReader br = new BufferedReader(streamReader);
                    String line;
                    line=br.readLine();
                    Log.d("Web Content!!!!! : ",line);
                    Bundle bundle =new Bundle();
                    bundle.putString("content",line);
                    Message msg = new Message();
                    msg.setData(bundle);
                    mHandler.sendMessage(msg);
                    //tv_showData.setText(line);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (urlConnection!=null) {
                        urlConnection.disconnect();
                    }
                }
            }
        }).start();
    }
}
