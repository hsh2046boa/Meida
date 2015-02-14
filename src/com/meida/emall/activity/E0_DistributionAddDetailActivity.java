package com.meida.emall.activity;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.external.androidquery.callback.AjaxStatus;
import com.external.maxwin.view.XListView.IXListViewListener;
import com.insthub.BeeFramework.activity.BaseActivity;
import com.insthub.BeeFramework.model.BusinessResponse;
import com.meida.emall.R;

/**
 * 新增配送及编辑
 * @author Administrator
 *
 */
public class E0_DistributionAddDetailActivity extends BaseActivity implements OnClickListener, BusinessResponse{
	private ImageView mBtnback;
	private Button mSubmit;
	private TextView mDisTitle;
	private EditText mDisName;
	private EditText mDisMsg;
	private EditText mDisFPrice;
	private EditText mDisSPrice;
	private EditText mDisOrder;
	private RadioGroup mDisGroup;
	private String mDisType;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_adddetail);
		
		initView();
	}
	
	private void initView(){
		mBtnback = (ImageView)findViewById(R.id.distribution_back);
		mDisTitle = (TextView)findViewById(R.id.dis_title);
		mSubmit = (Button)findViewById(R.id.submit);
		mDisName = (EditText)findViewById(R.id.dis_name);
		mDisMsg = (EditText)findViewById(R.id.dis_msg);
		mDisFPrice = (EditText)findViewById(R.id.dis_firstprice);
		mDisSPrice = (EditText)findViewById(R.id.dis_sendprice);
		mDisOrder = (EditText)findViewById(R.id.dis_order);
		mDisGroup = (RadioGroup)findViewById(R.id.group1);
		
		mBtnback.setOnClickListener(this);
		mSubmit.setOnClickListener(this);
		mDisGroup.setOnCheckedChangeListener(onCheckChange);
		
		mDisType = getIntent().getStringExtra("dis_type");
		if("edit".equals(mDisType)){
			mDisTitle.setText("编辑");
			mSubmit.setText("确认");
			
		}
	}
	
	OnCheckedChangeListener onCheckChange = new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup arg0, int arg1) {
			// TODO Auto-generated method stub
			switch(arg1){
			case R.id.radio1:
				break;
			case R.id.radio2:
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
			case R.id.distribution_back:
				E0_DistributionAddDetailActivity.this.finish();
				break;
			case R.id.submit:
				break;
		}
	}
}
