package com.example.frequencedivide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.hp.bean.DB;
import com.hp.bean.LinkTables;
import com.hp.bean.MyLinks;
import com.hp.constant.FinalData;
import com.hp.service.UserService;
import com.hp.tool.FreAdapter;

import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailInforsActivity extends Activity {

	private Context mContext;
	private String tv_bbName,tv_bbNum,tv_bbDate;
	private TextView m_tvName,m_tvNum,m_tvDate;
	private ListView listFrequence;
	private ListView listService,listAddation;
	private TextView clickView;
	
	ProgressDialog pgdDialog;
	ProgressDialog pgdDialog1;
	
	private String tv_current_fre;
	private String tv_current_union;
	private String ywdy = null;
	private String jznr = null;
	private String textYwmc = null;
	private String textJzh= null;
	
	UserService dao;
	int  flagdata ;
	int  whichSearch=0;
	List<LinkTables> linkTables = new ArrayList<LinkTables>();
	List<LinkTables> linkTables1 = new ArrayList<LinkTables>();
	List<MyLinks> myLinks = new ArrayList<MyLinks>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mContext = DetailInforsActivity.this;
	    setContentView(R.layout.listdetail);
	    init(); ///初始化控件
	    pgdDialog = new ProgressDialog(DetailInforsActivity.this);
	    pgdDialog1 = new ProgressDialog(DetailInforsActivity.this);
		toastMessage("正在加载此版本的频段......",Toast.LENGTH_SHORT-200,-150);
	   // pgdDialog.setMessage("正在加载此版本的频段......");
	 //   pgdDialog.show();
		new Thread(runnable).start();
		listFrequence.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
			    Toast.makeText(mContext, "所选择的频段为："+myLinks.get(arg2).getPlxx()+"-"+myLinks.get(arg2).getPlsx() , 500).show();
			    final List<String >  ywmcList =getYwList(myLinks.get(arg2).getYwList());
			    final List<String>   jzhmList = getJzList(myLinks.get(arg2).getPdjzList());
					ArrayAdapter<String > adapter = new ArrayAdapter<String >(DetailInforsActivity.this, android.R.layout.simple_list_item_1,ywmcList);
					listService.setAdapter(adapter);
					listService.setFastScrollAlwaysVisible(true);
					ArrayAdapter<String > adapter2 = new ArrayAdapter<String >(DetailInforsActivity.this, android.R.layout.simple_list_item_1,jzhmList);
					listAddation.setAdapter(adapter2);
					listAddation.setFastScrollAlwaysVisible(true);
					//监听业务名称信息
					listService.setOnItemClickListener(new OnItemClickListener() {
						@Override
						public void onItemClick(AdapterView<?> arg0, View arg1,
								int arg2, long arg3) {
							// TODO Auto-generated method stub
							flagdata = 1;
							textYwmc = ywmcList.get(arg2);
							new Thread(runnable).start();		
						}
					});	
					//监听校注信息
					listAddation.setOnItemClickListener(new OnItemClickListener() {
		
						@Override
						public void onItemClick(AdapterView<?> arg0, View arg1,
								int arg2, long arg3) {			
							// TODO Auto-generated method stub
							  
							    textJzh = jzhmList.get(arg2);
								flagdata = 2;
							    new Thread(runnable).start();			
						}
					});			
			}
		});
		}	
	//初始化
	private void init() {
		// TODO Auto-generated method stub
	    m_tvName = (TextView) findViewById(R.id.tx_name);
	    m_tvNum = (TextView) findViewById(R.id.tx_number);
	    m_tvDate = (TextView) findViewById(R.id.tx_date);    
	    clickView = (TextView) findViewById(R.id.btn_click);
	    listFrequence = (ListView) findViewById(R.id.frequence_list);
	    listService = (ListView) findViewById(R.id.service_list);
	    listAddation = (ListView) findViewById(R.id.addtion_list);
	    dao = new UserService();
		Bundle bundle = getIntent().getExtras();
		tv_bbNum = bundle.getString(DB.Tables.GJ_Hfbb.Fields.bbh);
		tv_bbName = bundle.getString(DB.Tables.GJ_Hfbb.Fields.bbmc);
		tv_bbDate = bundle.getString(DB.Tables.GJ_Hfbb.Fields.bbrq);
		if (FinalData.isSearched == 0) {
			flagdata = 0;

		}
		if (FinalData.isSearched == 1) {
			tv_current_fre = bundle.getString("frequency");
			flagdata = 3;
		}
		if(FinalData.isSearched == 2) {
			tv_current_fre = bundle.getString("frequency");
			tv_current_union =  bundle.getString("union");
			
			if (tv_current_union.equals("KHZ")) {
				FinalData.whichUnion = 1;
			}
			if (tv_current_union.equals("MHZ")) {
				FinalData.whichUnion = 2;
			}
			if (tv_current_union.equals("HZ")) {
				FinalData.whichUnion = 0;
			}
			flagdata = 3;
		}
		if (FinalData.isSearched == 3) {
			
			tv_current_union =  bundle.getString("union1");
			if (tv_current_union.equals("KHZ")) {
				FinalData.whichUnion = 1;
			}
			if (tv_current_union.equals("MHZ")) {
				FinalData.whichUnion = 2;
			}
			if (tv_current_union.equals("HZ")) {
				FinalData.whichUnion = 0;
			}
			flagdata = 0;
		}
		m_tvName.setText(tv_bbName);
		m_tvNum.setText(tv_bbNum);
		m_tvDate.setText(tv_bbDate);
	}

	//显示消息对话框
	private void toastMessage(String textshow,int duration,int height)
	{
		Toast toast = new Toast(this);	
		toast.setGravity(Gravity.CENTER, 0, height);
		ImageView  mView = new ImageView(DetailInforsActivity.this);
		mView.setImageResource(R.drawable.ic_launcher);		

		LinearLayout  layout = new LinearLayout(getApplicationContext());
		layout.setBackgroundColor(Color.BLACK);
		layout.addView(mView);
		TextView mTextView = new TextView(DetailInforsActivity.this);
		mTextView.setText(textshow);
		mTextView.setTextSize(20);
		mTextView.setTextColor(Color.WHITE);
		layout.addView(mTextView);
		toast.setView(layout);
	    toast.setDuration(duration);
		toast.show();
	}
	
	Runnable runnable =new Runnable() {		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			Message msg = new Message();
			if (flagdata == 0) {
				linkTables = dao.getLinkBybbh(tv_bbNum);
				msg.arg1 = 3;
			}
			if (flagdata == 3) {
				linkTables1 = dao.getLinkBybbhAndFre(tv_current_fre,tv_bbNum);
				msg.arg1 =4;///这里用于设置发送用频率搜索
			}
			else if (flagdata == 1) {
			   ywdy = dao.getYwdyBymc(textYwmc);
				msg.arg1 = 1;
			}else if(flagdata == 2){
				jznr = dao.getJznrByjzh(textJzh);
				msg.arg1 = 2;
			}
		    handler.sendMessage(msg);
		}
	};
	
	Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.arg1) {
			case 1:
				showDialogDetail(textYwmc,ywdy);
				break;
			case 2:
				showDialogDetail(textJzh,jznr);
				break;
			case 3:	
			//	pgdDialog.dismiss();
		        myLinks = dao.toMyLinks(linkTables);
				FreAdapter myAdapter = new FreAdapter(DetailInforsActivity.this, myLinks);
				listFrequence.setAdapter(myAdapter);
				toastMessage("请选择业务频段获取更多此频段信息",Toast.LENGTH_SHORT-200,0);	
			//	toastMessage("请选择业务频段获取更多此频段信息",Toast.LENGTH_SHORT-200,0);	
				break;
			case 4:
				///使用频率和版本号加载详细的显示信息
				myLinks = dao.toMyLinks(linkTables1);
				FreAdapter myAdapter1 = new FreAdapter(DetailInforsActivity.this, myLinks);
				listFrequence.setAdapter(myAdapter1);
				toastMessage("请选择业务频段获取更多此频段信息",Toast.LENGTH_SHORT-200,0);	
			  //  pgdDialog1.setMessage("请选择业务频段获取更多此频段信息");
			  //  pgdDialog1.cancel();
				break;
			default:
				break;
			}
		}
	};
	//显示业务定义和校注信息的
	private void showDialogDetail(String title, String content) {
		// TODO Auto-generated method stub
		AlertDialog.Builder dBuilder = new AlertDialog.Builder(DetailInforsActivity.this);
		dBuilder.setIcon(R.drawable.ic_launcher);
		dBuilder.setTitle(title);
		dBuilder.setMessage(content);
		dBuilder.setPositiveButton("确定", null);
		dBuilder.create().show();		
	}
	//去掉校注列表的重复数据
	private List<String> getJzList(List<String> pdjzList) {
	    List<String>   jzhmList = pdjzList;
	    List<String>  newjzList = new ArrayList<String>();
		 Map<String, Integer> map = new HashMap<String, Integer>();
		 for (int i = 0; i < jzhmList.size(); i++) {
			 String jzhm = jzhmList.get(i);
			 if (map.containsKey(jzhm)) {
				int index = map.get(jzhm);
				jzhm = jzhmList.get(index);
			}else {
				newjzList.add(jzhm);
				int index = newjzList.size() - 1;
				map.put(jzhm, index);
			}
		}
		return newjzList;
	}
	//去掉业务名称列表重复的数据
	private List<String> getYwList(List<String> ywlinkList){
	    List<String >  ywmcList = ywlinkList;
	    List<String > newywList =  new ArrayList<String>();

		 Map<String, Integer> map = new HashMap<String, Integer>();
		 for (int i = 0; i < ywmcList.size(); i++) {
			 String ywmc = ywmcList.get(i);

			 if (map.containsKey(ywmc)) {
				int index = map.get(ywmc);
			    ywmc = ywmcList.get(index);
			}else {
				newywList.add(ywmc);
				int index = newywList.size() - 1;
				map.put(ywmc, index);
			}
		}
		 return newywList;
	}

}
