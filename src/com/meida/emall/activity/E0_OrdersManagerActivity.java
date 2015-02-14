package com.meida.emall.activity;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.external.androidquery.callback.AjaxStatus;
import com.external.maxwin.view.XListView;
import com.external.maxwin.view.XListView.IXListViewListener;
import com.insthub.BeeFramework.activity.BaseActivity;
import com.insthub.BeeFramework.model.BusinessResponse;
import com.insthub.BeeFramework.view.ToastView;
import com.meida.emall.R;
import com.meida.emall.adapter.E0_OrderManagerAdapter;
import com.meida.emall.adapter.E0_SellerEditionAdapter;
import com.meida.emall.model.BuyerOrderModel;
import com.meida.emall.model.ProtocolConst;
import com.meida.emall.protocol.SELLERORDERS;
import com.meida.emall.protocol.STATUS;
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
	
	
	private Handler mhand = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch(msg.what){
				case 1001:
					String hold=getResources().getString(R.string.hold_on);
					pd = new ProgressDialog(E0_OrdersManagerActivity.this);
					pd.setMessage(hold);
					pd.show();
					
					int index = (Integer)msg.obj;
					SELLERORDERS seller = buyerorder.order_list.get(index);
					buyerorder.deleteOrderByID(seller.order_id);
					break;
			}
		}
		
	};
	
	
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
		
		xlistview.setPullLoadEnable(true);
		xlistview.setRefreshTime();
		xlistview.setXListViewListener(this,1);
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
			xlistview.stopLoadMore();
			xlistview.setRefreshTime();
			
			mOrderManagerAdapter = new E0_OrderManagerAdapter(this, buyerorder.order_list);
			mOrderManagerAdapter.setHandler(mhand);
			xlistview.setAdapter(mOrderManagerAdapter);
			//xlistview.setOnItemClickListener(onItemClick);
		}
		if(url.endsWith(ProtocolConst.CancleOrder)){
			STATUS responseStatus = STATUS.fromJson(jo.optJSONObject("status"));
			if (responseStatus.succeed == 1) {
				String str = getResources().getString(R.string.operation_success);
				ToastView toast = new ToastView(this, str);
            	toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                initData();
			}else{
				String str = getResources().getString(R.string.operation_failed);
				ToastView toast = new ToastView(this, str);
            	toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
			}
		}
	}
	
	OnItemClickListener onItemClick = new OnItemClickListener(){

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			SELLERORDERS seller = buyerorder.order_list.get(arg2);
		}
		
	};

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
