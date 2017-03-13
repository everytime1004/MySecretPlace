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
import com.jh.mysecretplace.model.Photo;

import java.util.List;

/**
 * Created by love on 2017-03-13.
 */

public class AdapterPhotoList extends RecyclerView.Adapter<AdapterPhotoList.PhotoViewHolder> {
    Context mContext;
    private List<Photo> mPhotoList;
    private Activity mActivity;
    private int mPhotoRow;

    public AdapterPhotoList(Context context, Activity activity, List<Photo> Photos) {
        this.mContext = context;
        this.mActivity = activity;
        this.mPhotoList = Photos;
        this.mPhotoRow = R.layout.view_my_photo_list_item;
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(mPhotoRow, parent, false);
        AdapterPhotoList.PhotoViewHolder viewHolder = new AdapterPhotoList.PhotoViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, final int position) {

    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public int getItemCount() {
        return mPhotoList.size();
    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder {
        public CardView cv;
        public ImageView image;
        public TextView title;
        public TextView decription;
        public TextView date;

        public PhotoViewHolder(View view) {
            super(view);
//        cv = (CardView) view.findViewById(R.id.row_Photo_cv);
//        image = (ImageView) view
//                .findViewById(R.id.row_Photo_imageview);
//        title = (TextView) view
//                .findViewById(R.id.row_Photo_title_tv);
//        decription = (TextView) view
//                .findViewById(R.id.row_Photo_desc_tv);
//        date = (TextView) view
//                .findViewById(R.id.row_Photo_date_tv);
        }
    }
}