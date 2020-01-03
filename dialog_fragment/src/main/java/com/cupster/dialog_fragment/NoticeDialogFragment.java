package com.cupster.dialog_fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class NoticeDialogFragment extends DialogFragment {

    public static final String KEY_TITLE = "title";
    public static final String KEY_SUB_TITLE = "subTitle";
    public static final String KEY_CONTENT = "content";
    public static final String KEY_BTN_OK = "btn_ok";
    public static final String KEY_BTN_CANCEL = "btn_cancel";
    public static final String KEY_IMG_URL = "img_url";

    //监听事件接口
    public interface onItemClickListener {
        void onItemClick(View v,String tag);
    }

    private onItemClickListener onItemClickListener;
    private View mRootView ;

    public static NoticeDialogFragment newInstance(String title ,String subTitle ,String content  ){
        NoticeDialogFragment fragment  = new NoticeDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TITLE ,title);
        bundle.putString(KEY_SUB_TITLE ,subTitle);
        bundle.putString(KEY_CONTENT ,content);
        fragment.setArguments(bundle);
        return fragment;
    }
    public static NoticeDialogFragment newInstance(String title ,String subTitle ,String content ,String imgUrl ){
        NoticeDialogFragment fragment  = new NoticeDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TITLE ,title);
        bundle.putString(KEY_SUB_TITLE ,subTitle);
        bundle.putString(KEY_CONTENT ,content);
        bundle.putString(KEY_IMG_URL ,imgUrl);
        fragment.setArguments(bundle);
        return fragment;
    }
    public static NoticeDialogFragment newInstance(String title ,String subTitle ,String content ,String btn_ok ,String btn_cancel ){
        NoticeDialogFragment fragment  = new NoticeDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TITLE ,title);
        bundle.putString(KEY_SUB_TITLE ,subTitle);
        bundle.putString(KEY_CONTENT ,content);
        bundle.putString(KEY_BTN_OK ,btn_ok);
        bundle.putString(KEY_BTN_CANCEL ,btn_cancel);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null){
            //1.关联xml
            mRootView = inflater.inflate(R.layout.dialog_frag_notice ,container ,false);
        }
        setStyle(NoticeDialogFragment.STYLE_NORMAL ,R.style.NoticeDialogStyle);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //2.设置内容
        TextView title = mRootView.findViewById(R.id.notice_title);
        TextView subTitle = mRootView.findViewById(R.id.notice_title_sub);
        TextView content = mRootView.findViewById(R.id.notice_content);
        TextView btn_ok = mRootView.findViewById(R.id.notice_btn_ok);
        TextView btn_cancel = mRootView.findViewById(R.id.notice_btn_cancel);
        ImageView img_header = mRootView.findViewById(R.id.notice_img);
        if (getArguments()!= null){
            if (!TextUtils.isEmpty(getArguments().getString(KEY_TITLE))){
                title.setText(getArguments().getString(KEY_TITLE));
            }else {
                title.setVisibility(View.GONE);
            }
            if (!TextUtils.isEmpty(getArguments().getString(KEY_SUB_TITLE))){
                subTitle.setText(getArguments().getString(KEY_SUB_TITLE));
            }else {
                btn_ok.setVisibility(View.GONE);
            }
            if (!TextUtils.isEmpty(getArguments().getString(KEY_CONTENT))){
                content.setText(getArguments().getString(KEY_CONTENT));
            }
            if (!TextUtils.isEmpty(getArguments().getString(KEY_BTN_OK))){
                btn_ok.setText(getArguments().getString(KEY_BTN_OK));
            }
            if (!TextUtils.isEmpty(getArguments().getString(KEY_BTN_CANCEL))){
                btn_cancel.setText(getArguments().getString(KEY_BTN_CANCEL));
            }else {
                btn_cancel.setVisibility(View.GONE);
            }
            if (!TextUtils.isEmpty(getArguments().getString(KEY_IMG_URL))){
                //TODO  getArguments().getString(KEY_IMG_URL)  加载图片
//                img_header
            }

        }
        //3.处理点击事件
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null){
                    onItemClickListener.onItemClick(v,KEY_BTN_OK);
                    dismiss();
                }
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null){
                    onItemClickListener.onItemClick(v ,KEY_BTN_CANCEL);
                    dismiss();
                }
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }

    public void setOnItemClickListener(NoticeDialogFragment.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setHeadImg(int resID){
        ImageView img_header = mRootView.findViewById(R.id.notice_img);
        img_header.setImageResource(resID);
    }
    public void setHeadImg(String url){
        ImageView img_header = mRootView.findViewById(R.id.notice_img);
//        img_header//TODO 加载图片
    }
    public void setBtnOkText(String okText){
        TextView btn_ok = mRootView.findViewById(R.id.notice_btn_ok);
        btn_ok.setText(okText);
    }
    public void setBtnCancelText(String cancelText){
        TextView btn_cancel = mRootView.findViewById(R.id.notice_btn_cancel);
        btn_cancel.setText(cancelText);
    }

    public void setBtnColorOk(int colorIntValue){
        TextView btn_ok = mRootView.findViewById(R.id.notice_btn_ok);
        btn_ok.setTextColor(colorIntValue);
    }
    public void setBtnColorCancel(int colorIntValue){
        TextView btn_cancel = mRootView.findViewById(R.id.notice_btn_cancel);
        btn_cancel.setTextColor(colorIntValue);
    }





}
