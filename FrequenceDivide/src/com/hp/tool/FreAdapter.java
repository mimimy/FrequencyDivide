package com.hp.tool;

import java.util.ArrayList;
import java.util.List;

import com.example.frequencedivide.R;
import com.hp.bean.GJ_county;
import com.hp.bean.GJ_ywpd;
import com.hp.bean.MyLinks;
import com.hp.constant.FinalData;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FreAdapter extends BaseAdapter {
	private Context mContext;
	private List<MyLinks> m_nameList  = new ArrayList<MyLinks>();
	private LayoutInflater mInflater;
	public FreAdapter(Context ctx ,List<MyLinks>nameList) {
		// TODO Auto-generated constructor stub
		mContext = ctx;
		m_nameList = nameList;
		mInflater  = LayoutInflater.from(ctx);//与findviewbyid相同，但这里是加载xml，并实例化。
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return m_nameList.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return m_nameList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ItemViewTag itemTag;
		if (arg1 == null) {
			arg1 = mInflater.inflate(R.layout.frequencelist, null);
			itemTag = new ItemViewTag((TextView)arg1.findViewById(R.id.plxx), 
					(TextView)arg1.findViewById(R.id.plsx),(ImageView) arg1.findViewById(R.id.myImage));
			arg1.setTag(itemTag);//给view设置标签，标签可以是任意对象
			
		}
		else {
			itemTag = (ItemViewTag) arg1.getTag();
		}
		MyLinks myLinks =  m_nameList.get(arg0);
		if (FinalData.isSearched == 2) {
			Double datax = Double.parseDouble(myLinks.getPlxx());
			Double datas = Double.parseDouble(myLinks.getPlsx());		
			if (FinalData.whichUnion ==1) {      
			       String mdaString = String.valueOf(datax/1000);
			       String sdaString = String.valueOf(datas/1000);
					itemTag.nameView.setText(mdaString+"KHZ");
					itemTag.valueView.setText(sdaString+"KHZ");
			}else if (FinalData.whichUnion ==2) {
			       String mdaString = String.valueOf(datax/1000000);
			       String sdaString = String.valueOf(datas/1000000);
					itemTag.nameView.setText(mdaString+"MHZ");
					itemTag.valueView.setText(sdaString+"MHZ");
			}else {
				itemTag.nameView.setText(myLinks.getPlxx()+"HZ");
				itemTag.valueView.setText(myLinks.getPlsx()+"HZ");
			}
		}else if (FinalData.isSearched == 3) {
			Double datax = Double.parseDouble(myLinks.getPlxx());
			Double datas = Double.parseDouble(myLinks.getPlsx());		    
			if (FinalData.whichUnion ==1) { 
			       String mdaString = String.valueOf(datax/1000);
			       String sdaString = String.valueOf(datas/1000);
					itemTag.nameView.setText(mdaString+"KHZ");
					itemTag.valueView.setText(sdaString+"KHZ");
			}else if (FinalData.whichUnion ==2) {      
			       String mdaString = String.valueOf(datax/1000000);
			       String sdaString = String.valueOf(datas/1000000);
					itemTag.nameView.setText(mdaString+"MHZ");
					itemTag.valueView.setText(sdaString+"MHZ");
			}else {
				itemTag.nameView.setText(myLinks.getPlxx()+"HZ");
				itemTag.valueView.setText(myLinks.getPlsx()+"HZ");
			}
		}
		else {
			itemTag.nameView.setText(myLinks.getPlxx()+"HZ");
			itemTag.valueView.setText(myLinks.getPlsx()+"HZ");
		}

		return arg1;
	}
class ItemViewTag{
	protected TextView nameView;
	protected TextView valueView;
	protected ImageView imageView;
	public ItemViewTag(TextView nameVw,TextView valueVw,ImageView mView) {
		// TODO Auto-generated constructor stub
		this.nameView = nameVw;
		this.valueView = valueVw;
		this.imageView = mView;
	}
}
}
