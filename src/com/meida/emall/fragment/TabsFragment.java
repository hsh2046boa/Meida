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

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.meida.emall.R;
import com.meida.emall.activity.A0_SigninActivity;
import com.meida.emall.model.ShoppingCartModel;

@SuppressLint("NewApi")
public class TabsFragment extends Fragment
{

	TextView tab_one;
    TextView tab_two;
    TextView tab_three;
    
    Drawable one;
    Drawable oneAct;
    Drawable two;
    Drawable twoAct;
    Drawable three;
    Drawable threeAct;

    ImageView tab_onebg;
    ImageView tab_twobg;
    ImageView tab_threebg;
//    ImageView tab_fourbg;
    
    private static TextView shopping_cart_num;
    private static LinearLayout shopping_cart_num_bg;
    
	private SharedPreferences shared;
	private SharedPreferences.Editor editor;
//    B0_IndexFragment homeFragment;
    HomeFragment homeFragment;
    D0_CategoryFragment searchFragment;
    C0_ShoppingCartFragment shoppingCartFragment;
    E0_ProfileFragment profileFragment;

    public TabsFragment() {
    }

    /*
     * (non-Javadoc)
     *
     * 
     * android.view.ViewGroup, android.os.Bundle)
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.toolbar, container, false);
        initDrawable();
        init(mainView);
        
        shared = getActivity().getSharedPreferences("userInfo", 0); 
		editor = shared.edit();
        
        return mainView;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public void onActivityCreated(Bundle paramBundle) {
        super.onActivityCreated(paramBundle);
        setRetainInstance(true);
    }

    void init(View mainView) {
    	
    	TabsFragment.shopping_cart_num = (TextView) mainView.findViewById(R.id.shopping_cart_num);
    	TabsFragment.shopping_cart_num_bg = (LinearLayout) mainView.findViewById(R.id.shopping_cart_num_bg);

        this.tab_one = (TextView) mainView.findViewById(R.id.toolbar_tabone);
        this.tab_onebg = (ImageView) mainView.findViewById(R.id.toolbar_tabonebg);
        this.tab_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnTabSelected("tab_one");
            }
        });

        this.tab_two = (TextView) mainView.findViewById(R.id.toolbar_tabtwo);
        this.tab_twobg = (ImageView) mainView.findViewById(R.id.toolbar_tabtwobg);
        this.tab_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnTabSelected("tab_two");
            }
        });

        this.tab_three = (TextView) mainView.findViewById(R.id.toolbar_tabthree);
        this.tab_threebg = (ImageView) mainView.findViewById(R.id.toolbar_tabthreebg);
        this.tab_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnTabSelected("tab_three");
            }
        });


        OnTabSelected("tab_one");
        
        if (this.getDisplayMetricsWidth() < 540) {
    		tab_one.setTextSize(10);
    		tab_two.setTextSize(10);
    		tab_three.setTextSize(10);
		}
    }
    
    private void initDrawable(){
    	one = this.getResources().getDrawable(R.drawable.footer_home_icon);
    	oneAct = this.getResources().getDrawable(R.drawable.footer_home_active_icon);
    	two = this.getResources().getDrawable(R.drawable.footer_shopping_cart_icon);
    	twoAct = this.getResources().getDrawable(R.drawable.footer_shopping_cart_active_icon);
    	three = this.getResources().getDrawable(R.drawable.footer_user_icon);
    	threeAct = this.getResources().getDrawable(R.drawable.footer_user_active_icon);
    }
    
  //获取屏幕宽度
  	public int getDisplayMetricsWidth() {
  		int i = getActivity().getWindowManager().getDefaultDisplay().getWidth();
  		int j = getActivity().getWindowManager().getDefaultDisplay().getHeight();
  		return Math.min(i, j);
  	}

	void OnTabSelected(String tabName) {
        if (tabName == "tab_one") {

            if (null == homeFragment)
            {
//                homeFragment = new B0_IndexFragment();
                homeFragment = new HomeFragment();
            }

            FragmentTransaction localFragmentTransaction = getFragmentManager().beginTransaction();
            localFragmentTransaction.replace(R.id.fragment_container, homeFragment, "tab_one");
            localFragmentTransaction.commit();

            this.tab_one.setCompoundDrawablesRelativeWithIntrinsicBounds(null, oneAct, null, null);
            this.tab_two.setCompoundDrawablesRelativeWithIntrinsicBounds(null, two, null, null);
            this.tab_three.setCompoundDrawablesRelativeWithIntrinsicBounds(null, three, null, null);
            
            this.tab_one.setSelected(true);
            this.tab_two.setSelected(false);
            this.tab_three.setSelected(false);

            this.tab_onebg.setVisibility(View.VISIBLE);
            this.tab_twobg.setVisibility(View.INVISIBLE);
            this.tab_threebg.setVisibility(View.INVISIBLE);
//            this.tab_fourbg.setVisibility(View.INVISIBLE);

        }
        else if (tabName == "tab_two")
        {
            
            shoppingCartFragment = new C0_ShoppingCartFragment();
            

            FragmentTransaction localFragmentTransaction = getFragmentManager().beginTransaction();
            localFragmentTransaction.replace(R.id.fragment_container, shoppingCartFragment, "tab_two");
            localFragmentTransaction.commit();

            this.tab_one.setCompoundDrawablesRelativeWithIntrinsicBounds(null, one, null, null);
            this.tab_two.setCompoundDrawablesRelativeWithIntrinsicBounds(null, twoAct, null, null);
            this.tab_three.setCompoundDrawablesRelativeWithIntrinsicBounds(null, three, null, null);
            
            this.tab_one.setSelected(false);
            this.tab_two.setSelected(true);
            this.tab_three.setSelected(false);

            this.tab_onebg.setVisibility(View.INVISIBLE);
            this.tab_twobg.setVisibility(View.VISIBLE);
            this.tab_threebg.setVisibility(View.INVISIBLE);
//            this.tab_fourbg.setVisibility(View.INVISIBLE);
        }
        else if (tabName == "tab_three")
        {
//            String uid = shared.getString("uid", "");
//            if(uid.equals(""))
//            {
//                Intent intent = new Intent(getActivity(), LoginActivity.class);
//                startActivityForResult(intent, 2);
//                getActivity().overridePendingTransition(R.anim.push_buttom_in,R.anim.push_buttom_out);
//            }
//            else
//            {

	        	profileFragment = new E0_ProfileFragment();
	        	FragmentTransaction localFragmentTransaction = getFragmentManager().beginTransaction();
            	localFragmentTransaction.replace(R.id.fragment_container, profileFragment, "tab_three");
            	localFragmentTransaction.commit();

                this.tab_one.setCompoundDrawablesRelativeWithIntrinsicBounds(null, one, null, null);
                this.tab_two.setCompoundDrawablesRelativeWithIntrinsicBounds(null, two, null, null);
                this.tab_three.setCompoundDrawablesRelativeWithIntrinsicBounds(null, threeAct, null, null);
                
                this.tab_one.setSelected(false);
                this.tab_two.setSelected(false);
                this.tab_three.setSelected(true);

                this.tab_onebg.setVisibility(View.INVISIBLE);
                this.tab_twobg.setVisibility(View.INVISIBLE);
                this.tab_threebg.setVisibility(View.VISIBLE);
//                this.tab_fourbg.setVisibility(View.INVISIBLE);
//           }
        }
    }
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    	 
    	super.onActivityResult(requestCode, resultCode, data);
    	
    	//登录成功返回到个人主页
		if (requestCode == 1) {
			if (data != null) {
                if (null == profileFragment)
                {
                    profileFragment = new E0_ProfileFragment();
                }

				FragmentTransaction localFragmentTransaction = getFragmentManager().beginTransaction();
				localFragmentTransaction.replace(R.id.fragment_container,profileFragment, "tab_three");
				localFragmentTransaction.commit();

				this.tab_one.setCompoundDrawablesRelativeWithIntrinsicBounds(null, one, null, null);
	            this.tab_two.setCompoundDrawablesRelativeWithIntrinsicBounds(null, two, null, null);
	            this.tab_three.setCompoundDrawablesRelativeWithIntrinsicBounds(null, threeAct, null, null);
	            
	            this.tab_one.setSelected(false);
	            this.tab_two.setSelected(false);
	            this.tab_three.setSelected(true);
			}
		}
        else if (requestCode == 2)
        {
            if (null != data)
            {
                if (null == shoppingCartFragment)
                {
                    shoppingCartFragment = new C0_ShoppingCartFragment();
                }

                FragmentTransaction localFragmentTransaction = getFragmentManager().beginTransaction();
                localFragmentTransaction.replace(R.id.fragment_container, shoppingCartFragment, "tab_two");
                localFragmentTransaction.commit();

                this.tab_one.setCompoundDrawablesRelativeWithIntrinsicBounds(null, one, null, null);
                this.tab_two.setCompoundDrawablesRelativeWithIntrinsicBounds(null, twoAct, null, null);
                this.tab_three.setCompoundDrawablesRelativeWithIntrinsicBounds(null, three, null, null);
                
                this.tab_one.setSelected(false);
                this.tab_two.setSelected(true);
                this.tab_three.setSelected(false);

                this.tab_onebg.setVisibility(View.INVISIBLE);
                this.tab_twobg.setVisibility(View.INVISIBLE);
                this.tab_threebg.setVisibility(View.VISIBLE);
//                this.tab_fourbg.setVisibility(View.INVISIBLE);
            }

        }
    }
    
    @Override
    public void onResume() {
    	 
    	super.onResume();
//    	setShoppingcartNum();
    }
    
    public static void setShoppingcartNum() {
    	//shopping_cart_num.setText("12");
    	if(ShoppingCartModel.getInstance().goods_num == 0) {
    		shopping_cart_num_bg.setVisibility(View.GONE);
        } else {
        	shopping_cart_num_bg.setVisibility(View.VISIBLE);
        	shopping_cart_num.setText(ShoppingCartModel.getInstance().goods_num+"");
        }
    }
    
    
}