package com.jh.mysecretplace.controller;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jh.mysecretplace.R;
import com.jh.mysecretplace.model.Store;

import java.util.List;

/**
 * Created by love on 2017-03-13.
 */

public class AdapterStoreList extends RecyclerView.Adapter<AdapterStoreList.StoreViewHolder> {
    Context mContext;
    private List<Store> mStoreList;
    private Activity mActivity;
    private int mStoreRow;

    public AdapterStoreList(Context context, Activity activity, List<Store> Stores) {
        this.mContext = context;
        this.mActivity = activity;
        this.mStoreList = Stores;
        this.mStoreRow = R.layout.view_my_store_list_item;
    }

    @Override
    public StoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(mStoreRow, parent, false);
        AdapterStoreList.StoreViewHolder viewHolder = new AdapterStoreList.StoreViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(StoreViewHolder holder, final int position) {

    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public int getItemCount() {
        return mStoreList.size();
    }

    public class StoreViewHolder extends RecyclerView.ViewHolder {
        public CardView cv;
        public ImageView image;
        public TextView title;
        public TextView decription;
        public TextView date;

        public StoreViewHolder(View view) {
            super(view);
//        cv = (CardView) view.findViewById(R.id.row_Store_cv);
//        image = (ImageView) view
//                .findViewById(R.id.row_Store_imageview);
//        title = (TextView) view
//                .findViewById(R.id.row_Store_title_tv);
//        decription = (TextView) view
//                .findViewById(R.id.row_Store_desc_tv);
//        date = (TextView) view
//                .findViewById(R.id.row_Store_date_tv);
        }
    }
}