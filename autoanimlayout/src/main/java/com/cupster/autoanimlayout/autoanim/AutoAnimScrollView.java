package com.cupster.autoanimlayout.autoanim;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

public class AutoAnimScrollView extends ScrollView {
    AutoAnimLinearLayout mContent;
    public AutoAnimScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mContent = (AutoAnimLinearLayout) getChildAt(0);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        int scrollveiwHeight = getHeight();
        //监听滑动状态--->childView从下面冒出来多少/childView.getHeight() = 动画的执行的百分比ratio
        //拿到里面每一个子控件，让他们按照ratio动起来！
        for (int i=0;i<mContent.getChildCount();i++){
            View child = mContent.getChildAt(i);
            if(!(child instanceof AutoAnimFrameLayout)){
                continue;
            }
            DiscrollInterface discrollInterface = (DiscrollInterface) child;
            //child离parent顶部的高度
            int childTop = child.getTop();
            //什么时候执行动画呢？当child滑进屏幕的时候
            int childHeight = child.getHeight();
            //t:就是滑出去的高度
            //child离屏幕顶部的高度
            int absoluteTop = (childTop - t);

            if(absoluteTop<=scrollveiwHeight){
                //child浮现的高度=ScrollView的高度 - child离屏幕顶部的高度
                int visibleGap =scrollveiwHeight - absoluteTop;
//				float ratio = child浮现的高度/child的高度;
                float ratio = visibleGap/(float)childHeight;
                //确保ratio是在0~1的范围。得到ratio在0~1的中间值
                discrollInterface.onDiscroll(clamp(ratio, 1f, 0f));
            }else{
                //恢复
                discrollInterface.onResetDiscroll();
            }
        }

    }
    //求三个数的中间大小的一个数。
    public static float clamp(float value, float max, float min){
        return Math.max(Math.min(value, max), min);
    }

}
