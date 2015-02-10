package com.meida.emall.activity;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.external.androidquery.callback.AjaxStatus;
import com.external.maxwin.view.XListView;
import com.external.maxwin.view.XListView.IXListViewListener;
import com.insthub.BeeFramework.activity.BaseActivity;
import com.insthub.BeeFramework.model.BusinessResponse;
import com.meida.emall.R;
import com.meida.emall.adapter.E0_OrderManagerAdapter;
import com.meida.emall.adapter.E0_SellerEditionAdapter;
import com.meida.emall.model.BuyerOrderModel;
import com.meida.emall.model.ProtocolConst;
/**
 * 订单管理
 * @author LENOV
 *
 */
public class E0_OrdersManagerActivity extends BaseActivity implements IXListViewListener, OnClickListener, BusinessResponse{
	private XListView xlistview;
	private ImageView mBtnback;
	private BuyerOrderModel buyerorder;
	private ProgressDialog pd = null;
	private E0_OrderManagerAdapter mOrderManagerAdapter;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.e0_ordersmanager);
		
		initView();
		initData();
	}
	
	private void initData(){
		buyerorder = new BuyerOrderModel(this);
		buyerorder.addResponseListener(this);
		buyerorder.getBuyerOrder("1", "all");
		
		String hold=getResources().getString(R.string.hold_on);
		pd = new ProgressDialog(E0_OrdersManagerActivity.this);
		pd.setMessage(hold);
		pd.show();
	}
	
	private void initView(){
		xlistview = (XListView)findViewById(R.id.profile_list);
		mBtnback = (ImageView)findViewById(R.id.profile_back);
		mBtnback.setOnClickListener(this);
		
		xlistview.setPullLoadEnable(false);
		xlistview.setRefreshTime();
		xlistview.setXListViewListener(this,1);
		xlistview.setAdapter(null);
	}

	@Override
	public void OnMessageResponse(String url, JSONObject jo, AjaxStatus status)
			throws JSONException {
		// TODO Auto-generated method stub
		if(pd.isShowing()) {
			pd.dismiss();
		}
		if(url.endsWith(ProtocolConst.BuyerOrder)) {			
			System.out.println("sss");
			xlistview.stopRefresh();
			xlistview.setRefreshTime();
			
			//mE0SellAdapter = new E0_SellerEditionAdapter(this, fairModel.fair_List);
			//xlistView.setAdapter(mE0SellAdapter);
			//xlistView.setOnItemClickListener(listener)
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
			case R.id.profile_back:
				E0_OrdersManagerActivity.this.finish();
				break;
		}
	}

	@Override
	public void onRefresh(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoadMore(int id) {
		// TODO Auto-generated method stub
		
	}
}
