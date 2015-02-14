package com.meida.emall.protocol;

import org.json.JSONException;
import org.json.JSONObject;

import com.external.activeandroid.Model;
import com.external.activeandroid.annotation.Column;
import com.external.activeandroid.annotation.Table;

@Table(name="fairadd")
public class FAIRADD extends Model {
	@Column(name = "goods_id")
	public int goods_id;
	
	@Column(name = "category")
	public String category;
	
	@Column(name = "goods_name")
	public String goods_name;
	
	@Column(name = "brand")
	public String brand;
	
	@Column(name = "label")
	public String label;
	
	@Column(name = "inventory")
	public String inventory;
	
	@Column(name = "price")
	public String price;
	
	@Column(name = "freight")
	public String freight;
	
	@Column(name = "location")
	public String location;
	
	@Column(name = "no")
	public String no;
	
	@Column(name = "description")
	public String description;
	
	@Column(name = "imagepath")
	public String imagepath;

	public static FAIRADD fromJson(JSONObject obj)throws JSONException{
		if(obj == null){
			return null;
		}
		
		FAIRADD add = new FAIRADD();
		add.goods_id = obj.optInt("goods_id");
		add.category = obj.optString("category");
		add.goods_name = obj.optString("goods_name");
		add.brand = obj.optString("brand");
		add.label = obj.optString("label");
		add.inventory = obj.optString("inventory");
		add.price = obj.optString("price");
		add.freight = obj.optString("freight");
		add.location = obj.optString("location");
		add.no = obj.optString("no");
		add.description = obj.optString("description");
		add.imagepath = obj.optString("imagepath");
		
		
		return add;
	}
	
	public JSONObject toJson()throws JSONException{
		JSONObject obj = new JSONObject();
		obj.put("goods_id", goods_id);
		obj.put("category", category);
		obj.put("goods_name", goods_name);
		obj.put("brand", brand);
		obj.put("label", label);
		obj.put("inventory", inventory);
		obj.put("price", price);
		obj.put("freight", freight);
		obj.put("location", location);
		obj.put("no", no);
		obj.put("description", description);
		obj.put("imagepath", imagepath);
		
		return obj;
	}

}
