package com.meida.emall.activity;

import org.json.JSONException;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.external.maxwin.view.XListView;
import com.external.maxwin.view.XListView.IXListViewListener;
import com.insthub.BeeFramework.activity.BaseActivity;
import com.insthub.BeeFramework.model.BusinessMessage;
import com.meida.emall.R;

public class CommentActivity extends BaseActivity implements IXListViewListener {

	private ImageView back;
	private XListView xlistView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.comment);
		
		back = (ImageView) findViewById(R.id.collect_back);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {				
				finish();
			}
		});
		
		xlistView = (XListView) findViewById(R.id.collect_list);
		xlistView.setPullLoadEnable(true);
		xlistView.setRefreshTime();
		xlistView.setXListViewListener(this,1);
	}

	@Override
	public void OnMessageResponse(BusinessMessage response)
			throws JSONException {
		super.OnMessageResponse(response);
	}

	@Override
	public void onRefresh(int id) {
		
	}

	@Override
	public void onLoadMore(int id) {
		
	}

}
