package com.jh.mysecretplace.controller;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jh.mysecretplace.R;
import com.jh.mysecretplace.model.NavigationItem;
import com.jh.mysecretplace.utils.DLog;

import java.util.List;

/**
 * Created by love on 2017-03-11.
 */

public class NavListItemAdapter extends RecyclerView.Adapter<NavListItemAdapter.NavListItemViewHolder>{

    private Context mContext;
    private List<NavigationItem> mItemList;
    private Activity mActivity;
    private int mItemLayout;

    public NavListItemAdapter(Context mContext, Activity activity, List<NavigationItem> items) {
        this.mItemLayout = R.layout.nav_list_item;
        this.mContext = mContext;
        this.mItemList = items;
        this.mActivity = activity;
    }

    @Override
    public NavListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(mItemLayout, parent, false);
        NavListItemAdapter.NavListItemViewHolder viewHolder = new NavListItemAdapter.NavListItemViewHolder(v);

        DLog.d(mContext, "NavListItemViewHolder onCreateViewHolder called");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NavListItemViewHolder holder, final int position) {
        holder.icon.setImageDrawable(mItemList.get(position).getIcon());
//        holder.icon.setImageResource(R.drawable.messenger_bubble_small_blue);
        holder.title.setText(mItemList.get(position).getTitle());
        DLog.d(mContext, "onBIndViewHolder "+(position) + " : "+mItemList.get(position).getIcon()+", "+ mItemList.get(position).getTitle());
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public class NavListItemViewHolder extends RecyclerView.ViewHolder {
        public ImageView icon;
        public TextView title;


        public NavListItemViewHolder(View view) {
            super(view);
            icon = (ImageView) view
                    .findViewById(R.id.nav_list_item_icon);
            title = (TextView) view
                    .findViewById(R.id.nav_list_item_title);
        }
    }
}
