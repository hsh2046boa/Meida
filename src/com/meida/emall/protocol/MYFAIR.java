package com.meida.emall.protocol;

import org.json.JSONException;
import org.json.JSONObject;

import com.external.activeandroid.Model;
import com.external.activeandroid.annotation.Table;


public class MYFAIR extends Model{
public int goods_id;
public String goods_name;
public String add_time;
public String default_image;
public String price;
public String stock;
public String seller_quantity;

public static MYFAIR fromJson(JSONObject obj) throws JSONException{
	if(obj == null){
		return null;
	}
	MYFAIR fair = new MYFAIR();
	fair.goods_id = obj.optInt("goods_id");
	fair.goods_name = obj.optString("goods_name");
	fair.add_time = obj.optString("add_time");
	fair.default_image = obj.optString("default_image");
	fair.price = obj.optString("price");
	fair.stock = obj.optString("stock");
	fair.seller_quantity = obj.optString("seller_quantity");
	
	return fair;
}

public JSONObject toJson() throws JSONException{
	JSONObject obj = new JSONObject();
	obj.put("goods_id", goods_id);
	obj.put("goods_name", goods_name);
	obj.put("add_time", add_time);
	obj.put("default_image", default_image);
	obj.put("price", price);
	obj.put("stock", stock);
	obj.put("seller_quantity", seller_quantity);
	
	return obj;
}

















}
