package com.meida.emall.protocol;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.external.activeandroid.Model;
import com.external.activeandroid.annotation.Column;
import com.external.activeandroid.annotation.Table;

@Table(name = "FAVOR")
public class FAVOR extends Model {

	@Column(name = "price")
    public String price;

    @Column(name = "goods_name")
    public String goods_name;

    @Column(name = "goods_id")
    public int goods_id;

    @Column(name = "default_image")
    public String   default_image;

public static FAVOR fromJson(JSONObject jsonObject)  throws JSONException
{
    if(null == jsonObject){
      return null;
     }

    FAVOR   localItem = new FAVOR();

    JSONArray subItemArray;

    localItem.price = jsonObject.optString("price");

    localItem.goods_name = jsonObject.optString("goods_name");

    localItem.goods_id = jsonObject.optInt("goods_id");

    localItem.default_image = jsonObject.optString("default_image");

    return localItem;
}

public JSONObject  toJson() throws JSONException 
{
    JSONObject localItemObject = new JSONObject();
    JSONArray itemJSONArray = new JSONArray();
    localItemObject.put("price", price);
    localItemObject.put("goods_name", goods_name);
    localItemObject.put("goods_id", goods_id);
    localItemObject.put("default_image", default_image);
    return localItemObject;
}
}
