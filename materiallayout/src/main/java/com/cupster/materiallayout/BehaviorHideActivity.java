package com.cupster.materiallayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

/**
 *  自定义Behavior 跟随依赖View 响应动作
 */
public class BehaviorHideActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_behavior_hide_bottom);

        CoordinatorLayout coordinatorLayout = findViewById(R.id.coordinate);
        AppBarLayout appBarLayout = findViewById(R.id.appbar);

        RecyclerView rv = findViewById(R.id.recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        RvAdapter adapter = new RvAdapter(this , getDatas());
        rv.setAdapter(adapter);



    }

    private List<String> getDatas() {
        List<String> data = new ArrayList<>();
        for (int i =0 ; i<18 ;i++){
            data.add("==="+i +"===");
        }
        return data;
    }
}
