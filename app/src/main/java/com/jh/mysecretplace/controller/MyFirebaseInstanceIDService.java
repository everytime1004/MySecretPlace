package com.jh.mysecretplace.controller;

import android.content.Intent;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.jh.mysecretplace.utils.DLog;

/**
 * Created by love on 2017-02-14.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIIDService";

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    // [START refresh_token]

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        DLog.d(getApplicationContext(), "onTokenRefresh called:tttttttttttttttttttttttttttttttttttt");
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        DLog.d(getApplicationContext(), "Refreshed token: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(refreshedToken);
    }
    // [END refresh_token]

    /**
     * Persist token to third-party servers.
     *
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private void sendRegistrationToServer(String token) {
        String url = "http://192.168.192.155:3000/api/v1/omniauth";
        Intent sendTokenToServer = new Intent(getApplicationContext(), RequestSendFcmTK.class);
        sendTokenToServer.putExtra("url", url);
        sendTokenToServer.putExtra("token", token);

        startService(sendTokenToServer);
    }
}