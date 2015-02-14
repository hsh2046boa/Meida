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
import com.meida.emall.protocol.ADDRESS;
import com.meida.emall.protocol.FAIRADD;
import com.meida.emall.protocol.GCATEGORYGOOD;
import com.meida.emall.protocol.SESSION;
import com.meida.emall.protocol.STATUS;

public class FairAddModel extends BaseModel {
	public ArrayList<FAIRADD> fairadd_list = new ArrayList<FAIRADD>();

	public FairAddModel(Context context) {
		super(context);
	}

	public void sendFairAdd(FAIRADD add) {
		String url = ProtocolConst.GoodsAdd;
		BeeCallback<JSONObject> cb = new BeeCallback<JSONObject>() {

			@Override
			public void callback(String url, JSONObject jo,
					AjaxStatus status) {
				// TODO Auto-generated method stub
				FairAddModel.this.callback(url, jo, status);
				try {
                    STATUS responseStatus = STATUS.fromJson(jo.optJSONObject("status"));
					if(responseStatus.succeed == 1) {
						FairAddModel.this.OnMessageResponse(url, jo, status);
					}
					
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
        params.put("category", add.category);
        params.put("goods_name", add.goods_name);
        params.put("brand", add.brand);
        params.put("label", add.label);
        params.put("inventory", add.inventory);
        params.put("price", add.price);
        params.put("freight", add.freight);
        params.put("location", add.location);
        params.put("no", add.no);
        params.put("description", add.description);
        
		cb.url(url).type(JSONObject.class).params(params);
		aq.ajax(cb);
		
	}
	
	public void sendFairAddPic(String goodid,String imagepath){
		String url = ProtocolConst.GoodsAddImage;
		BeeCallback<JSONObject> cb = new BeeCallback<JSONObject>() {

			@Override
			public void callback(String url, JSONObject jo,
					AjaxStatus status) {
				// TODO Auto-generated method stub
				FairAddModel.this.callback(url, jo, status);
				try {
                    STATUS responseStatus = STATUS.fromJson(jo.optJSONObject("status"));
					if(responseStatus.succeed == 1) {
						FairAddModel.this.OnMessageResponse(url, jo, status);
					}
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
        params.put("goods_id", goodid);
        params.put("pic[0]", imagepath);
        
		cb.url(url).type(JSONObject.class).params(params);
		aq.ajax(cb);
	}
	
	public void getFairOne(String goods_id){
		String url = ProtocolConst.GoodsGetOne;
		BeeCallback<JSONObject> cb = new BeeCallback<JSONObject>() {

			@Override
			public void callback(String url, JSONObject jo,
					AjaxStatus status) {
				// TODO Auto-generated method stub
				FairAddModel.this.callback(url, jo, status);
				try {
                    STATUS responseStatus = STATUS.fromJson(jo.optJSONObject("status"));
					if(responseStatus.succeed == 1) {

						JSONArray dataJsonArray = jo.optJSONArray("data");
                        if (null != dataJsonArray && dataJsonArray.length() > 0) {
                        	fairadd_list.clear();
                            for (int i = 0; i < dataJsonArray.length(); i++) {
                                JSONObject addressJsonObject = dataJsonArray.getJSONObject(i);
                                FAIRADD addressItem = FAIRADD.fromJson(addressJsonObject);
                                fairadd_list.add(addressItem);
                                
                            }
                        }
					
						FairAddModel.this.OnMessageResponse(url, jo, status);
					}
					
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
        params.put("goods_id", goods_id);
        
        cb.url(url).type(JSONObject.class).params(params);
		aq.ajax(cb);
	}

}
