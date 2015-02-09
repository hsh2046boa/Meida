package com.meida.emall.protocol;

import org.json.JSONObject;

import com.external.activeandroid.Model;
import com.external.activeandroid.annotation.Column;
import com.external.activeandroid.annotation.Table;
@Table(name = "DEFGOOD")
public class DEFGOOD extends Model {

	@Column(name = "default_image")
	public String default_image;
	
	@Column(name = "goods_name")
	public String goods_name;
	
	@Column(name = "price")
	public String price;
	
	@Column(name = "goods_id")
	public String goods_id;
	
	public static DEFGOOD fromJson(JSONObject jsonObject){
		if(null == jsonObject){
		       return null;
		      }
		DEFGOOD localItem = new DEFGOOD();
		localItem.default_image = jsonObject.optString("default_image");
		localItem.goods_id = jsonObject.optString("goods_id");
		localItem.goods_name = jsonObject.optString("goods_name");
		localItem.price = jsonObject.optString("price");
		return localItem;
	}
	
}
