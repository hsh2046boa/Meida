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

import android.app.ProgressDialog;

import com.insthub.BeeFramework.model.BeeQuery;
import com.insthub.BeeFramework.model.BaseModel;
import com.insthub.BeeFramework.model.BeeCallback;
import com.meida.emall.R;
import com.meida.emall.protocol.*;

import android.content.Context;
import android.os.Environment;
import android.os.Message;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.external.androidquery.callback.AjaxStatus;


public class HomeModel extends BaseModel
{
	public ArrayList<SIMPLEGOODS> simplegoodsList = new ArrayList<SIMPLEGOODS>();
	public ArrayList<CATEGORYGOODS> categorygoodsList = new ArrayList<CATEGORYGOODS>();
	public ArrayList<PLAYER> 	playersList = new ArrayList<PLAYER>();

    String pkName;
    
    public String rootpath;
	
    public HomeModel(Context context )
    {
        super(context);
        pkName = mContext.getPackageName();

        rootpath = context.getCacheDir()+"/Emall/cache" ;
        
//        readHomeDataCache();
//        readGoodsDataCache();
    }
    
    public void readHomeDataCache() {

		String path = rootpath+"/"+pkName+"/homeData.dat";
		File f1 = new File(path);
		if(f1.exists()) {
			try {
				InputStream is = new FileInputStream(f1);
				InputStreamReader input = new InputStreamReader(is, "UTF-8");
				BufferedReader bf = new BufferedReader(input);
				
				homeDataCache(bf.readLine());
				
				bf.close();
				input.close();
				is.close();
				
			} catch (FileNotFoundException e) {
				 
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				 
				e.printStackTrace();
			} catch (IOException e) {
				 
				e.printStackTrace();
			}
		}
		
    }
    
    public String homeDataCache() {
		String path = rootpath+"/"+pkName+"/homeData.dat";
		File f1 = new File(path);
		String s = null ;
		if(f1.exists()) {
			try {
				InputStream is = new FileInputStream(f1);
				InputStreamReader input = new InputStreamReader(is, "UTF-8");
				BufferedReader bf = new BufferedReader(input);
				
				s = bf.readLine();
				
				bf.close();
				input.close();
				is.close();
				
			} catch (FileNotFoundException e) {
				 
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				 
				e.printStackTrace();
			} catch (IOException e) {
				 
				e.printStackTrace();
			}
		}
		
		return s;
    }
    
    public void readGoodsDataCache() {
		String path = rootpath+"/"+pkName+"/goodsData.dat";
		File f1 = new File(path);
		if(f1.exists()) {
			try {
				InputStream is = new FileInputStream(f1);
				InputStreamReader input = new InputStreamReader(is, "UTF-8");
				BufferedReader bf = new BufferedReader(input);
				
				goodsDataCache(bf.readLine());
				
				bf.close();
				input.close();
				is.close();
				
			} catch (FileNotFoundException e) {
				 
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				 
				e.printStackTrace();
			} catch (IOException e) {
				 
				e.printStackTrace();
			}
		}
		
    }
    

    
    public void homeDataCache(String result) {
    	
    	try {
			if (result != null) {
				JSONObject jsonObject = new JSONObject(result);
				
				STATUS responseStatus = STATUS.fromJson(jsonObject.optJSONObject("status"));
				if (responseStatus.succeed == 1) 
				{
					JSONObject dataJsonObject = jsonObject.optJSONObject("data");
					if (null != dataJsonObject) 
					{
						JSONArray playerJSONArray = dataJsonObject.optJSONArray("player");
						if (null != playerJSONArray && playerJSONArray.length() > 0) 
						{
							playersList.clear();
							for (int i = 0; i < playerJSONArray.length(); i++) 
							{
								JSONObject playerJsonObject = playerJSONArray.getJSONObject(i);
								PLAYER playItem = PLAYER.fromJson(playerJsonObject);
								playersList.add(playItem);
							}
						}
						
//						JSONArray simpleGoodsJsonArray = dataJsonObject.optJSONArray("promote_goods");
//						
//						if (null != simpleGoodsJsonArray && simpleGoodsJsonArray.length() > 0) 
//						{
//							simplegoodsList.clear();
//							for (int i = 0; i < simpleGoodsJsonArray.length(); i++) 
//							{
//								JSONObject simpleGoodsJsonObject = simpleGoodsJsonArray.getJSONObject(i);
//								SIMPLEGOODS simplegoods = SIMPLEGOODS.fromJson(simpleGoodsJsonObject);
//								simplegoodsList.add(simplegoods);
//							}
//						}
						
					}
				
				}
				
			}

		} catch (Exception e) {
			 
			e.printStackTrace();
		}
    	
    }
    
    public void goodsDataCache(String result) {
    	
    	try {
			if (result != null) {
				JSONObject jsonObject = new JSONObject(result);
				
				STATUS responseStatus = STATUS.fromJson(jsonObject.optJSONObject("status"));
				if (responseStatus.succeed == 1) {
					JSONArray categorygoodJsonArray = jsonObject.optJSONArray("data");
					if (null != categorygoodJsonArray && categorygoodJsonArray.length() > 0) 
					{														
						categorygoodsList.clear();
						for (int i = 0; i < categorygoodJsonArray.length(); i++) 
						{
							JSONObject categoryGoodsJsonObject = categorygoodJsonArray.getJSONObject(i);
							CATEGORYGOODS simplegoods = CATEGORYGOODS.fromJson(categoryGoodsJsonObject);
							categorygoodsList.add(simplegoods);
						}													
						
					}
				}
				
			}

		} catch (Exception e) {
			 
			e.printStackTrace();
		}
    	
    }
    

    
    
    public void fetchHotSelling()
    {
		String url = ProtocolConst.HOMEDATA;
        
		BeeCallback<JSONObject> cb = new BeeCallback<JSONObject>(){
			
			@Override
			public void callback(String url, JSONObject jo, AjaxStatus status) {
				
				try 
				{
					STATUS responseStatus = STATUS.fromJson(jo.optJSONObject("status"));
					if (responseStatus.succeed == 1) 
					{
						fileSave(jo.toString(), "homeData");
						
						JSONArray dataJsonArray = jo.optJSONArray("data");
						if (dataJsonArray != null) {
							playersList.clear();
							for (int i = 0; i < dataJsonArray.length(); i++) {
								JSONObject playerJson = dataJsonArray.getJSONObject(i);
								PLAYER item = PLAYER.fromJson(playerJson);
								playersList.add(item);
							}
							
//							JSONArray simpleGoodsJsonArray = dataJsonObject.optJSONArray("promote_goods");
//							
//							if (null != simpleGoodsJsonArray && simpleGoodsJsonArray.length() > 0) 
//							{
//								simplegoodsList.clear();
//								for (int i = 0; i < simpleGoodsJsonArray.length(); i++) 
//								{
//									JSONObject simpleGoodsJsonObject = simpleGoodsJsonArray.getJSONObject(i);
//									SIMPLEGOODS simplegoods = SIMPLEGOODS.fromJson(simpleGoodsJsonObject);
//									simplegoodsList.add(simplegoods);
//								}
//							}
//                            else
//                            {
//                                simplegoodsList.clear();
//                            }
//							
							HomeModel.this.OnMessageResponse(url, jo, status);
							
						}												
					}
					
				} catch (JSONException e) {					
				}
				
			}
			
		};
		
		SESSION session = SESSION.getInstance();
		 
		JSONObject requestJsonObject = new JSONObject();

		Map<String, String> params = new HashMap<String, String>();
		try 
		{
            requestJsonObject.put("session",session.toJson());
		} catch (JSONException e) {
			
		}

        params.put("json",requestJsonObject.toString());
		
		cb.url(url).type(JSONObject.class).params(params);

        aq.ajax(cb);

    }
    
    public void fetchCategoryGoods()
    {
		String url = ProtocolConst.CATEGORYGOODS;
        
		BeeCallback<JSONObject> cb = new BeeCallback<JSONObject>(){
			
			@Override
			public void callback(String url, JSONObject jo, AjaxStatus status) {
				done(url, jo, status);				
				try 
				{
					STATUS responseStatus = STATUS.fromJson(jo.optJSONObject("status"));
					if (responseStatus.succeed == 1) 
					{
						fileSave(jo.toString(), "goodsData");
						JSONArray categorygoodJsonArray = jo.optJSONArray("data");
						if (null != categorygoodJsonArray && categorygoodJsonArray.length() > 0) 
						{														
							categorygoodsList.clear();
							for (int i = 0; i < categorygoodJsonArray.length(); i++) 
							{
								JSONObject categoryGoodsJsonObject = categorygoodJsonArray.getJSONObject(i);
								CATEGORYGOODS simplegoods = CATEGORYGOODS.fromJson(categoryGoodsJsonObject);
								categorygoodsList.add(simplegoods);
							}													
							HomeModel.this.OnMessageResponse(url, jo, status);
						}
                        else
                        {
                            categorygoodsList.clear();
                        }
						
						
					}
					
				} catch (JSONException e) {					
				}
				
			}
			
		};
		
		SESSION session = SESSION.getInstance();
		 
		JSONObject requestJsonObject = new JSONObject();

		Map<String, String> params = new HashMap<String, String>();
		try 
		{
            requestJsonObject.put("session",session.toJson());
		} catch (JSONException e) {
			
		}

        params.put("json",requestJsonObject.toString());
		
		cb.url(url).type(JSONObject.class).params(params);		
        aq.ajax(cb);               
                
    }



    protected void done(String url, JSONObject jo, AjaxStatus status)
    {
    	String localUrl = url;
    	JSONObject result = jo;
    }
    
    public String web;
	public void helpArticle(int article_id) {
		
		String url = ProtocolConst.ARTICLE;

		BeeCallback<JSONObject> cb = new BeeCallback<JSONObject>() {

			@Override
			public void callback(String url, JSONObject jo, AjaxStatus status) {

				HomeModel.this.callback(url, jo, status);
				
				try {
					STATUS responseStatus = STATUS.fromJson(jo.optJSONObject("status"));
					web = jo.getString("data").toString();
					
					HomeModel.this.OnMessageResponse(url, jo, status);
					
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
            requestJsonObject.put("article_id", article_id);
		} catch (JSONException e) {
			// TODO: handle exception
		}

        params.put("json",requestJsonObject.toString());
		
		cb.url(url).type(JSONObject.class).params(params);
		aq.ajax(cb);
		
	}
	
	// 缓存数据
	private PrintStream ps = null;
	public void fileSave(String result, String name) {

		String path = rootpath+"/"+pkName;

		File filePath = new File(path);
		if (!filePath.exists()) {
			filePath.mkdirs();
		}
		
		File file = new File(filePath+"/"+name+".dat");
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			ps = new PrintStream(fos);
			ps.print(result);
			ps.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
}
