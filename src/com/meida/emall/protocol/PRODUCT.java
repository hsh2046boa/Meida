package com.meida.emall.protocol;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.external.activeandroid.Model;
import com.external.activeandroid.annotation.Column;
import com.external.activeandroid.annotation.Table;

@Table(name = "PRODUCT")
public class PRODUCT extends Model {

	@Column(name = "description")
    public String description;

    @Column(name = "default_image")
    public String default_image;

    @Column(name = "goods_number")
    public String goods_number;

    public ArrayList<SPEC>   spec = new ArrayList<SPEC>();

    @Column(name = "store_id")
    public String store_id;

    @Column(name = "goods_name")
    public String goods_name;

    @Column(name = "store_name")
    public String store_name;

    @Column(name = "GOODS_id")
    public int id;

   @Column(name="collected")
   public int collected;

public static PRODUCT fromJson(JSONObject jsonObject)  throws JSONException
{
    if(null == jsonObject){
      return null;
     }

    PRODUCT   localItem = new PRODUCT();

    JSONArray subItemArray;

    localItem.description = jsonObject.optString("description");

    localItem.default_image = jsonObject.optString("default_image");

    localItem.goods_number = jsonObject.optString("goods_number");

    subItemArray = jsonObject.optJSONArray("_specs");
    if(null != subItemArray)
     {
        for(int i = 0;i < subItemArray.length();i++)
         {
            JSONObject subItemObject = subItemArray.getJSONObject(i);
            SPEC subItem = SPEC.fromJson(subItemObject);
            localItem.spec.add(subItem);
        }
    }

    localItem.store_id = jsonObject.optString("store_id");

    localItem.goods_name = jsonObject.optString("goods_name");

    localItem.store_name = jsonObject.optString("store_name");

    localItem.id = jsonObject.optInt("goods_id");

    localItem.collected=jsonObject.optInt("collected");

    return localItem;
}

public JSONObject  toJson() throws JSONException 
{
    JSONObject localItemObject = new JSONObject();
    JSONArray itemJSONArray = new JSONArray();
    localItemObject.put("description", description);
    localItemObject.put("default_image", default_image);
    localItemObject.put("goods_number", goods_number);

    for(int i =0; i< spec.size(); i++)
    {
        SPEC itemData =spec.get(i);
        JSONObject itemJSONObject = itemData.toJson();
        itemJSONArray.put(itemJSONObject);
    }
    localItemObject.put("_specs", itemJSONArray);
    localItemObject.put("store_id", store_id);
    localItemObject.put("goods_name", goods_name);
    localItemObject.put("store_name", store_name);
    localItemObject.put("goods_id", id);
    localItemObject.put("collected",collected);

    localItemObject.put("specification", itemJSONArray);
    return localItemObject;
}
}
