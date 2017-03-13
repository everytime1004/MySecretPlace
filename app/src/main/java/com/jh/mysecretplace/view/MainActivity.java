package com.jh.mysecretplace.view;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.jh.mysecretplace.R;
import com.jh.mysecretplace.controller.NavListItemAdapter;
import com.jh.mysecretplace.model.NavigationItem;
import com.jh.mysecretplace.utils.DLog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    private final int NAV_MY_POST_VIEW = 0;
    private final int NAV_MY_STOR_VIEW = 1;
    private final int NAV_MY_PHOTO_VIEW = 2;
    private final int NAV_MY_DRINK_VIEW = 3;
    private final int NAV_MY_FOOD_VIEW = 4;
    private final int NAV_MY_INFO_VIEW = 5;
    private final int NAV_MY_SETTINGS_VIEW = 6;

    private static final String MY_POST_VIEW = "MY_POST_VIEW";
    private static final String MY_STOR_VIEW = "MY_STOR_VIEW";
    private static final String MY_PHOTO_VIEW = "MY_PHOTO_VIEW";
    private static final String MY_DRINK_VIEW = "MY_DRINK_VIEW";
    private static final String MY_FOOD_VIEW = "MY_FOOD_VIEW";
    private static final String MY_INFO_VIEW = "MY_INFO_VIEW";
    private static final String MY_SETTINGS_VIEW = "MY_SETTINGS_VIEW";

    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private ActionBarDrawerToggle mDrawerToggle;
    private SearchView mSearchView;

    public int mNaviTouchedPoistion = -1;
    public int mNaviState = 2; // 메뉴 상태 1: open, 2: close
    public final static int NS_CLOSE = 2;
    public final static int NS_OPEN = 1;

    private CharSequence mTitle ;

    public boolean mChangedState = false;

    public static int FIRST_PAGE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setDrawerLayout();
        setNavigationView();
        MyPostView myPostView = new MyPostView();
        getFragmentManager().beginTransaction().add(R.id.main_container, myPostView, MY_POST_VIEW).commit();
    }

    private void setDrawerLayout(){
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        setTitle(MY_POST_VIEW);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                mToolbar,
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
                mNaviState = NS_CLOSE;
                mNaviTouchedPoistion = -1;
                super.onDrawerClosed(view);
            }

            public void onDrawerOpened(View drawerView) {
                mNaviState = NS_OPEN;
                super.onDrawerOpened(drawerView);
            }
        };
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mNaviState == NS_OPEN) {
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });
        mDrawerLayout.addDrawerListener(mDrawerToggle);

        mSearchView = (SearchView) findViewById(R.id.toolbar_search);
        mSearchView.setLayoutParams(new Toolbar.LayoutParams(Gravity.END));
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                DLog.d(getApplicationContext(), "Toolbar::onQueryTextSubmit called");
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                DLog.d(getApplicationContext(), "Toolbar::onQueryTextChange called");
                return false;
            }
        });
    }

    private void setNavigationView(){
        TextView navHeaderUserName = (TextView)findViewById(R.id.nav_header_user_name_tv);
        TextView navHeaderUserEmail = (TextView)findViewById(R.id.nav_header_user_email_tv);

        navHeaderUserName.setText("테스트입니다.");
        navHeaderUserEmail.setText("testest@naver.com");
        RecyclerView navItemListView = (RecyclerView) findViewById(R.id.navigation_items_rv);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        navItemListView.setLayoutManager(layoutManager);
        navItemListView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                onTouchEvent(rv, e);
                return false;
            }
            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                handleTouchEvent(rv, e);
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }

            private boolean handleTouchEvent(RecyclerView rv, MotionEvent motionEvent) {
                switch (motionEvent.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN: {
                        DLog.d(getApplicationContext(), "Navigation TouchEvent Test position: ACTION_DOWN");
                        View touchedView = rv.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                        mNaviTouchedPoistion = rv.getChildAdapterPosition(touchedView);
                        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
                        break;
                    }

                    case MotionEvent.ACTION_CANCEL: {
                        mNaviTouchedPoistion = -1;
                        DLog.d(getApplicationContext(), "Navigation TouchEvent Test position: ACTION_CANCEL");
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        DLog.d(getApplicationContext(), "Navigation TouchEvent Test position: ACTION_UP");
                        if (mNaviTouchedPoistion != -1) {
                            DLog.d(getApplicationContext(), "Navigation TouchEvent Test position: ACTION_UP_NOT -1 : " + mNaviTouchedPoistion);
                            switch (mNaviTouchedPoistion) {
                                case NAV_MY_POST_VIEW: {
                                    MyPostView view = new MyPostView();
                                    getFragmentManager().beginTransaction().replace(R.id.main_container, view, MY_POST_VIEW).commit();
                                    break;
                                }
                                case NAV_MY_STOR_VIEW: {
                                    MyStoreView view = new MyStoreView();
                                    getFragmentManager().beginTransaction().replace(R.id.main_container, view, MY_STOR_VIEW).commit();
                                    break;
                                }
                                case NAV_MY_PHOTO_VIEW: {
                                    MyPhotoView view = new MyPhotoView();
                                    getFragmentManager().beginTransaction().replace(R.id.main_container, view, MY_PHOTO_VIEW).commit();
                                    break;
                                }
                                case NAV_MY_DRINK_VIEW: {
                                    MyDrinkListView view = new MyDrinkListView();
                                    getFragmentManager().beginTransaction().replace(R.id.main_container, view, MY_DRINK_VIEW).commit();
                                    break;
                                }
                                case NAV_MY_FOOD_VIEW: {
                                    MyFoodListView view = new MyFoodListView();
                                    getFragmentManager().beginTransaction().replace(R.id.main_container, view, MY_FOOD_VIEW).commit();
                                    break;
                                }
                                case NAV_MY_INFO_VIEW: {
                                    MyInfoView view = new MyInfoView();
                                    getFragmentManager().beginTransaction().replace(R.id.main_container, view, MY_INFO_VIEW).commit();
                                    break;
                                }
                                case NAV_MY_SETTINGS_VIEW: {
                                    SettingsView view = new SettingsView();
                                    getFragmentManager().beginTransaction().replace(R.id.main_container, view, MY_SETTINGS_VIEW).commit();
                                    break;
                                }
                            }
                            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                            mDrawerLayout.closeDrawer(GravityCompat.START);
                        }
                        break;
                    }
                }
                return false;
            }
        });

        Resources res = getResources();
        String[] titles = res.getStringArray(R.array.nav_list_titles);
        TypedArray icons = res.obtainTypedArray(R.array.nav_list_icons);
//
        List<NavigationItem> itemList = new ArrayList<>();
        for(int i = 0 ; i < titles.length ; i++){
            NavigationItem item = new NavigationItem(icons.getDrawable(i), titles[i]);
            itemList.add(new NavigationItem(icons.getDrawable(i), titles[i]));
            DLog.d(getApplicationContext(), "Item "+(i+1) + " : "+icons.getDrawable(i)+", "+ titles[i]);
        }

        NavListItemAdapter navAdapter = new NavListItemAdapter(getApplicationContext(), MainActivity.this, itemList);
        navItemListView.setAdapter(navAdapter);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        DLog.d(getApplicationContext(), "onOptionsItemSelected called" + item.toString());
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    public void onBackPressed() {
        DLog.d(getApplicationContext(), "onBackPressed called");
        if(mNaviState == NS_OPEN)
            mDrawerLayout.closeDrawer(GravityCompat.START);
        else if (mChangedState) {
            showAlertDialog();
        }else{
            super.onBackPressed();
        }
    }

    private void showAlertDialog(){
        AlertDialog.Builder alert_confirm = new AlertDialog.Builder(this);
        alert_confirm.setMessage(getString(R.string.alert_cancel_or_not));
        alert_confirm.setPositiveButton(getString(R.string.submit), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mChangedState = false;
                onBackPressed();
            }
        });
        alert_confirm.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alert = alert_confirm.create();
        alert.setIcon(R.mipmap.ic_launcher);
        alert.setTitle("경고");
        alert.show();
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