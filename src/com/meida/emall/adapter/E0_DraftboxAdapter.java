package com.meida.emall.adapter;

import java.util.ArrayList;
import java.util.List;

import com.meida.emall.R;
import com.meida.emall.protocol.FAIRADD;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class E0_DraftboxAdapter extends BaseAdapter {
	private Context mContext;
	private List<FAIRADD> mlist;
	private LayoutInflater minflater;
	private List<Integer> mSelect;
	private Handler mhandler;
	
	public E0_DraftboxAdapter(Context c,List<FAIRADD> list){
		mContext = c;
		mlist = list;
		mSelect = new ArrayList<Integer>();
		minflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	public void setHandler(Handler handler){
		mhandler = handler;
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
		final Holder mhold;
		if(v == null){
			mhold = new Holder();
			v = minflater.inflate(R.layout.item_draftbox, null);
		}else{
			mhold = (Holder)v.getTag();
		}
		
		mhold.select.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(mSelect.contains(position)){
					mSelect.remove(position);
					mhold.select.setBackgroundResource(R.drawable.ic_launcher);
				}else{
					mSelect.add(position);
					mhold.select.setBackgroundResource(R.drawable.ic_launcher);
				}
				Message msg = mhandler.obtainMessage();
				msg.what = 1001;
				msg.obj = mSelect;
				mhandler.sendMessage(msg);
			}
			
		});
		
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
