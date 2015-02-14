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
import com.meida.emall.protocol.MYFAIR;
import com.meida.emall.protocol.SELLERORDERS;
import com.meida.emall.protocol.SESSION;
import com.meida.emall.protocol.STATUS;

public class BuyerOrderModel extends BaseModel {
	public ArrayList<SELLERORDERS> order_list = new ArrayList<SELLERORDERS>();

	public BuyerOrderModel(Context context) {
		super(context);
	}

	public void getBuyerOrder(String pagerNum, String type) {
		String url = ProtocolConst.BuyerOrder;
		BeeCallback<JSONObject> cb = new BeeCallback<JSONObject>() {

			@Override
			public void callback(String url, JSONObject jo, AjaxStatus status) {
				// TODO Auto-generated method stub
				BuyerOrderModel.this.callback(url, jo, status);
				try {
					STATUS responseStatus = STATUS.fromJson(jo.optJSONObject("status"));
					if (responseStatus.succeed == 1) {
						JSONArray dataJsonArray = jo.optJSONArray("data");
						order_list.clear();
						if (null != dataJsonArray && dataJsonArray.length() > 0) {
							order_list.clear();
							for (int i = 0; i < dataJsonArray.length(); i++) {
								JSONObject orderJsonObject = dataJsonArray.getJSONObject(i);
								SELLERORDERS order_list_Item = SELLERORDERS.fromJson(orderJsonObject);
								order_list.add(order_list_Item);
							}
						}
					}
					BuyerOrderModel.this.OnMessageResponse(url, jo, status);
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
			// TODO: handle exception
		}
		params.put("json", requestJsonObject.toString());
		params.put("page", pagerNum);
		params.put("type", type);

		cb.url(url).type(JSONObject.class).params(params);
		aq.ajax(cb);
	}
	
	public void deleteOrderByID(int order_id){
		String url = ProtocolConst.CancleOrder;
		BeeCallback<JSONObject> cb = new BeeCallback<JSONObject>() {

			@Override
			public void callback(String url, JSONObject jo, AjaxStatus status) {
				// TODO Auto-generated method stub
				BuyerOrderModel.this.callback(url, jo, status);
				try {
					/*STATUS responseStatus = STATUS.fromJson(jo.optJSONObject("status"));
					if (responseStatus.succeed == 1) {
						
					}*/
					BuyerOrderModel.this.OnMessageResponse(url, jo, status);
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
			// TODO: handle exception
		}
		params.put("json", requestJsonObject.toString());
		params.put("order_id", order_id+"");

		cb.url(url).type(JSONObject.class).params(params);
		aq.ajax(cb);
	}
}
