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
import com.jh.mysecretplace.model.Food;

import java.util.List;

/**
 * Created by love on 2017-03-13.
 */

public class AdapterFoodList extends RecyclerView.Adapter<AdapterFoodList.FoodViewHolder> {
    Context mContext;
    private List<Food> mFoodList;
    private Activity mActivity;
    private int mFoodRow;

    public AdapterFoodList(Context context, Activity activity, List<Food> Foods) {
        this.mContext = context;
        this.mActivity = activity;
        this.mFoodList = Foods;
        this.mFoodRow = R.layout.view_my_food_list_item;
    }

    @Override
    public FoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(mFoodRow, parent, false);
        AdapterFoodList.FoodViewHolder viewHolder = new AdapterFoodList.FoodViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FoodViewHolder holder, final int position) {

    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public int getItemCount() {
        return mFoodList.size();
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder {
        public CardView cv;
        public ImageView image;
        public TextView title;
        public TextView decription;
        public TextView date;

        public FoodViewHolder(View view) {
            super(view);
//        cv = (CardView) view.findViewById(R.id.row_Food_cv);
//        image = (ImageView) view
//                .findViewById(R.id.row_Food_imageview);
//        title = (TextView) view
//                .findViewById(R.id.row_Food_title_tv);
//        decription = (TextView) view
//                .findViewById(R.id.row_Food_desc_tv);
//        date = (TextView) view
//                .findViewById(R.id.row_Food_date_tv);
        }
    }
}