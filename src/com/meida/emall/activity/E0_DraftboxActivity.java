package com.meida.emall.activity;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
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

public class E0_DraftboxActivity extends BaseActivity implements IXListViewListener, OnClickListener, BusinessResponse{
	private ImageView mBtnBack;
	private Button mBtnDelete;
	private Button mBtnSubmit;
	private XListView xlistview;
	private TextView mTextSelete;
	private E0_DraftboxAdapter mDraftboxAdapter;
	
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
	}

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
				E0_DraftboxActivity.this.finish();
				break;
			case R.id.box_delete:
				break;
			case R.id.box_submit:
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
