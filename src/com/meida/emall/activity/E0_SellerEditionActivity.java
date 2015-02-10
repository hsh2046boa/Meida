package com.meida.emall.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.external.androidquery.callback.AjaxStatus;
import com.external.maxwin.view.XListView;
import com.external.maxwin.view.XListView.IXListViewListener;
import com.insthub.BeeFramework.activity.BaseActivity;
import com.insthub.BeeFramework.model.BusinessResponse;
import com.insthub.BeeFramework.view.WebImageView;
import com.meida.emall.R;
import com.meida.emall.model.ProtocolConst;
import com.meida.emall.model.UserInfoModel;
import com.meida.emall.protocol.USER;
import com.umeng.analytics.MobclickAgent;

public class E0_SellerEditionActivity extends BaseActivity implements IXListViewListener, OnClickListener, BusinessResponse{
	private XListView    xlistView;
	private View         mHeader;
	private WebImageView photo;
	private ImageView    camera;
	private ImageView    mBackBtn;
	private Button       mOrdersBtn;
	private Button       mShopBtn;
	private Button       mMoneyBtn;
	private Button       mReleaseBtn;
	private LinearLayout memberLevelLayout;
    private TextView     memberLevelName;
    private ImageView    memberLevelIcon;
    private TextView     name;
	private String       uid;
	private boolean      isRefresh = false;
	private SharedPreferences shared;
	private SharedPreferences.Editor editor;
	private USER user;
	private UserInfoModel userInfoModel;
    private File file;
	private View view;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.e0_selleredition);
		
		initView();
	}
	
	private void initView(){
		mHeader = getLayoutInflater().inflate(R.layout.profile_seller_header, null);
		mReleaseBtn = (Button)findViewById(R.id.profile_seller);
		mBackBtn = (ImageView)findViewById(R.id.profile_back);
		xlistView = (XListView)findViewById(R.id.profile_list);
		xlistView.addHeaderView(mHeader);
		
		xlistView.setPullLoadEnable(false);
		xlistView.setRefreshTime();
		xlistView.setXListViewListener(this,1);
		xlistView.setAdapter(null);
		
		mOrdersBtn = (Button)mHeader.findViewById(R.id.profile_orders);
		mShopBtn = (Button)mHeader.findViewById(R.id.profile_favor);
		mMoneyBtn = (Button)mHeader.findViewById(R.id.profile_money);
		photo = (WebImageView)mHeader.findViewById(R.id.profile_head_photo);
		camera = (ImageView)mHeader.findViewById(R.id.profile_head_camera);
		memberLevelLayout = (LinearLayout)mHeader.findViewById(R.id.member_level_layout);
        memberLevelName   = (TextView)mHeader.findViewById(R.id.member_level);
        memberLevelIcon   = (ImageView)mHeader.findViewById(R.id.member_level_icon);
        name = (TextView) mHeader.findViewById(R.id.profile_head_name);
		
		mReleaseBtn.setOnClickListener(this);
		mBackBtn.setOnClickListener(this);
		mOrdersBtn.setOnClickListener(this);
		mShopBtn.setOnClickListener(this);
		mMoneyBtn.setOnClickListener(this);
		photo.setOnClickListener(this);
		camera.setOnClickListener(this);
		
		shared = getSharedPreferences("userInfo", 0); 
		editor = shared.edit();
		
		File files = new File(getCacheDir()+"/Emall/cache"+"/temp.jpg");
		if(files.exists()) {
			photo.setImageBitmap(BitmapFactory.decodeFile(getCacheDir()+"/Emall/cache"+"/temp.jpg"));
		} else {
			photo.setImageResource(R.drawable.profile_no_avarta_icon);
		}
		
		if (null == userInfoModel)
        {
            userInfoModel = new UserInfoModel(this);
        }
        userInfoModel.addResponseListener(this);
		
		uid = shared.getString("uid", "");
		if (uid.equals("")) {
            String click = getResources().getString(R.string.click_to_login);
			name.setText(click);
			camera.setVisibility(View.GONE);
            memberLevelLayout.setVisibility(View.GONE);

		} else {
			userInfoModel.getUserInfo();
			camera.setVisibility(View.VISIBLE);
            memberLevelLayout.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void OnMessageResponse(String url, JSONObject jo, AjaxStatus status)
			throws JSONException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent;
		switch(v.getId()){
			case R.id.profile_seller:
				break;
			case R.id.profile_back:
				E0_SellerEditionActivity.this.finish();
				break;
			case R.id.profile_orders:
				break;
			case R.id.profile_favor:
				break;
			case R.id.profile_money:
				if (uid.equals("")) {
					isRefresh = true;
					intent = new Intent(E0_SellerEditionActivity.this, A0_SigninActivity.class);
					startActivity(intent);
	            	overridePendingTransition(R.anim.push_buttom_in,R.anim.push_buttom_out);
				} else {
					intent = new Intent(E0_SellerEditionActivity.this,MoneyActivity.class);
				    startActivity(intent);
	                overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
				}
			    
				break;
			case R.id.profile_head_photo:
				 if (uid.equals("")) {
		 				isRefresh = true;
		 				intent = new Intent(E0_SellerEditionActivity.this, A0_SigninActivity.class);
		             	startActivity(intent);
		             	overridePendingTransition(R.anim.push_buttom_in,R.anim.push_buttom_out);
		 			 }
				break;
			case R.id.profile_head_camera:
				if (uid.equals("")) {
					isRefresh = true;
					intent = new Intent(E0_SellerEditionActivity.this, A0_SigninActivity.class);
					startActivity(intent);
	            	overridePendingTransition(R.anim.push_buttom_in,R.anim.push_buttom_out);
				} else {
					intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);  
		            startActivityForResult(intent, 1);
	                overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
				}
				break;
		}
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {   
        super.onActivityResult(requestCode, resultCode, data);  
        
        if (resultCode == Activity.RESULT_OK) {
        	if(requestCode == 1) {
        		String sdStatus = Environment.getExternalStorageState();
                Bundle bundle = data.getExtras();
                Bitmap bitmap = (Bitmap) bundle.get("data");// 获取相机返回的数据，并转换为Bitmap图片格式
                if (file == null) {
					file = new File(ProtocolConst.FILEPATH);
					if (!file.exists()) {
						file.mkdirs();
					}
				}
                FileOutputStream b = null;
                String fileName = getCacheDir()+"/Emall/cache" +"/temp.jpg";
                try {
                    b = new FileOutputStream(fileName);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        b.flush();
                        b.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                ((ImageView) findViewById(R.id.profile_head_photo)).setImageBitmap(bitmap);// 将图片显示在ImageView里
        	}

        }
        
        if(requestCode == 2) {
        	userInfoModel.getUserInfo();
    	}
    	
    	
    }  
	
	@Override
    public void onDestroy() {
        userInfoModel.removeResponseListener(this);
        super.onDestroy();
    }
    @Override
    public void onPause() {
         
        super.onPause();
        MobclickAgent.onPageEnd("Profile");
    }

	@Override
	public void onRefresh(int id) {
		// TODO Auto-generated method stub
		if (!uid.equals("")) {
			userInfoModel.getUserInfo();
		}
	}

	@Override
	public void onLoadMore(int id) {
		// TODO Auto-generated method stub
		
	}

}