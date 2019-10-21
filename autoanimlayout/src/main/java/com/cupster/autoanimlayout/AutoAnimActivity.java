package com.cupster.autoanimlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cupster.autoanimlayout.autoanim2.AutoLayoutInflater;

public class AutoAnimActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_auto_anim);
        View view = new AutoLayoutInflater(this).inflate(R.layout.activity_auto_anim2 ,null);
        setContentView(view);
    }
}
