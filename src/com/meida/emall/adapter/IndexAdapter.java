package com.meida.emall.adapter;

import com.insthub.BeeFramework.view.GridView4LV;
import com.meida.emall.MeidaApp;
import com.meida.emall.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class IndexAdapter extends BaseAdapter {

	 private LayoutInflater mInflater = null;
	 private Context mContext;
	 private ImageLoader imageLoader = ImageLoader.getInstance();
	public IndexAdapter(Context mContext) {
		super();
		this.mContext = mContext;
		mInflater = LayoutInflater.from(mContext);
	}

	@Override
	public int getCount() {
		return 1;
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
			convertView = mInflater.inflate(R.layout.index_item, null);
		}
		
		GridView4LV gv = (GridView4LV) convertView.findViewById(R.id.gridView);
		gv.setAdapter(new ImageAdapter());
		
		return convertView;
	}
	
	public class ImageAdapter extends BaseAdapter{

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
			ImageView imageView;
            if (convertView == null) {
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(200, 100));
                imageView.setAdjustViewBounds(false);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(8, 8, 8, 8);
            } else {
                imageView = (ImageView) convertView;
            }
            imageLoader.displayImage("http://meida.zhuguanjia.net/data/system/default_goods_image.gif", imageView, MeidaApp.options);
			return imageView;
		}
		
	}

}
