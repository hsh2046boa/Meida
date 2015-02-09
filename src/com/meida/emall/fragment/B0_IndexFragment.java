package com.meida.emall.fragment;
//
//                       __
//                      /\ \   _
//    ____    ____   ___\ \ \_/ \           _____    ___     ___
//   / _  \  / __ \ / __ \ \    <     __   /\__  \  / __ \  / __ \
//  /\ \_\ \/\  __//\  __/\ \ \\ \   /\_\  \/_/  / /\ \_\ \/\ \_\ \
//  \ \____ \ \____\ \____\\ \_\\_\  \/_/   /\____\\ \____/\ \____/
//   \/____\ \/____/\/____/ \/_//_/         \/____/ \/___/  \/___/
//     /\____/
//     \/___/
//
//  Powered by BeeFramework
//

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.meida.emall.MeidaAppConst;
import com.meida.emall.MeidaApp;
import com.meida.emall.MeidaManager;
import com.meida.emall.MeidaManager.RegisterApp;
import com.meida.emall.activity.B1_ProductListActivity;
import com.meida.emall.activity.B2_ProductDetailActivity;
import com.meida.emall.activity.BannerWebActivity;
import com.meida.emall.activity.C0_ShoppingCartActivity;
import com.meida.emall.adapter.B0_IndexAdapter;
import com.meida.emall.adapter.Bee_PageAdapter;
import com.meida.emall.adapter.IndexAdapter;
import com.meida.emall.model.ConfigModel;
import com.meida.emall.model.HomeModel;
import com.meida.emall.model.LoginModel;
import com.meida.emall.model.ProtocolConst;
import com.meida.emall.model.ShoppingCartModel;
import com.meida.emall.protocol.FILTER;
import com.meida.emall.protocol.PLAYER;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.external.androidquery.callback.AjaxStatus;
import com.external.maxwin.view.XListView;
import com.external.viewpagerindicator.PageIndicator;
import com.insthub.BeeFramework.activity.WebViewActivity;
import com.insthub.BeeFramework.fragment.BaseFragment;
import com.insthub.BeeFramework.model.BusinessResponse;
import com.insthub.BeeFramework.view.MyListView;
import com.insthub.BeeFramework.view.PagerOutLayout;
import com.insthub.BeeFramework.view.SubViewPager;
import com.meida.emall.R;
import com.umeng.analytics.MobclickAgent;


public class B0_IndexFragment extends BaseFragment implements BusinessResponse,XListView.IXListViewListener, RegisterApp
{
    private ViewPager bannerViewPager;
    private PagerOutLayout mlayout;
    private PageIndicator mIndicator;
    private MyListView mListView;
//    private B0_IndexAdapter listAdapter;
    private IndexAdapter listAdapter;
//    private int currentItem = 0; // 当前图片的索引号
//    private ScheduledExecutorService scheduledExecutorService;
//    private static boolean REFRESH = false;

    
    private ArrayList<View> bannerListView;
    private Bee_PageAdapter bannerPageAdapter;
    LinearLayout bannerView;

//    private View mTouchTarget;
    private ShoppingCartModel shoppingCartModel;

	private HomeModel dataModel;
	
//	private ImageView back;
//	private TextView title;

	private Button sign;
	private Button cart;
	private Button center;
	
//    private SharedPreferences shared;
//	private SharedPreferences.Editor editor;
    protected ImageLoader imageLoader = ImageLoader.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        imageLoader.init(ImageLoaderConfiguration.createDefault(getActivity()));
        View mainView = inflater.inflate(R.layout.b0_index,null);
        
//        back = (ImageView) mainView.findViewById(R.id.top_view_back);
//        back.setVisibility(View.GONE);
//        title = (TextView) mainView.findViewById(R.id.top_view_text);
//        Resources resource = this.getResources();
//        String ecmobileStr=resource.getString(R.string.ecmobile);
//        title.setText(ecmobileStr);
        
        if (null == dataModel)
        {
            dataModel = new HomeModel(getActivity());
            dataModel.fetchHotSelling();
            dataModel.fetchCategoryGoods();
        }

        if (null == ConfigModel.getInstance())
        {
            ConfigModel configModel = new ConfigModel(getActivity());
            configModel.getConfig();
        }

        dataModel.addResponseListener(this);

        bannerView = (LinearLayout)LayoutInflater.from(getActivity()).inflate(R.layout.b0_index_banner, null);

        bannerViewPager = (ViewPager)bannerView.findViewById(R.id.banner_viewpager);
        
        mlayout = (PagerOutLayout) bannerView.findViewById(R.id.mylayout);
        mlayout.setChild_viewpager(bannerViewPager);
        
        LayoutParams params1 = bannerViewPager.getLayoutParams();
		params1.width = getDisplayMetricsWidth();
		params1.height = (int) (params1.width*1.0/484*200);
		
		bannerViewPager.setLayoutParams(params1);

        bannerListView = new ArrayList<View>();

        
        bannerPageAdapter = new Bee_PageAdapter(bannerListView);

        bannerViewPager.setAdapter(bannerPageAdapter);
        bannerViewPager.setCurrentItem(0);
        
//        bannerViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//
//            private int mPreviousState = ViewPager.SCROLL_STATE_IDLE;
//            @Override
//            public void onPageScrolled(int i, float v, int i2) {
//            	
//            }
//
//            @Override
//            public void onPageSelected(int i) {
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//                // All of this is to inhibit any scrollable container from consuming our touch events as the user is changing pages
//                if (mPreviousState == ViewPager.SCROLL_STATE_IDLE) {
//                    if (state == ViewPager.SCROLL_STATE_DRAGGING) {
//                        mTouchTarget = bannerViewPager;
//                    }
//                } else {
//                    if (state == ViewPager.SCROLL_STATE_IDLE || state == ViewPager.SCROLL_STATE_SETTLING) {
//                        mTouchTarget = null;
//                    }
//                }
//
//                mPreviousState = state;
//            }
//        });


        mIndicator = (PageIndicator)bannerView.findViewById(R.id.indicator);
        mIndicator.setViewPager(bannerViewPager); 
        
//        //设置小点的宽度
        LayoutParams params = mIndicator.getLayoutParams();
		params.width = 100;
		mIndicator.setLayoutParams(params);
        
		sign = (Button) bannerView.findViewById(R.id.sign);
		sign.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
			}
		});
		cart = (Button) bannerView.findViewById(R.id.cart);
		cart.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(), C0_ShoppingCartActivity.class));
				
			}
		});
		center = (Button) bannerView.findViewById(R.id.center);
		center.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
			}
		});

        mListView = (MyListView)mainView.findViewById(R.id.home_listview);
        mListView.addHeaderView(bannerView);
        mListView.bannerView = bannerView;

        mListView.setPullLoadEnable(false);
        mListView.setPullRefreshEnable(true);
        mListView.setXListViewListener(this,0);
        mListView.setRefreshTime();

        homeSetAdapter();

		ShoppingCartModel shoppingCartModel = new ShoppingCartModel(getActivity());
		shoppingCartModel.addResponseListener(this);
		shoppingCartModel.homeCartList();
		
        return mainView;
    }

	public boolean isActive = false;
    @Override
    public void onResume() {
        super.onResume();
       
        if (!isActive) {
            isActive = true;
            MeidaManager.registerApp(this);
        }
        
//        if (scheduledExecutorService.isShutdown()) {
//			scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
//		}
//    	scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1, 3, TimeUnit.SECONDS);
        
        LoginModel loginModel = new LoginModel(getActivity());
		
		ConfigModel configModel = new ConfigModel(getActivity());
        configModel.getConfig();
        MobclickAgent.onPageStart("Home");
    }

    public void homeSetAdapter() {
    	if(dataModel.homeDataCache() != null) {
          if (null == listAdapter)
          {
//              listAdapter = new B0_IndexAdapter(getActivity(), dataModel);
              listAdapter = new IndexAdapter(getActivity());

          }
          mListView.setAdapter(listAdapter);
          addBannerView();
    	}
    	
    	
    }

    public void OnMessageResponse(String url, JSONObject jo, AjaxStatus status)
    {
        if (url.endsWith(ProtocolConst.HOMEDATA))
        {
//            mListView.stopRefresh();
//            mListView.setRefreshTime();

            if (null == listAdapter)
            {
//                listAdapter = new B0_IndexAdapter(getActivity(), dataModel);
            	listAdapter = new IndexAdapter(getActivity());
            }
            mListView.setAdapter(listAdapter);
            addBannerView();
        }
        else if (url.endsWith(ProtocolConst.CATEGORYGOODS))
        {
            mListView.stopRefresh();
            mListView.setRefreshTime();

            if (null == listAdapter)
            {
//                listAdapter = new B0_IndexAdapter(getActivity(), dataModel);
            	listAdapter = new IndexAdapter(getActivity());
            }
            mListView.setAdapter(listAdapter);
            
//            if (REFRESH) {
//          	if (scheduledExecutorService.isShutdown()) {
//					scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
//				}
//				scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1, 3, TimeUnit.SECONDS);
//  		}
//            addBannerView();
        } 
        else if (url.endsWith(ProtocolConst.CARTLIST))
        {
        	TabsFragment.setShoppingcartNum();
		}
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroy() {    
    	super.onDestroy();
    	dataModel.removeResponseListener(this);
    }

    public void onRefresh(int id)
    {
//    	scheduledExecutorService.shutdown();
//        REFRESH = true;
        
        dataModel.fetchHotSelling();
        dataModel.fetchCategoryGoods();

    }

    @Override
    public void onLoadMore(int id) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
    
 // 切换当前显示的图片
//  	private Handler handler = new Handler() {
//  		public void handleMessage(android.os.Message msg) {
//  			bannerViewPager.setCurrentItem(currentItem);// 切换当前显示的图片
//  			mIndicator.setCurrentItem(currentItem);
//  		};
//  	};
  	
  	/**
 	 * 换行切换任务
 	 * 
 	 * @author Administrator
 	 * 
 	 */
// 	private class ScrollTask implements Runnable {
//
// 		public void run() {
// 			synchronized (bannerViewPager) {
// 				currentItem = (currentItem + 1) % dataModel.playersList.size();
// 				handler.obtainMessage().sendToTarget(); // 通过Handler切换图片
// 			}
// 		}
//
// 	}

    public void addBannerView()
    {
        bannerListView.clear();
        for (int i = 0; i < dataModel.playersList.size(); i++)
        {
            PLAYER player = dataModel.playersList.get(i);
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
    
	//获取屏幕宽度
	public int getDisplayMetricsWidth() {
		int i = getActivity().getWindowManager().getDefaultDisplay().getWidth();
		int j = getActivity().getWindowManager().getDefaultDisplay().getHeight();
		return Math.min(i, j);
	}


	@Override
	public void onRegisterResponse(boolean success) {
		 
	}



	@Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("Home");
    }
    
    @Override
	public void onStart() {
		super.onStart();
//		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
	}

	@Override
    public void onStop() {
    	 
    	super.onStop();
//    	scheduledExecutorService.shutdown();
    	if (!isAppOnForeground()) {
            //app 进入后台
            isActive = false;
        }
    }
    
    /**
     * 程序是否在前台运行
     *
     * @return
     */
    public boolean isAppOnForeground() {
        // Returns a list of application processes that are running on the
        // device

        ActivityManager activityManager = (ActivityManager) getActivity().getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        String packageName = getActivity().getApplicationContext().getPackageName();

        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        if (appProcesses == null) {
        	return false;
        }
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            // The name of the process that this object is associated with.
            if (appProcess.processName.equals(packageName)
                    && appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }

        return false;
    }


}
