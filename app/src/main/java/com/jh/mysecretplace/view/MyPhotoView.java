package com.jh.mysecretplace.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jh.mysecretplace.R;

/**
 * Created by love on 2017-03-11.
 */

public class MyPhotoView extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.view_my_photo_list, container, false);

        return v;
    }
}
