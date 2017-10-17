package com.hp.tool;

import java.util.ArrayList;
import java.util.List;

import com.example.frequencedivide.R;
import com.hp.bean.GJ_Hfbb;
import com.hp.bean.GJ_county;
import com.hp.tool.CityGridAdapter.ItemViewTag;

import android.content.Context;
import android.text.NoCopySpan.Concrete;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HfbbAdapter extends BaseAdapter {
	private Context mContext;
	private List<GJ_Hfbb> m_nameList  = new ArrayList<GJ_Hfbb>();
	private LayoutInflater mInflater;
	LinearLayout.LayoutParams params;
	
	public HfbbAdapter(Context context, List<GJ_Hfbb> hfbbs) {
		// TODO Auto-generated constructor stub
		mContext = context;
		m_nameList = hfbbs ;
		mInflater  = LayoutInflater.from(context);//与findviewbyid相同，但这里是加载xml，并实例化。
		params = new LinearLayout.LayoutParams(250, 200);
		params.gravity = Gravity.CENTER_HORIZONTAL;	
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
		ItemViewTag itemTag;
		if (arg1 == null) {
			arg1 = mInflater.inflate(R.layout.versionlayout, null);
			itemTag = new ItemViewTag((TextView)arg1.findViewById(R.id.grid_text), (TextView)arg1.findViewById(R.id.grid_value));
			arg1.setTag(itemTag);//给view设置标签，标签可以是任意对象
		}
		else {
			itemTag = (ItemViewTag) arg1.getTag();
		}
		GJ_Hfbb hfbb =  m_nameList.get(arg0);
		itemTag.nameView.setText(hfbb.getBbmc());
		System.out.println("111111"+hfbb.getBbmc());
		itemTag.valueView.setText(hfbb.getBbh());
		return arg1;
	}
class ItemViewTag{
	protected TextView nameView;
	protected TextView valueView;
	public ItemViewTag(TextView nameVw,TextView valueVw) {
		// TODO Auto-generated constructor stub
		this.nameView = nameVw;
		this.valueView = valueVw;
	}
}

}
