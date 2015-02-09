package com.meida.emall.activity;
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

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.*;

import com.external.maxwin.view.XListView;
import com.iflytek.speech.RecognizerResult;
import com.iflytek.speech.SpeechError;
import com.iflytek.ui.RecognizerDialog;
import com.iflytek.ui.RecognizerDialogListener;
import com.insthub.BeeFramework.activity.BaseActivity;
import com.insthub.BeeFramework.view.MyViewGroup;
import com.meida.emall.R;
import com.meida.emall.MeidaManager;
import com.meida.emall.adapter.D0_CategoryAdapter;
import com.meida.emall.protocol.CATEGORY;
import com.meida.emall.protocol.FILTER;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class D1_CategoryActivity extends BaseActivity 
{

    private ImageView backButton;
    private TextView title;

    private XListView childListView;
    D0_CategoryAdapter childListAdapter;
    CATEGORY category ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.d1_category);

        
        backButton = (ImageView)findViewById(R.id.top_view_back);
        title = (TextView) findViewById(R.id.top_view_text);
        backButton.setVisibility(View.VISIBLE);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        childListView = (XListView)findViewById(R.id.child_list);
        childListView.setPullLoadEnable(false);
        childListView.setPullRefreshEnable(false);
        String categoryStr = getIntent().getStringExtra("category");
        title.setText(getIntent().getStringExtra("category_name"));
        
        try
        {
            JSONObject jsonObject = new JSONObject(categoryStr);
            CATEGORY category1 = CATEGORY.fromJson(jsonObject);
            this.category = category1;
            childListAdapter = new D0_CategoryAdapter(this,this.category.children);
            childListView.setAdapter(childListAdapter);
            childListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (position - 1 < category.children.size())
                    {
                        CATEGORY item = category.children.get(position - 1);

                        try
                        {
                            Intent intent = new Intent(D1_CategoryActivity.this, B1_ProductListActivity.class);
                            FILTER filter = new FILTER();
                            filter.category_id = String.valueOf(item.id);
                            intent.putExtra(B1_ProductListActivity.FILTER,filter.toJson().toString());
                            startActivity(intent);
                            overridePendingTransition(R.anim.push_right_in,
                                    R.anim.push_right_out);
                        }
                        catch (JSONException e)
                        {

                        }

                    }
                }
            });
        }
        catch (JSONException e)
        {

        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if(MeidaManager.getUmengKey(this)!=null){
            MobclickAgent.onPageStart("CategoryList");
            MobclickAgent.onResume(this, MeidaManager.getUmengKey(this),"");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(MeidaManager.getUmengKey(this)!=null){
            MobclickAgent.onPageEnd("CategoryList");
            MobclickAgent.onPause(this);
        }
    }
}
