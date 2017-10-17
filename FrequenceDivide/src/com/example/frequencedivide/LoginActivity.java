package com.example.frequencedivide;

import com.hp.service.UserService;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;


public class LoginActivity extends Activity {
	
	EditText username = null;
	EditText password = null;
	Button btnlogin = null;
	Button btnregist= null;
	UserService userService;//用户管理
	ProgressDialog pgdialog = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		userService = new UserService();
		pgdialog = new ProgressDialog(LoginActivity.this);
		username = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);
		btnlogin = (Button) findViewById(R.id.btn_submit);
		btnregist = (Button) findViewById(R.id.btn_register);
		btnregist.setOnClickListener(registClickListener);
		btnlogin.setOnClickListener(new View.OnClickListener() {
		
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (validate()) {
					pgdialog.setTitle("系统登陆");
					pgdialog.setMessage("系统正在登陆中");
					pgdialog.show();
					//开启线程联网登陆操作
					Thread loginThread = new Thread(runnable);
					loginThread.start();
				}
			}
		});
		}
	
	private View.OnClickListener registClickListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
			startActivity(intent);
		}
	};
//实现登陆任务，运行在子线程中，涉及登陆与交互，因此需要handler协助
Runnable runnable = new Runnable() {
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Message msg = new Message();
		if (login()) {
			msg.arg1 = 1;  //登陆成功
		}else {
			msg.arg1 = 0;
		}
		handler.sendMessage(msg);
	}


};
	Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			if (msg.arg1 == 1) {
				pgdialog.dismiss(); //隐藏
				Intent intent;
					intent= new Intent(LoginActivity.this,ManagerAcitvity.class);
					startActivity(intent);
				//	finish();
				}
			else {
				pgdialog.dismiss();
				Toast.makeText(LoginActivity.this, "用户名或密码错误，请重新输入", Toast.LENGTH_LONG).show();
			}
		};
	};
	//用于实现登陆
	private boolean login() {
		// TODO Auto-generated method stub

		String account  = username.getText().toString();
		
		String pssword  = password.getText().toString();
		boolean isok = userService.login(account, pssword);
		if (isok) {
			return true;
		}else {
			return false;
		}

	}

//用于验证账号和密码不为空
private boolean validate() {
	 String userName = username.getText().toString();
	 String passWord = password.getText().toString();
	 if (userName.equals("")) {
		Toast.makeText(LoginActivity.this, "账号不能为空", Toast.LENGTH_LONG).show();
	}
	 if(passWord.equals("")){
		Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_LONG).show();
	}
	return true;	
}
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
	
	
}
