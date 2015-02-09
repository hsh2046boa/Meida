package com.meida.emall.protocol;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.external.activeandroid.Model;
import com.external.activeandroid.annotation.Column;
import com.external.activeandroid.annotation.Table;

@Table(name = "SPEC")
public class SPEC extends Model {

	@Column(name = "spec_id")
    public int id;
	
	@Column(name = "goods_id")
	public int goods_id;

    @Column(name = "price")
    public String price;

    @Column(name = "stock")
    public String stock;
    
    @Column(name = "color_rgb")
    public String color_rgb;
    
    @Column(name = "sku")
    public String sku;
    
    @Column(name = "spec_1")
    public String spec_1;
    
    @Column(name = "spec_2")
    public String spec_2;

public static SPEC fromJson(JSONObject jsonObject)  throws JSONException
{
    if(null == jsonObject){
      return null;
     }

    SPEC   localItem = new SPEC();

    JSONArray subItemArray;

    localItem.id = jsonObject.optInt("spec_id");
    
    localItem.goods_id = jsonObject.optInt("goods_id"); 

    localItem.price = jsonObject.optString("price");

    localItem.stock = jsonObject.optString("stock");
    
    localItem.color_rgb = jsonObject.optString("color_rgb");
    
    localItem.sku = jsonObject.optString("sku");
    
    localItem.spec_1 = jsonObject.optString("spec_1");
    
    localItem.spec_2 = jsonObject.optString("spec_2");
    return localItem;
}

public JSONObject  toJson() throws JSONException 
{
    JSONObject localItemObject = new JSONObject();
    JSONArray itemJSONArray = new JSONArray();
    localItemObject.put("spec_id", id);
    localItemObject.put("goods_id", goods_id);
    localItemObject.put("price", price);
    localItemObject.put("stock", stock);
    localItemObject.put("color_rgb", color_rgb);
    localItemObject.put("sku", sku);
    localItemObject.put("spec_1", spec_1);
    localItemObject.put("spec_2", spec_2);
    return localItemObject;
}
}
