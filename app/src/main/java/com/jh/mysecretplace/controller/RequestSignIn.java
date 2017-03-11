package com.jh.mysecretplace.controller;

import android.content.Context;

import com.jh.mysecretplace.utils.DLog;
import com.jh.mysecretplace.utils.UrlJsonAsyncTask;
import com.jh.mysecretplace.view.SplashActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by love on 2017-01-29.
 */

public class RequestSignIn extends UrlJsonAsyncTask {
    private Context mContext;
    private SplashActivity mActivity;
    private String mIdToken;
    private String mProvider;
    private String mUid;
    private String mFtoken;


    public RequestSignIn(Context context, SplashActivity activity, String idToken, String provider, String uid, String email, String ftoken) {
        super(context);
        mActivity = activity;
        mIdToken = idToken;
        mProvider = provider;
        mUid = uid;
        mFtoken= ftoken;
    }

    @Override
    protected JSONObject doInBackground(String... urls) {
        JSONObject jsonObject = new JSONObject();
        OutputStream outputStream = null;
        BufferedWriter writer = null;
        BufferedReader bufferedReader = null;
        InputStream inputStream = null;

        try {
            URL url = new URL(urls[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            // Post
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("X-User-IdToken", mIdToken);
            urlConnection.setRequestProperty("X-User-Provider", mProvider);
            urlConnection.setRequestProperty("X-User-ID", mUid);
            urlConnection.setRequestProperty("X-User-Fcm-Token", mFtoken);

            outputStream = new BufferedOutputStream(urlConnection.getOutputStream());
            writer = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
            writer.flush();
//            writer.write();
            writer.close();
            outputStream.close();

            // get stream
            if (urlConnection.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST) {
                inputStream = urlConnection.getInputStream();
            } else {
                inputStream = urlConnection.getErrorStream();
            }
            // parse stream
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String temp, response = "";
            while ((temp = bufferedReader.readLine()) != null) {
                response += temp;
            }
            bufferedReader.close();
            jsonObject = new JSONObject(response);

            return jsonObject;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null)
                    outputStream.close();
                if (writer != null)
                    writer.close();
                if (bufferedReader != null)
                    bufferedReader.close();
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(JSONObject json) {
        super.onPostExecute(json);
        DLog.d(mContext, "RequestSignIn::onPostExecute called");

    }
}