package com.meida.emall.protocol;

import org.json.JSONException;
import org.json.JSONObject;

import com.external.activeandroid.Model;

public class SELLERORDERS extends Model {
	public int order_id;
	public String order_sn;
	public String order_amount;
	public String add_time;
	public String status;
	public String evaluation_status;

	public static SELLERORDERS fromJson(JSONObject obj) throws JSONException {
		if (obj == null) {
			return null;
		}
        SELLERORDERS seller = new SELLERORDERS();
        seller.order_id = obj.optInt("order_id");
        seller.order_sn = obj.optString("order_sn");
        seller.order_amount = obj.optString("order_amount");
        seller.add_time = obj.optString("add_time");
        seller.status = obj.optString("status");
        seller.evaluation_status = obj.optString("evaluation_status");
        
        return seller;
	}
	
	public JSONObject toJson()throws JSONException{
		JSONObject obj = new JSONObject();
		obj.put("order_id", order_id);
		obj.put("order_sn", order_sn);
		obj.put("order_amount", order_amount);
		obj.put("add_time", add_time);
		obj.put("status", status);
		obj.put("evaluation_status", evaluation_status);
		
		return obj;
	}
}
