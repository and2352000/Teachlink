package com.example.ray.teachlink;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class MainActivity extends AppCompatActivity {
    private Button bt_submit;
    private TextView tv_showData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_showData = (TextView) findViewById(R.id.tv_showData);
        bt_submit = (Button) findViewById(R.id.bt_submit);

        bt_submit.setOnClickListener(new ButtonOnClick());
    }

    private class ButtonOnClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {

                new Thread(new Runnable() {
                    public void run() {
                        URL url;
                        HttpURLConnection  urlConnection = null;
                        try {
                            url = new URL("http://192.168.1.170/");
                            urlConnection = (HttpURLConnection) url.openConnection();
                            Log.d("Connecting status!!!", String.valueOf(urlConnection.getResponseCode()));
                            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
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
}
