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
import com.meida.emall.protocol.ADDRESS;
import com.meida.emall.protocol.GCATEGORYGOOD;
import com.meida.emall.protocol.SESSION;
import com.meida.emall.protocol.STATUS;

public class GcategorygoodModel extends BaseModel {

	public ArrayList<GCATEGORYGOOD> gcategory_list = new ArrayList<GCATEGORYGOOD>();
	
	public GcategorygoodModel(Context context){
		super(context);
	}
	
	public void getGoodsCategory(String pid){
		String url = ProtocolConst.GcategoryGood;
		BeeCallback<JSONObject> cb = new BeeCallback<JSONObject>(){

			@Override
			public void callback(String url, JSONObject jo,
					AjaxStatus status) {
				// TODO Auto-generated method stub
				GcategorygoodModel.this.callback(url, jo, status);
				try {
					STATUS responseStatus = STATUS.fromJson(jo.optJSONObject("status"));
					
					if(responseStatus.succeed == 1) {
						JSONArray dataJsonArray = jo.optJSONArray("data");
                        if (null != dataJsonArray && dataJsonArray.length() > 0) {
                        	gcategory_list.clear();
                            for (int i = 0; i < dataJsonArray.length(); i++) {
                                JSONObject addressJsonObject = dataJsonArray.getJSONObject(i);
                                GCATEGORYGOOD addressItem = GCATEGORYGOOD.fromJson(addressJsonObject);
                                //addressItem.save();
                                gcategory_list.add(addressItem);
                                
                            }
                        }
                        GcategorygoodModel.this.OnMessageResponse(url, jo, status);
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
        params.put("pid", pid);
        
		cb.url(url).type(JSONObject.class).params(params);
		aq.ajax(cb);
	}
	
	
}
