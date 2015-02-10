package com.meida.emall.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;

import com.external.androidquery.callback.AjaxStatus;
import com.insthub.BeeFramework.model.BaseModel;
import com.insthub.BeeFramework.model.BeeCallback;
import com.meida.emall.R;
import com.meida.emall.protocol.GOODORDER;
import com.meida.emall.protocol.MYFAIR;
import com.meida.emall.protocol.PAGINATED;
import com.meida.emall.protocol.SESSION;
import com.meida.emall.protocol.STATUS;

public class MyFairModel extends BaseModel {
	public ArrayList<MYFAIR> fair_List = new ArrayList<MYFAIR>();

	public MyFairModel(Context context) {
         super(context);
	}

	public void getMyFair(String pagerNum) {
		String url = ProtocolConst.MyFair;
		BeeCallback<JSONObject> cb = new BeeCallback<JSONObject>() {

			@Override
			public void callback(String url, JSONObject jo, AjaxStatus status) {
				// TODO Auto-generated method stub
				MyFairModel.this.callback(url, jo, status);
				try {
					STATUS responseStatus = STATUS.fromJson(jo.optJSONObject("status"));
					if (responseStatus.succeed == 1) {
						JSONArray dataJsonArray = jo.optJSONArray("data");
						fair_List.clear();
						if (null != dataJsonArray && dataJsonArray.length() > 0) {
							fair_List.clear();
							for (int i = 0; i < dataJsonArray.length(); i++) {
								JSONObject orderJsonObject = dataJsonArray.getJSONObject(i);
								MYFAIR order_list_Item = MYFAIR.fromJson(orderJsonObject);
								fair_List.add(order_list_Item);
							}
						}
					}
					MyFairModel.this.OnMessageResponse(url, jo, status);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

		};
		SESSION session = SESSION.getInstance();
		JSONObject requestJsonObject = new JSONObject();
		Map<String, String> params = new HashMap<String, String>();
		try 
		{
            requestJsonObject.put("session",session.toJson());
            
		} catch (JSONException e) {
			// TODO: handle exception
		}
        params.put("json",requestJsonObject.toString());
        params.put("page", pagerNum);
        cb.url(url).type(JSONObject.class).params(params);
		aq.ajax(cb);
	}
}
