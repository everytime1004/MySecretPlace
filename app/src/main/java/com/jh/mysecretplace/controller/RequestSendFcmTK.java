package com.jh.mysecretplace.controller;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;

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
 * Created by love on 2017-02-14.
 */

public class RequestSendFcmTK extends IntentService {
    public RequestSendFcmTK() {
        super("RequestSendFcmTK");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        URL url = null;
        JSONObject jsonObject = new JSONObject();
        OutputStream outputStream = null;
        BufferedWriter writer = null;
        BufferedReader bufferedReader = null;
        InputStream inputStream = null;
        SharedPreferences sp = getSharedPreferences("UserInfo", MODE_PRIVATE);

        try {
            url = new URL(intent.getStringExtra("url"));
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("PUT");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("X-User-Fcm-Token", intent.getStringExtra("token"));
            urlConnection.setRequestProperty("X-User-Token", sp.getString("idk", ""));
            urlConnection.setRequestProperty("X-User-Email", sp.getString("email", ""));

            outputStream = new BufferedOutputStream(urlConnection.getOutputStream());
            writer = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
            writer.flush();
            writer.close();

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
            outputStream.close();
            jsonObject = new JSONObject(response);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
