//package com.jh.mysecretplace.view;
//
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.os.Handler;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.facebook.CallbackManager;
//import com.facebook.FacebookCallback;
//import com.facebook.FacebookException;
//import com.facebook.login.LoginManager;
//import com.facebook.login.LoginResult;
//import com.facebook.login.widget.LoginButton;
//import com.google.android.gms.auth.api.Auth;
//import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
//import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
//import com.google.android.gms.auth.api.signin.GoogleSignInResult;
//import com.google.android.gms.common.ConnectionResult;
//import com.google.android.gms.common.SignInButton;
//import com.google.android.gms.common.api.GoogleApiClient;
//import com.jh.mysecretplace.R;
//import com.jh.mysecretplace.controller.req_google_sign_in;
//import com.jh.mysecretplace.utils.DLog;
//import com.jh.mysecretplace.utils.util;
//import com.twitter.sdk.android.Twitter;
//import com.twitter.sdk.android.core.Callback;
//import com.twitter.sdk.android.core.Result;
//import com.twitter.sdk.android.core.TwitterAuthConfig;
//import com.twitter.sdk.android.core.TwitterException;
//import com.twitter.sdk.android.core.TwitterSession;
//import com.twitter.sdk.android.core.identity.TwitterLoginButton;
//
//import java.util.Arrays;
//
//import io.fabric.sdk.android.Fabric;
//
///**
// * Created by love on 2017-01-09.
// */
//
//public class SplashActivity_not_firebase extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
//
//    public static int SPLASH_DISPLAY_LENGTH = 2000;
//    private static final int RC_SIGN_IN = 9001;
//
//    private SharedPreferences mPreferences;
//    private CallbackManager mCallbackManager;
//    private GoogleApiClient mGoogleApiClient;
//    private ProgressDialog mProgressDialog;
//    private TwitterLoginButton mTwitterSignInBtn;
//
//    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
//    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
//    private static final String TWITTER_KEY = "IicnS2YxbsFIj4SzDkam8xigU";
//    private static final String TWITTER_SECRET = "5NSLKRAwEsd0IaEpiTRiTqQLv0N1aP9rhKDJBuE9ccn3FPlP9c";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash);
//
//        // Here, thisActivity is the current activity
//        mPreferences = getSharedPreferences("AppInfo", MODE_PRIVATE);
//
//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if (!mPreferences.contains("tk")) {
//
//                    setViewSetting();
//                } else {
//                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                    startActivity(intent);
//                }
//            }
//        }, SPLASH_DISPLAY_LENGTH);
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//
//    }
//
//    private void setViewSetting() {
//        setContentView(R.layout.view_sign_in);
//        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
//        Fabric.with(this, new Twitter(authConfig));
//
//        mCallbackManager = CallbackManager.Factory.create();
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.google_web_client_id))
//                .requestEmail()
//                .build();
//
//
//        mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .enableAutoManage(this, this)
//                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
//                .build();
//
//        Button loginSignUpBtn = (Button) findViewById(R.id.login_sign_up_btn);
//        Button loginSignInBtn = (Button) findViewById(R.id.login_sign_in_btn);
//        LoginButton loginSignInWithFacebook = (LoginButton) findViewById(R.id.login_sign_in_with_facebook);
//        final EditText loginEmailEt = (EditText) findViewById(R.id.login_email_et);
//        final EditText loginPasswdEt = (EditText) findViewById(R.id.login_passwd_et);
//        SignInButton googleSignInBtn = (SignInButton) findViewById(R.id.login_sign_in_with_google);
//        mTwitterSignInBtn = (TwitterLoginButton) findViewById(R.id.login_sign_in_with_twitter);
//        mTwitterSignInBtn.setCallback(new Callback<TwitterSession>() {
//            @Override
//            public void success(Result<TwitterSession> result) {
//                // Do something with result, which provides a TwitterSession for making API calls
//
//                TwitterSession session = result.data;
//                // TODO: Remove toast and use the TwitterSession's userID
//                // with your app's user model
//                String msg = "@" + session.getUserName() + " logged in! (#" + session.getUserId() + ")";
//                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void failure(TwitterException exception) {
//                // Do something on failure
//            }
//        });
//
//        loginSignInBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (loginPasswdEt.length() == 0 || !util.isValidEmail(loginEmailEt.getText().toString())) {
//                    Toast.makeText(getApplicationContext(), R.string.login_warning, Toast.LENGTH_SHORT).show();
//                    return;
//                } else {
//                    // login
//                }
//            }
//        });
//
//        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"));
//        loginSignInWithFacebook.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                DLog.d(getApplicationContext(), "FacebookCallback : onSuccess() called");
//                DLog.d(getApplicationContext(), "FacebookCallback : token : " + loginResult.getAccessToken().getToken());
//
////                RequestSignIn reqFacebookSignIn = new RequestSignIn(getApplicationContext(),SplashActivity_not_firebase.this,  loginResult.getAccessToken().toString());
////                reqFacebookSignIn.setActivityInfo(SplashActivity_not_firebase.this);
////                reqFacebookSignIn.execute("http://172.20.10.6:3000/api/v1/omniauth");
////                reqFacebookSignIn.setMessageLoading("인증 중입니다.");
//            }
//
//            @Override
//            public void onCancel() {
//                DLog.d(getApplicationContext(), "FacebookCallback : onCancel() called");
//            }
//
//            @Override
//            public void onError(FacebookException exception) {
//                DLog.d(getApplicationContext(), "FacebookCallback : onError() called");
//                DLog.d(getApplicationContext(), "exception : " + exception.getMessage());
//            }
//        });
//
//        googleSignInBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
//                startActivityForResult(signInIntent, RC_SIGN_IN);
//            }
//        });
//
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        mCallbackManager.onActivityResult(requestCode, resultCode, data);
//        mTwitterSignInBtn.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == RC_SIGN_IN) {
//            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
//            handleSignInResult(result);
//        }
//    }
//
//    @Override
//    public void onConnectionFailed(ConnectionResult connectionResult) {
//        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
//        // be available.
//        DLog.d(getApplicationContext(), "onConnectionFailed:" + connectionResult);
//    }
//
//    private void handleSignInResult(GoogleSignInResult result) {
//        DLog.d(getApplicationContext(), "handleSignInResult:" + result.isSuccess());
//        if (result.isSuccess()) {
//            // Signed in successfully, show authenticated UI.
//            GoogleSignInAccount acct = result.getSignInAccount();
//            DLog.d(getApplicationContext(), "handleSignInResult:" + acct.getDisplayName());
//            DLog.d(getApplicationContext(), "handleSignInResult:" + acct.getGivenName());
//            DLog.d(getApplicationContext(), "handleSignInResult:" + acct.getFamilyName());
//            DLog.d(getApplicationContext(), "handleSignInResult:" + acct.getEmail());
//            DLog.d(getApplicationContext(), "handleSignInResult:" + acct.getId());
//            DLog.d(getApplicationContext(), "handleSignInResult:" + acct.getIdToken());
//
//
//            req_google_sign_in reqGoogleSignIn = new req_google_sign_in(getApplicationContext(), SplashActivity_not_firebase.this, acct.getIdToken());
//            reqGoogleSignIn.setActivityInfo(SplashActivity_not_firebase.this);
//            reqGoogleSignIn.execute("http://172.20.10.6:3000/api/v1/omniauth");
//            reqGoogleSignIn.setMessageLoading("인증 중입니다.");
//
//        } else {
//            // Signed out, show unauthenticated UI.
////            updateUI(false);
//        }
//    }
//
//}