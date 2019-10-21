package com.cupster.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.cupster.animation.utils.AnimationUtil;
import com.cupster.animation.utils.ProperAnimUtil;

public class AnimActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);

         imageView = findViewById(R.id.img_test);
        imageView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        //视图动画
//        Animation animation;
//        animation = AnimationUtil.inFromLeftAnimation();
//        animation = AnimationUtil.inFromTopAnimation(this);
//        animation = AnimationUtil.inFromRightAnimation();
//        animation = AnimationUtil.inFromBottomAnimation(this);

//        animation = AnimationUtil.outToLeftAnimation();
//        animation = AnimationUtil.outToRightAnimation();
//        animation = AnimationUtil.outToTopAnimation(this);
//        animation = AnimationUtil.outToBottomAnimation(this);

//        imageView.startAnimation(animation);

        //属性动画，使用java 代码
//        startPropertyAnim(imageView);

        //属性动画，使用res/animator/xxx.xml资源
//        ProperAnimUtil.loadAnimator(this,imageView,R.animator.objcet_alpha);
//        ProperAnimUtil.loadAnimator(this,imageView,R.animator.objcet_set);

        //Img 组成动画
//        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();
//        animationDrawable.start();

        //cycles属性
        imageView.startAnimation(AnimationUtils.loadAnimation(this,R.anim.shake1));
    }

    /**
     *  example basic code
     * @param view
     */
    private void startPropertyAnim(View view) {
        //1.1透明渐变
        // 将直接把TextView这个view对象的透明度渐变。
        // 注意第二个参数："alpha"，指明了是透明度渐变属性动画
        // 透明度变化从1—>0.1—>1—>0.5—>1，TextView对象经历4次透明度渐变
//        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "alpha", 1f, 0.1f, 1f, 0.5f, 1f);
//        anim.setDuration(5000);// 动画持续时间

        //2.旋转
        // 第二个参数"rotation"表明要执行旋转
        // 0f -> 360f，从旋转360度，也可以是负值，负值即为逆时针旋转，正值是顺时针旋转。
//        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "rotation", 0f, 360f);
//        anim.setDuration(5000);

        //3.位移
        // 向左移动500pix，然后再移动到原来的位置复原。  【左负右正】
        // 参数“translationX”指明在x坐标轴位移，即水平位移。
//        float translationX = view.getTranslationX();// X轴方向上的坐标
//        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "translationX", translationX, -500f, translationX);
//        anim.setDuration(5000);

        //4.缩放
        // 将一个TextView沿垂直方向先从原大小（1f）放大到5倍大小（5f），然后再变回原大小。
//        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "scaleY", 1f, 5f, 1f);
//        anim.setDuration(5000);

//        anim.setInterpolator(new AccelerateDecelerateInterpolator());//差值器
//
//        // 这里是一个回调监听，获取属性动画在执行期间的具体值
//        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                float value = (Float) animation.getAnimatedValue();
//                Log.d("value = ", value + "");
//            }
//        });
//        anim.start();

        //============================================================================================

        //5.动画集
        // 透明渐变动画：完全不透明 -> 完全透明 -> 完全不透明
        ObjectAnimator anim1_alpha = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f, 1f);
        // 旋转动画：旋转360度
        ObjectAnimator anim2_rotation = ObjectAnimator.ofFloat(view, "rotation", 0f, 360f);
        // 位移动画：水平左移然后复位
        float translationX = view.getTranslationX();
        ObjectAnimator anim3_translationX = ObjectAnimator.ofFloat(view, "translationX", translationX, -500f, translationX);
        // 缩放动画：原大小 -> 缩小一半 -> 放大2倍 -> 复原
        ObjectAnimator anim4_scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.5f,2f, 1f);

        AnimatorSet animSet = new AnimatorSet();
        animSet.setDuration(10000); // 动画持续时间为10秒

        // 动画执行的监听回调事件
        animSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                // 动画结束
            }

            @Override
            public void onAnimationStart(Animator animation) {
                // 动画开始
            }
        });
        // 播放的顺序
        // 第1先执行的是anim3_translationX
        // 第2顺序执行的是anim1_alpha和anim2_rotation。anim1_alpha和anim2_rotation将同时执行。
        // 最后执行的是anim4_scaleY
//        animSet.play(anim1_alpha).with(anim2_rotation).before(anim4_scaleY).after(anim3_translationX);
        animSet.playTogether(anim1_alpha,anim2_rotation,anim3_translationX,anim4_scaleY);
        animSet.start();
    }












}
