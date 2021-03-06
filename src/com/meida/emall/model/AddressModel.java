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
import com.meida.emall.protocol.ADDRESS;
import com.meida.emall.protocol.REGIONS;
import com.meida.emall.protocol.SESSION;
import com.meida.emall.protocol.SIMPLEGOODS;
import com.meida.emall.protocol.STATUS;

public class AddressModel extends BaseModel {

	public ArrayList<ADDRESS> addressList = new ArrayList<ADDRESS>();
	public ArrayList<REGIONS> regionsList0 = new ArrayList<REGIONS>();
	public ArrayList<REGIONS> regionsList1 = new ArrayList<REGIONS>();
	public ArrayList<REGIONS> regionsList2 = new ArrayList<REGIONS>();
	public ArrayList<REGIONS> regionsList3 = new ArrayList<REGIONS>();
	public AddressModel(Context context) {
		super(context);
		 
	}
	
	// 获取地址列表
	public void getAddressList() {
		String url = ProtocolConst.ADDRESS_LIST;

		BeeCallback<JSONObject> cb = new BeeCallback<JSONObject>() {

			@Override
			public void callback(String url, JSONObject jo, AjaxStatus status) {

				AddressModel.this.callback(url, jo, status);
				try {
					STATUS responseStatus = STATUS.fromJson(jo.optJSONObject("status"));
					
					if(responseStatus.succeed == 1) {
						JSONArray dataJsonArray = jo.optJSONArray("data");
                        if (null != dataJsonArray && dataJsonArray.length() > 0) {
                        	addressList.clear();
                            for (int i = 0; i < dataJsonArray.length(); i++) {
                                JSONObject addressJsonObject = dataJsonArray.getJSONObject(i);
                                ADDRESS addressItem = ADDRESS.fromJson(addressJsonObject);
                                //addressItem.save();
                                if(!addressItem.province_name.equals("null")) {
                                	addressList.add(addressItem);
                                }
                                
                            }
                        }
                        AddressModel.this.OnMessageResponse(url, jo, status);
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
		} catch (JSONException e) {
			// TODO: handle exception
		}

        params.put("json",requestJsonObject.toString());
		
		cb.url(url).type(JSONObject.class).params(params);
		ProgressDialog pd = new ProgressDialog(mContext);
        pd.setMessage(mContext.getResources().getString(R.string.hold_on));
		aq.progress(pd).ajax(cb);
		
	}
	
	// 添加地址
	public void addAddress(String consignee, String tel, String email, String mobile, String zipcode, String address, String country, String province, String city, String district) {
		String url = ProtocolConst.ADDRESS_ADD;

		BeeCallback<JSONObject> cb = new BeeCallback<JSONObject>() {

			@Override
			public void callback(String url, JSONObject jo, AjaxStatus status) {
				AddressModel.this.callback(url, jo, status);
				try {
					STATUS responseStatus = STATUS.fromJson(jo.optJSONObject("status"));
					if(responseStatus.succeed == 1) {
						JSONArray dataJsonArray = jo.optJSONArray("data");
                        if (null != dataJsonArray && dataJsonArray.length() > 0) {
                        	addressList.clear();
                            for (int i = 0; i < dataJsonArray.length(); i++) {
                                JSONObject addressJsonObject = dataJsonArray.getJSONObject(i);
                                ADDRESS addressItem = ADDRESS.fromJson(addressJsonObject);
                                addressList.add(addressItem);
                            }
                        }
                       
					}
					AddressModel.this.OnMessageResponse(url, jo, status);
					
				} catch (JSONException e) {
					 
					e.printStackTrace();
				}
			}

		};

		SESSION session = SESSION.getInstance();
		ADDRESS add = new ADDRESS();
		add.consignee = consignee;
		add.tel = tel;
		add.email = email;
		add.mobile = mobile;
		add.zipcode = zipcode;
		add.address = address;
		add.country = country;
		add.province = province;
		add.city = city;
		add.district = district;
		 
		JSONObject requestJsonObject = new JSONObject();

		Map<String, String> params = new HashMap<String, String>();
		try 
		{
            requestJsonObject.put("session",session.toJson());
            requestJsonObject.put("address",add.toJson());
		} catch (JSONException e) {
			// TODO: handle exception
		}

        params.put("json",requestJsonObject.toString());
		
		cb.url(url).type(JSONObject.class).params(params);
		ProgressDialog pd = new ProgressDialog(mContext);
        pd.setMessage(mContext.getResources().getString(R.string.hold_on));
		aq.progress(pd).ajax(cb);
		
	}
	
	// 获取地区城市
	public void region(String parent_id, final int i) {
		String url = ProtocolConst.REGION;

		BeeCallback<JSONObject> cb = new BeeCallback<JSONObject>() {

			@Override
			public void callback(String url, JSONObject jo, AjaxStatus status) {

				AddressModel.this.callback(url, jo, status);
				
				try {
					STATUS responseStatus = STATUS.fromJson(jo.optJSONObject("status"));
					
					if(responseStatus.succeed == 1) {
						
						JSONObject data = jo.optJSONObject("data");
						JSONArray regionsJsonArray = data.optJSONArray("regions");
						regionsList0.clear();
						if (null != regionsJsonArray && regionsJsonArray.length() > 0) {
							regionsList0.clear();
							for (int i = 0; i < regionsJsonArray.length(); i++) {
								JSONObject regionsJsonObject = regionsJsonArray.getJSONObject(i);
								REGIONS regions = REGIONS.fromJson(regionsJsonObject);
								regionsList0.add(regions);
							}
						}

						
//						if(i == 0) {
//							if (null != regionsJsonArray && regionsJsonArray.length() > 0) {
//								regionsList0.clear();
//								for (int i = 0; i < regionsJsonArray.length(); i++) {
//									JSONObject regionsJsonObject = regionsJsonArray.getJSONObject(i);
//									REGIONS regions = REGIONS.fromJson(regionsJsonObject);
//									regionsList0.add(regions);
//								}
//							}
//						} else if(i == 1) {
//							if (null != regionsJsonArray && regionsJsonArray.length() > 0) {
//								regionsList1.clear();
//								for (int i = 0; i < regionsJsonArray.length(); i++) {
//									JSONObject regionsJsonObject = regionsJsonArray.getJSONObject(i);
//									REGIONS regions = REGIONS.fromJson(regionsJsonObject);
//									regionsList1.add(regions);
//								}
//							}
//						} else if(i == 2) {
//							if (null != regionsJsonArray && regionsJsonArray.length() > 0) {
//								regionsList2.clear();
//								for (int i = 0; i < regionsJsonArray.length(); i++) {
//									JSONObject regionsJsonObject = regionsJsonArray.getJSONObject(i);
//									REGIONS regions = REGIONS.fromJson(regionsJsonObject);
//									regionsList2.add(regions);
//								}
//							}
//						} else if(i == 3) {
//							if (null != regionsJsonArray && regionsJsonArray.length() > 0) {
//								regionsList3.clear();
//								for (int i = 0; i < regionsJsonArray.length(); i++) {
//									JSONObject regionsJsonObject = regionsJsonArray.getJSONObject(i);
//									REGIONS regions = REGIONS.fromJson(regionsJsonObject);
//									regionsList3.add(regions);
//								}
//							}
//						}
						AddressModel.this.OnMessageResponse(url, jo, status);
					}
					
				} catch (JSONException e) {
					 
					e.printStackTrace();
				}
			}

		};

		Map<String, String> params = new HashMap<String, String>();
		SESSION session = SESSION.getInstance();
		JSONObject requestJsonObject = new JSONObject();
		try 
		{
            requestJsonObject.put("session",session.toJson());
		} catch (JSONException e) {
			// TODO: handle exception
		}
        params.put("json",requestJsonObject.toString());
		params.put("parent_id", parent_id);

		cb.url(url).type(JSONObject.class).params(params);
		aq.ajax(cb);

	}
	
	public ADDRESS address;
	// 获取地址详细信息
	public void getAddressInfo(String address_id) {
		String url = ProtocolConst.ADDRESS_INFO;

		BeeCallback<JSONObject> cb = new BeeCallback<JSONObject>() {

			@Override
			public void callback(String url, JSONObject jo, AjaxStatus status) {

				AddressModel.this.callback(url, jo, status);
				
				try {
					STATUS responseStatus = STATUS.fromJson(jo.optJSONObject("status"));
					if(responseStatus.succeed == 1) {
						address = ADDRESS.fromJson(jo.optJSONObject("data"));
                        
                        AddressModel.this.OnMessageResponse(url, jo, status);
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
            requestJsonObject.put("address_id",address_id);
		} catch (JSONException e) {
			// TODO: handle exception
		}

        params.put("json",requestJsonObject.toString());
		cb.url(url).type(JSONObject.class).params(params);
		ProgressDialog pd = new ProgressDialog(mContext);
        pd.setMessage(mContext.getResources().getString(R.string.hold_on));
		aq.progress(pd).ajax(cb);
		
	}
	
	// 设置默认地址
	public void addressDefault(String address_id) {
		String url = ProtocolConst.ADDRESS_DEFAULT;

		BeeCallback<JSONObject> cb = new BeeCallback<JSONObject>() {

			@Override
			public void callback(String url, JSONObject jo, AjaxStatus status) {

				AddressModel.this.callback(url, jo, status);
					
				try {
					STATUS responseStatus = STATUS.fromJson(jo.optJSONObject("status"));
					if(responseStatus.succeed == 1) {
	                    AddressModel.this.OnMessageResponse(url, jo, status);
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
	        requestJsonObject.put("address_id",address_id);
		} catch (JSONException e) {
			// TODO: handle exception
		}

	    params.put("json",requestJsonObject.toString());
	    cb.url(url).type(JSONObject.class).params(params);
	    ProgressDialog pd = new ProgressDialog(mContext);
        pd.setMessage(mContext.getResources().getString(R.string.hold_on));
		aq.progress(pd).ajax(cb);
			
	}
	
	// 删除地址
	public void addressDelete(String address_id) {
		String url = ProtocolConst.ADDRESS_DELETE;

		BeeCallback<JSONObject> cb = new BeeCallback<JSONObject>() {

			@Override
			public void callback(String url, JSONObject jo, AjaxStatus status) {

				AddressModel.this.callback(url, jo, status);
					
				try {
					STATUS responseStatus = STATUS.fromJson(jo.optJSONObject("status"));
					if(responseStatus.succeed == 1) {
	                    AddressModel.this.OnMessageResponse(url, jo, status);
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
	        requestJsonObject.put("address_id",address_id);
		} catch (JSONException e) {
			// TODO: handle exception
		}

	    params.put("json",requestJsonObject.toString());
	    cb.url(url).type(JSONObject.class).params(params);
	    ProgressDialog pd = new ProgressDialog(mContext);
        pd.setMessage(mContext.getResources().getString(R.string.hold_on));
		aq.progress(pd).ajax(cb);
			
	}
	
	// 修改地址
	public void addressUpdate(String address_id, String consignee, String tel, String email, String mobile, String zipcode, String address, String country, String province, String city, String district) {
		String url = ProtocolConst.ADDRESS_UPDATE;

		BeeCallback<JSONObject> cb = new BeeCallback<JSONObject>() {

			@Override
			public void callback(String url, JSONObject jo, AjaxStatus status) {
				AddressModel.this.callback(url, jo, status);
				try {
					STATUS responseStatus = STATUS.fromJson(jo.optJSONObject("status"));
					if(responseStatus.succeed == 1) {
						JSONArray dataJsonArray = jo.optJSONArray("data");
                        if (null != dataJsonArray && dataJsonArray.length() > 0) {
                        	addressList.clear();
                            for (int i = 0; i < dataJsonArray.length(); i++) {
                                JSONObject addressJsonObject = dataJsonArray.getJSONObject(i);
                                ADDRESS addressItem = ADDRESS.fromJson(addressJsonObject);
                                addressList.add(addressItem);
                            }
                        }
                        AddressModel.this.OnMessageResponse(url, jo, status);
					}
					
				} catch (JSONException e) {
					 
					e.printStackTrace();
				}
			}

		};

		SESSION session = SESSION.getInstance();
		ADDRESS add = new ADDRESS();
		add.consignee = consignee;
		add.tel = tel;
		add.email = email;
		add.mobile = mobile;
		add.zipcode = zipcode;
		add.address = address;
		add.country = country;
		add.province = province;
		add.city = city;
		add.district = district;
		 
		JSONObject requestJsonObject = new JSONObject();

		Map<String, String> params = new HashMap<String, String>();
		try 
		{
            requestJsonObject.put("session",session.toJson());
            requestJsonObject.put("address_id", address_id);
            requestJsonObject.put("address",add.toJson());
		} catch (JSONException e) {
			e.printStackTrace();
		}

        params.put("json",requestJsonObject.toString());
		
		cb.url(url).type(JSONObject.class).params(params);
		ProgressDialog pd = new ProgressDialog(mContext);
        pd.setMessage(mContext.getResources().getString(R.string.hold_on));
		aq.progress(pd).ajax(cb);
		
	}

}
