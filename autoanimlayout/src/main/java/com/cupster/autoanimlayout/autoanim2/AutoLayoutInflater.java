package com.cupster.autoanimlayout.autoanim2;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.cupster.autoanimlayout.R;

import java.lang.reflect.Constructor;

public class AutoLayoutInflater extends LayoutInflater {


    public AutoLayoutInflater(Context context){
        super(context);
        setFactory2(new Factory());
    }

    @Override
    public LayoutInflater cloneInContext(Context newContext) {
        return new AutoLayoutInflater(newContext);
    }

    public static class Factory implements Factory2{
        private final String[] sClassPrefix = {
                "android.widget.",
                "android.view."
        };
        @Override
        public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
            View view = null;
            if(name.contains(".")){
                view = createMyView(name,context,attrs);
            }else{
                for (String prefix : sClassPrefix) {
                    view = createMyView(prefix + name, context, attrs);
                    if (view != null) {
                        break;
                    }
                }
            }
            //获取
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DiscrollView_LayoutParams);
            if (a != null && a.length() > 0) {
                //获取自定义属性的值
                LayoutTag tag = new LayoutTag();
                tag.discrollve_alpha =  a.getBoolean(R.styleable.DiscrollView_LayoutParams_discrollve_alpha,false);
                tag.discrollve_scaleX = a.getBoolean(R.styleable.DiscrollView_LayoutParams_discrollve_scaleX, false);
                tag.discrollve_scaleY = a.getBoolean(R.styleable.DiscrollView_LayoutParams_discrollve_scaleY, false);
                tag.discrollve_translation = a.getInt(R.styleable.DiscrollView_LayoutParams_discrollve_translation, -1);
                tag.discrollve_fromBgColor = a.getColor(R.styleable.DiscrollView_LayoutParams_discrollve_fromBgColor, -1);
                tag.discrollve_toBgColor = a.getColor(R.styleable.DiscrollView_LayoutParams_discrollve_toBgColor, -1);
                //index
                view.setTag(tag);
            }
            a.recycle();
            return view;
        }

        private View createMyView(String name, Context context, AttributeSet attrs){
            try {
                Class clazz = Class.forName(name);
                Constructor<View> constructor = clazz.getConstructor(Context.class, AttributeSet.class);
                return  constructor.newInstance(context, attrs);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;

        }

        @Override
        public View onCreateView(String name, Context context, AttributeSet attrs) {
            return null;
        }
    }


}
