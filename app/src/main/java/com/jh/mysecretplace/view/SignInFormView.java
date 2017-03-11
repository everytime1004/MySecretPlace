package com.jh.mysecretplace.view;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.jh.mysecretplace.R;
import com.jh.mysecretplace.controller.HandleSessionController;
import com.jh.mysecretplace.utils.DLog;
import com.jh.mysecretplace.utils.util;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import static com.facebook.FacebookSdk.getApplicationContext;
import static com.jh.mysecretplace.view.SplashActivity.RC_SIGN_IN;

/**
 * Created by love on 2017-03-05.
 */

public class SignInFormView extends Fragment implements GoogleApiClient.OnConnectionFailedListener {

    private TwitterLoginButton mTwitterLoginBtn;
    private CallbackManager mFacebookCallbackManager;
    private GoogleApiClient mGoogleApiClient;
    private SplashActivity mActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.view_sign_in, container, false);
        mFacebookCallbackManager = CallbackManager.Factory.create();
        mActivity = (SplashActivity) getActivity();
        setViewSetting(v, mActivity.getFirebaseAuth());
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mTwitterLoginBtn.onActivityResult(requestCode, resultCode, data);
        mFacebookCallbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                HandleSessionController hsc = new HandleSessionController(getActivity(), mActivity.getFirebaseAuth());
                hsc.firebaseAuthWithGoogle(account);
                // 서버로 보내기
            } else {
                // Google Sign In failed, update UI appropriately
                // ...
            }
        }
    }

    private void setViewSetting(View v, final FirebaseAuth fa) {
        Button loginSignUpBtn = (Button) v.findViewById(R.id.login_sign_up_btn);
        Button loginSignInBtn = (Button) v.findViewById(R.id.login_sign_in_btn);
        final EditText loginEmailEt = (EditText) v.findViewById(R.id.login_email_et);
        final EditText loginPasswdEt = (EditText) v.findViewById(R.id.login_passwd_et);

        loginSignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loginPasswdEt.length() == 0 || !util.isValidEmail(loginEmailEt.getText().toString())) {
                    Toast.makeText(getApplicationContext(), R.string.login_warning, Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    // login
                    HandleSessionController hsc = new HandleSessionController(getActivity(), fa);
                    hsc.handleEmailSession(loginEmailEt.getText().toString(), loginPasswdEt.getText().toString());
                }
            }
        });

        loginSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "테스트", Toast.LENGTH_SHORT).show();
                SignUpFormView secondFragment = new SignUpFormView();
                getFragmentManager().beginTransaction().replace(R.id.splash_container, secondFragment).addToBackStack(null).commit();
            }
        });

        LoginButton signInWithFacebook = (LoginButton) v.findViewById(R.id.login_sign_in_with_facebook);
        signInWithFacebook.setReadPermissions("email", "public_profile");
        signInWithFacebook.setFragment(this);
        signInWithFacebook.registerCallback(mFacebookCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                DLog.d(getApplicationContext(), "facebook:onSuccess:" + loginResult);
                HandleSessionController hsc = new HandleSessionController(getActivity(), fa);
                hsc.handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                DLog.d(getApplicationContext(), "facebook:onCancel");
                // ...
            }

            @Override
            public void onError(FacebookException error) {
                DLog.d(getApplicationContext(), "facebook:onError" + error);
                // ...
            }
        });

        SignInButton signInWithGoogle = (SignInButton) v.findViewById(R.id.login_sign_in_with_google);
        signInWithGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setGoogleSetting();
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });

        mTwitterLoginBtn = (TwitterLoginButton) v.findViewById(R.id.login_sign_in_with_twitter);
        mTwitterLoginBtn.setCallback(new Callback<TwitterSession>() {

            @Override
            public void success(Result<TwitterSession> result) {
                DLog.d(getApplicationContext(), "twitter:success " + result.response);
                HandleSessionController hsc = new HandleSessionController(getActivity(), fa);
                hsc.handleTwitterSession(result.data);

                // Do something with result, which provides a TwitterSession for making API calls
            }

            @Override
            public void failure(TwitterException exception) {
                DLog.d(getApplicationContext(), "twitter:failure " + exception.getMessage());
                DLog.d(getApplicationContext(), "twitter:failure " + exception.toString());
                exception.printStackTrace();

                // Do something on failure
            }
        });
    }

    private void setGoogleSetting() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.google_web_client_id))
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(mActivity)
                .enableAutoManage(mActivity /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        DLog.d(getApplicationContext(), "onConnectionFailed:" + connectionResult);
    }
}
