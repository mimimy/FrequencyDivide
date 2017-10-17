package com.hp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {

	//打开并获取数据库的链接
	public Connection openConnection()
	{
		//实例化properties，The Properties class represents a persistent set of properties.
		//The Properties can be saved to a stream or loaded from a stream
		Properties properties = new Properties();
		String driver = null;
		String url = null;
		String username = null;
		String password = null;
		try {
			//Reads a property list (key and element pairs) from the input byte stream
			properties.load(this.getClass().getClassLoader().getResourceAsStream(
					"DBconfig.properties"));
		
			driver = properties.getProperty("driver");
			url = properties.getProperty("url");
			username = properties.getProperty("username");
			password = properties.getProperty("password");
			//注册驱动
			Class.forName(driver);
			//返回数据库连接
			return DriverManager.getConnection(url,username,password);//创建连接
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return null;	
	}
	//关闭数据库
	public void closeConn(Connection conn){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
}
