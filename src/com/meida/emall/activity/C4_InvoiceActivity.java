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

import com.insthub.BeeFramework.activity.BaseActivity;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.meida.emall.R;
import com.meida.emall.adapter.C4_InvoiceAdapter;
import com.meida.emall.protocol.INV_LIST;

public class C4_InvoiceActivity extends BaseActivity implements OnClickListener {

	private ImageView back;
	private Button save;
	
	private EditText taitou;
	
	private ListView listView1;
	private ListView listView2;
	
	private ArrayList<INV_LIST> list1 = new ArrayList<INV_LIST>();
	private ArrayList<INV_LIST> list2 = new ArrayList<INV_LIST>();
	
	private C4_InvoiceAdapter invoiceAdapter1;
	private C4_InvoiceAdapter invoiceAdapter2;
	
	private String type_id = null;
	private String content_id = null;

	private String inv_payee = null; //发票抬头
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c4_invoice);
		
        listView1 = (ListView) findViewById(R.id.invoice_list1);
        listView2 = (ListView) findViewById(R.id.invoice_list2);

		Intent intent = getIntent();
		String s = intent.getStringExtra("payment");
		type_id = intent.getStringExtra("inv_type");
		content_id = intent.getStringExtra("inv_content");
		inv_payee = intent.getStringExtra("inv_payee");
		
		try{
			JSONObject jo = new JSONObject(s);
			JSONArray contentArray = jo.optJSONArray("inv_content_list");

			if (null != contentArray && contentArray.length() > 0)
            {
				list1.clear();
				for (int i = 0; i < contentArray.length(); i++)
                {
					JSONObject contentJsonObject = contentArray.getJSONObject(i);
					INV_LIST content_Item = INV_LIST.fromJson(contentJsonObject);
					list1.add(content_Item);
				}
        	}
            else
            {
                listView1.setVisibility(View.GONE);
            }
			
			JSONArray typetArray = jo.optJSONArray("inv_type_list");
			if (null != typetArray && typetArray.length() > 0)
            {
				list2.clear();
				for (int i = 0; i < typetArray.length(); i++)
                {
					JSONObject typeJsonObject = typetArray.getJSONObject(i);
					INV_LIST type_Item = INV_LIST.fromJson(typeJsonObject);
					list2.add(type_Item);
				}
        	}
            else
            {
                listView2.setVisibility(View.GONE);
            }
		
		} catch (JSONException e) {			
			e.printStackTrace();
		}
		
		back = (ImageView) findViewById(R.id.invoice_back);
		back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {				
				finish();
			}
		});
		
		save = (Button) findViewById(R.id.invoice_save);
		save.setOnClickListener(this);
		taitou = (EditText) findViewById(R.id.invoice_taitou);
		
		taitou.setText(inv_payee);
		
		invoiceAdapter1 = new C4_InvoiceAdapter(this, list1, type_id);
		listView1.setAdapter(invoiceAdapter1);
		
		invoiceAdapter2 = new C4_InvoiceAdapter(this, list2, content_id);
		listView2.setAdapter(invoiceAdapter2);
		
		listView1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {								
				invoiceAdapter1.flag = position;
				invoiceAdapter1.notifyDataSetChanged();
				
				type_id = list1.get(position).id;
				
			}
		});
		
		listView2.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {				
				invoiceAdapter2.flag = position;
				invoiceAdapter2.notifyDataSetChanged();
				content_id = list2.get(position).id;
			}
		});
		
	}

	@Override
	public void onClick(View v) {		
		switch(v.getId()) {
		case R.id.invoice_save:
			Intent intent = new Intent();
			intent.putExtra("inv_type", type_id);
			intent.putExtra("inv_content", content_id);
			intent.putExtra("inv_payee", taitou.getText().toString());
			setResult(Activity.RESULT_OK, intent);  
            finish(); 
			break;
		}
		
	}
}
