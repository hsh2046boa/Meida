package com.meida.emall.model;
//
//                       __
//                      /\ \   _
//    ____    ____   ___\ \ \_/ \           _____    ___     ___
//   / _  \  / __ \ / __ \ \    <     __   /\__  \  / __ \  / __ \
//  /\ \_\ \/\  __//\  __/\ \ \\ \   /\_\  \/_/  / /\ \_\ \/\ \_\ \
//  \ \____ \ \____\ \____\\ \_\\_\  \/_/   /\____\\ \____/\ \____/
//   \/____\ \/____/\/____/ \/_//_/         \/____/ \/___/  \/___/
//     /\____/
//     \/___/
//
//  Powered by BeeFramework
//

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.content.res.Resources;

import com.insthub.BeeFramework.view.ToastView;
import com.meida.emall.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.Gravity;

import com.external.androidquery.callback.AjaxStatus;
import com.insthub.BeeFramework.model.BaseModel;
import com.insthub.BeeFramework.model.BeeCallback;
import com.meida.emall.ErrorCodeConst;
import com.meida.emall.protocol.SESSION;
import com.meida.emall.protocol.SHOPHELP;
import com.meida.emall.protocol.SIGNUPFILEDS;
import com.meida.emall.protocol.SIMPLEGOODS;
import com.meida.emall.protocol.STATUS;
import com.meida.emall.protocol.USER;

public class RegisterModel extends BaseModel {

	private SharedPreferences shared;
	private SharedPreferences.Editor editor;
	public ArrayList<SIGNUPFILEDS> signupfiledslist = new ArrayList<SIGNUPFILEDS>();
	
	public RegisterModel(Context context) {
		super(context);
		 
		shared = context.getSharedPreferences("userInfo", 0); 
		editor = shared.edit();
	}
	
	public STATUS responseStatus;
	
	public void signupFields() {
		String url = ProtocolConst.SIGNUPFIELDS;
		
		BeeCallback<JSONObject> cb = new BeeCallback<JSONObject>() {

			@Override
			public void callback(String url, JSONObject jo, AjaxStatus status) {
				
				RegisterModel.this.callback(url, jo, status);
				try {
					responseStatus = STATUS.fromJson(jo.optJSONObject("status"));
					if(responseStatus.succeed == ErrorCodeConst.ResponseSucceed)
                    {
						JSONArray dataJsonArray = jo.optJSONArray("data");
                        if (null != dataJsonArray && dataJsonArray.length() > 0)
                        {
                        	signupfiledslist.clear();
                            for (int i = 0; i < dataJsonArray.length(); i++)
                            {
                                JSONObject signupfiledsJsonObject = dataJsonArray.getJSONObject(i);
                                SIGNUPFILEDS signupfiledsItem = SIGNUPFILEDS.fromJson(signupfiledsJsonObject);
                                signupfiledslist.add(signupfiledsItem);
                            }
                        }
						
					}
                    else if (responseStatus.error_code == ErrorCodeConst.UserOrEmailExist)
                    {
                        Resources resource = mContext.getResources();
                        String user_or_email_exists = resource.getString(R.string.user_or_email_exists);
                        ToastView toast = new ToastView(mContext, user_or_email_exists);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }

					RegisterModel.this.OnMessageResponse(url, jo, status);
					
				} catch (JSONException e) {
					 
					e.printStackTrace();
				}
			}

		};
		
		cb.url(url).type(JSONObject.class);
		aq.ajax(cb);
		
	}
	
	public void signup(final String name, String password, String password2, String email, String field) {
		String url = ProtocolConst.SIGNUP;
		
		BeeCallback<JSONObject> cb = new BeeCallback<JSONObject>() {

			@Override
			public void callback(String url, JSONObject jo, AjaxStatus status) {

				RegisterModel.this.callback(url, jo, status);
				try {
					responseStatus = STATUS.fromJson(jo.optJSONObject("status"));
					
					if(responseStatus.succeed == 1) {
						
						JSONObject data = jo.optJSONObject("data");
						SESSION session = SESSION.fromJson(data.optJSONObject("session"));
						USER user = USER.fromJson(data.optJSONObject("user"));
						user.save();
						
						editor.putString("user_name", name);
						editor.putString("uid", session.uid);
	                    editor.putString("sid", session.sid);
	                    editor.commit();
						
					}
					
					RegisterModel.this.OnMessageResponse(url, jo, status);
				} catch (JSONException e) {
					 
					e.printStackTrace();
				}
			}

		};
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user_name", name);
		params.put("password", password);
		params.put("password_confirm", password);
		params.put("email", email);
		params.put("referrer", field);

		cb.url(url).type(JSONObject.class).params(params);
		aq.ajax(cb);
		
	}
	
}
