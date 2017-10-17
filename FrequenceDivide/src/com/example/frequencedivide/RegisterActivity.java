package com.example.frequencedivide;
import com.hp.bean.User;
import com.hp.service.UserService;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class RegisterActivity extends Activity {
	Button btnSumbit = null; //提交按钮
	EditText edtAccount = null;//账号
	EditText edtPassWord = null;//密码
	EditText edtName = null;//姓名
	EditText edtRePassWord = null;//密码确认
	RadioButton rdbMale = null;//性别
	RadioButton rdbFeMale = null;//性别女单选按钮
	EditText edtContact = null;//联系方式
	Context context = null;//上下文对象
	ProgressDialog pgDialog = null; //用于显示提示的进度条
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	   setContentView(R.layout.register);
	   init();
	}
	
	private void init() {
		// TODO Auto-generated method stub
		context = RegisterActivity.this;
		edtAccount = (EditText) findViewById(R.id.edtUserName_register);
		edtContact = (EditText) findViewById(R.id.edtContact);
		edtName = (EditText) findViewById(R.id.edtRealName);
		edtRePassWord = (EditText) findViewById(R.id.edtRePassWord_register);
		edtPassWord = (EditText) findViewById(R.id.edtPassWord_register);
		rdbMale = (RadioButton) findViewById(R.id.rdbMale);
		rdbFeMale = (RadioButton) findViewById(R.id.rdbFeMale);
		btnSumbit = (Button) findViewById(R.id.btnSubmit);
		btnSumbit.setOnClickListener(btnRegistListener);
	//	dialog = new MyDialog(context);
		pgDialog = new ProgressDialog(context);
	}
	
	private View.OnClickListener btnRegistListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			//输入验证
			if (validate()) {
				//提交保存--网络的耗时操作
				Thread thread = new Thread(runnable); //启动线程，处理
				thread.start();
				pgDialog.setTitle("用户注册");
				pgDialog.setMessage("用户注册中。。");
				pgDialog.show();
				
			}
		}
	};	
	private boolean validate() {
		// TODO Auto-generated method stub
		if (edtAccount.getText().toString().trim().equals("")
				||edtName.getText().toString().trim().equals("")
				||edtContact.getText().toString().trim().equals("")
				|| edtPassWord.getText().toString().trim().equals("")) {
			Toast.makeText(context, "信息填写不全", Toast.LENGTH_LONG).show();
			return false;
		}else {
			if (edtPassWord.getText().toString().equals(edtRePassWord.getText().toString())) {
				return true;
			}else {
				Toast.makeText(context, "两次输入密码不一致", Toast.LENGTH_LONG).show();
		//		dialog.ShowDialog("两次输入密码不一致");
				return false;
			}
		}

	}
	Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			pgDialog.dismiss();
			if (msg.arg1 == 1) {
				Toast.makeText(context, "注册成功", Toast.LENGTH_LONG).show();
				//保存注册成功用户信息
				saveUserMsg();
				finish();
			}else if (msg.arg1 == 2) {
				Toast.makeText(context, "帐户已存在", Toast.LENGTH_LONG).show();
			}
			else {
				Toast.makeText(context, "注册失败", Toast.LENGTH_LONG).show();
			}
		}

	
	};
	private void saveUserMsg() {
		// TODO Auto-generated method stub
		SharedPreferences sharedPreferences = getSharedPreferences("user_infor", MODE_WORLD_WRITEABLE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString("account", edtAccount.getText().toString());
		editor.putString("password", edtPassWord.getText().toString());
		editor.commit();
	}
	//使用线程验证--提交保存
	Runnable runnable = new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			UserService userService = new UserService();
			User user = new User();
			user.setAccount(edtAccount.getText().toString());
			user.setPassword(edtRePassWord.getText().toString());
			user.setName(edtName.getText().toString());
			user.setContact(edtContact.getText().toString());
			user.setGender(rdbMale.isChecked()?1:0);
		//	user.setRemark("会员");
			Message msg = new Message();//发送消息在子线程中做
			if (userService.userRegister(user)) {
				msg.arg1 = 1;//注册成功msg标志为1
			}else {
				msg.arg1 = 0;
			}
			//通过handler发送消息
			handler.sendMessage(msg);
		}
	};
	
}
