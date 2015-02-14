package com.meida.emall.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.external.androidquery.callback.AjaxStatus;
import com.external.maxwin.view.XListView;
import com.external.maxwin.view.XListView.IXListViewListener;
import com.insthub.BeeFramework.activity.BaseActivity;
import com.insthub.BeeFramework.model.BusinessResponse;
import com.meida.emall.R;
import com.meida.emall.adapter.E0_DraftboxAdapter;
import com.meida.emall.model.FairAddModel;
import com.meida.emall.model.ProtocolConst;
import com.meida.emall.protocol.FAIRADD;
import com.meida.emall.protocol.STATUS;

public class E0_DraftboxActivity extends BaseActivity implements IXListViewListener, OnClickListener, BusinessResponse{
	private ImageView mBtnBack;
	private Button mBtnDelete;
	private Button mBtnSubmit;
	private XListView xlistview;
	private TextView mTextSelete;
	private E0_DraftboxAdapter mDraftboxAdapter;
	private ProgressDialog pd = null;
	private FairAddModel model;
	private List<Integer> mlist = new ArrayList<Integer>();
	private Handler mhandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch(msg.what){
			case 1001:
				mlist.clear();
				mlist = (List<Integer>)msg.obj;
				mTextSelete.setText("已选"+mlist.size()+"件商品");
				break;
			}
		}
		
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_draftbox);
		initView();
	}
	
	private void initView(){
		mBtnBack = (ImageView)findViewById(R.id.profile_back);
		mBtnDelete = (Button)findViewById(R.id.box_delete);
		mBtnSubmit = (Button)findViewById(R.id.box_submit);
		mTextSelete = (TextView)findViewById(R.id.box_select_no);
		
		mBtnBack.setOnClickListener(this);
		mBtnDelete.setOnClickListener(this);
		mBtnSubmit.setOnClickListener(this);
		
		model = new FairAddModel(this);
		model.addResponseListener(this);
	}

	@Override
	public void OnMessageResponse(String url, JSONObject jo, AjaxStatus status)
			throws JSONException {
		// TODO Auto-generated method stub
		if(pd.isShowing()) {
			pd.dismiss();
		}
        if(url.endsWith(ProtocolConst.GoodsAdd)){

        }
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
			case R.id.profile_back:
				E0_DraftboxActivity.this.finish();
				break;
			case R.id.box_delete:
				FAIRADD del = new FAIRADD();
				del.delete(FAIRADD.class, del.goods_id);
				break;
			case R.id.box_submit:
			    FAIRADD add = new FAIRADD();
				model.sendFairAdd(add);
				
				String hold=getResources().getString(R.string.hold_on);
				pd = new ProgressDialog(E0_DraftboxActivity.this);
				pd.setMessage(hold);
				pd.show();
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
