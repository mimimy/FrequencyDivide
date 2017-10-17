package com.hp.constant;

public interface FreUrl {
	public static final String BASE_URL = "http://10.0.2.2:8088/FreDivideService/";
	public static final String  USER_REGISTER_URL = BASE_URL +"FreServlet?type=userRegister";
	//登陆iurl
	public static final String  USER_LOGIN_URL = BASE_URL +"FreServlet?type=login";
	//获取国家列表的url
	public static final String GET_COUNTY_URL = BASE_URL +"FreServlet?type=getCounty";
	//获取版本的URl
	public static final String GET_BBH_URL = BASE_URL +"FreServlet?type=getHfbbByGjdm&gjdm=%s";
	//获取所有信息的url
	public static final String GET_INFORS_URL = BASE_URL +"FreServlet?type=getAllInfors&bbh=%s";
	//获取业务定义
	public static final String GET_YWDY_URL = BASE_URL+"FreServlet?type=getYwdyBymc&ywmc=%s";
	//获取校注内容
	public static final String GET_JZNR_URL = BASE_URL+"FreServlet?type=getJznrByjz&jzh=%s";	
	//根据版本号和频率搜索infors
	public static final String GET_INFORS_BYBBHAFRE_URL = BASE_URL+"FreServlet?type=getInforsByFreAndBbh&bbh=%s&frequency=%s";
	
}
