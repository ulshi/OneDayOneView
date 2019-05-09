package com.cupster.lib.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cupster.lib.views.util.ToastUtil;

public class ToastActivity extends AppCompatActivity {

    int i =1;
    private  View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);

         view = findViewById(R.id.tv);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.postDelayed(run,1000);
            }
        });
    }

    Runnable run = new Runnable() {
        @Override
        public void run() {
            switch (i%4){
                case 0:
                    ToastUtil.debug("正常");
                    break;
                case 1:
                    ToastUtil.debugGreen("成功");
                    break;
                case 2:
                    ToastUtil.debugOrange("提示");
                    break;
                case 3:
                    ToastUtil.debugRed("出错啦");
                    break;
                default:
                    break;
            }
            i++;
            view.postDelayed(this,5000);
        }
    };
}
