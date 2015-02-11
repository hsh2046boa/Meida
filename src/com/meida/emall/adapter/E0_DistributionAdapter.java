package com.meida.emall.adapter;

import java.util.List;

import com.meida.emall.R;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class E0_DistributionAdapter extends BaseAdapter {
	private Context mContext;
	private List mlist;
	private LayoutInflater minflater;
	private Handler mHandler;
	
	public E0_DistributionAdapter(Context c,List list){
		mContext = c;
		mlist = list;
		minflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	public void setHandler(Handler handler){
		mHandler = handler;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mlist.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mlist.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View v, ViewGroup parent) {
		// TODO Auto-generated method stub
		Holder mhold;
		if(v == null){
			mhold = new Holder();
			v = minflater.inflate(R.layout.item_distribution, null);
			mhold.type = (TextView)v.findViewById(R.id.tranlate_type);
			mhold.message = (TextView)v.findViewById(R.id.message);
			mhold.fprice = (TextView)v.findViewById(R.id.first_price);
			mhold.sprice = (TextView)v.findViewById(R.id.send_price);
			mhold.open = (TextView)v.findViewById(R.id.open);
			mhold.edit = (TextView)v.findViewById(R.id.edit);
			mhold.delete = (TextView)v.findViewById(R.id.delete);
			v.setTag(mhold);
		}else{
			mhold = (Holder)v.getTag();
		}
		
		mhold.type.setText("");
		mhold.message.setText("");
		mhold.fprice.setText("");
		mhold.sprice.setText("");
		mhold.open.setText("");
		mhold.edit.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Message msg = mHandler.obtainMessage();
				msg.what = 1001;
				msg.obj = position;
				mHandler.sendMessage(msg);
			}
			
		});
		mhold.delete.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Message msg = mHandler.obtainMessage();
				msg.what = 1002;
				msg.obj = position;
				mHandler.sendMessage(msg);
			}
			
		});
		
		return v;
	}
	
	class Holder{
		TextView type;
		TextView message;
		TextView fprice;
		TextView sprice;
		TextView open;
		TextView edit;
		TextView delete;
	}

}
