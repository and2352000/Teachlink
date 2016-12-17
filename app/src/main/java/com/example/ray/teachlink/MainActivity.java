package com.example.ray.teachlink;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;


public class MainActivity extends AppCompatActivity {
    private Button bt_submit;
    private TextView tv_showData;
    private static Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_showData = (TextView) findViewById(R.id.tv_showData);
        bt_submit = (Button) findViewById(R.id.bt_submit);
        bt_submit.setOnClickListener(new ButtonOnClick());

        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                //What you want to do with these data? Just write here!
                super.handleMessage(msg);
                Bundle bundle= msg.getData();
                int select=msg.what;
                String content = bundle.getString("content");

                switch (select){
                    case 1:
                        if(true) {
                            Toast.makeText(MainActivity.this, content, Toast.LENGTH_SHORT).show();
                        }
                        break;
                }

            }
        };

    }

    private class ButtonOnClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            try {
                URL url = new URL("http://192.168.1.170");
                WebData webData = new WebData(url,mHandler);
                webData.getData();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        }
    }
}
