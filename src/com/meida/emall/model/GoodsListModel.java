package com.meida.emall.model;
//
//                       __
//                      /\ \   _
//    ____    ____   ___\ \ \_/ \           _____    ___     ___
//   / _  \  / __ \ / __ \ \    <     __   /\__  \  / __ \  / __ \
//  /\ \_\ \/\  __//\  __/\ \ \\ \   /\_\  \/_/  / /\ \_\ \/\ \_\ \
//  \ \____ \ \____\ \____\\ \_\\_\  \/_/   /\____\\ \____/\ \____/
//   \/____\ \/____/\/____/ \/_//_/         \/____/ \/___/  \/___/
//     /\____/
//     \/___/
//
//  Powered by BeeFramework
//

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.res.Resources;

import com.meida.emall.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;

import com.external.androidquery.callback.AjaxStatus;
import com.insthub.BeeFramework.model.BaseModel;
import com.insthub.BeeFramework.model.BeeCallback;
import com.meida.emall.protocol.DEFGOOD;
import com.meida.emall.protocol.FILT;
import com.meida.emall.protocol.FILTER;
import com.meida.emall.protocol.PAGINATED;
import com.meida.emall.protocol.PAGINATION;
import com.meida.emall.protocol.SESSION;
import com.meida.emall.protocol.SIMPLEGOODS;
import com.meida.emall.protocol.STATUS;

public class GoodsListModel extends BaseModel {

	public ArrayList<SIMPLEGOODS> simplegoodsList = new ArrayList<SIMPLEGOODS>();
	
	public ArrayList<DEFGOOD> goodsList = new ArrayList<DEFGOOD>();

    public static String PRICE_DESC = "price desc";
    public static String PRICE_ASC  = "price asc";
    public static String IS_HOT  = "sales desc";
//    public static String PRICE_DESC = "price_desc";
//    public static String PRICE_ASC  = "price_asc";
//    public static String IS_HOT  = "is_hot";

    public static final int PAGE_COUNT = 6;

	public GoodsListModel(Context context) 
	{
		super(context);	
	}
		
    public void fetchProductList(FILT filter)
    {
    	String url = ProtocolConst.B2CGOODSLIST;
    	
    	BeeCallback<JSONObject> cb = new BeeCallback<JSONObject>(){
    		
    		@Override
    		public void callback(String url, JSONObject jo, AjaxStatus status) {
    			GoodsListModel.this.callback(url, jo, status);
    			try 
    			{
    				STATUS responseStatus = STATUS.fromJson(jo.optJSONObject("status"));
//    				PAGINATED paginated = PAGINATED.fromJson(jo.optJSONObject("paginated"));
    				
    				if (responseStatus.succeed == 1) 
    				{												
    					JSONArray simpleJsonArray = jo.optJSONArray("data");
    					
    					goodsList.clear();
    					if (null != simpleJsonArray && simpleJsonArray.length() > 0) 
    					{
    						goodsList.clear();
    						for (int i = 0; i < simpleJsonArray.length(); i++) 
    						{
    							JSONObject simpleJsonObject = simpleJsonArray.getJSONObject(i);
    							DEFGOOD goods = DEFGOOD.fromJson(simpleJsonObject);
    							goodsList.add(goods);
    						}
    					}
    					
    					GoodsListModel.this.OnMessageResponse(url, jo, status);
    					
    				}
    				
    			} catch (JSONException e) {
    				// TODO: handle exception
    			}
    			
    		}
    		
    	};
    	
    	SESSION session = SESSION.getInstance();
    	
    	PAGINATION pagination = new PAGINATION();
    	pagination.page = 1;
    	pagination.count = PAGE_COUNT;
    	
    	JSONObject requestJsonObject = new JSONObject();
    	
    	Map<String, String> params = new HashMap<String, String>();
    	try 
    	{
//    		requestJsonObject.put("filter",filter.toJson());
//    		requestJsonObject.put("pagination",pagination.toJson());
    		requestJsonObject.put("session", session.toJson());
    	} catch (JSONException e) {
    		// TODO: handle exception
    	}
    	
    	params.put("json",requestJsonObject.toString());
    	
    	params.put("cate_id", filter.cate_id);
    	params.put("order", filter.order);
    	params.put("keyword", filter.keyword);
    	params.put("page", "1");
    	
    	cb.url(url).type(JSONObject.class).params(params);
    	ProgressDialog pd = new ProgressDialog(mContext);
    	
    	Resources resource = mContext.getResources();
    	String wait=resource.getString(R.string.hold_on);
    	pd.setMessage(wait);
    	aq.progress(pd).ajax(cb);
    	
    }
    
    public void fetchPreSearch(FILTER filter) {
		String url = ProtocolConst.SEARCH;
        
		BeeCallback<JSONObject> cb = new BeeCallback<JSONObject>(){
			
			@Override
			public void callback(String url, JSONObject jo, AjaxStatus status) {
				GoodsListModel.this.callback(url, jo, status);
				try 
				{
					STATUS responseStatus = STATUS.fromJson(jo.optJSONObject("status"));
                    PAGINATED paginated = PAGINATED.fromJson(jo.optJSONObject("paginated"));
					
					if (responseStatus.succeed == 1) 
					{												
						JSONArray simpleGoodsJsonArray = jo.optJSONArray("data");
							
						simplegoodsList.clear();
						if (null != simpleGoodsJsonArray && simpleGoodsJsonArray.length() > 0) 
						{
							simplegoodsList.clear();
							for (int i = 0; i < simpleGoodsJsonArray.length(); i++) 
							{
								JSONObject simpleGoodsJsonObject = simpleGoodsJsonArray.getJSONObject(i);
								SIMPLEGOODS simplegoods = SIMPLEGOODS.fromJson(simpleGoodsJsonObject);
								simplegoodsList.add(simplegoods);
							}
						}
						
						GoodsListModel.this.OnMessageResponse(url, jo, status);
						
					}
					
				} catch (JSONException e) {
					// TODO: handle exception
				}
				
			}
			
		};

		SESSION session = SESSION.getInstance();

		PAGINATION pagination = new PAGINATION();
		pagination.page = 1;
		pagination.count = PAGE_COUNT;
		
		JSONObject requestJsonObject = new JSONObject();

		Map<String, String> params = new HashMap<String, String>();
		try 
		{
            requestJsonObject.put("filter",filter.toJson());
            requestJsonObject.put("pagination",pagination.toJson());
            requestJsonObject.put("session", session.toJson());
		} catch (JSONException e) {
			// TODO: handle exception
		}

        params.put("json",requestJsonObject.toString());
		
		cb.url(url).type(JSONObject.class).params(params);
		ProgressDialog pd = new ProgressDialog(mContext);

        Resources resource = mContext.getResources();
        String wait=resource.getString(R.string.hold_on);
        pd.setMessage(wait);
	    aq.progress(pd).ajax(cb);

    }
    
    public void fetchPreSearchMore(FILTER filter)
    {
		String url = ProtocolConst.SEARCH;
        
		BeeCallback<JSONObject> cb = new BeeCallback<JSONObject>(){
			
			@Override
			public void callback(String url, JSONObject jo, AjaxStatus status) {
				GoodsListModel.this.callback(url, jo, status);
				try 
				{
					STATUS responseStatus = STATUS.fromJson(jo.optJSONObject("status"));
                    PAGINATED paginated = PAGINATED.fromJson(jo.optJSONObject("paginated"));
					
					if (responseStatus.succeed == 1) 
					{												
						JSONArray simpleGoodsJsonArray = jo.optJSONArray("data");
							
						if (null != simpleGoodsJsonArray && simpleGoodsJsonArray.length() > 0) 
						{
							for (int i = 0; i < simpleGoodsJsonArray.length(); i++) 
							{
								JSONObject simpleGoodsJsonObject = simpleGoodsJsonArray.getJSONObject(i);
								SIMPLEGOODS simplegoods = SIMPLEGOODS.fromJson(simpleGoodsJsonObject);
								simplegoodsList.add(simplegoods);
							}
						}
						
						GoodsListModel.this.OnMessageResponse(url, jo, status);
						
					}
					
				} catch (JSONException e) {
					// TODO: handle exception
				}
				
			}
			
		};

		PAGINATION pagination = new PAGINATION();
		pagination.page = (int)Math.ceil((double)simplegoodsList.size()*1.0/PAGE_COUNT)+1;
		pagination.count = PAGE_COUNT;
		
		JSONObject requestJsonObject = new JSONObject();

		Map<String, String> params = new HashMap<String, String>();
		try 
		{
            requestJsonObject.put("filter",filter.toJson());
            requestJsonObject.put("pagination",pagination.toJson());
		} catch (JSONException e) {
			// TODO: handle exception
		}

        params.put("json",requestJsonObject.toString());
		
		cb.url(url).type(JSONObject.class).params(params);
        aq.ajax(cb);

    }

}
