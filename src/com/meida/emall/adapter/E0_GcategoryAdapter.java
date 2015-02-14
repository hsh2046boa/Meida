package com.meida.emall.adapter;

import java.util.List;

import com.meida.emall.R;
import com.meida.emall.adapter.F3_RegionPickAdapter.ViewHolder;
import com.meida.emall.protocol.GCATEGORYGOOD;
import com.meida.emall.protocol.REGIONS;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class E0_GcategoryAdapter extends BaseAdapter {
	private Context context;
	private List<GCATEGORYGOOD> list;
	private LayoutInflater inflater;
	
	public E0_GcategoryAdapter(Context c,List<GCATEGORYGOOD> list){
		context = c;
		this.list = list;
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View v, ViewGroup arg2) {
		// TODO Auto-generated method stub
		
		ViewHolder holder;
		if(v == null) {
			holder = new ViewHolder();
			v = inflater.inflate(R.layout.f3_region_pick_cell, null);
			holder.name = (TextView) v.findViewById(R.id.city_item_name);
			
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}
		
		GCATEGORYGOOD regions = list.get(position);
		holder.name.setText(regions.cate_name);
		
		return v;
	}
	
	class ViewHolder {
		TextView name;
		
	}

}
