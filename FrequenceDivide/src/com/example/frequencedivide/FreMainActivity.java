package com.example.frequencedivide;

import com.example.frequencedivide.R;
import com.example.frequencedivide.R.anim;
import com.example.frequencedivide.R.drawable;
import com.example.frequencedivide.R.id;
import com.example.frequencedivide.R.layout;
import com.example.frequencedivide.R.menu;
import com.hp.tool.ImageOption;

import android.support.v7.app.ActionBarActivity;
import android.R.bool;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.animation.AnimationUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;

public class FreMainActivity extends ActionBarActivity  implements OnGestureListener{

	Bitmap bitmap;
	Intent getMainActivityIntent  = null;//获取主界面intent
	int count = 1; //定义当前的滑动界面
	Context context;
	LayoutInflater mInflater;
	ViewFlipper flipper;
	LinearLayout llFirst;  //要切换的视图
	LinearLayout llSecond;
	RelativeLayout rlThird;
	private Button btnEnter;//进入
	private GestureDetector detector;//配合viewfliiper和listner切换视图
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	//	setContentView(R.layout.activity_fre_main);
		//判断是否为第一次启动系统
		SharedPreferences preferences = getSharedPreferences("user_infor", MODE_WORLD_WRITEABLE);
		boolean isFirst = preferences.getBoolean("isFirst", true);
		if (isFirst) {
			
			View view = initView();
			setContentView(view);
			saveTag();
			//手势监听
			detector = new GestureDetector(this);
			
			btnEnter.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					gotoMainActivity();
					
				}
			});
		}else {
			gotoMainActivity();
		}
	}
	private void saveTag(){
		SharedPreferences preferences = getSharedPreferences("user_infor", MODE_WORLD_WRITEABLE);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putBoolean("isFirst", false);
		editor.commit();
	}

	private void gotoMainActivity(){
		
		Intent intent = new Intent(this,LoginActivity.class);
		startActivity(intent);
		finish();
		
	}
private View initView() {
		// TODO Auto-generated method stub
	context  = FreMainActivity.this;
	mInflater = LayoutInflater.from(context);
	View view = mInflater.inflate(R.layout.welcome, null);
	flipper = (ViewFlipper) view.findViewById(R.id.viewFlipper);
	llFirst = (LinearLayout) view.findViewById(R.id.llFirst);//采用代码的方式获取背景图，以免在xml过大
	bitmap = ImageOption.getBitmapFromResource(context, R.drawable.guild1);
	llFirst.setBackgroundDrawable(new BitmapDrawable(bitmap));//设置背景
	llSecond = (LinearLayout) view.findViewById(R.id.llSecond);
	bitmap = ImageOption.getBitmapFromResource(context, R.drawable.guild1);
	llSecond.setBackgroundDrawable(new BitmapDrawable(bitmap));
	rlThird = (RelativeLayout) view.findViewById(R.id.rlThird);
	bitmap = ImageOption.getBitmapFromResource(context, R.drawable.guild1);
	rlThird.setBackgroundDrawable(new BitmapDrawable(bitmap));
	
	btnEnter = (Button) view.findViewById(R.id.btnEnter);
	
	return view;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fre_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return this.detector.onTouchEvent(event);//点击时候，转发给detector处理
	}
	@Override
	public boolean onDown(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	//只需要手指滑动响应,左右滑动处理函数
	public boolean onFling(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		if (arg0.getX() - arg1.getX() >5) {//向右滑动，则要切换视图
			//设置进入或者出的动画
			this.flipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_in));
			this.flipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_out));		
			if (count <3) {//判段，三个
				this.flipper.showNext();//下一个视图，viewflipper自动加载
				count++;
			}
		}	else	if (arg0.getX() - arg1.getX() <-5) {//向右滑动，则要切换视图
			//设置进入或者出的动画
			this.flipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_in));
			this.flipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_out));		
			if (count >1) {//判段，三个
				this.flipper.showNext();//下一个视图，viewflipper自动加载
				count--;
			}
		}
		return true;
	}

	@Override
	public void onLongPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}
}