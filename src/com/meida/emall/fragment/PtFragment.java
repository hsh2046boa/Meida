package com.meida.emall.fragment;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.external.androidquery.callback.AjaxStatus;
import com.external.maxwin.view.XListView;
import com.insthub.BeeFramework.fragment.BaseFragment;
import com.insthub.BeeFramework.model.BusinessResponse;
import com.insthub.BeeFramework.view.MyListView;
import com.meida.emall.R;
import com.meida.emall.adapter.PtAdapter;

public class PtFragment extends BaseFragment implements BusinessResponse,XListView.IXListViewListener {

	private MyListView mListView;
	private PtAdapter adapter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View mainView = inflater.inflate(R.layout.b0_pt, null);
		
		mListView = (MyListView) mainView.findViewById(R.id.pt_listview);
		adapter = new PtAdapter(getActivity());
		mListView.setAdapter(adapter);
		mListView.setPullLoadEnable(false);
        mListView.setXListViewListener(this,0);
        mListView.setRefreshTime();
		return mainView;
	}

	@Override
	public void onRefresh(int id) {
		mListView.setRefreshTime();
		mListView.stopRefresh();
	}

	@Override
	public void onLoadMore(int id) {
		
	}

	@Override
	public void OnMessageResponse(String url, JSONObject jo, AjaxStatus status)
			throws JSONException {
		// TODO Auto-generated method stub
		super.OnMessageResponse(url, jo, status);
	}

}
