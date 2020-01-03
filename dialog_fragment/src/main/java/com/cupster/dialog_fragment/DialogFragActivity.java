package com.cupster.dialog_fragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DialogFragActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_frag);


        NoticeDialogFragment dialogFragment  = NoticeDialogFragment.newInstance("更新","2019-11-18"
                ,"");
        dialogFragment.setOnItemClickListener(new NoticeDialogFragment.onItemClickListener() {
            @Override
            public void onItemClick(View v,String tag) {
                if (tag.equals(NoticeDialogFragment.KEY_BTN_OK)){

                }
                if (tag.equals(NoticeDialogFragment.KEY_BTN_CANCEL)){

                }
            }
        });
        dialogFragment.show(getSupportFragmentManager(),"notice");
    }
}
