package com.jh.mysecretplace.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.jh.mysecretplace.R;
import com.jh.mysecretplace.utils.DLog;

/**
 * Created by love on 2017-03-11.
 */

public class MyInfoView extends Fragment implements View.OnClickListener{
    private Button mUserInfoEditBtn;
    private ImageView mUserImageView;
    private TextView mUserNameTv;
    private EditText mUserNameEt;
    private TextView mUserEmailTv;
    private EditText mUserEmailEt;
    private TextView mUserBirthTv;
    private DatePicker mUserBirthDp;
    private RadioGroup mUserGenderRg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.view_my_info, container, false);
        viewSetting(v);

        return v;
    }

    private void viewSetting(View v){
        mUserInfoEditBtn = (Button) v.findViewById(R.id.view_my_info_btn);
        mUserImageView = (ImageView) v.findViewById(R.id.view_my_info_user_image);
        mUserNameTv = (TextView) v.findViewById(R.id.view_my_info_user_name_tv);
        mUserNameEt = (EditText) v.findViewById(R.id.view_my_info_user_name_et);
        mUserEmailTv = (TextView) v.findViewById(R.id.view_my_info_user_email_tv);
        mUserEmailEt = (EditText) v.findViewById(R.id.view_my_info_user_email_et);
        mUserBirthTv = (TextView) v.findViewById(R.id.view_my_info_user_birth_tv);
        mUserBirthDp = (DatePicker) v.findViewById(R.id.view_my_info_user_birth_dp);
        mUserGenderRg = (RadioGroup) v.findViewById(R.id.view_my_info_user_gender_rg);

        mUserInfoEditBtn.setOnClickListener(this);
        mUserImageView.setOnClickListener(this);
        mUserNameTv.setOnClickListener(this);
        mUserNameEt.setOnClickListener(this);
        mUserEmailTv.setOnClickListener(this);
        mUserEmailEt.setOnClickListener(this);
        mUserBirthTv.setOnClickListener(this);
        mUserBirthDp.setOnClickListener(this);
        mUserGenderRg.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        DLog.d(getActivity().getApplicationContext(), "onLickListener : id = " + v.getId());
        ((MainActivity)getActivity()).mChangedState = true;
        switch (v.getId()){
            case R.id.view_my_info_btn:{
                //dialog
                break;
            }case R.id.view_my_info_user_image:{
                // camera
                break;
            }case R.id.view_my_info_user_name_tv:{
                mUserNameEt.requestFocus();
                break;
            }case R.id.view_my_info_user_name_et:{
                break;
            }case R.id.view_my_info_user_email_tv:{
                DLog.d(getActivity().getApplicationContext(), "onLickListener : view_my_info_user_email_tv");
                mUserEmailEt.requestFocus();
                break;
            }case R.id.view_my_info_user_email_et:{
                break;
            }case R.id.view_my_info_user_birth_tv:{
                break;
            }case R.id.view_my_info_user_birth_dp:{
                break;
            }case R.id.view_my_info_user_gender_rg:{

                break;
            }
        }
    }


}
