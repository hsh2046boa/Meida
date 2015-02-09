package com.meida.emall.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.insthub.BeeFramework.adapter.BeeBaseAdapter;
import com.meida.emall.R;

public class CommentAdapter extends BeeBaseAdapter {

	public CommentAdapter(Context c, ArrayList dataList) {
		super(c, dataList);
	}

	protected class CommentCellHolder extends BeeCellHolder{
		public TextView name;
		public TextView date;
		public TextView content;
	}
	
	@Override
	public int getCount() {
		return 5;
	}

	@Override
	protected BeeCellHolder createCellHolder(View cellView) {
		CommentCellHolder holder = new CommentCellHolder();
		holder.name = (TextView) cellView.findViewById(R.id.name);
		holder.date = (TextView) cellView.findViewById(R.id.date);
		holder.content = (TextView) cellView.findViewById(R.id.content);
		return holder;
	}

	@Override
	protected View bindData(int position, View cellView, ViewGroup parent,
			BeeCellHolder h) {
		CommentCellHolder holder = (CommentCellHolder) h;
		return cellView;
	}

	@Override
	public View createCellView() {
		return mInflater.inflate(R.layout.comment_item, null);
	}

}
