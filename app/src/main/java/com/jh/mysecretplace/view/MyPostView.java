package com.jh.mysecretplace.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jh.mysecretplace.R;
import com.jh.mysecretplace.controller.AdapterPostList;
import com.jh.mysecretplace.model.Post;

import java.util.ArrayList;

/**
 * Created by love on 2017-03-11.
 */

public class MyPostView extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Post> myPosts = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.view_my_post_list, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.view_my_post_rv);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(v.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        myPosts.add(new Post(1, 1, "타이틀테스트", "내용테스트", "2016-02-03 12:33:33"));
        myPosts.add(new Post(2, 1, "타이틀테스트2", "내용테스트2", "2016-02-03 12:22:33"));
        myPosts.add(new Post(3, 1, "타이틀테스트2", "내용테스트2", "2016-02-03 12:44:33"));

        mAdapter = new AdapterPostList(v.getContext(), getActivity(), myPosts);
        mRecyclerView.setAdapter(mAdapter);

        return v;
    }
}

