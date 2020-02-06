package com.cupster.materiallayout.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cupster.materiallayout.R;
import com.cupster.materiallayout.RvAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 *  Encoding by Cupster on 2020/1/4 22:51.
 *
 */
public class RecyclerFragment extends Fragment {
    // Rename parameter arguments, choose names that match
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //  Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public RecyclerFragment() {
        // Required empty public constructor
    }


    // Rename and change types and number of parameters
    public static RecyclerFragment newInstance(String param1) {
        RecyclerFragment fragment = new RecyclerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_recycler, container, false);
        initView(layout);
        return layout;
    }

    private void initView( View layout) {
        RecyclerView rv = layout.findViewById(R.id.recycler_frag);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);
        RvAdapter adapter = new RvAdapter(getActivity() , getDatas());
        rv.setAdapter(adapter);
    }

    //  Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }



    private List<String> getDatas() {
        List<String> data = new ArrayList<>();
        for (int i =0 ; i<18 ;i++){
            if (mParam1.equals("1")){
                data.add("==="+i +"===");
            }
            if (mParam1.equals("2")){
                data.add("_____"+i +"_____");
            }
            if (mParam1.equals("3")){
                data.add("+++++"+i +"+++++");

            }
        }
        return data;
    }
}
