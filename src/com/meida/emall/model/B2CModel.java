package com.meida.emall.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.external.androidquery.callback.AjaxStatus;
import com.insthub.BeeFramework.model.BaseModel;
import com.insthub.BeeFramework.model.BeeCallback;
import com.meida.emall.protocol.CATE;
import com.meida.emall.protocol.DEFGOOD;
import com.meida.emall.protocol.SESSION;
import com.meida.emall.protocol.STATUS;

public class B2CModel extends BaseModel {

	public static String PRICE_DESC = "price_desc";
	public static String PRICE_ASC = "price_asc";

	public ArrayList<DEFGOOD> DefList = new ArrayList<DEFGOOD>();

	public ArrayList<CATE> CateList = new ArrayList<CATE>();

	public B2CModel(Context context) {
		super(context);
	}

	public void fetchB2CList() {
		String url = ProtocolConst.B2CLIST;

		BeeCallback<JSONObject> cb = new BeeCallback<JSONObject>() {

			@Override
			public void callback(String url, JSONObject object,
					AjaxStatus status) {
				B2CModel.this.callback(url, object, status);
				try {
					STATUS responseStatus = STATUS.fromJson(object
							.optJSONObject("status"));
					if (responseStatus.succeed == 1) {
						JSONObject data = object.optJSONObject("data");
						JSONArray bidding = data.optJSONArray("bidding");
						DefList.clear();
						for (int i = 0; i < bidding.length(); i++) {
							DEFGOOD item = DEFGOOD.fromJson(bidding
									.optJSONObject(i));
							DefList.add(item);
						}
						B2CModel.this.OnMessageResponse(url, object, status);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

		};

		SESSION session = SESSION.getInstance();

		JSONObject requestJsonObject = new JSONObject();

		Map<String, String> params = new HashMap<String, String>();
		try {
			requestJsonObject.put("session", session.toJson());
		} catch (JSONException e) {

		}

		params.put("json", requestJsonObject.toString());

		cb.url(url).type(JSONObject.class).params(params);

		aq.ajax(cb);
	}

	public void fetchB2Ccate() {
		String url = ProtocolConst.B2CCATE;

		BeeCallback<JSONObject> cb = new BeeCallback<JSONObject>() {

			@Override
			public void callback(String url, JSONObject object,
					AjaxStatus status) {
				B2CModel.this.callback(url, object, status);
				try {
					STATUS responseStatus = STATUS.fromJson(object
							.optJSONObject("status"));
					if (responseStatus.succeed == 1) {
						JSONArray data = object.optJSONArray("data");
						for (int i = 0; i < data.length(); i++) {
							CATE item = CATE.fromJson(data.optJSONObject(i));
							CateList.add(item);
						}
						B2CModel.this.OnMessageResponse(url, object, status);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

		};

		SESSION session = SESSION.getInstance();

		JSONObject requestJsonObject = new JSONObject();

		Map<String, String> params = new HashMap<String, String>();
		try {
			requestJsonObject.put("session", session.toJson());
		} catch (JSONException e) {

		}

		params.put("json", requestJsonObject.toString());

		cb.url(url).type(JSONObject.class).params(params);

		aq.ajax(cb);
	}

}
