package com.meida.emall.protocol;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.external.activeandroid.Model;

public class GCATEGORYGOOD extends Model {
	public int cate_id;
	public String store_id;
	public String cate_name;
	public String cate_code;
	public String parent_id;
	public String sort_order;
	public String if_show;

	public static GCATEGORYGOOD fromJson(JSONObject obj) throws JSONException {
		if (obj == null) {
			return null;
		}
		GCATEGORYGOOD good = new GCATEGORYGOOD();
		good.cate_id = obj.optInt("cate_id");
		good.store_id = obj.optString("store_id");
		good.cate_name = obj.optString("cate_name");
		good.cate_code = obj.optString("cate_code");
		good.parent_id = obj.optString("parent_id");
		good.sort_order = obj.optString("sort_order");
		good.if_show = obj.optString("if_show");

		return good;
	}
	
	public JSONObject toJson()throws JSONException{
		JSONObject obj = new JSONObject();
		obj.put("cate_id", cate_id);
		obj.put("store_id", store_id);
		obj.put("cate_name", cate_name);
		obj.put("cate_code", cate_code);
		obj.put("parent_id", parent_id);
		obj.put("sort_order", sort_order);
		obj.put("if_show", if_show);
		
		return obj;
	}
}
