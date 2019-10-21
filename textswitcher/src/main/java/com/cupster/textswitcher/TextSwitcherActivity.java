package com.cupster.textswitcher;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.cupster.textswitcher.utils.TextSwitcherAnimUtil;

import java.util.ArrayList;
import java.util.List;

public class TextSwitcherActivity extends AppCompatActivity {
    TextSwitcherAnimUtil textSwitcherAnimUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_switcher);

//        tvSwitcherJava();

        tvSwitcherJava4xml();
    }

    private void tvSwitcherJava() {
        TextSwitcher tvSwitcher = findViewById(R.id.text_switcher);
        tvSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                return new TextView(TextSwitcherActivity.this);
            }
        });

        final List<String> texts = new ArrayList<>();
        for (int i =0 ; i< 5 ; i++){
            texts.add("轮播---"+i+i+i+i);
        }
        tvSwitcher.setText(texts.get(0));
        textSwitcherAnimUtil = new TextSwitcherAnimUtil(tvSwitcher ,texts);
        textSwitcherAnimUtil.create();

        tvSwitcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = textSwitcherAnimUtil.getMarker() % texts.size();
                switch (index){
                    case 0:
                        Toast.makeText(TextSwitcherActivity.this ,texts.get(index),Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        Toast.makeText(TextSwitcherActivity.this ,texts.get(index),Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        Toast.makeText(TextSwitcherActivity.this ,texts.get(index),Toast.LENGTH_LONG).show();
                        break;
                    case 3:
                        Toast.makeText(TextSwitcherActivity.this ,texts.get(index),Toast.LENGTH_LONG).show();
                        break;
                    case 4:
                        Toast.makeText(TextSwitcherActivity.this ,texts.get(index),Toast.LENGTH_LONG).show();
                        break;
                    default:
                        Toast.makeText(TextSwitcherActivity.this ,texts.get(index),Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });
    }



    //================= action 2   =======================
    TextSwitcher tvSwitcher2;
    String[] news={"双11回馈活动产品利率增长0.05%","国家大数据发展纲要","郑重公告","某某网站会员须知","网站维护公告"};
    private int markIndex = 0 ;
    private void tvSwitcherJava4xml() {
        tvSwitcher2 = findViewById(R.id.text_switcher2);

        tvSwitcher2.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(TextSwitcherActivity.this);
                textView.setSingleLine();
                textView.setTextSize(22);//字号
                textView.setTextColor(Color.parseColor("#ff3333"));
                textView.setEllipsize(TextUtils.TruncateAt.END);
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.gravity = Gravity.CENTER_VERTICAL;
                textView.setLayoutParams(params);
                return textView;
            }
        });
        tvSwitcher2.postDelayed(new Runnable() {
            @Override
            public void run() {
                markIndex = ++markIndex % news.length;
                tvSwitcher2.setText(news[markIndex]);
                tvSwitcher2.postDelayed(this ,2000);
            }
        },2000);
        tvSwitcher2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (markIndex){
                    case 0:
                        Toast.makeText(TextSwitcherActivity.this ,news[markIndex],Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        Toast.makeText(TextSwitcherActivity.this ,news[markIndex],Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        Toast.makeText(TextSwitcherActivity.this ,news[markIndex],Toast.LENGTH_LONG).show();
                        break;
                    case 3:
                        Toast.makeText(TextSwitcherActivity.this ,news[markIndex],Toast.LENGTH_LONG).show();
                        break;
                    case 4:
                        Toast.makeText(TextSwitcherActivity.this ,news[markIndex],Toast.LENGTH_LONG).show();
                        break;
                    default:
                        Toast.makeText(TextSwitcherActivity.this ,news[markIndex],Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });
    }
}
