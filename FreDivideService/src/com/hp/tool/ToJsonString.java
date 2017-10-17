package com.hp.tool;

import java.util.List;

import com.google.gson.Gson;
import com.hp.bean.GJ_county;
import com.hp.bean.MyLink;

public class ToJsonString {

	public static String listToJson(List<?> coutry) {
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		return gson.toJson(coutry);
		
	}


}
