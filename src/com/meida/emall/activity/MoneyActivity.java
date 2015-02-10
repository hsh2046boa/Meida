package com.meida.emall.activity;

import org.json.JSONException;

import android.os.Bundle;

import com.insthub.BeeFramework.activity.BaseActivity;
import com.insthub.BeeFramework.model.BusinessMessage;
import com.meida.emall.R;
/**
 * 电子币中心
 * @author LENOV
 *
 */
public class MoneyActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.money);
	}

	@Override
	public void OnMessageResponse(BusinessMessage response)
			throws JSONException {
		super.OnMessageResponse(response);
	}

}
