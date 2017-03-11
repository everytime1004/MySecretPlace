package com.jh.mysecretplace.view;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.jh.mysecretplace.R;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}

//    public void test(){
//        Button bb = (Button)findViewById(R.id.testfcm);
//        bb.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SharedPreferences sp = getSharedPreferences("UserInfo", MODE_PRIVATE);
//                if(sp.getString("ftk", "") == "")
//                    Toast.makeText(getApplication(), "토큰 없다", Toast.LENGTH_SHORT).show();
//                else{
//                    String url = "http://192.168.192.155:3000/api/v1/users/"+sp.getString("id", "");
//                    Intent sendTokenToServer = new Intent(getApplicationContext(), RequestSendFcmTK.class);
//                    sendTokenToServer.putExtra("url", url);
//                    sendTokenToServer.putExtra("token", sp.getString("ftk", ""));
//
//                    startService(sendTokenToServer);
//                }
//            }
//        });
//
//        Button cc = (Button)findViewById(R.id.check);
//        cc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SharedPreferences sp = getSharedPreferences("UserInfo", MODE_PRIVATE);
//                if(sp.getString("ftk", "") != "")
//                    Toast.makeText(getApplication(), sp.getString("ftk", ""), Toast.LENGTH_SHORT).show();
//                else{
//                    Toast.makeText(getApplication(), "토큰 없다", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }