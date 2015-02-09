package com.meida.emall.fragment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.external.viewpagerindicator.TabPageIndicator;
import com.meida.emall.R;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragment extends Fragment {

	private static String[] TITLES;
	
	private List<Fragment> items;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View homeView = inflater.inflate(R.layout.home, null);
		
		Resources resource = (Resources) getActivity().getResources();
		String a = resource.getString(R.string.index);
		String b = resource.getString(R.string.b2c);
		String c = resource.getString(R.string.c2c);
		String p = resource.getString(R.string.get_pt);
		TITLES = new String[] {a, p, b, c};
		
		B2CFragment b2c = new B2CFragment();
		items = new ArrayList<Fragment>();
		items.add(new B0_IndexFragment());
		items.add(new PtFragment());
		items.add(b2c);
		items.add(new B2CFragment());
		
		
		HomeAdapter adapter = new HomeAdapter(getChildFragmentManager());
		
		ViewPager pager = (ViewPager) homeView.findViewById(R.id.pager);
		pager.setAdapter(adapter);
//		pager.setAdapter(new TabAdapter(getChildFragmentManager()));
		
		TabPageIndicator indicator = (TabPageIndicator) homeView.findViewById(R.id.tabPageIndicator);
		indicator.setViewPager(pager);
		return homeView;
	}
	
	@Override
	public void onDetach() {
		super.onDetach();
		try {
		    Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
		    childFragmentManager.setAccessible(true);
		    childFragmentManager.set(this, null);

		} catch (NoSuchFieldException e) {
		    throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
		    throw new RuntimeException(e);
		}
	}

	class HomeAdapter extends FragmentPagerAdapter {

		public HomeAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			return items.get(position%items.size());
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return TITLES[position%TITLES.length].toUpperCase();
		}
		
		@Override
		public int getCount() {
			return TITLES.length;
		}
		
	}
	
	class TabAdapter extends FragmentStatePagerAdapter{

		public TabAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
//			Log.i("suxoyo", "position="+position);
			return items.get(position%items.size());
		}
		
		@Override
		public CharSequence getPageTitle(int position) {
			return TITLES[position%TITLES.length].toUpperCase();
		}

		@Override
		public int getCount() {
			return items.size();
		}
		
	}

}
