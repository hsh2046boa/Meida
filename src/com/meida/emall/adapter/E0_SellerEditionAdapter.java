package com.meida.emall.adapter;

import java.util.List;

import com.meida.emall.R;
import com.meida.emall.protocol.MYFAIR;


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

public class E0_SellerEditionAdapter extends BaseAdapter {
	List<MYFAIR> mlist;
	Context mContext;
	LayoutInflater minflater;
	Handler mhandler;
	
	public E0_SellerEditionAdapter(Context c,List<MYFAIR> list){
		mContext = c;
		mlist = list;
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
		Holder mhold;
		if(v == null){
			mhold = new Holder();
			v = minflater.inflate(R.layout.item_e0_selleredition, null);
			mhold.def_image = (ImageView)v.findViewById(R.id.default_image);
			mhold.title = (TextView)v.findViewById(R.id.title);
			mhold.prive = (TextView)v.findViewById(R.id.price);
			mhold.quanlity = (TextView)v.findViewById(R.id.quanlity);
			mhold.edit = (TextView)v.findViewById(R.id.edit);
			v.setTag(mhold);
		}else{
			mhold = (Holder)v.getTag();
		}
		MYFAIR fair = mlist.get(position);
		if(fair != null){
			mhold.def_image.setBackgroundResource(R.drawable.ic_launcher);
			mhold.title.setText(fair.goods_name);
			mhold.prive.setText("￥"+fair.price);
			mhold.quanlity.setText("已售"+fair.seller_quantity+"件");
			mhold.quanlity.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Message msg = mhandler.obtainMessage();
					msg.what = 1001;
					msg.obj = position;
					mhandler.sendMessage(msg);
				}
				
			});
		}
		
		return v;
	}
	
	class Holder{
		ImageView def_image;
		TextView title;
		TextView prive;
		TextView quanlity;
		TextView edit;
	}

}
