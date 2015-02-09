package com.meida.emall.adapter;

import java.util.ArrayList;

import com.meida.emall.R;
import com.meida.emall.protocol.CATE;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PopupAdapter extends BaseAdapter {

	private Activity activity;
	private ArrayList dataList = new ArrayList();
	
	private LayoutInflater inflater;
	public PopupAdapter(Activity activity, ArrayList dataList) {
		super();
		this.activity = activity;
		this.dataList = dataList;
		
		inflater = activity.getLayoutInflater();
	}

	@Override
	public int getCount() {
		return dataList.size();
	}

	@Override
	public Object getItem(int position) {
		return dataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.popup_item, null);
		}
		convertView.setTag(dataList.get(position));
		TextView name = (TextView) convertView.findViewById(R.id.name);
		
		name.setText(((CATE)dataList.get(position)).cate_name);
		return convertView;
	}

}
