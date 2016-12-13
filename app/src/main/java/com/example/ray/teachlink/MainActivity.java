package com.example.ray.teachlink;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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
                super.handleMessage(msg);
                Bundle bundle= msg.getData();
                String content = bundle.getString("content");
                tv_showData.setText(content);
            }
        };

    }

    private class ButtonOnClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            try {
                URL url = new URL("http://192.168.0.23/");
                WebData webData = new WebData(url,mHandler);
                webData.getData();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        }
    }
}
