package com.meida.emall.activity;

import org.json.JSONException;

import android.os.Bundle;

import com.insthub.BeeFramework.activity.BaseActivity;
import com.insthub.BeeFramework.model.BusinessMessage;
import com.meida.emall.R;

public class UserInfoActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_info);
	}

	@Override
	public void OnMessageResponse(BusinessMessage response)
			throws JSONException {
		// TODO Auto-generated method stub
		super.OnMessageResponse(response);
	}

}
