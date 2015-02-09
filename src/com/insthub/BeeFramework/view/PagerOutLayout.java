package com.insthub.BeeFramework.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

public class PagerOutLayout extends RelativeLayout {
	ViewPager child_viewpager;
	float startX;

	/**
	 * @param context
	 * @param attrs
	 */
	public PagerOutLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public boolean onInterceptTouchEvent(MotionEvent event) {
		int action = event.getAction();
		switch (action) {
		//按下
		case MotionEvent.ACTION_DOWN:
			startX = event.getX();
			getParent().requestDisallowInterceptTouchEvent(true);
			break;
		//滑动，在此对里层viewpager的第一页和最后一页滑动做处理	
		case MotionEvent.ACTION_MOVE:
			if (startX == event.getX()) {
				if (0 == child_viewpager.getCurrentItem()
						|| child_viewpager.getCurrentItem() == child_viewpager
								.getAdapter().getCount() - 1) {
					getParent().requestDisallowInterceptTouchEvent(false);
				}
			}
			//里层viewpager已经是最后一页，此时继续向右滑（手指从右往左滑）
			else if (startX > event.getX()) {
				if (child_viewpager.getCurrentItem() == child_viewpager
						.getAdapter().getCount() - 1) {
					getParent().requestDisallowInterceptTouchEvent(false);
				}
			}
			//里层viewpager已经是第一页，此时继续向左滑（手指从左往右滑）
			else if (startX < event.getX()) {
				if (child_viewpager.getCurrentItem() == 0) {
					getParent().requestDisallowInterceptTouchEvent(false);
				}
			} else {
				getParent().requestDisallowInterceptTouchEvent(true);
			}
			break;
			//抬起
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL:
			getParent().requestDisallowInterceptTouchEvent(false);
			break;
		}
		return false;
	}
	//注入里层viewpager 
	public void setChild_viewpager(ViewPager child_viewpager) {
		this.child_viewpager = child_viewpager;
	}
}