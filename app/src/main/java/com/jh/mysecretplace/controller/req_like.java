//package com.jh.mysecretplace.controller;
//
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.graphics.Bitmap;
//import android.graphics.drawable.BitmapDrawable;
//import android.graphics.drawable.Drawable;
//import android.util.Base64;
//import android.util.Log;
//import android.widget.Toast;
//
//import com.torykorea.likeholic.lib.UrlJsonAsyncTask;
//import com.torykorea.likeholic.view.MainActivity;
//import com.torykorea.likeholic.view.TagActivity;
//
//import org.apache.http.client.HttpResponseException;
//import org.apache.http.client.ResponseHandler;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.BasicResponseHandler;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.util.ArrayList;
//
//public class req_like extends UrlJsonAsyncTask {
//	@SuppressWarnings("unused")
//	private Context mContext;
//	private SharedPreferences mPreferences;
//	private TagActivity mTageActivity;
//	private int mPageId;
//	private int mUserId;
//	private Drawable mPostImage;
//	private String mDescription;
//	private boolean mPostToFacebook;
//	private boolean mPostToTwitter;
//	private boolean mLock;
//	private byte[] mData;
//	private ArrayList<String> mTagFriends;
//
//	public req_like(Context context, TagActivity tagActivity, int pageId,
//			int userId, Drawable postImage, String description, boolean lock,
//			boolean postToFacebook, boolean postToTwitter,
//			ArrayList<String> tagFriends) {
//		super(tagActivity);
//		this.mContext = context;
//		this.mTageActivity = tagActivity;
//		this.mPageId = pageId;
//		this.mUserId = userId;
//		this.mPostImage = postImage;
//		this.mDescription = description;
//		this.mPostToFacebook = postToFacebook;
//		this.mPostToTwitter = postToTwitter;
//		this.mLock = lock;
//		this.mTagFriends = tagFriends;
//		this.mPreferences = context.getSharedPreferences("UserInfo",
//				Context.MODE_PRIVATE);
//	}
//
//	@Override
//	protected JSONObject doInBackground(String... urls) {
//		DefaultHttpClient client = new DefaultHttpClient();
//		HttpPost post = new HttpPost(urls[0]);
//		JSONObject holder = new JSONObject();
//		JSONObject taskObj = new JSONObject();
//		String response = null;
//		JSONObject json = new JSONObject();
//		if (mPostImage != null) {
//			ByteArrayOutputStream imageStream = new ByteArrayOutputStream();
//			BitmapDrawable drawable = (BitmapDrawable) this.mPostImage;
//			Bitmap imageBitmap = drawable.getBitmap();
//			imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, imageStream);
//			mData = imageStream.toByteArray();
//			String imageString = new String(Base64.encode(mData, 1));
//			try {
//				taskObj.put("image", imageString);
//			} catch (JSONException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			try {
//				imageStream.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//
//		try {
//			try {
//				json.put("success", false);
//				json.put("info", "Something went wrong. Retry!");
//
//				if (mPostToFacebook) {
//					taskObj.put("tagFriends", mTagFriends);
//				}
//
//				taskObj.put("user_id", mUserId);
//				taskObj.put("page_id", mPageId);
//				taskObj.put("description", mDescription);
//				taskObj.put("share_fb", mPostToFacebook);
//				taskObj.put("lock", !mLock);
//
//				holder.put("post", taskObj);
//				StringEntity se = new StringEntity(holder.toString(), "utf-8");
//				post.setEntity(se);
//				post.setHeader("Accept", "application/json");
//				post.setHeader("Content-Type", "application/json");
//				post.setHeader(
//						"Authorization",
//						"Token token="
//								+ mPreferences.getString("authToken", ""));
//
//				ResponseHandler<String> responseHandler = new BasicResponseHandler();
//				response = client.execute(post, responseHandler);
//				json = new JSONObject(response);
//
//			} catch (HttpResponseException e) {
//				e.printStackTrace();
//				Log.e("ClientProtocol", "" + e);
//			} catch (IOException e) {
//				e.printStackTrace();
//				Log.e("IO", "" + e);
//			}
//		} catch (JSONException e) {
//			e.printStackTrace();
//			Log.e("JSON", "" + e);
//		}
//
//		return json;
//	}
//
//	@Override
//	protected void onPostExecute(JSONObject json) {
//		try {
//			if (json.getBoolean("success")) {
//				Intent toMainIntent = new Intent(mTageActivity,
//						MainActivity.class);
//				mTageActivity.startActivity(toMainIntent);
//				mTageActivity.finish();
//
//				Toast.makeText(context, json.getString("info"),
//						Toast.LENGTH_LONG).show();
//				if (!json.getJSONObject("data").getString("facebook_post_id")
//						.equals("")) {
////					mTageActivity.mFacebookInstance.requsetCommentToPost(json
////							.getJSONObject("data")
////							.getString("facebook_post_id"), mDescription);
//				}
//				if (mPostToFacebook) {
//					if (json.getJSONObject("data").getString("image_url")
//							.equals("")) {
////						mTageActivity.mFacebookInstance.requsetPostToFeed(
////								json.getJSONObject("data").getInt("post_id"),
////								mDescription,
////								json.getJSONObject("data").getString(
////										"page_image_url"),
////								json.getJSONObject("data").getString(
////										"page_description"), mTagFriends);
//					} else {
////						mTageActivity.mFacebookInstance.requsetPostToFeed(
////								json.getJSONObject("data").getInt("post_id"),
////								mDescription,
////								json.getJSONObject("data").getString(
////										"image_url"),
////								json.getJSONObject("data").getString(
////										"page_description"), mTagFriends);
//					}
//
//				}
//				if (mPostToTwitter) {
//					mTageActivity.mTwitterInstance.requsetUploadPost(
//							mDescription,
//							json.getJSONObject("data").getString("page_name"));
//				}
//
//			}
//		} catch (Exception e) {
//			// something went wrong: show a Toast
//			// with the exception message
//			Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
//		} finally {
//			super.onPostExecute(json);
//		}
//	}
//}
