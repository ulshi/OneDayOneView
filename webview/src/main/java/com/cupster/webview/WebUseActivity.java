package com.cupster.webview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WebUseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_use);
        H5Activity.open(this,"https://www.baidu.com");
    }
}
