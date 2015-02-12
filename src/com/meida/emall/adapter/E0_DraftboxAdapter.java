package com.meida.emall.adapter;

import java.util.List;

import com.meida.emall.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class E0_DraftboxAdapter extends BaseAdapter {
	private Context mContext;
	private List mlist;
	private LayoutInflater minflater;
	
	public E0_DraftboxAdapter(Context c,List list){
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
			v = minflater.inflate(R.layout.item_draftbox, null);
		}else{
			mhold = (Holder)v.getTag();
		}
		
		return v;
	}
	
	class Holder{
		ImageView image;
		TextView content;
		TextView price;
		TextView numdown;
		TextView numup;
		TextView num;
		ImageView select;
	}

}
