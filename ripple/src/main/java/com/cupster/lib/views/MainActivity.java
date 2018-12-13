package com.cupster.lib.views;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

/**
 * 1.拷贝 RippleLayout/SupportRippleLayout 二选一
 * 2.拷贝res/values/attrs.xml
 * 3.布局文件中使用
 */
public class MainActivity extends AppCompatActivity {


    RippleLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.ripple_layout);
        ImageView imageView = findViewById(R.id.centerImage);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (layout.isRippleAnimationRunning()){
                    layout.stopRippleAnimation();
                }else {
                    layout.startRippleAnimation();
                }
            }
        });
    }





}
