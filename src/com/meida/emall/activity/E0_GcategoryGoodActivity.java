package com.meida.emall.activity;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.external.androidquery.callback.AjaxStatus;
import com.insthub.BeeFramework.activity.BaseActivity;
import com.insthub.BeeFramework.model.BusinessResponse;
import com.meida.emall.R;
import com.meida.emall.adapter.E0_GcategoryAdapter;
import com.meida.emall.adapter.F3_RegionPickAdapter;
import com.meida.emall.model.AddressModel;
import com.meida.emall.model.GcategorygoodModel;
import com.meida.emall.model.ProtocolConst;

public class E0_GcategoryGoodActivity extends Activity implements BusinessResponse {
	private TextView title;
	private ListView listView;
	private GcategorygoodModel model;
	private E0_GcategoryAdapter mgcateAdapter;
	
	private int category_id = 0;
	private String category_name="";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.e0_gcategory);
		
		title = (TextView) findViewById(R.id.category_title);
		listView = (ListView) findViewById(R.id.category_list);
        Resources resource = (Resources) getBaseContext().getResources();
        String scoun=resource.getString(R.string.goods_category );
        title.setText(scoun);
        
        model = new GcategorygoodModel(this);
        model.addResponseListener(this);
        model.getGoodsCategory("0");
        
        listView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				category_id = model.gcategory_list.get(arg2).cate_id;
				category_name = model.gcategory_list.get(arg2).cate_name;
				
			}
        	
        });
	}

	@Override
	public void OnMessageResponse(String url, JSONObject jo, AjaxStatus status)
			throws JSONException {
		// TODO Auto-generated method stub
		if(url.endsWith(ProtocolConst.GcategoryGood)){

			if(model.gcategory_list.size() == 0) {
				Intent intent = new Intent();
				intent.putExtra("country_id", category_id);
				intent.putExtra("country_name", category_name);
				setResult(Activity.RESULT_OK, intent);  
	            finish(); 
			}
			
			mgcateAdapter = new E0_GcategoryAdapter(this, model.gcategory_list); 
			listView.setAdapter(mgcateAdapter);
			
		}
	}

}
