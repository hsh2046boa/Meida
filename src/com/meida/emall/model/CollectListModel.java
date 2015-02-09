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

import com.meida.emall.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;

import com.external.androidquery.callback.AjaxStatus;
import com.insthub.BeeFramework.model.BaseModel;
import com.insthub.BeeFramework.model.BeeCallback;
import com.meida.emall.protocol.COLLECT_LIST;
import com.meida.emall.protocol.FAVOR;
import com.meida.emall.protocol.PAGINATED;
import com.meida.emall.protocol.PAGINATION;
import com.meida.emall.protocol.SESSION;
import com.meida.emall.protocol.STATUS;

public class CollectListModel extends BaseModel {

	public ArrayList<COLLECT_LIST> collectList = new ArrayList<COLLECT_LIST>();
	public ArrayList<FAVOR> favorList = new ArrayList<FAVOR>();
	public PAGINATED paginated;
	public CollectListModel(Context context) {
		super(context);
		 
	}
	
	public void getCollectList() {
		String url = ProtocolConst.COLLECT_LIST;

		BeeCallback<JSONObject> cb = new BeeCallback<JSONObject>() {

			@Override
			public void callback(String url, JSONObject jo, AjaxStatus status) {

				CollectListModel.this.callback(url, jo, status);
				
				try {
					STATUS responseStatus = STATUS.fromJson(jo.optJSONObject("status"));
					
					if(responseStatus.succeed == 1) {
						JSONArray dataJsonArray = jo.optJSONArray("data");
						favorList.clear();
                        if (null != dataJsonArray && dataJsonArray.length() > 0) {
                        	favorList.clear();
                            for (int i = 0; i < dataJsonArray.length(); i++) {
                                JSONObject collectJsonObject = dataJsonArray.getJSONObject(i);
                                FAVOR collectItem = FAVOR.fromJson(collectJsonObject);
                                favorList.add(collectItem);
                            }
                        }
//                        paginated = PAGINATED.fromJson(jo.optJSONObject("paginated"));
                        CollectListModel.this.OnMessageResponse(url, jo, status);
					}
					
				} catch (JSONException e) {
					 
					e.printStackTrace();
				}
				
			}

		};

		SESSION session = SESSION.getInstance();
//		PAGINATION pagination = new PAGINATION();
//		pagination.page = 1;
//		pagination.count = 10;
		 
		JSONObject requestJsonObject = new JSONObject();

		Map<String, String> params = new HashMap<String, String>();
		try 
		{
            requestJsonObject.put("session",session.toJson());
//            requestJsonObject.put("pagination",pagination.toJson());
//            requestJsonObject.put("rec_id", 0);
		} catch (JSONException e) {
			// TODO: handle exception
		}

        params.put("json",requestJsonObject.toString());
		
		cb.url(url).type(JSONObject.class).params(params);
		ProgressDialog pd = new ProgressDialog(mContext);
        pd.setMessage(mContext.getResources().getString(R.string.hold_on));
		aq.progress(pd).ajax(cb);
		
	}
	
	
	public void getCollectListMore() {
		String url = ProtocolConst.COLLECT_LIST;

		BeeCallback<JSONObject> cb = new BeeCallback<JSONObject>() {

			@Override
			public void callback(String url, JSONObject jo, AjaxStatus status) {

				CollectListModel.this.callback(url, jo, status);
				
				try {
					STATUS responseStatus = STATUS.fromJson(jo.optJSONObject("status"));
					
					if(responseStatus.succeed == 1) {
						JSONArray dataJsonArray = jo.optJSONArray("data");
                        if (null != dataJsonArray && dataJsonArray.length() > 0) {
                            for (int i = 0; i < dataJsonArray.length(); i++) {
                                JSONObject collectJsonObject = dataJsonArray.getJSONObject(i);
                                COLLECT_LIST collectItem = COLLECT_LIST.fromJson(collectJsonObject);
                                collectList.add(collectItem);
                            }
                        }
                        paginated = PAGINATED.fromJson(jo.optJSONObject("paginated"));
                        CollectListModel.this.OnMessageResponse(url, jo, status);
					}
					
				} catch (JSONException e) {
					 
					e.printStackTrace();
				}
				
			}

		};

		SESSION session = SESSION.getInstance();
		PAGINATION pagination = new PAGINATION();
		pagination.page = 1;
		pagination.count = 10;
		 
		JSONObject requestJsonObject = new JSONObject();

		Map<String, String> params = new HashMap<String, String>();
		try 
		{
            requestJsonObject.put("session",session.toJson());
            requestJsonObject.put("pagination",pagination.toJson());
            requestJsonObject.put("rec_id", collectList.get(collectList.size()-1).rec_id);
		} catch (JSONException e) {
			// TODO: handle exception
		}

        params.put("json",requestJsonObject.toString());
		
		cb.url(url).type(JSONObject.class).params(params);
		aq.ajax(cb);
		
	}
	
	
	// 删除收藏商品
	public void collectDelete(String rec_id) {
		String url = ProtocolConst.COLLECT_DELETE;

		BeeCallback<JSONObject> cb = new BeeCallback<JSONObject>() {

			@Override
			public void callback(String url, JSONObject jo, AjaxStatus status) {

				CollectListModel.this.callback(url, jo, status);
					
				try {
					STATUS responseStatus = STATUS.fromJson(jo.optJSONObject("status"));
					if(responseStatus.succeed == 1) {
						CollectListModel.this.OnMessageResponse(url, jo, status);
					}
						
				} catch (JSONException e) {
					 
					e.printStackTrace();
				}
			}

		};

		SESSION session = SESSION.getInstance();
			 
		JSONObject requestJsonObject = new JSONObject();

		Map<String, String> params = new HashMap<String, String>();
		try 
		{
	        requestJsonObject.put("session",session.toJson());
	        requestJsonObject.put("rec_id",rec_id);
		} catch (JSONException e) {
			// TODO: handle exception
		}

	    params.put("json",requestJsonObject.toString());
	    cb.url(url).type(JSONObject.class).params(params);
	    ProgressDialog pd = new ProgressDialog(mContext);
        pd.setMessage(mContext.getResources().getString(R.string.hold_on));
		aq.progress(pd).ajax(cb);
			
	}

}
