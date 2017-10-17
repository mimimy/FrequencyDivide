package com.hp.tool;

import java.util.Map;

import net.sf.json.JSONObject;

import com.hp.bean.DB;
import com.hp.bean.User;

/**
 *作用：json字符床转换为对象
 */

public class JsonToObject {
	//map转换为user对象
	public static User getUserFromMap(Map<String, String> map) {
		//定义对象
		User user = new User();
		//判断map是否包含键值,包含则获取
		if (map.containsKey(DB.Tables.User.Fields.ACCOUNT)) {
			String value = map.get(DB.Tables.User.Fields.ACCOUNT);//value表示读取到的表的字段
			user.setAccount(value);//通过map获取数据库的值，设置给user对象			
		}
		if (map.containsKey(DB.Tables.User.Fields.PASSWORD)) {
			String value = map.get(DB.Tables.User.Fields.PASSWORD);
			user.setPassword(value);
		}
		if (map.containsKey(DB.Tables.User.Fields.NAME)) {
			String value = map.get(DB.Tables.User.Fields.NAME);
			user.setName(value);
		}
		if (map.containsKey(DB.Tables.User.Fields.GENDER)) {
			String value = map.get(DB.Tables.User.Fields.GENDER);
			user.setGender(Integer.valueOf(value));
		}
		if (map.containsKey(DB.Tables.User.Fields.REMARK)) {
			String value = map.get(DB.Tables.User.Fields.REMARK);
			user.setRemark(value);
		}
		if (map.containsKey(DB.Tables.User.Fields.CONTACT)) {
			String value = map.get(DB.Tables.User.Fields.CONTACT);
			user.setRemark(value);
		}		
		return user;
		
	}
	//json转换为user
	public static User getUserFromJson(String json) {
		User user = new User();
		//通过字符串创建json对象
		try {
			///解析为键值的方式
		//通过对象来解析,通过键值获取，table表中的这些变量都是键值
		JSONObject obj = JSONObject.fromObject(json);
		user.setUserId(obj.getInt(DB.Tables.User.Fields.USERID));
		user.setAccount(obj.getString(DB.Tables.User.Fields.ACCOUNT));
		user.setPassword(obj.getString(DB.Tables.User.Fields.PASSWORD));
		user.setName(obj.getString(DB.Tables.User.Fields.NAME));
		user.setGender(obj.getInt(DB.Tables.User.Fields.GENDER));
		user.setContact(obj.getString(DB.Tables.User.Fields.CONTACT));
		//user.setRemark(obj.getString(DB.Tables.User.Fields.REMARK));
		return user;	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
}
