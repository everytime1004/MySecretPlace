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
import com.jh.mysecretplace.model.Drink;

import java.util.List;

/**
 * Created by love on 2017-03-13.
 */

public class AdapterDrinkList extends RecyclerView.Adapter<AdapterDrinkList.DrinkViewHolder> {
    Context mContext;
    private List<Drink> mDrinkList;
    private Activity mActivity;
    private int mDrinkRow;

    public AdapterDrinkList(Context context, Activity activity, List<Drink> Drinks) {
        this.mContext = context;
        this.mActivity = activity;
        this.mDrinkList = Drinks;
        this.mDrinkRow = R.layout.view_my_drink_list_item;
    }

    @Override
    public DrinkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(mDrinkRow, parent, false);
        AdapterDrinkList.DrinkViewHolder viewHolder = new AdapterDrinkList.DrinkViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DrinkViewHolder holder, final int position) {

    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public int getItemCount() {
        return mDrinkList.size();
    }

    public class DrinkViewHolder extends RecyclerView.ViewHolder {
        public CardView cv;
        public ImageView image;
        public TextView title;
        public TextView decription;
        public TextView date;

        public DrinkViewHolder(View view) {
            super(view);
//            cv = (CardView) view.findViewById(R.id.row_Drink_cv);
//            image = (ImageView) view
//                    .findViewById(R.id.row_Drink_imageview);
//            title = (TextView) view
//                    .findViewById(R.id.row_Drink_title_tv);
//            decription = (TextView) view
//                    .findViewById(R.id.row_Drink_desc_tv);
//            date = (TextView) view
//                    .findViewById(R.id.row_Drink_date_tv);
        }
    }
}
