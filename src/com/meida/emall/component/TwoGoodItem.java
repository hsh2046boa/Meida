package com.meida.emall.component;

import java.util.List;

import com.meida.emall.MeidaApp;
import com.meida.emall.R;
import com.meida.emall.activity.B2_ProductDetailActivity;
import com.meida.emall.model.ProtocolConst;
import com.meida.emall.protocol.DEFGOOD;
import com.meida.emall.protocol.SIMPLEGOODS;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TwoGoodItem extends LinearLayout {

	Context mContext;
    private ImageView good_cell_photo_one;
    private ImageView good_cell_photo_two;
    private TextView    good_cell_price_one;
    private TextView    good_cell_price_two;
    private TextView market_price_one;
    private TextView market_price_two;
    private TextView    good_cell_desc_one;
    private TextView    good_cell_desc_two;
    private LinearLayout good_cell_one;
    private LinearLayout  good_cell_two;
    
    protected ImageLoader imageLoader = ImageLoader.getInstance();
	public TwoGoodItem(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
	}
	
	void init()
    {
        if (null == good_cell_one)
        {
            good_cell_one = (LinearLayout)findViewById(R.id.good_item_one);
        }

        if (null == good_cell_two)
        {
            good_cell_two = (LinearLayout)findViewById(R.id.good_item_two);
        }

        if (null == good_cell_photo_one)
        {
            good_cell_photo_one = (ImageView)good_cell_one.findViewById(R.id.gooditem_photo);
        }

        if (null == good_cell_photo_two)
        {
            good_cell_photo_two = (ImageView)good_cell_two.findViewById(R.id.gooditem_photo);
        }

        if (null == good_cell_price_one)
        {
            good_cell_price_one = (TextView)good_cell_one.findViewById(R.id.shop_price);
        }

        if (null == good_cell_price_two)
        {
            good_cell_price_two = (TextView)good_cell_two.findViewById(R.id.shop_price);
        }

        if (null == good_cell_desc_one)
        {
            good_cell_desc_one = (TextView)good_cell_one.findViewById(R.id.good_desc);
        }

        if (null == good_cell_desc_two)
        {
            good_cell_desc_two = (TextView)good_cell_two.findViewById(R.id.good_desc);
        }
        
        if (null == market_price_one)
        {
        	market_price_one = (TextView)good_cell_one.findViewById(R.id.promote_price);
        	market_price_one.getPaint().setAntiAlias(true);
        	market_price_one.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }
        
        if (null == market_price_two)
        {
        	market_price_two = (TextView)good_cell_two.findViewById(R.id.promote_price);
        	market_price_two.getPaint().setAntiAlias(true);
        	market_price_two.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }

    }

    public void bindData(List<DEFGOOD> listData)
    {
        init();
        
        if (listData.size() >0)
        {
            final DEFGOOD goodOne = listData.get(0);
            if (null != goodOne && null != goodOne.default_image)
            {
            	imageLoader.displayImage(ProtocolConst.HOMELINK + goodOne.default_image,good_cell_photo_one, MeidaApp.options);
                
            }

            if (null!= goodOne.price&& goodOne.price.length() > 0)
            {
                good_cell_price_one.setText(goodOne.price);
            }
            else
            {
                good_cell_price_one.setText(goodOne.price);
            }


            market_price_one.setText(goodOne.price);
            good_cell_desc_one.setText(goodOne.goods_name);
            
            good_cell_photo_one.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					 
					Intent it = new Intent(mContext,B2_ProductDetailActivity.class);
		        	it.putExtra("good_id", Integer.valueOf(goodOne.goods_id));
		            mContext.startActivity(it);
				}
			});

            if (listData.size() >1)
            {
                good_cell_two.setVisibility(View.VISIBLE);
                final DEFGOOD goodTwo = listData.get(1);
                if (null != goodTwo && null != goodTwo.default_image)
                {
                	imageLoader.displayImage(ProtocolConst.HOMELINK + goodTwo.default_image,good_cell_photo_two, MeidaApp.options);
                }

                if (null != goodTwo.price && goodTwo.price.length() > 0)
                {
                    good_cell_price_two.setText(goodTwo.price);
                }
                else
                {
                    good_cell_price_two.setText(goodTwo.price);
                }

//                market_price_two.setText(goodTwo.price);
                good_cell_desc_two.setText(goodTwo.goods_name);
                good_cell_photo_two.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						 
						Intent it = new Intent(mContext,B2_ProductDetailActivity.class);
			        	it.putExtra("good_id", Integer.valueOf(goodTwo.goods_id));
			            mContext.startActivity(it);
					}
				});
                
            }
            else
            {
                good_cell_two.setVisibility(View.INVISIBLE);
            }
        }
    }

}
