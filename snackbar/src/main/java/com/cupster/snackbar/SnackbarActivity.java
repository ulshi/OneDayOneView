package com.cupster.snackbar;

import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class SnackbarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snackbar);

        initView();
    }


    private CoordinatorLayout coordinatorLayout;
    private FloatingActionButton fab;

    private void initView() {
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.snackbar_container);
        fab = (FloatingActionButton) findViewById(R.id.snackbar_fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                easyShow();
//                clickShow();
//                addCallback();
                allPropertyShow();
            }
        });


    }

    private void easyShow() {
        Snackbar
                .make(coordinatorLayout ,"snackbar easyShow"  ,Snackbar.LENGTH_SHORT)
                .show();
    }

    private void clickShow() {
        Snackbar
                .make(coordinatorLayout ,"snackbar clickShow "  ,Snackbar.LENGTH_SHORT)
                .setAction("setAction", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Snackbar.make(coordinatorLayout, "Action 被点击", Snackbar.LENGTH_SHORT).show();
                    }
                })

                .show();
    }

    private void addCallback() {
        Snackbar
                .make(coordinatorLayout ,"snackbar easyShow"  ,Snackbar.LENGTH_SHORT)
                .addCallback(new Snackbar.Callback(){
                    @Override
                    public void onDismissed(Snackbar transientBottomBar, int event) {
                        super.onDismissed(transientBottomBar, event);
                        Toast.makeText(SnackbarActivity.this, "Snackbar隐藏", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onShown(Snackbar sb) {
                        super.onShown(sb);
                        Toast.makeText(SnackbarActivity.this, "Snackbar显示", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    private void allPropertyShow() {
        Snackbar snackbar = Snackbar.make(coordinatorLayout, "Action 被点击", Snackbar.LENGTH_SHORT);
        snackbar.setText("动态文本");//动态设置文本显示内容
        snackbar.setActionTextColor(Color.RED);//动态设置Action文本的颜色
        snackbar.setDuration(5000);//动态设置显示时间

        View snackbarView = snackbar.getView();//获取Snackbar显示的View对象
        //获取显示文本View,并设置其显示颜色
        ((TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text)).setTextColor(Color.BLUE);
        //获取Action文本View，并设置其显示颜色
        ((TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_action)).setTextColor(Color.BLUE);
        //设置Snackbar的背景色
        snackbarView.setBackgroundColor(Color.GREEN);

        //设置Snackbar显示的位置
        ViewGroup.LayoutParams params = snackbarView.getLayoutParams();
        CoordinatorLayout.LayoutParams layoutParams = new CoordinatorLayout.LayoutParams(params.width, params.height);
        layoutParams.gravity = Gravity.CENTER_VERTICAL;//垂直居中
        snackbarView.setLayoutParams(layoutParams);
        snackbar.show();
    }
}
