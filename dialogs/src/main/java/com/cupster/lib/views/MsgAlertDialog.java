package com.cupster.lib.views;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MsgAlertDialog extends Dialog {

    private TextView title;
    private TextView msgText;
    private Button confirm;
    private Button cancel;

    private String titleStr;
    private String msgStr;
    private String confirmStr;
    private String cancelStr;

    private onConfirmOnClickListener confirmOnClickListener;
    private onCancelOnClickListener cancelOnClickListener;

    public interface onConfirmOnClickListener{
        void onConfirmClick();
    }

    public interface onCancelOnClickListener{
        void onCancelClick();
    }

    protected MsgAlertDialog(@NonNull Context context) {
        super(context,R.style.DriverDialog);
    }

    public void setConfirmClickListener(String str,onConfirmOnClickListener listener){
        confirmStr =str;
        confirmOnClickListener = listener;
    }

    public void setCancelClickListener(String str ,onCancelOnClickListener listener){
        cancelStr = str;
        cancelOnClickListener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_alert_msg);//自定义布局
        setCanceledOnTouchOutside(false);

        initView();
        initData();
        initEvent();
    }

    private void initEvent(){
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (confirmOnClickListener != null){
                    confirmOnClickListener.onConfirmClick();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cancelOnClickListener!=null){
                    cancelOnClickListener.onCancelClick();
                }
            }
        });
    }

    private void initData(){
        if (titleStr!=null){
            title.setText(titleStr);
        }
        if (msgStr!=null){
            msgText.setText(msgStr);
        }
        if (confirmStr!=null){
            confirm.setText(confirmStr);
        }
        if (cancelStr!=null){
            cancel.setText(cancelStr);
        }
    }

    private void initView(){
        title = findViewById(R.id.dialog_title);
        msgText = findViewById(R.id.dialog_message);
        confirm = findViewById(R.id.dialog_confirm);
        cancel = findViewById(R.id.dialog_cancel);

    }

    public void setTitle(String tStr){
        title.setText(tStr);
    }

    public void setMsg(String msg){
        msgText.setText(msg);
    }

}
