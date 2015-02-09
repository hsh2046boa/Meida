package com.meida.emall.activity;
//
//                       __
//                      /\ \   _
//    ____    ____   ___\ \ \_/ \           _____    ___     ___
//   / _  \  / __ \ / __ \ \    <     __   /\__  \  / __ \  / __ \
//  /\ \_\ \/\  __//\  __/\ \ \\ \   /\_\  \/_/  / /\ \_\ \/\ \_\ \
//  \ \____ \ \____\ \____\\ \_\\_\  \/_/   /\____\\ \____/\ \____/
//   \/____\ \/____/\/____/ \/_//_/         \/____/ \/___/  \/___/
//     /\____/
//     \/___/
//
//  Powered by BeeFramework
//

import java.util.ArrayList;

import android.content.res.Resources;

import com.insthub.BeeFramework.activity.BaseActivity;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.meida.emall.R;
import com.meida.emall.adapter.C2_PaymentAdapter;
import com.meida.emall.protocol.PAYMENT;

public class C2_PaymentActivity extends BaseActivity {
	
	private TextView title;
	private ImageView back;
	
	private ListView listView;
	
	private C2_PaymentAdapter paymentAdapter;
	
	private ArrayList<PAYMENT> list = new ArrayList<PAYMENT>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c2_payment);
		
		Intent intent = getIntent();
		String s = intent.getStringExtra("payment");

        if (null != s)
        {
            try{
                JSONObject jo = new JSONObject(s);
                JSONArray paymentArray = jo.optJSONArray("payment_list");
                if (null != paymentArray && paymentArray.length() > 0) {
                    list.clear();
                    for (int i = 0; i < paymentArray.length(); i++) {
                        JSONObject paymentJsonObject = paymentArray.getJSONObject(i);
                        PAYMENT payment_Item = PAYMENT.fromJson(paymentJsonObject);
                        list.add(payment_Item);
                    }
                }

            } catch (JSONException e) {                
                e.printStackTrace();
            }
        }

		
		title = (TextView) findViewById(R.id.top_view_text);
        Resources resource = (Resources) getBaseContext().getResources();
        String pay=resource.getString(R.string.balance_pay);
		title.setText(pay);
		back = (ImageView) findViewById(R.id.top_view_back);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {				
				finish();
			}
		});
		
		listView = (ListView) findViewById(R.id.payment_list);
		
		paymentAdapter = new C2_PaymentAdapter(this, list);
		listView.setAdapter(paymentAdapter);
		
		listView.setOnItemClickListener(new OnItemClickListener()
        {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {				
				Intent intent = new Intent();
                PAYMENT payment = list.get(position);

                try
                {
                    intent.putExtra("payment",payment.toJson().toString());
                }
                catch (JSONException e)
                {

                }
				setResult(Activity.RESULT_OK, intent);  
	            finish(); 
			}
		});
		
		
	}
}
