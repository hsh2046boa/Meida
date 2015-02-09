package com.meida.emall.protocol;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.external.activeandroid.Model;
import com.external.activeandroid.annotation.Column;
import com.external.activeandroid.annotation.Table;

@Table(name = "COMMENT")
public class COMMENT extends Model {

	@Column(name = "comment")
    public String comment;

	@Column(name = "rec_id")
	public String rec_id;
	
    @Column(name = "buyer_id")
    public String buyer_id;

    @Column(name = "buyer_name")
    public String buyer_name;

    @Column(name = "evaluation")
    public String evaluation;

    @Column(name = "evaluation_time")
    public String evaluation_time;

public static COMMENT fromJson(JSONObject jsonObject)  throws JSONException
{
    if(null == jsonObject){
      return null;
     }

    COMMENT   localItem = new COMMENT();

    JSONArray subItemArray;

    localItem.comment = jsonObject.optString("comment");

    localItem.rec_id = jsonObject.optString("rec_id");
    
    localItem.buyer_id = jsonObject.optString("buyer_id");

    localItem.buyer_name = jsonObject.optString("buyer_name");

    localItem.evaluation = jsonObject.optString("evaluation");

    localItem.evaluation_time = jsonObject.optString("evaluation_time");
    return localItem;
}

public JSONObject  toJson() throws JSONException 
{
    JSONObject localItemObject = new JSONObject();
    JSONArray itemJSONArray = new JSONArray();
    localItemObject.put("comment", comment);
    localItemObject.put("rec_id", rec_id); 
    localItemObject.put("buyer_id", buyer_id); 
    localItemObject.put("buyer_name", buyer_name);
    localItemObject.put("evaluation", evaluation);
    localItemObject.put("evaluation_time", evaluation_time);
    return localItemObject;
}
}
