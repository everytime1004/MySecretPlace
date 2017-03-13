package com.jh.mysecretplace.controller;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.TwitterAuthProvider;
import com.jh.mysecretplace.utils.DLog;
import com.jh.mysecretplace.view.MainActivity;
import com.twitter.sdk.android.core.TwitterSession;


/**
 * Created by love on 2017-03-10.
 */

public class HandleSessionController {
    private Activity mActivity;
    private FirebaseAuth mAuth;

    public HandleSessionController(Activity activity, FirebaseAuth auth){
        mActivity = activity;
        mAuth = auth;
    }

    public void firebaseAuthWithGoogle(final GoogleSignInAccount acct) {
        DLog.d(mActivity, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(mActivity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        DLog.d(mActivity, "signInWithCredential:onComplete:" + task.isSuccessful());
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            DLog.d(mActivity, "signInWithCredential" + task.getException());
                            Toast.makeText(mActivity, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }else{
                            Intent i = new Intent(mActivity, MainActivity.class);
                            mActivity.startActivity(i);
                            mActivity.finish();
                        }
                        // ...
                    }
                });
    }

    public void handleFacebookAccessToken(AccessToken token) {
        DLog.d(mActivity, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(mActivity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        DLog.d(mActivity, "signInWithCredential:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            DLog.d(mActivity, "signInWithCredential" + task.getException());
                            Toast.makeText(mActivity, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }else{
                            Intent i = new Intent(mActivity, MainActivity.class);
                            mActivity.startActivity(i);
                        }
                    }
                });
    }

    public void handleTwitterSession(TwitterSession session) {
        DLog.d(mActivity, "handleTwitterSession:" + session);

        AuthCredential credential = TwitterAuthProvider.getCredential(
                session.getAuthToken().token,
                session.getAuthToken().secret);

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(mActivity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        DLog.d(mActivity, "signInWithCredential:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            DLog.d(mActivity, "signInWithCredential" + task.getException());
                            Toast.makeText(mActivity, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }else{
                            Intent i = new Intent(mActivity, MainActivity.class);

                        }

                        // ...
                    }
                });
    }

    public void handleEmailSession(String email, String pass) {
        DLog.d(mActivity, "handleEmailSession:");
        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(mActivity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        DLog.d(mActivity, "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {

                            DLog.d(mActivity, "signInWithEmail:failed" + task.getException());
                            Toast.makeText(mActivity, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }else{
                            Intent i = new Intent(mActivity, MainActivity.class);

                        }

                        // ...
                    }
                });
    }
}
