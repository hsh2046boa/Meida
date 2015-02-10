package com.meida.emall.adapter;

import java.util.List;

import com.meida.emall.R;
import com.meida.emall.protocol.SELLERORDERS;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class E0_OrderManagerAdapter extends BaseAdapter {
	private Context mContext;
	private List<SELLERORDERS> mlist;
	private LayoutInflater minflater;
	
	public E0_OrderManagerAdapter(Context c,List<SELLERORDERS> list){
		mContext = c;
		mlist = list;
		minflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
	public View getView(int position, View v, ViewGroup parent) {
		// TODO Auto-generated method stub
		Holder mhold;
		if(v == null){
			mhold = new Holder();
			v = minflater.inflate(R.layout.item_buyerorder, null);
			mhold.no = (TextView)v.findViewById(R.id.order_no);
			mhold.price = (TextView)v.findViewById(R.id.order_price);
			mhold.time = (TextView)v.findViewById(R.id.order_time);
			mhold.delete = (TextView)v.findViewById(R.id.order_delete);
			v.setTag(mhold);
		}else{
			mhold = (Holder)v.getTag();
		}
		SELLERORDERS sell = mlist.get(position);
		if(sell != null){
			mhold.no.setText(sell.order_sn);
			mhold.price.setText(sell.order_amount);
			mhold.time.setText(sell.add_time);
		}
		return v;
	}

	class Holder{
		TextView no;
		TextView price;
		TextView time;
		TextView delete;
	}
}
