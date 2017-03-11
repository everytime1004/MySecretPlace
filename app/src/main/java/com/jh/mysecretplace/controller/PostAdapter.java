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
import com.jh.mysecretplace.model.Post;

import java.util.List;

/**
 * Created by love on 2017-02-26.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    Context mContext;
    private List<Post> mPostList;
    private Activity mActivity;
    private int mPostRow;

    public PostAdapter(Context context, Activity activity, List<Post> posts) {
        this.mContext = context;
        this.mActivity = activity;
        this.mPostList = posts;
        this.mPostRow = R.layout.row_post;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(mPostRow, parent, false);
        PostAdapter.PostViewHolder viewHolder  = new PostAdapter.PostViewHolder(v);

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
        public ImageView image;
        public TextView title;
        public TextView date;

        public PostViewHolder(View view) {
            super(view);
            image = (ImageView) view
                    .findViewById(R.id.row_post_imageview);
            title = (TextView) view
                    .findViewById(R.id.row_post_title);
            date = (TextView) view
                    .findViewById(R.id.row_post_date);
        }
    }
}
