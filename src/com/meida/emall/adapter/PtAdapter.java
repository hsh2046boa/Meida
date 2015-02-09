package com.meida.emall.adapter;

import com.meida.emall.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class PtAdapter extends BaseAdapter {

	private Context mContext;
	
	private LayoutInflater inflater;
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	public PtAdapter(Context mContext) {
		super();
		this.mContext = mContext;
		inflater = LayoutInflater.from(mContext);
	}

	@Override
	public int getCount() {
		return 10;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.pt_item, null);
		}
		
		
		
		return convertView;
	}

}
