package com.meida.emall.activity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.external.androidquery.callback.AjaxStatus;
import com.insthub.BeeFramework.activity.BaseActivity;
import com.insthub.BeeFramework.model.BusinessResponse;
import com.meida.emall.R;
import com.meida.emall.model.FairAddModel;
import com.meida.emall.model.GcategorygoodModel;
import com.meida.emall.model.ProtocolConst;
import com.meida.emall.protocol.FAIRADD;
import com.meida.emall.protocol.GCATEGORYGOOD;
import com.meida.emall.protocol.STATUS;
/**
 * 商品新增、修改
 * @author Administrator
 *
 */
public class E0_AddGoodsActivity extends BaseActivity implements OnClickListener, BusinessResponse{
	private ImageView mBtnBack;
	private Button mBtnSelect;
	private Button mBtnPhoto;
	private Button mBtnBox;
	private Button mBtnSubmit;
	private EditText mEditClassification;
	private EditText mEditName;
	private EditText mEditBrand;
	private EditText mEditLabel;
	private EditText mEditInventory;
	private EditText mEditPrice;
	private EditText mEditFreight;
	private EditText mEditLocation;
	private EditText mEditNo;
	private EditText mEditDescription;
	private EditText mEditPicture;
	private ProgressDialog pd = null;
	
	private String country_id="";
	private String province_id="";
	private String city_id="";
	private String county_id="";
	
	private String country_name="";
	private String province_name="";
	private String city_name="";
	private String county_name="";
	
	private int category_id = 0;
	private String category_name="";
	private FairAddModel model;
	private final static int CONSULT_DOC_PICTURE = 1000;
	private final static int CONSULT_DOC_CAMERA = 1001;
	
	private Uri outputFileUri;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_addgoods);
		
		initView();
	}
	
	private void initView(){
		mBtnBack = (ImageView)findViewById(R.id.profile_back);
		mBtnSelect = (Button)findViewById(R.id.add_select);
		mBtnPhoto = (Button)findViewById(R.id.add_photo);
		mBtnBox = (Button)findViewById(R.id.add_save);
		mBtnSubmit = (Button)findViewById(R.id.add_submit);
		mEditClassification = (EditText)findViewById(R.id.add_classification);
		mEditName = (EditText)findViewById(R.id.add_name);
		mEditBrand = (EditText)findViewById(R.id.add_brand);
		mEditLabel = (EditText)findViewById(R.id.add_label);
		mEditInventory = (EditText)findViewById(R.id.add_inventory);
		mEditPrice = (EditText)findViewById(R.id.add_price);
		mEditFreight = (EditText)findViewById(R.id.add_freight);
		mEditLocation = (EditText)findViewById(R.id.add_location);
		mEditNo = (EditText)findViewById(R.id.add_no);
		mEditDescription = (EditText)findViewById(R.id.add_description);
		mEditPicture = (EditText)findViewById(R.id.add_picture);
		
		mBtnBack.setOnClickListener(this);
		mBtnSelect.setOnClickListener(this);
		mBtnPhoto.setOnClickListener(this);
		mBtnBox.setOnClickListener(this);
		mBtnSubmit.setOnClickListener(this);
		mEditClassification.setOnClickListener(this);
		mEditLocation.setOnClickListener(this);
		
		model = new FairAddModel(this);
		model.addResponseListener(this);
		
		if("Edit".equals("")){
			mBtnBox.setVisibility(View.GONE);
			((TextView)findViewById(R.id.edit_title)).setText("编辑");
			String goods_id = getIntent().getStringExtra("goods_id");
			model.getFairOne(goods_id);
			
			String hold=getResources().getString(R.string.hold_on);
			pd = new ProgressDialog(E0_AddGoodsActivity.this);
			pd.setMessage(hold);
			pd.show();
		}
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
        switch(v.getId()){
	        case R.id.profile_back:
	        	E0_AddGoodsActivity.this.finish();
	        	break;
	        case R.id.add_select:
	        	Intent select = new Intent(Intent.ACTION_GET_CONTENT);
	        	select.addCategory(Intent.CATEGORY_OPENABLE);
	        	select.setType("image/*");
	        	startActivityForResult(Intent.createChooser(select, "选择图片"), CONSULT_DOC_PICTURE);
	        	break;
	        case R.id.add_photo:
	        	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	        	String time = sdf.format(new Date());
	        	File file = new File(Environment.getExternalStorageDirectory()+"/emal/pic",time+".jpg");
	        	outputFileUri = Uri.fromFile(file);
	        	Intent pic = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	        	pic.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
	        	startActivityForResult(pic, CONSULT_DOC_CAMERA);
	        	break;
	        case R.id.add_save:
	        case R.id.add_submit:
	        	String classification = mEditClassification.getText().toString();
	        	String name = mEditName.getText().toString();
	        	String brand = mEditBrand.getText().toString();
	        	String label = mEditLabel.getText().toString();
	        	String inventory = mEditInventory.getText().toString();
	        	String price = mEditPrice.getText().toString();
	        	String freiString = mEditFreight.getText().toString();
	        	String location = mEditLocation.getText().toString();
	        	String no = mEditNo.getText().toString();
	        	String descript = mEditDescription.getText().toString();
	        	String picture = mEditPicture.getText().toString();
	        	if(TextUtils.isEmpty(classification)){
	        		Toast.makeText(E0_AddGoodsActivity.this, "商品类别不能为空", Toast.LENGTH_LONG).show();
	        		return;
	        	}
	        	if(TextUtils.isEmpty(name)){
	        		Toast.makeText(E0_AddGoodsActivity.this, "商品名称不能为空", Toast.LENGTH_LONG).show();
	        		return;
	        	}
	        	if(TextUtils.isEmpty(brand)){
	        		Toast.makeText(E0_AddGoodsActivity.this, "品牌不能为空", Toast.LENGTH_LONG).show();
	        		return;
	        	}
	        	if(TextUtils.isEmpty(label)){
	        		Toast.makeText(E0_AddGoodsActivity.this, "标签不能为空", Toast.LENGTH_LONG).show();
	        		return;
	        	}
	        	if(TextUtils.isEmpty(inventory)){
	        		Toast.makeText(E0_AddGoodsActivity.this, "库存量不能为空", Toast.LENGTH_LONG).show();
	        		return;
	        	}
	        	if(TextUtils.isEmpty(price)){
	        		Toast.makeText(E0_AddGoodsActivity.this, "价格不能为空", Toast.LENGTH_LONG).show();
	        		return;
	        	}
	        	if(TextUtils.isEmpty(freiString)){
	        		Toast.makeText(E0_AddGoodsActivity.this, "运费不能为空", Toast.LENGTH_LONG).show();
	        		return;
	        	}
	        	if(TextUtils.isEmpty(location)){
	        		Toast.makeText(E0_AddGoodsActivity.this, "所在地不能为空", Toast.LENGTH_LONG).show();
	        		return;
	        	}
	        	if(TextUtils.isEmpty(no)){
	        		Toast.makeText(E0_AddGoodsActivity.this, "货号不能为空", Toast.LENGTH_LONG).show();
	        		return;
	        	}
	        	if(TextUtils.isEmpty(descript)){
	        		Toast.makeText(E0_AddGoodsActivity.this, "商品描述不能为空", Toast.LENGTH_LONG).show();
	        		return;
	        	}
	        	if(TextUtils.isEmpty(picture)){
	        		Toast.makeText(E0_AddGoodsActivity.this, "图片不能为空", Toast.LENGTH_LONG).show();
	        		return;
	        	}
	        	FAIRADD add = new FAIRADD();
	        	add.goods_id = 0;
	        	add.category = category_id+"";
	        	add.goods_name = name;
	        	add.brand = brand;
	        	add.label = label;
	        	add.inventory = inventory;
	        	add.price = price;
	        	add.freight = freiString;
	        	add.location = location;
	        	add.no = no;
	        	add.description = descript;
	        	add.imagepath = picture;
	        	dealFairAdd(add,v);
	        	break;
	        case R.id.add_location:
	        	Intent dial = new Intent(E0_AddGoodsActivity.this,F3_RegionPickActivity.class);
	        	startActivityForResult(dial, 10000);
	        	break;
	        case R.id.add_classification:
	        	Intent category = new Intent(E0_AddGoodsActivity.this,E0_GcategoryGoodActivity.class);
	        	startActivityForResult(category, 10001);
	        	break;
        }
	}
	
	private void dealFairAdd(FAIRADD add,View v){
		int tag = (Integer)v.getTag();
		switch(tag){
			case 1:
				if(add != null){
					add.save();
					Intent intent = new Intent(E0_AddGoodsActivity.this,E0_DraftboxActivity.class);
					startActivity(intent);
					E0_AddGoodsActivity.this.finish();
				}
				break;
			case 2:
				model.sendFairAdd(add);
				
				String hold=getResources().getString(R.string.hold_on);
				pd = new ProgressDialog(E0_AddGoodsActivity.this);
				pd.setMessage(hold);
				pd.show();
				break;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(data == null){
			return;
		}
		if(resultCode == Activity.RESULT_OK){
			if(requestCode == 10000){
				country_id = data.getStringExtra("country_id");
				province_id = data.getStringExtra("province_id");
				city_id = data.getStringExtra("city_id");
				county_id = data.getStringExtra("county_id");
				
				country_name = data.getStringExtra("country_name");
				province_name = data.getStringExtra("province_name");
				city_name = data.getStringExtra("city_name");
				county_name = data.getStringExtra("county_name");
			}else if(requestCode == 10001){
				category_id = data.getIntExtra("country_id",0);
				category_name = data.getStringExtra("country_name");
			}else if(requestCode == CONSULT_DOC_PICTURE){
				Uri uri = data.getData();
				String[] proj = {MediaStore.Images.Media.DATA};
				Cursor cursor = managedQuery(uri, proj, null, null, null);
				int column = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
				cursor.moveToFirst();
				String path = cursor.getString(column);
				mEditPicture.setText(path);
			}else if(requestCode == CONSULT_DOC_CAMERA){
				String path = outputFileUri.getPath();
				mEditPicture.setText(path);
			}
		}
		
		
	}

	@Override
	public void OnMessageResponse(String url, JSONObject jo, AjaxStatus status)
			throws JSONException {
		// TODO Auto-generated method stub
		if(pd.isShowing()) {
			pd.dismiss();
		}
        if(url.endsWith(ProtocolConst.GoodsAdd)){
        	try {
				STATUS responseStatus = STATUS.fromJson(jo.optJSONObject("status"));
				if(responseStatus.succeed == 1) {
					JSONObject dataJson = jo.optJSONObject("data");
                    if (null != dataJson ) {
                    	String goods_id = dataJson.optString("goods_id");
                    	if(goods_id != null){
                    		String picture = mEditPicture.getText().toString();
                    		model.sendFairAddPic(goods_id, picture);
                    		
                    		String hold=getResources().getString(R.string.hold_on);
            				pd = new ProgressDialog(E0_AddGoodsActivity.this);
            				pd.setMessage(hold);
            				pd.show();
                    	}
                    }
				}
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
        }
		if(url.endsWith(ProtocolConst.GoodsAddImage)){
			Toast.makeText(E0_AddGoodsActivity.this, "提交成功", Toast.LENGTH_LONG).show();
		}
		if(url.endsWith(ProtocolConst.GoodsGetOne)){
			
			mEditClassification.setText("");
        	mEditName.setText("");
        	mEditBrand.setText("");
        	mEditLabel.setText("");
        	mEditInventory.setText("");
        	mEditPrice.setText("");
        	mEditFreight.setText("");
        	mEditLocation.setText("");
        	mEditNo.setText("");
        	mEditDescription.setText("");
        	mEditPicture.setText("");
		}
	}

}
