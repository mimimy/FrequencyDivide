package com.example.frequencedivide;

import java.util.ArrayList;
import java.util.List;

import com.hp.bean.*;
import com.hp.constant.FinalData;
import com.hp.service.UserService;
import com.hp.tool.CityGridAdapter;
import com.hp.tool.HfbbAdapter;

import android.R.color;
import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

public class ManagerAcitvity extends Activity {

	private ArrayList<GJ_county> counties;
	private List<GJ_Hfbb> hfbbs;

	TextView  tv_coutry; //国家
	TextView tv_version;//版本
	TextView tv_frequence;//频率
	TextView tv_union;//频率单位
	TextView tv_service;//业务
	Button btn_search;

	String tx_gjdm = ""; //保存国家代码
	String tx_bbh = ""; //保存版本号
	String tx_bbmc="";
	String tx_bbrq ="";
	String tx_frence ="";
	String tx_union ="";

	UserService userService ;
	boolean flag; //判断进入哪个线程操作


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.manage);

		init();
		buttonClicked();
	}
	/**
	 * 初始化
	 */
	private void init() {
		// TODO Auto-generated method stub
		tv_coutry = (TextView) findViewById(R.id.edt_coutry);
		tv_version = (TextView) findViewById(R.id.edt_version);
		tv_frequence = (TextView) findViewById(R.id.edt_fre);
		tv_union = (TextView) findViewById(R.id.edt_unit);
		tv_union.setTextColor(Color.BLACK);
		tv_service = (TextView) findViewById(R.id.edt_service);
		btn_search = (Button) findViewById(R.id.btn_search);
		userService = new UserService();
		hfbbs = new ArrayList<GJ_Hfbb>();
	}
	/**
	 * 点击事件
	 */
	private void buttonClicked() {
		// TODO Auto-generated method stub
		tv_coutry.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				flag = true;
				new Thread(runnable).start();
			}
		});
		tv_version.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (validate1()) {
					flag = false;
					new Thread(runnable).start();
				}
			}
		});
		btn_search.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				tx_frence = tv_frequence.getText().toString();
				tx_union = tv_union.getText().toString();
				if (validate1() &&tx_frence.equals("")&&tx_union.equals("") ) {
					FinalData.isSearched = 0;
				}
				if (validate1() &&(!tx_frence.equals(""))&&tx_union.equals("")) {
					FinalData.isSearched = 1;
				}
			
				if (validate1() &&(!tx_frence.equals(""))&&(!tx_union.equals(""))) {
					FinalData.isSearched = 2;
				}
				if (validate1() &&(tx_frence.equals(""))&&(!tx_union.equals(""))) {
					FinalData.isSearched = 3;
				}
				Intent intent  = new Intent(ManagerAcitvity.this,DetailInforsActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString(DB.Tables.GJ_Hfbb.Fields.bbh, tx_bbh);
				bundle.putString(DB.Tables.GJ_Hfbb.Fields.bbmc, tx_bbmc);
				bundle.putString(DB.Tables.GJ_Hfbb.Fields.bbrq, tx_bbrq);
				if (FinalData.isSearched == 0) {
					
				}
				if (FinalData.isSearched == 1) {
					bundle.putString("frequency",tx_frence);
				}
				if (FinalData.isSearched == 2) {
					bundle.putString("frequency",tx_frence);
					bundle.putString("union",tx_union);
				}
				if (FinalData.isSearched == 3) {
					bundle.putString("union1",tx_union);
				}
				intent.putExtras(bundle);
				startActivity(intent);///继续写接受函数
			}
		});

	   tv_union.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			showDialogUion();
		}
	});
	
	}	

	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			if (msg.arg1 == 1) {
				showDialogCountry();
			}
			else {
				showDialogHfbbs();
			}
		}
	};
	
	Runnable runnable =new Runnable() {
		public void run() {
			Message msg = new Message();
			if (flag) {
				msg.arg1 = 1;
				counties =  userService.getCountry();//获取国家
			}else {
				msg.arg1 = 0;
				hfbbs = userService.getHfbbs(tx_gjdm);
			}
			handler.sendMessage(msg);
		}
		
	};	
	//国家代码非空判断
	//用于验证账号和密码不为空
	private boolean validate1() {
		 String county = tv_coutry.getText().toString();
		 String version = tv_version.getText().toString();
		 if (county.equals("")) {
			Toast.makeText(ManagerAcitvity.this, "请选择国家", Toast.LENGTH_SHORT).show();
			finish();
		}
		 else if(version.equals("")){
			Toast.makeText(ManagerAcitvity.this, "请选则版本名称", Toast.LENGTH_SHORT).show();
		}
		return true;	
	}	
	/**
	 *自定义对话框 显示版本号
	 */
	private void showDialogHfbbs() {
		// TODO Auto-generated method stub
		LinearLayout bbsLayout = new LinearLayout(ManagerAcitvity.this);
		bbsLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
		final ListView mView = new ListView(ManagerAcitvity.this);
		mView.setFadingEdgeLength(0);
	    HfbbAdapter mAdapter = new HfbbAdapter(getApplicationContext(), hfbbs);
	    mView.setAdapter(mAdapter);
	    mView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
	    bbsLayout.addView(mView);
		final AlertDialog dialog = new AlertDialog.Builder(ManagerAcitvity.this)
					.setView(bbsLayout).create();///用警告对话框加载layout
		dialog.setCanceledOnTouchOutside(false);
		dialog.show();	
	    mView.setOnItemClickListener(new OnItemClickListener() {   	
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				arg1 = mView.getAdapter().getView(arg2, arg1, arg0);
				TextView  mTextView = (TextView)arg1. findViewById(R.id.grid_text);
				TextView mvalueView = (TextView)arg1. findViewById(R.id.grid_value);//国家代码
				dialog.cancel();
				tx_bbmc = mTextView.getText().toString();
				tv_version.setText(tx_bbmc);
				tx_bbh = mvalueView.getText().toString();
				tx_bbrq = hfbbs.get(arg2).getBbrq();
			}
		});	    
	}	
	//显示国家列表
	private void showDialogCountry() {
		LinearLayout couLayout = new LinearLayout(ManagerAcitvity.this);
		couLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
		final ListView mView = new ListView(ManagerAcitvity.this);
		mView.setFadingEdgeLength(0);
	    CityGridAdapter mAdapter = new CityGridAdapter(getApplicationContext(), counties);
	    mView.setAdapter(mAdapter);
	    mView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
	    couLayout.addView(mView);
		final AlertDialog dialog = new AlertDialog.Builder(ManagerAcitvity.this)
					.setView(couLayout).create();///用警告对话框加载layout
		dialog.setCanceledOnTouchOutside(false);
		dialog.show();	
	    mView.setOnItemClickListener(new OnItemClickListener() {   	
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				arg1 = mView.getAdapter().getView(arg2, arg1, arg0);
				TextView  mTextView = (TextView)arg1. findViewById(R.id.grid_text);
				TextView mvalueView = (TextView)arg1. findViewById(R.id.grid_value);//国家代码
				dialog.cancel();
				tv_coutry.setText(mTextView.getText().toString());
				tx_gjdm = mvalueView.getText().toString();
			}
		});	    
	}
	//显示单位
	private void showDialogUion() {

		final List<String > dataList = new ArrayList<String>();
		dataList.add("HZ");
		dataList.add("KHZ");
		dataList.add("MHZ");
		LinearLayout couLayout = new LinearLayout(ManagerAcitvity.this);
		couLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
		final ListView mView = new ListView(ManagerAcitvity.this);
		mView.setFadingEdgeLength(0);
		couLayout.setBackgroundColor(Color.BLUE);
		ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,dataList);	
	    mView.setAdapter(mAdapter);
	//    mView.setDividerHeight(R.drawable.abc_ab_bottom_solid_dark_holo);
	    mView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
	    couLayout.addView(mView);
		final AlertDialog dialog = new AlertDialog.Builder(ManagerAcitvity.this)
					.setView(couLayout).create();///用警告对话框加载layout
		dialog.setCanceledOnTouchOutside(false);
		dialog.show();		
	    mView.setOnItemClickListener(new OnItemClickListener() {   	
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				arg1 = mView.getAdapter().getView(arg2, arg1, arg0);
			//	TextView  mTextView = (TextView)arg1. findViewById(R.id.myunion);
				dialog.cancel();
			   tv_union.setText(dataList.get(arg2));
				//tv_union.setText("nihao");
			}
		});	    
	}	
	
	
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fre_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
