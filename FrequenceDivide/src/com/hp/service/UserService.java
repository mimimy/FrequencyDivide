package com.hp.service;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.hp.bean.DB;
import com.hp.bean.DB.Tables;
import com.hp.bean.GJ_Hfbb;
import com.hp.bean.GJ_county;
import com.hp.bean.LinkTables;
import com.hp.bean.MyLinks;
import com.hp.bean.User;
import com.hp.constant.FreUrl;
import com.hp.util.HttpUtil;

import android.R.string;
import android.util.Log;


public class UserService {
	
    HttpClient client = new DefaultHttpClient();
	private HttpParams httpParams;//设置访问控制参数
	List<NameValuePair> params;  //变量及属性列表
    
	//登陆
	public boolean login(String account, String password) {
		// TODO Auto-generated method stub
	//	String url ="http://10.0.2.2:8080/FreDivideService/FreServlet?type=login";
		String url = FreUrl.USER_LOGIN_URL;
		HttpPost post = HttpUtil.getHttpPost(url);
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		NameValuePair pair1 = new BasicNameValuePair("account", account);
		list.add(pair1);
		NameValuePair pair2 = new BasicNameValuePair("password", password);
		list.add(pair2);

		try {
			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(list,HTTP.UTF_8);
			post.setEntity(formEntity);//作为内容添加到post中
			String json = HttpUtil.queryStringForPost(post);//以post的方式qingqiu
			if (json.trim().equals("1")) 
			{
				return true;
			}
			else {
				return false;	
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	
	};
	//获取国家名称和国家代码
	public ArrayList<GJ_county> getCountry() {
		// TODO Auto-generated method stub//getGjmcFrBB//getGjmcFrBB
	//	String url ="http://10.0.2.2:8080/FreDivideService/FreServlet?type=getCounty";
		String url = FreUrl.GET_COUNTY_URL;
		String json = HttpUtil.queryStringForGet(url);
		ArrayList<GJ_county> m_countrys = new  ArrayList<GJ_county>();
		try {
			JSONArray array = new JSONArray(json);
			for(int i=0;i<array.length();i++){
				JSONObject obj =(JSONObject) array.opt(i);
				GJ_county county = getUserByJSONObject(obj);
				m_countrys.add(county);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return m_countrys;
		
	}
	//根据JSONObject 获取国家对象
	private GJ_county getUserByJSONObject(JSONObject obj) {
		// TODO Auto-generated method stub
		GJ_county county = new GJ_county();
		try {
			county.setGjdm(obj.getString(Tables.GJ_county.Fields.gjdm));
			county.setGjmc(obj.getString(Tables.GJ_county.Fields.gjmc));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return county;
	}	
	/**
	 * 根据国家代码获取版本数据表的信息
	 * @param gjdm
	 * @return
	 */
	public List<GJ_Hfbb> getHfbbs(String gjdm ) {
		
	//	String murl ="http://10.0.2.2:8080/FreDivideService/FreServlet?type=getHfbbByGjdm&gjdm=%s";
		String murl = FreUrl.GET_BBH_URL;
		String url = String.format(murl, gjdm);
		List<GJ_Hfbb> bbList = new ArrayList<GJ_Hfbb>();
		String json = HttpUtil.queryStringForGet(url);
		try {
			JSONArray jsonArray = new JSONArray(json);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject object = jsonArray.getJSONObject(i);
				GJ_Hfbb hfbb = 	getBbhByJSONObject(object);
				bbList.add(hfbb);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return bbList;
		
	}
	/**
	 * 将JSONObject转换为对象格式存储
	 * @param object
	 * @return
	 */
	private GJ_Hfbb getBbhByJSONObject(JSONObject object) {
		// TODO Auto-generated method stub
		GJ_Hfbb hfbb = new GJ_Hfbb();
		try {
			hfbb.setBbh(object.getString(Tables.GJ_Hfbb.Fields.bbh));
			hfbb.setBbmc(object.getString(Tables.GJ_Hfbb.Fields.bbmc));
			hfbb.setBbrq(object.getString(Tables.GJ_Hfbb.Fields.bbrq));
			hfbb.setBbxz(object.getString(Tables.GJ_Hfbb.Fields.bbxz));
			hfbb.setGjdm(object.getString(Tables.GJ_Hfbb.Fields.gjdm));
			hfbb.setPzdw(object.getString(Tables.GJ_Hfbb.Fields.pzdw));
			hfbb.setYly(object.getString(Tables.GJ_Hfbb.Fields.yly));	
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hfbb;
	}

	/**
	 * 根据业务名称获取业务定义
	 * @param ywmc
	 * @return
	 */
	public String getYwdyBymc(String ywmc) {	
	//	String murl = "http://10.0.2.2:8080/FreDivideService/FreServlet?type=getYwdyBymc&ywmc=%s";
		String murl = FreUrl.GET_YWDY_URL;
		String url = String.format(murl, ywmc);
		String json = HttpUtil.queryStringForGet(url);
		try {
			json = URLDecoder.decode(json, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	/**
	 * 根据校注号获取校注内容
	 * @param jzh
	 * @return
	 */
	public String getJznrByjzh(String jzh) {
		// TODO Auto-generated method stub
	//	String murl = "http://10.0.2.2:8080/FreDivideService/FreServlet?type=getJznrByjz&jzh=%s";
		String murl =FreUrl.GET_JZNR_URL;
		String url = String.format(murl, jzh);
		System.out.println(url);	
		String 	json = HttpUtil.queryStringForGet(url);
		return json;
	}
	/**
	 * 根据版本号获取多项表的信息
	 * @param bbh
	 * @return
	 */
	public List<LinkTables> getLinkBybbh(String bbh) {
		List<LinkTables>  linkTables = new ArrayList<LinkTables>();
	//	String murl ="http://10.0.2.2:8080/FreDivideService/FreServlet?type=getAllInfors&bbh=%s";
		String murl = FreUrl.GET_INFORS_URL;
	    String url = String.format(murl, bbh);
		String json = HttpUtil.queryStringForGet(url);
		try {
			JSONArray jsonArray = new JSONArray(json);	
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject object = jsonArray.getJSONObject(i);				
				LinkTables link = getTableByBbh(object);
				linkTables.add(link);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			new RuntimeException(e+"网络连接异常");
		}
		return linkTables;
	}
	
	public   List<MyLinks> toMyLinks(List<LinkTables> linkTables){
	 //     int j= 0;
		List<MyLinks>  linksList = new ArrayList<MyLinks>();

		Map<String,Integer> map = new HashMap<String,Integer>();
		Map<String,Integer> map1 = new HashMap<String,Integer>();
		 for (int i = 0; i < linkTables.size(); i++) {
			LinkTables linkTable = linkTables.get(i);
			MyLinks myLink = new MyLinks();
			String plxx =linkTable.getPlxx();
			String plsx= linkTable.getPlsx();
			if (map.containsKey(plxx) && map1.containsKey(plsx) ) {
				int indexx = map.get(plxx);
				int indexs = map1.get(plsx);
				if (indexs == indexx) {
					myLink = linksList.get(indexx);
				}
			}else {
				linksList.add(myLink);
				int index = linksList.size() - 1;
				map.put(plxx, index);
				map1.put(plsx, index);
			}
			myLink.getYwList().add(linkTable.getYwmc());
		    myLink.getPdxhList().add(linkTable.getPdxh());
		    myLink.getYwdmList().add(linkTable.getYwdm());
		    myLink.getPdjzList().add(linkTable.getJzh());
		    myLink.setPlsx(plsx);
		    myLink.setPlxx(plxx);
		 }
		return linksList;
	}
	
	/**
	 * 数据格式的转化
	 * @param object
	 * @return
	 */
	private LinkTables getTableByBbh(JSONObject object) {
		// TODO Auto-generated method stub
		LinkTables linkList = new LinkTables();
		try {
			linkList.setJzh(object.getString(DB.Tables.GJ_ywjzgx.Fields.jzh));
			linkList.setPdxh(object.getString(DB.Tables.GJ_ywpd.Fields.pdxh));
			linkList.setPlsx(object.getString(DB.Tables.GJ_ywpd.Fields.plsx));
			linkList.setPlxx(object.getString(DB.Tables.GJ_ywpd.Fields.plxx));
			linkList.setYwmc(object.getString(DB.Tables.GJ_ywdy.Fields.ywmc));
			linkList.setYwdm(object.getString(DB.Tables.GJ_ywdy.Fields.ywdm));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return linkList;
	}
	public List<LinkTables> getLinkBybbhAndFre(String tv_current_fre,String tv_bbNum) {
		// TODO Auto-generated method stub
		List<LinkTables>  linkTableList = new ArrayList<LinkTables>();
		//String murl ="http://10.0.2.2:8080/FreDivideService/FreServlet?type=getAllInfors&bbh=%s";
	//	String nurl = "http://10.0.0.2:8080/FreDivideService/FreServlet?type=getInforsByFreAndBbh&bbh=%s&frequency=%s";
	   String murl = FreUrl.GET_INFORS_BYBBHAFRE_URL;
	    String url = String.format(murl,tv_bbNum, tv_current_fre);
		String json = HttpUtil.queryStringForGet(url);
		try {
			JSONArray jsonArray = new JSONArray(json);	
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject object = jsonArray.getJSONObject(i);				
				LinkTables link = getTableByBbh(object);
				linkTableList.add(link);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			new RuntimeException(e+"网络连接异常");
		}
		return linkTableList;
	}
	
	public boolean userRegister(User user) {
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		String json = gson.toJson(user);
		String url = FreUrl.USER_REGISTER_URL;
		HttpPost post = HttpUtil.getHttpPost(url);
		//因为是yipost的方法请求因此封装为键值对的方式传递
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		NameValuePair pair = new BasicNameValuePair("userJson", json);
		list.add(pair);
		try {
			post.setEntity(new UrlEncodedFormEntity(list,HTTP.UTF_8));//编码后提交给后台服务器
			String result = HttpUtil.queryStringForPost(post);
			if (result.trim().equals("1")) 
				return true;
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
