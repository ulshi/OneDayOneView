package com.cupster.lib.views;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.normal_btn);
        btn.setOnClickListener(this);
        btn = findViewById(R.id.list_btn);
        btn.setOnClickListener(this);
        btn = findViewById(R.id.choice_btn);
        btn.setOnClickListener(this);
        btn = findViewById(R.id.more_choice_btn);
        btn.setOnClickListener(this);
        btn = findViewById(R.id.editext_btn);
        btn.setOnClickListener(this);
        btn = findViewById(R.id.diy_dialog);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.normal_btn:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    dialogNormal();     // 普通
                }
                break;
            case R.id.list_btn:
                dialogList();       // 列表
                break;
            case R.id.choice_btn:
                dialogChoice();     // 单选
                break;
            case R.id.more_choice_btn:
                dialogMoreChoice(); // 多选
                break;
            case R.id.editext_btn:
                dialogEditText();   //可编辑
                break;
            case R.id.diy_dialog:
                dialogDIY();
                break;
            default:
                break;
        }
    }

    private void tShow(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }


    private void dialogDIY(){
        MsgAlertDialog dialog = new MsgAlertDialog(this);
        dialog.create();
        dialog.setTitle("标题");
        dialog.setMsg("提示信息");

        dialog.show();
        dialog.setConfirmClickListener("确定",new MsgAlertDialog.onConfirmOnClickListener(){
            @Override
            public void onConfirmClick() {
                tShow("click 确定");
            }
        });
        dialog.setCancelClickListener("取消", new MsgAlertDialog.onCancelOnClickListener() {
            @Override
            public void onCancelClick() {
               tShow("click 取消");
            }
        });

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void dialogNormal() {
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                switch (which) {
                    case Dialog.BUTTON_POSITIVE:
                        tShow("确定");
                        break;
                    case Dialog.BUTTON_NEGATIVE:
                        tShow("取消");
                        break;
                    case Dialog.BUTTON_NEUTRAL:
                        tShow("忽略");
                        break;
                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher)
                .setTitle("普通对话框")
                .setMessage("是否确认退出")
                .setPositiveButton("确认", listener)
//                .setPositiveButtonIcon(getDrawable(R.mipmap.ic_launcher))
                .setNegativeButton("取消", listener)
//                .setNegativeButtonIcon(getDrawable(R.mipmap.ic_launcher))
                .setNeutralButton("忽略", listener)
                .setNeutralButtonIcon(getDrawable(R.mipmap.ic_launcher))
                .setCancelable(false)
                .create()
                .show();

    }

    private void dialogList() {
        final String items[] = {"列表1", "列表2", "列表3", "列表4"};

        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                switch (which) {
                    case Dialog.BUTTON_POSITIVE:
//                        dialogInterface.dismiss();//默认消失，不用设置
                        tShow("确定");
                        break;
                    default:
//                        dialogInterface.dismiss();//默认消失，不用设置
                        tShow(items[which]);
                        break;
                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this, 0);
        builder.setTitle("列表")
                .setIcon(R.mipmap.ic_launcher)
                .setItems(items,listener)
                .setPositiveButton("确定",listener)
                .setCancelable(false)
                .create()
                .show();

    }

    private void dialogChoice() {
        final String items[] = {"男", "女", "其他"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this,0);
        builder.setTitle("单选");
//        builder.setIcon(R.mipmap.ic_launcher);
        builder.setSingleChoiceItems(items, 2,//默认选中位置
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(MainActivity.this, items[which],
                                Toast.LENGTH_SHORT).show();
                    }
                });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT)
                        .show();
            }
        });
        builder.create().show();

    }

    private void dialogMoreChoice() {
        final String items[] = {"JAVA", "C++", "JavaScript", "MySQL"};
        final boolean selected[] = {true, false, true, false};
        AlertDialog.Builder builder = new AlertDialog.Builder(this,0);
        builder.setTitle("多选");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMultiChoiceItems(items, selected,
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which,
                                        boolean isChecked) {

                        Toast.makeText(MainActivity.this,
                                items[which] + isChecked, Toast.LENGTH_SHORT)
                                .show();
                    }
                });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT)
                        .show();
            }
        });
        builder.create().show();

    }

    private void dialogEditText() {
        final EditText editText = new EditText(this);
        editText.setHint(" input something");
        final AlertDialog.Builder builder = new AlertDialog.Builder(this,0);
        builder.setTitle("可编辑");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setView(editText);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, editText.getText().toString() + "", Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();

    }


}
