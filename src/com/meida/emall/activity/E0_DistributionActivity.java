package com.meida.emall.activity;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;

import com.external.androidquery.callback.AjaxStatus;
import com.external.maxwin.view.XListView;
import com.external.maxwin.view.XListView.IXListViewListener;
import com.insthub.BeeFramework.activity.BaseActivity;
import com.insthub.BeeFramework.model.BusinessResponse;
import android.widget.AdapterView.OnItemClickListener;
import com.meida.emall.R;
import com.meida.emall.adapter.E0_DistributionAdapter;
import com.meida.emall.adapter.E0_DistributionAddDetailActivity;
/**
 * 店铺设置
 * @author LENOV
 *
 */
public class E0_DistributionActivity extends BaseActivity implements IXListViewListener, OnClickListener, BusinessResponse{
	private XListView xlistview;
	private ImageView mBtnback;
	private Button mAddDistri;
	private E0_DistributionAdapter mDistributionAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_distribution);
		
		initView();
	}
	
	private void initView(){
		xlistview = (XListView)findViewById(R.id.profile_list);
		mBtnback = (ImageView)findViewById(R.id.profile_back);
		mAddDistri = (Button)findViewById(R.id.add_distribution);
		
		mBtnback.setOnClickListener(this);
		mAddDistri.setOnClickListener(this);
	}
	
	private Handler mhandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch(msg.what){
				case 1001:
					
					break;
				case 1002:
					
					break;
			}
		}
		
	};

	@Override
	public void OnMessageResponse(String url, JSONObject jo, AjaxStatus status)
			throws JSONException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
			case R.id.profile_back:
				E0_DistributionActivity.this.finish();
				break;
			case R.id.add_distribution:
				Intent add = new Intent(E0_DistributionActivity.this,E0_DistributionAddDetailActivity.class);
				add.putExtra("type", "add");
				startActivity(add);
				break;
		}
	}
	
	OnItemClickListener onItemClick = new OnItemClickListener(){

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			Intent add = new Intent(E0_DistributionActivity.this,E0_DistributionAddDetailActivity.class);
			add.putExtra("type", "edit");
			startActivity(add);
		}
		
	};

	@Override
	public void onRefresh(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoadMore(int id) {
		// TODO Auto-generated method stub
		
	}
}
