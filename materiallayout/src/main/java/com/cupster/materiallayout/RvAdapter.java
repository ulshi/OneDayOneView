package com.cupster.materiallayout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Encoding by Cupster on 2020/1/3 23:01.
 */
public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {

    private Context mContext;
    private List<String> mDatas;


    public RvAdapter(Context mContext, List<String> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View item = LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_1 , viewGroup ,false);
        ViewHolder holder = new ViewHolder(item);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.text1.setText(mDatas.get(i));
    }

    @Override
    public int getItemCount() {
        return mDatas==null?0:mDatas.size();
    }

    static  class  ViewHolder extends RecyclerView.ViewHolder{

        public TextView text1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text1 = itemView.findViewById(android.R.id.text1);
        }


    }
}
