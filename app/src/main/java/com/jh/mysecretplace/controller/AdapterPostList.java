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
import com.jh.mysecretplace.model.Post;

import java.util.List;

/**
 * Created by love on 2017-02-26.
 */

public class AdapterPostList extends RecyclerView.Adapter<AdapterPostList.PostViewHolder> {
    Context mContext;
    private List<Post> mPostList;
    private Activity mActivity;
    private int mPostRow;

    public AdapterPostList(Context context, Activity activity, List<Post> posts) {
        this.mContext = context;
        this.mActivity = activity;
        this.mPostList = posts;
        this.mPostRow = R.layout.view_my_post_list_item;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(mPostRow, parent, false);
        AdapterPostList.PostViewHolder viewHolder  = new AdapterPostList.PostViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, final int position) {

    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public int getItemCount() {
        return mPostList.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        public CardView cv;
        public ImageView image;
        public TextView title;
        public TextView decription;
        public TextView date;

        public PostViewHolder(View view) {
            super(view);
            cv = (CardView) view.findViewById(R.id.row_post_cv);
            image = (ImageView) view
                    .findViewById(R.id.row_post_imageview);
            title = (TextView) view
                    .findViewById(R.id.row_post_title_tv);
            decription = (TextView) view
                    .findViewById(R.id.row_post_desc_tv);
            date = (TextView) view
                    .findViewById(R.id.row_post_date_tv);
        }
    }
}
