package com.meida.emall.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.insthub.BeeFramework.adapter.BeeBaseAdapter;
import com.insthub.BeeFramework.adapter.BeeBaseAdapter.BeeCellHolder;
import com.meida.emall.R;
import com.meida.emall.component.TwoGoodItem;
import com.meida.emall.protocol.DEFGOOD;

public class B2CListAdapter extends BeeBaseAdapter {

	public B2CListAdapter(Context c, ArrayList dataList) {
		super(c, dataList);
		
	}

	@Override
	public int getCount() {
		if(dataList.size()%2>0) {
			return dataList.size()/2+1;
		} else {
			return dataList.size()/2;
		}
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
	public int getViewTypeCount() {
		return 1;
	}

	@Override
	public int getItemViewType(int position) {
		return 1;
	}

	@Override
	protected BeeCellHolder createCellHolder(View cellView) {
		BeeCellHolder holder = new BeeCellHolder();
		return holder;
	}

	@Override
	protected View bindData(int position, View cellView, ViewGroup parent,
			BeeCellHolder h) {
		List<DEFGOOD> items = null;
		int distance =  dataList.size() - position*2;
        int cellCount = distance >= 2? 2:distance;

        items = dataList.subList(position*2,position*2+cellCount);

        ((TwoGoodItem)cellView).bindData(items);
		return null;
	}

	@Override
	public View createCellView() {
		return mInflater.inflate(R.layout.b2c_item_cell, null);
	}

	@Override
	public View getView(int position, View cellView, ViewGroup parent) {
		BeeCellHolder holder = null;
        if (cellView == null )
        {
            cellView = createCellView();
            holder = createCellHolder(cellView);
            if (null != holder)
            {
                cellView.setTag(holder);
            }

        }
        else
        {
            holder = (BeeCellHolder)cellView.getTag();
            if (holder == null)
            {
                Log.v("lottery", "error");
            }
            else
            {
                Log.d("ecmobile", "last position" + holder.position +"    new position" + position+"\n");
            }

        }

        if(null != holder)
        {
            holder.position = position;
        }

        bindData(position, cellView, parent, holder);
		return cellView;
	}

}
