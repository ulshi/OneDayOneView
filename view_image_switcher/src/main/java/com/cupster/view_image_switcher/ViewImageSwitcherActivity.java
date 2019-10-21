package com.cupster.view_image_switcher;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

public class ViewImageSwitcherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image_switcher);


        viewSwitch();

//        dynViewSwitch();

        imageSwitch();


    }

    private void viewSwitch() {
        Button buttonPrev = (Button) findViewById(R.id.prev);
        Button buttonNext = (Button) findViewById(R.id.next);

        final ViewSwitcher viewSwitcher = (ViewSwitcher) findViewById(R.id.viewSwitcher);//布局已包含 子view

        Animation slide_in_left = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation slide_out_right = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);

        viewSwitcher.setInAnimation(slide_in_left);
        viewSwitcher.setOutAnimation(slide_out_right);

        viewSwitcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        buttonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // viewSwitcher.showPrevious();切换效果类似
                viewSwitcher.setDisplayedChild(0);
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // viewSwitcher.showNext();切换效果类似
                viewSwitcher.setDisplayedChild(1);
            }
        });
    }


//    private void dynViewSwitch() {

    //    }

    //        next(null);// 页面加载时显示第一页
//
//        }
//            screenCount = dataItems.size() / perScreenCount + 1;
//        } else {
//            screenCount = dataItems.size() / perScreenCount;
//        if (dataItems.size() % perScreenCount == 0) {
//        // 计算总屏数
//
//        }
//            dataItems.add(dataItem);
//            dataItem.drawable = getResources().getDrawable(images[i]);
//            dataItem.lable = "lable" + i;
//            DataItem dataItem = new DataItem();
//        for (int i = 0; i < images.length; i++) {
//
//        });
//            }
//                return inflater.inflate(R.layout.viewanimator_gridview, null);
//            public View makeView() {
//            @Override
//
//        viewSwitcher.setFactory(new ViewFactory() {
//        // 设置每一屏显示的视图，这里设置为GridView
//        ViewSwitcher viewSwitcher = (ViewSwitcher) findViewById(R.id.viewSwitcher);



    int[] imgIds ={R.mipmap.icon_money,R.mipmap.icon_new_msg,R.mipmap.icon_orange_add,R.mipmap.icon_red_delete};
    int imageIndex =0 ;
    /**
     * 当前选中的图片id序号
     */
    private int currentPosition;
    /**
     * 按下点的X坐标
     */
    private float downX;
     ImageSwitcher imageSwitcher;
    @SuppressLint("ClickableViewAccessibility")
    private void imageSwitch() {

        imageSwitcher = findViewById(R.id.img_switcher);
        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this ,android.R.anim.slide_in_left));
        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this ,android.R.anim.slide_out_right));
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView i = new ImageView(ViewImageSwitcherActivity.this);
                i.setBackgroundColor(0xfff);
                i.setScaleType(ImageView.ScaleType.CENTER_CROP);
                i.setPadding(20,20,20,20);
                i.setLayoutParams(new ImageSwitcher.LayoutParams(ImageSwitcher.LayoutParams.MATCH_PARENT, ImageSwitcher.LayoutParams.MATCH_PARENT));
                return i ;
            }
        });
        imageSwitcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        imageSwitcher.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:{
                        //手指按下的X坐标
                        downX = event.getX();
                        break;
                    }
                    case MotionEvent.ACTION_UP:{
                        float lastX = event.getX();
                        //抬起的时候的X坐标大于按下的时候就显示上一张图片
                        if(lastX > downX){
                            if(imageIndex > 0){
                                //设置动画，这里的动画比较简单，不明白的去网上看看相关内容
                                imageIndex --;
                                imageSwitcher.setImageResource(imgIds[imageIndex % imgIds.length]);
//                                setImageBackground(imageIndex);
                            }else{
                                Toast.makeText(getApplication(), "已经是第一张", Toast.LENGTH_SHORT).show();
                            }
                        }

                        if(lastX < downX){
                            if(imageIndex < imgIds.length - 1){
                                imageIndex ++ ;
                                imageSwitcher.setImageResource(imgIds[imageIndex]);
//                                setImageBackground(imageIndex);
                            }else{
                                Toast.makeText(getApplication(), "到了最后一张", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    break;
                }

                return true;
            }
        });


    }
}
