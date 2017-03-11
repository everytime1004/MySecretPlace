package com.jh.mysecretplace.view;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

import com.facebook.CallbackManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.jh.mysecretplace.R;
import com.jh.mysecretplace.utils.DLog;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;

/**
 * Created by love on 2017-01-09.
 */

public class SplashActivity extends FragmentActivity implements GoogleApiClient.OnConnectionFailedListener {

    public static int SPLASH_DISPLAY_LENGTH = 2000;
    protected static final int RC_SIGN_IN = 9001;
    protected static final String FRAGMENT_SIGN_IN_TAG = "SIGN_IN";

    private SharedPreferences mPreferences;

    private CallbackManager mFacebookCallbackManager;

    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "G1XITZ5SzeB5OyTqyNrrJ9LsL";
    private static final String TWITTER_SECRET = "SRIdrpQNu63cynUTYuadiNjj8n93zf0vv8GesIE8WWe3OJHyot";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_splash);

        // Here, thisActivity is the current activity
        mPreferences = getSharedPreferences("AppInfo", MODE_PRIVATE);
        setFirebaseSetting();
        setFacebookAuthSetting();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getWindow().setBackgroundDrawable(null);
                if (!mPreferences.contains("tk")) {
                    SignInFormView firstFragment = new SignInFormView();
                    firstFragment.setArguments(getIntent().getExtras());
                    getFragmentManager().beginTransaction().add(R.id.splash_container, firstFragment, FRAGMENT_SIGN_IN_TAG).commit();

                } else {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void setFirebaseSetting(){
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                DLog.d(getApplication(), "onAuthStateChanged called");

//                if(firebaseAuth != null && firebaseAuth.getCurrentUser() != null){
//                    FirebaseDatabase database = FirebaseDatabase.getInstance();
//                    DatabaseReference myRef = database.getReference("user").child(mAuth.getCurrentUser().getUid());
//
//                    myRef.setValue(new User(mAuth.getCurrentUser().getDisplayName(), mAuth.getCurrentUser().getEmail()), new DatabaseReference.CompletionListener(){
//                        @Override
//                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
//                            if(databaseError != null){
//                                DLog.d(getApplicationContext(), "databaseCompletionListner:onComplete error" + databaseError.getMessage());
//                            }else{
//                                DLog.d(getApplicationContext(), "databaseCompletionListner:onComplete success");
//                            }
//                        }
//                    });
//                }
            }
        };
    }

    private void setGoogleSetting(){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.google_web_client_id))
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

    }

    private void setFacebookAuthSetting() {
        mFacebookCallbackManager = CallbackManager.Factory.create();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment = getFragmentManager().findFragmentByTag(FRAGMENT_SIGN_IN_TAG);
        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        DLog.d(getApplicationContext(), "onConnectionFailed:" + connectionResult);
    }

    public FirebaseAuth getFirebaseAuth(){
        return mAuth;
    }
    public CallbackManager getFacebookCallbackManager(){
        return mFacebookCallbackManager;
    }
    public GoogleApiClient getGoogleApiClient(){
        return mGoogleApiClient;
    }

}