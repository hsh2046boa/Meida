package com.meida.emall.fragment;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;

import com.external.androidquery.callback.AjaxStatus;
import com.external.maxwin.view.XListView;
import com.external.viewpagerindicator.PageIndicator;
import com.insthub.BeeFramework.activity.WebViewActivity;
import com.insthub.BeeFramework.fragment.BaseFragment;
import com.insthub.BeeFramework.model.BusinessResponse;
import com.insthub.BeeFramework.view.MyListView;
import com.insthub.BeeFramework.view.PagerOutLayout;
import com.meida.emall.MeidaApp;
import com.meida.emall.R;
import com.meida.emall.activity.B1_ProductListActivity;
import com.meida.emall.activity.B2_ProductDetailActivity;
import com.meida.emall.adapter.B1_ProductListAdapter;
import com.meida.emall.adapter.B2CListAdapter;
import com.meida.emall.adapter.Bee_PageAdapter;
import com.meida.emall.adapter.PopupAdapter;
import com.meida.emall.model.B2CModel;
import com.meida.emall.model.GoodsListModel;
import com.meida.emall.model.HomeModel;
import com.meida.emall.model.ProtocolConst;
import com.meida.emall.protocol.FILT;
import com.meida.emall.protocol.FILTER;
import com.meida.emall.protocol.PLAYER;
import com.nostra13.universalimageloader.core.ImageLoader;

public class B2CFragment extends BaseFragment implements BusinessResponse,XListView.IXListViewListener {

	private ViewPager bannerViewPager;
    private PagerOutLayout mLayout;
    private PageIndicator mIndicator;
    
    private ArrayList<View> bannerListView;
    private Bee_PageAdapter bannerPageAdapter;
    private LinearLayout bannerView;
    private EditText searchInput;
    private Button search;
    private Button cate;
    
    private HomeModel homeModel;
    
	private MyListView mListView;
	private B2CModel dataModel;
	private B2CListAdapter listAdapter;
	
	private PopupWindow popup;
	private ListView popList;
	private View popView;
	
	public static final int IS_HOT = 0;
    public static final int PRICE_DESC_INT = 1;
    public static final int PRICE_ASC_INT = 2;
    
    FILTER filter = new FILTER();
    protected ImageLoader imageLoader = ImageLoader.getInstance();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View mainView = inflater.inflate(R.layout.b0_b2c, null);
		
		if (dataModel == null) {
			dataModel = new B2CModel(getActivity());
			dataModel.fetchB2CList();
			dataModel.fetchB2Ccate();
			
			homeModel = new HomeModel(getActivity());
			homeModel.fetchHotSelling();
		}else {
			dataModel.fetchB2CList();
			dataModel.fetchB2Ccate();
			homeModel.fetchHotSelling();
		}
		dataModel.addResponseListener(this);
		homeModel.addResponseListener(this);
		
		bannerView = (LinearLayout)LayoutInflater.from(getActivity()).inflate(R.layout.b2c_banner, null);

        bannerViewPager = (ViewPager)bannerView.findViewById(R.id.b2c_banner);
        
        mLayout = (PagerOutLayout) bannerView.findViewById(R.id.b2c_layout);
        mLayout.setChild_viewpager(bannerViewPager);
        
        LayoutParams params1 = bannerViewPager.getLayoutParams();
		params1.width = getDisplayMetricsWidth();
		params1.height = (int) (params1.width*1.0/484*200);
		
		bannerViewPager.setLayoutParams(params1);

        bannerListView = new ArrayList<View>();

        
        bannerPageAdapter = new Bee_PageAdapter(bannerListView);

        bannerViewPager.setAdapter(bannerPageAdapter);
        bannerViewPager.setCurrentItem(0);
        
        mIndicator = (PageIndicator)bannerView.findViewById(R.id.indicator);
        mIndicator.setViewPager(bannerViewPager); 
        
//        //设置小点的宽度
        LayoutParams params = mIndicator.getLayoutParams();
		params.width = 100;
		mIndicator.setLayoutParams(params);
		
		searchInput = (EditText) bannerView.findViewById(R.id.search_input);
		search = (Button) bannerView.findViewById(R.id.search_btn);
		cate = (Button) bannerView.findViewById(R.id.cate_btn);
		
		search.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), B1_ProductListActivity.class);
				FILT fil = new FILT();
				fil.keyword = searchInput.getText()+"";
				try {
					intent.putExtra("filter", fil.toJson().toString());
				} catch (JSONException e) {
					e.printStackTrace();
				}
				startActivity(intent);
				
			}
		});
		cate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (dataModel.CateList.size() > 0) {
					makePopup(v);
				}
				
			}
		});
		
		mListView = (MyListView) mainView.findViewById(R.id.b2c_listview);
		mListView.addHeaderView(bannerView);
		mListView.bannerView = bannerView;
		mListView.setPullLoadEnable(false);
        mListView.setXListViewListener(this,0);
		
		return mainView;
	}
	
	public void addBannerView()
    {
        bannerListView.clear();
        for (int i = 0; i < homeModel.playersList.size(); i++)
        {
            PLAYER player = homeModel.playersList.get(i);
            ImageView  viewOne =  (ImageView)LayoutInflater.from(getActivity()).inflate(R.layout.b0_index_banner_cell,null);
            
            imageLoader.displayImage(ProtocolConst.HOMELINK + player.pic,viewOne, MeidaApp.options);
            
            //viewOne.setImageWithURL(getActivity(),player.photo.url,R.drawable.default_image);
            try
            {
                viewOne.setTag(player.toJson().toString());
            }
            catch (JSONException e)
            {

            }

            bannerListView.add(viewOne);

            viewOne.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                     
                    String playerJSONString = (String) v.getTag();

                    try {
                        JSONObject jsonObject = new JSONObject(playerJSONString);
                        PLAYER player1 = PLAYER.fromJson(jsonObject);
                        
                        switch (player1.type) {
						case 1:
							Intent intent = new Intent(getActivity(), B2_ProductDetailActivity.class);
	                        intent.putExtra("good_id", Integer.parseInt(player1.link));
	                        getActivity().startActivity(intent);
	                        getActivity().overridePendingTransition(R.anim.push_right_in,
	                                R.anim.push_right_out);
							break;
						case 2:
							Intent intent1 = new Intent(getActivity(), B1_ProductListActivity.class);
							FILTER filter = new FILTER();
		                     filter.category_id = String.valueOf(player1.link);
							intent1.putExtra(B1_ProductListActivity.FILTER, filter.toJson().toString());
							startActivity(intent1);
			                getActivity().overridePendingTransition(R.anim.push_right_in,
			                        R.anim.push_right_out);
							break;
						case 3:
							Intent in = new Intent(getActivity(), WebViewActivity.class);
							in.putExtra("weburl", player1.link);
							startActivity(in);
							getActivity().overridePendingTransition(R.anim.push_buttom_in,R.anim.push_buttom_out);
							break;
						}

                    } catch (JSONException e) {

                    }

                }
            });

        }

        mIndicator.notifyDataSetChanged();
        mIndicator.setCurrentItem(0);
        bannerPageAdapter.mListViews = bannerListView;
        bannerViewPager.setAdapter(bannerPageAdapter);

    }

	private void makePopup(View parent){
		if (popup == null) {
			popView = LayoutInflater.from(getActivity()).inflate(R.layout.popup, null);
			popList = (ListView) popView.findViewById(R.id.popup_listView);
			
			PopupAdapter adapter = new PopupAdapter(getActivity(), dataModel.CateList);
			popList.setAdapter(adapter);
			
			popup = new PopupWindow(popView, 300, 840);
		}
		
		popup.setFocusable(true);
		popup.setOutsideTouchable(true);
		popup.setBackgroundDrawable(new BitmapDrawable());
//		popup.showAsDropDown(parent, getDisplayMetricsWidth()/2, 0);
		popup.showAtLocation(parent, Gravity.NO_GRAVITY, getDisplayMetricsWidth()/2, popup.getHeight());
		
		popList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(getActivity(), B1_ProductListActivity.class);
				FILT filt = new FILT();
				filt.cate_id = dataModel.CateList.get(arg2).cate_id+"";
				try {
					intent.putExtra("filter", filt.toJson().toString());
				} catch (JSONException e) {
					e.printStackTrace();
				}
				startActivity(intent);
				popup.dismiss();
			}
		});
	}

	@Override
	public void OnMessageResponse(String url, JSONObject jo, AjaxStatus status)
			throws JSONException {
		if (url.endsWith(ProtocolConst.HOMEDATA)) {
			addBannerView();
		} else if(url.endsWith(ProtocolConst.B2CLIST)) {
			if (listAdapter == null) {
				listAdapter = new B2CListAdapter(getActivity(), dataModel.DefList);
			}
			mListView.setAdapter(listAdapter);
			mListView.setRefreshTime();
			mListView.stopRefresh();
		}else if (url.endsWith(ProtocolConst.B2CCATE)) {
			
		}
	}

	@Override
	public void onRefresh(int id) {
		dataModel.fetchB2CList();
	}

	@Override
	public void onLoadMore(int id) {
		
	}
	
	//获取屏幕宽度
		public int getDisplayMetricsWidth() {
			int i = getActivity().getWindowManager().getDefaultDisplay().getWidth();
			int j = getActivity().getWindowManager().getDefaultDisplay().getHeight();
			return Math.min(i, j);
		}
		

}
