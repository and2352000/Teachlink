package com.example.ray.teachlink;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by ray on 2016/12/14.
 */

public class WebData {
    private URL url=null;
    private Handler mHandler;
    private Map<String,String> reqData;
    private String query=null;
    //private UserInfo user;
    public WebData(URL url,Handler mHandler){
        this.url=url;
        this.mHandler=mHandler;
    }

    public void getData(){
        new Thread(new Runnable() {
            public void run() {

                HttpURLConnection urlConnection = null;
                try {
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setReadTimeout(5000);
                    urlConnection.setConnectTimeout(5000);
                    urlConnection.setDoInput(true);
                    urlConnection.setDoInput(true);
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setUseCaches(false);

                    //Open connection for sending data
                    OutputStream os =urlConnection.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os,"UTF-8"));
                    writer.write(query);
                    writer.flush();
                    writer.close();
                    urlConnection.connect();

                    //Get response content
                    Log.d("Server conn status!!!", String.valueOf(urlConnection.getResponseCode()));
                    InputStream dataStream = new BufferedInputStream(urlConnection.getInputStream());
                    InputStreamReader streamReader = new InputStreamReader(dataStream,"UTF-8");
                    BufferedReader br = new BufferedReader(streamReader);
                    String line;
                    line=br.readLine();
                    Log.d("Web Content!!!!! : ",line);
                    Bundle bundle =new Bundle();
                    bundle.putString("content",line);
                    //set msg
                    Message msg = new Message();
                    msg.what=1;
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
    public void setReqData(Map<String,String> reqData){
        this.reqData=reqData;
        Uri.Builder builder = new Uri.Builder();
        //Append parameters to URL
        if(reqData.get("select")=="login") {
            builder = builder
                    .appendQueryParameter("select",reqData.get("select"))
                    .appendQueryParameter("username", reqData.get("username"))
                    .appendQueryParameter("password", reqData.get("password"));
            query = builder.build().getEncodedQuery();
            Log.d("POST query",query);
        }
    }

}
