package com.hp.servlet;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.hp.bean.User;
import com.hp.dao.impl.UserServiceImpl;
import com.hp.tool.JsonToObject;
import com.hp.tool.ToJsonString;


public class FreServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public FreServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");//返回给客户端是普通文本
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String json = "ok";
		UserServiceImpl dao = new UserServiceImpl();
		String type = request.getParameter("type");
		/**
		 * 用户登陆
		 */
		if (type.equals("login")) {
			String account = request.getParameter("account");
			String password = request.getParameter("password");
			//int permission = Integer.parseInt(request.getParameter("permission"));
			////----------------判断登陆------------------//
			if (dao.login(account, password)) {
				json = "1";//登陆成功
			}else {
				json = "0";
			}	
		}
	
		//获取国家名称
		if (type.equals("getCounty")) {
			json  = ToJsonString.listToJson(dao.getAllCounty());
		//	System.out.println("test" + json +"test json");
		}
		if (type.equals("userRegister")) {
			String userJson = request.getParameter("userJson");
			System.out.println(userJson);
			User user = JsonToObject.getUserFromJson(userJson);
			if (dao.checkAccountIsExsit(user.getAccount())) {
				json = "2"; //该账号存在
			}
			else if (dao.userRegister(user)) {
				json = "1";//注册成功	
			}else {
				json = "0";//注册失败
			
			}
		}
		//根据国家代码获取版本号
		if (type.equals("getHfbbByGjdm")) {
			String userJson = request.getParameter("gjdm");
			json  = ToJsonString.listToJson(dao.getHfbbByGjdm(userJson));

		}
		//getInforsByFreAndBbh

		if (type.equals("getInforsByFreAndBbh")) {
			String mBbh = request.getParameter("bbh");
			String mFrequnce = request.getParameter("frequency");
			json = ToJsonString.listToJson(dao.getInforsByFreAndBbh(mBbh,mFrequnce));
		}
		if (type.equals("getAllInfors")) {
			String bbh = request.getParameter("bbh");
			json = ToJsonString.listToJson(dao.getAllInfors(bbh));
		}
		if (type.equals("getYwdyBymc")) {
			String yw = request.getParameter("ywmc");
			String ywmc =new String(yw.getBytes("iso8859-1"),"UTF-8");
			json = dao.getYwdyBymc(ywmc);
		}
		if (type.equals("getJznrByjz")) {
			String jzh = String.valueOf(request.getParameter("jzh"));
			json = dao.getJznrByjz(jzh);
		}
		out.print(json);	
		System.out.println(json);
		out.flush();
		out.close();
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
