package com.cupster.materiallayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.coordinatorlayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open(CoordinatorLayoutActivity.class);
            }
        });
        findViewById(R.id.with_viewpager).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open(ViewPageActivity.class);
            }
        });
        findViewById(R.id.collapsing_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open(CollapsingToolbarLayoutActivity.class);
            }
        });
        findViewById(R.id.elema).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open(ElemaMap4DetailActivity.class);
            }
        });
        findViewById(R.id.behavior_hide_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open(BehaviorHideActivity.class);
            }
        });

    }


    void open (Class<? extends AppCompatActivity> clazz){
        Intent intent = new Intent(this ,clazz);
        startActivity(intent);
    }



}
