package com.cupster.animation.utils;


import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.Log;
import android.view.View;

import com.cupster.animation.R;

/**
 *  属性动画
 *
 *         1.res/animator/filename.xml  资源文件夹
 */
public class ProperAnimUtil {

    private Animator alpha(View view ,long duration ,float... alphaArr) {
        // 将直接把TextView这个view对象的透明度渐变。
        // 注意第二个参数："alpha"，指明了是透明度渐变属性动画
        // 透明度变化从1—>0.1—>1—>0.5—>1，TextView对象经历4次透明度渐变
//        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "alpha", 1f, 0.1f, 1f, 0.5f, 1f);
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "alpha", alphaArr);
        anim.setDuration(duration);// 动画持续时间
        // 这里是一个回调监听，获取属性动画在执行期间的具体值
//        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                float value = (Float) animation.getAnimatedValue();
//                Log.d("alpha", value + "");
//            }
//        });
        return anim;
    }
    public static Animator loadAnimator(Context context  , int animResID) {
        Animator anim = AnimatorInflater.loadAnimator(context,animResID);
        return anim;
    }
    public static void loadAnimator(Context context ,View view , int animResID) {
        Animator anim = AnimatorInflater.loadAnimator(context,animResID);
        anim.setTarget(view);
        anim.start();
    }

}
