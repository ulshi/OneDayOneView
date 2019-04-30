package com.cupster.lib.views.ios;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cupster.lib.views.R;


public class IosMsgAlert extends AlertDialog implements OnClickListener {

    private LayoutInflater mInflater;
    private View mView;
    private TextView mTvMessage;
    private TextView mTvTitle;
    private OnClickListener mPositiveListener;
    private OnClickListener mNegativeListener;
    private RelativeLayout mRelativeTitle;
    private Context mContext;
    private TextView mTvSure;
    private TextView mTvCancel;
    private View btn_divider;

    public IosMsgAlert(Context context) {
        this(context, R.style.ios_dialog);
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    public IosMsgAlert(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = mInflater.inflate(R.layout.dialog_ios, null);
        setContentView(mView);
        mTvMessage = (TextView) mView.findViewById(R.id.tv_message);
        mTvTitle = (TextView) mView.findViewById(R.id.tv_title);
        mRelativeTitle = (RelativeLayout) mView.findViewById(R.id.relative_title);
        mRelativeTitle.setVisibility(View.GONE);
        btn_divider = mView.findViewById(R.id.divider);
        mTvSure = (TextView) mView.findViewById(R.id.tv_sure);
        mTvSure.setOnClickListener(this);
        mTvCancel = (TextView) mView.findViewById(R.id.tv_cancel);
        mTvCancel.setOnClickListener(this);
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.e( "onDetachedFromWindow: ", "dismiss");
        mContext = null;
        mInflater = null;
    }

    public void setWidth(float widthPortrait, float widthLandScape){
        Window dialogWindow = this.getWindow();
        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        //手机横竖屏时候
        if (mContext.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
//            p.height = (int) (d.getHeight() * 0.25); // 高度设置为屏幕的
            p.width = (int) (d.getWidth() * widthPortrait); // 宽度设置为屏幕的
        } else if (mContext.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            p.height = (int) (d.getHeight() * 0.3); // 高度设置为屏幕的
            p.width = (int) (d.getWidth() * widthLandScape); // 宽度设置为屏幕的
        }
        dialogWindow.setAttributes(p);
    }

    @Override
    public void setTitle(CharSequence title) {
        mRelativeTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText(title);
    }

    @Override
    public void setMessage(CharSequence message) {
        super.setMessage(message);
        StringBuilder sb = new StringBuilder();
        sb.append("      ");
        sb.append(message);
        mTvMessage.setText(sb.toString());
    }

    public void sestMessageColor(int color) {
        mTvMessage.setTextColor(color);
    }
    public void sestMessageColor(SpannableStringBuilder builder) {
        mTvMessage.setText(builder);
    }
    public void sestTitleColor(SpannableStringBuilder builder) {
        mTvTitle.setText(builder);
    }

    public void setBtnText(String tvCancel, String tvSure) {
        mTvSure.setText(tvSure);
        mTvCancel.setText(tvCancel);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_sure:
                if (mPositiveListener != null) {
                    mPositiveListener.onClick(this, R.id.tv_sure);
                }
                dismiss();
                break;
            case R.id.tv_cancel:
                if (mNegativeListener != null) {
                    mNegativeListener.onClick(this, R.id.tv_cancel);
                }
                dismiss();
                break;
            default:
                break;
        }
    }

    /**
     * 设置message显示模式
     *
     * @param gravity
     */
    public void setMessageGravity(int gravity) {
        mTvMessage.setGravity(gravity);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(dip2px(mContext, 30), dip2px(mContext, 30), dip2px(mContext, 20), dip2px(mContext, 10));
        mTvMessage.setLayoutParams(params);
    }

    /**
     * 设置Title显示模式
     *
     * @param gravity
     */
    public void setTitleGravity(int gravity) {
        mTvTitle.setGravity(gravity);
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        params.setMargins(dip2px(mContext, 30), dip2px(mContext, 30), dip2px(mContext, 30), dip2px(mContext, 10));
//        mTvTitle.setLayoutParams(params);
    }

    public static int dip2px(Context context, float dpValue)
    {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dpValue * scale + 0.5F);
    }

    /**
     * 设置message字体颜色
     *
     * @param color 颜色值，非资源id
     */
    public void setMessageColor(int color) {
        mTvMessage.setTextColor(color);
    }
    public void setTitleColor(int color) {
        mTvTitle.setTextColor(color);
    }

    /**
     * 设置message字体大小
     *
     * @param size
     */
    public void setMessageSize(float size) {
        mTvMessage.setTextSize(size);
    }

    public void setPositiveOnClickListener(OnClickListener listener) {
        mPositiveListener = listener;
    }

    public void setNegativeOnClickListener(OnClickListener listener) {
        mNegativeListener = listener;
    }

    public void setLeftVisible(int visible) {
        mTvCancel.setVisibility(visible);
        btn_divider.setVisibility(visible);
    }

}
