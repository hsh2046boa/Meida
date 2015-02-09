package com.meida.emall.protocol;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.external.activeandroid.Model;
import com.external.activeandroid.annotation.Column;
import com.external.activeandroid.annotation.Table;

@Table(name = "FILT")
public class FILT extends Model {

	@Column(name = "keyword")
    public String keyword;

    @Column(name = "order_by")
    public String order;

    @Column(name = "cate_id")
    public String cate_id;

//    @Column(name = "price_range") 

public static FILT fromJson(JSONObject jsonObject)  throws JSONException
{
    if(null == jsonObject){
      return null;
     }

    FILT   localItem = new FILT();

    localItem.keyword = jsonObject.optString("keyword");

    localItem.order = jsonObject.optString("order");

    localItem.cate_id = jsonObject.optString("cate_id");
    return localItem;
}

public JSONObject  toJson() throws JSONException 
{
    JSONObject localItemObject = new JSONObject();
    localItemObject.put("keyword", keyword);
    localItemObject.put("sort_by", order);
    localItemObject.put("cate_id", cate_id);

    return localItemObject;
}
}
