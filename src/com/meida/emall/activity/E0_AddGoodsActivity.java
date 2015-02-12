package com.meida.emall.activity;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.external.androidquery.callback.AjaxStatus;
import com.insthub.BeeFramework.activity.BaseActivity;
import com.insthub.BeeFramework.model.BusinessResponse;
import com.meida.emall.R;

public class E0_AddGoodsActivity extends BaseActivity implements OnClickListener, BusinessResponse{
	private ImageView mBtnBack;
	private Button mBtnSelect;
	private Button mBtnPhoto;
	private Button mBtnBox;
	private Button mBtnSubmit;
	private EditText mEditClassification;
	private EditText mEditName;
	private EditText mEditBrand;
	private EditText mEditLabel;
	private EditText mEditInventory;
	private EditText mEditPrice;
	private EditText mEditFreight;
	private EditText mEditLocation;
	private EditText mEditNo;
	private EditText mEditDescription;
	private EditText mEditPicture;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_addgoods);
		
		initView();
		getData();
	}
	
	private void getData(){
		
	}
	
	private void initView(){
		mBtnBack = (ImageView)findViewById(R.id.profile_back);
		mBtnSelect = (Button)findViewById(R.id.add_select);
		mBtnPhoto = (Button)findViewById(R.id.add_photo);
		mBtnBox = (Button)findViewById(R.id.add_save);
		mBtnSubmit = (Button)findViewById(R.id.add_submit);
		mEditClassification = (EditText)findViewById(R.id.add_classification);
		mEditName = (EditText)findViewById(R.id.add_name);
		mEditBrand = (EditText)findViewById(R.id.add_brand);
		mEditLabel = (EditText)findViewById(R.id.add_label);
		mEditInventory = (EditText)findViewById(R.id.add_inventory);
		mEditPrice = (EditText)findViewById(R.id.add_price);
		mEditFreight = (EditText)findViewById(R.id.add_freight);
		mEditLocation = (EditText)findViewById(R.id.add_location);
		mEditNo = (EditText)findViewById(R.id.add_no);
		mEditDescription = (EditText)findViewById(R.id.add_description);
		mEditPicture = (EditText)findViewById(R.id.add_picture);
		
		mBtnBack.setOnClickListener(this);
		mBtnSelect.setOnClickListener(this);
		mBtnPhoto.setOnClickListener(this);
		mBtnBox.setOnClickListener(this);
		mBtnSubmit.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
        switch(v.getId()){
	        case R.id.profile_back:
	        	E0_AddGoodsActivity.this.finish();
	        	break;
	        case R.id.add_select:
	        	
	        	break;
	        case R.id.add_photo:
	        	
	        	break;
	        case R.id.add_save:
	        	
	        	break;
	        case R.id.add_submit:
	        	
	        	break;
        }
	}

	@Override
	public void OnMessageResponse(String url, JSONObject jo, AjaxStatus status)
			throws JSONException {
		// TODO Auto-generated method stub
		
	}

}
