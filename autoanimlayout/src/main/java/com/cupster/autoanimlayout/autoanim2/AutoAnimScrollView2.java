package com.cupster.autoanimlayout.autoanim2;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class AutoAnimScrollView2 extends ScrollView {

    LinearLayout linearLayout;
    public AutoAnimScrollView2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        linearLayout = (LinearLayout)getChildAt(0);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        int scrollveiwHeight = getHeight();
        //监听滑动状态--->childView从下面冒出来多少/childView.getHeight() = 动画的执行的百分比ratio
        //拿到里面每一个子控件，让他们按照ratio动起来！
        for (int i=0;i<linearLayout.getChildCount();i++){
            View child = linearLayout.getChildAt(i);
            /*if(!(child instanceof MyFrameLayout)){
                continue;
            }*/
            if(child== null ||child.getTag()==null){
                continue;
            }
            LayoutTag tag = (LayoutTag) child.getTag();
            Log.e("MyScrollView2","class="+child.getClass().getSimpleName()+"   "+tag.toString());
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
                onDiscroll(child,tag,clamp(ratio, 1f, 0f),child.getHeight(),child.getWidth());
            }else{
                //恢复
                onResetDiscroll(child,tag,child.getHeight(),child.getWidth());
            }
        }

    }
    //求三个数的中间大小的一个数。
    public static float clamp(float value, float max, float min){
        return Math.max(Math.min(value, max), min);
    }


    private static final int TRANSLATION_FROM_TOP = 0x01;
    private static final int TRANSLATION_FROM_BOTTOM = 0x02;
    private static final int TRANSLATION_FROM_LEFT = 0x04;
    private static final int TRANSLATION_FROM_RIGHT = 0x08;


    public void onDiscroll(View view,LayoutTag tag,float ratio,int mHeight,int mWidth) {
        //判断是否有动画的属性，开启动画
        //ratio:0~1
        if(tag.discrollve_alpha){
            view.setAlpha(ratio);
        }
        if(tag.discrollve_scaleX){
            view.setScaleX(ratio);
        }
        if(tag.discrollve_scaleY){
            view.setScaleY(ratio);
        }
        //平移---int值： left，right，top，bottom，   left|bottom
        if(isTranslationFrom(tag,TRANSLATION_FROM_BOTTOM)){//是否是fromBottom
            view.setTranslationY(mHeight*(1-ratio));//height-->0 (0代表原来的位置)
        }
        if(isTranslationFrom(tag,TRANSLATION_FROM_TOP)){//从顶部平移进来
            view.setTranslationY(-mHeight*(1-ratio));//-height--->0
        }
        if(isTranslationFrom(tag,TRANSLATION_FROM_LEFT)){
            view.setTranslationX(-mWidth*(1-ratio));//mWidth--->0(0代表恢复到本来原来的位置)
        }
        if(isTranslationFrom(tag,TRANSLATION_FROM_RIGHT)){
            view.setTranslationX(mWidth*(1-ratio));//-mWidth--->0(0代表恢复到本来原来的位置)
        }
        //判断从什么颜色到什么颜色
        if(tag.discrollve_fromBgColor!=-1&&tag.discrollve_toBgColor!=-1){
            ArgbEvaluator sArgbEvaluator = new ArgbEvaluator();
            view.setBackgroundColor((int) sArgbEvaluator.evaluate(ratio, tag.discrollve_fromBgColor, tag.discrollve_toBgColor));
        }


    }

    private boolean isTranslationFrom(LayoutTag tag,int translationMask){
        if(tag.discrollve_translation ==-1){
            return false;
        }
        //fromLeft|fromeBottom & fromBottom = fromBottom
        return (tag.discrollve_translation & translationMask) == translationMask;
    }

    public void onResetDiscroll(View view, LayoutTag tag, int mHeight, int mWidth) {
        int ratio = 0;
        //ratio:0~1
        if(tag.discrollve_alpha){
            view.setAlpha(ratio);
        }
        if(tag.discrollve_scaleX){
            view.setScaleX(ratio);
        }
        if(tag.discrollve_scaleY){
            view.setScaleY(ratio);
        }
        //平移---int值： left，right，top，bottom，   left|bottom
        if(isTranslationFrom(tag,TRANSLATION_FROM_BOTTOM)){//是否是fromBottom
            view.setTranslationY(mHeight*(1-ratio));//height-->0 (0代表原来的位置)
        }
        if(isTranslationFrom(tag,TRANSLATION_FROM_TOP)){//从顶部平移进来
            view.setTranslationY(-mHeight*(1-ratio));//-height--->0
        }
        if(isTranslationFrom(tag,TRANSLATION_FROM_LEFT)){
            view.setTranslationX(-mWidth*(1-ratio));//mWidth--->0(0代表恢复到本来原来的位置)
        }
        if(isTranslationFrom(tag,TRANSLATION_FROM_RIGHT)){
            view.setTranslationX(mWidth*(1-ratio));//-mWidth--->0(0代表恢复到本来原来的位置)
        }
    }

}
