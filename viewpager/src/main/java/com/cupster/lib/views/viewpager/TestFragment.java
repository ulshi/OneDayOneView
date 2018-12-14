package com.cupster.lib.views.viewpager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TestFragment extends Fragment {

    private static final String TAG = "tag";
    
    public TestFragment(){

    }

    public static TestFragment newInstance(String tag){
        Bundle bundle = new Bundle();
        bundle.putString(TAG,tag);
        TestFragment fragment = new TestFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.fragment_test,container,false);
//        TextView  tv = root.findViewById()
        TextView textView =(TextView)root;
        textView.setText(getArguments().getString(TAG));
        return root;
    }
}
