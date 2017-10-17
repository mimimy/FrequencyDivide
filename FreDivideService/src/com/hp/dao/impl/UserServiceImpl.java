package com.hp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.hp.bean.DB;
import com.hp.bean.GJ_Hfbb;
import com.hp.bean.GJ_county;
import com.hp.bean.LinkTables;

import com.hp.bean.User;
import com.hp.bean.DB.Tables;
import com.hp.dao.UserService;
import com.hp.util.DBUtil;




public class UserServiceImpl implements UserService{

	public boolean login(String username, String password) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		//这里注意sql语句的格式，错误在这里不容易发现
		String sql = "select count(*) as result from   " + 
							DB.Tables.User.TABLENAME
							+" where " + DB.Tables.User.Fields.ACCOUNT 
							+ " =? and " +
							DB.Tables.User.Fields.PASSWORD +" =?  " ;
		
		DBUtil util = new DBUtil();
		Connection connection = util.openConnection();//获取连接
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			statement.setString(2, password);//两个参数
			//statement.setInt(3, permission);
			ResultSet rSet = statement.executeQuery();//查询结果
		
			if (rSet.next()) {//账号和密码来查询
				int result = rSet.getInt("result");//第一条记录，只有一条记录
				System.out.println(result);
				if (result>0) {
					return true;
				}
			}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 检查注册的新账号石否存在
	 * @param account 账号
	 * @return true 表示存在，false 表示不存在
	 */
		public boolean checkAccountIsExsit(String account) {
			//判断数据库中是否包含传进的账户
			String sql = " select count(*) as result from " + DB.Tables.User.TABLENAME
			+ " where " +DB.Tables.User.Fields.ACCOUNT + " =?";
			DBUtil util = new DBUtil();
			Connection connection  = util.openConnection();
			try {
				PreparedStatement statemen = connection.prepareStatement(sql);
				statemen.setString(1, account);
				ResultSet rsResultSet = statemen.executeQuery();//查询返回结果存入到结果中
				if (rsResultSet.next()) {
					int result = rsResultSet.getInt("result");
					if (result>0) {
						return true;
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			}	
			return false;	
		}
		//用户注册
		public boolean userRegister(User user) {
			// TODO Auto-generated method stub
			String sql = "insert into " + Tables.User.TABLENAME + " ( "
			+ Tables.User.Fields.ACCOUNT + ","
			+ Tables.User.Fields.PASSWORD + "," + Tables.User.Fields.NAME
			+ "," + Tables.User.Fields.GENDER + ","
			+ Tables.User.Fields.CONTACT + "," + Tables.User.Fields.REMARK
			+ " ) values(?,?,?,?,?,?)";
			
			DBUtil util = new DBUtil();
			Connection connection = util.openConnection();
			PreparedStatement perp;
			try {
				perp = connection.prepareStatement(sql);//预编译
				//赋值
				perp.setString(1, user.getAccount());
				perp.setString(2, user.getPassword());
				perp.setString(3, user.getName());
				perp.setInt(4, user.getGender());
				perp.setString(5, user.getContact());
				perp.setString(6, user.getRemark());
				perp.execute();//执行
				return true;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("sql check-->" + sql);
			}
			
			return false;
		}	
	
	
	public List<GJ_county> getAllCounty() {
		// TODO Auto-generated method stub
		List<GJ_county> users = null;
		String sql = " select distinct "+Tables.GJ_county.TABLENAME+"."+Tables.GJ_county.Fields.gjdm+","+
		Tables.GJ_county.TABLENAME+"."+Tables.GJ_county.Fields.gjmc+" from "+
		Tables.GJ_county.TABLENAME + "," + Tables.GJ_Hfbb.TABLENAME+ " where "+
		Tables.GJ_county.TABLENAME+"."+Tables.GJ_Hfbb.Fields.gjdm+"=" +
		Tables.GJ_Hfbb.TABLENAME+"."+Tables.GJ_Hfbb.Fields.gjdm;

		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		try {
			PreparedStatement prep = conn.prepareStatement(sql);

			ResultSet rs = prep.executeQuery();
			users = new ArrayList<GJ_county>();
			while (rs.next()) {
				GJ_county county = getUserFronResultSet(rs);
				users.add(county);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	private GJ_county getUserFronResultSet(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		GJ_county county  = new GJ_county();
		county.setGjdm(rs.getString(Tables.GJ_county.Fields.gjdm));
		county.setGjmc(rs.getString(Tables.GJ_county.Fields.gjmc));

		return county;
	}

	//获取版本列表
	public List<GJ_Hfbb> getGjHfbbs(){
		List<GJ_Hfbb> hfbbs = new ArrayList<GJ_Hfbb>();
		String sql = "select * from "+Tables.GJ_Hfbb.TABLENAME;
		DBUtil util = new DBUtil();
		PreparedStatement statement = null;
		ResultSet rSet = null;
		Connection connection = util.openConnection();
		try {
			statement = connection.prepareStatement(sql);
			rSet = statement.executeQuery();
			while (rSet.next()) {
				GJ_Hfbb gjHfbb = getHfbbByResultSet(rSet);
				hfbbs.add(gjHfbb);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		
			try {
				connection.close();
				statement.close();
				rSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
			
		return hfbbs;
		
	}
	//获取版本对象
	private GJ_Hfbb getHfbbByResultSet(ResultSet rSet) {
		// TODO Auto-generated method stub
		GJ_Hfbb gjHfbb = new GJ_Hfbb();
		try {
			gjHfbb.setBbh(rSet.getString(Tables.GJ_Hfbb.Fields.bbh));
			gjHfbb.setBbmc(rSet.getString(Tables.GJ_Hfbb.Fields.bbmc));
			gjHfbb.setBbrq(rSet.getString(Tables.GJ_Hfbb.Fields.bbrq));
			gjHfbb.setBbxz(rSet.getString(Tables.GJ_Hfbb.Fields.bbxz));
			gjHfbb.setGjdm(rSet.getString(Tables.GJ_Hfbb.Fields.gjdm));
			gjHfbb.setPzdw(rSet.getString(Tables.GJ_Hfbb.Fields.pzdw));
			gjHfbb.setYly(rSet.getString(Tables.GJ_Hfbb.Fields.yly));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return gjHfbb;
	}
	public List<GJ_Hfbb> getHfbbByGjdm(String m_gjdm){
		List<GJ_Hfbb> gjHfbbs = new ArrayList<GJ_Hfbb>();
		String sql = " select * from "+Tables.GJ_Hfbb.TABLENAME + " where "
		+ Tables.GJ_Hfbb.Fields.gjdm + " =? ";
		
		DBUtil util = new DBUtil();
		PreparedStatement statement = null;
		ResultSet rSet = null;
		Connection connection = util.openConnection();
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, m_gjdm);
			rSet = statement.executeQuery();
			while (rSet.next()) {
				GJ_Hfbb gjHfbb = getHfbbByResultSet(rSet);
				gjHfbbs.add(gjHfbb);			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		
			try {
				connection.close();
				statement.close();
				rSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
		return gjHfbbs;
		
	}

	public List<LinkTables> getInforsByFreAndBbh(String nbbh,String frequrncy){
		
		List<LinkTables> links = new ArrayList<LinkTables>();
		String sql = "select distinct " +DB.Tables.GJ_ywdy.Fields.ywmc+","
		+DB.Tables.GJ_ywpd.TABLENAME+"."+DB.Tables.GJ_ywpd.Fields.ywdm+","
		+DB.Tables.GJ_ywpd.Fields.plxx+","
		+DB.Tables.GJ_ywpd.Fields.plsx+","+DB.Tables.GJ_ywpd.TABLENAME+"."+DB.Tables.GJ_ywpd.Fields.pdxh+","
		+DB.Tables.GJ_ywjzgx.Fields.jzh
		+" from "
		+DB.Tables.GJ_ywdy.TABLENAME+","+DB.Tables.GJ_ywpd.TABLENAME+" LEFT OUTER JOIN "
		+DB.Tables.GJ_ywjzgx.TABLENAME +" USING("+ DB.Tables.GJ_ywpd.Fields.pdxh+")"
		+" where "+DB.Tables.GJ_ywpd.TABLENAME+"."+DB.Tables.GJ_ywpd.Fields.ywdm+" = "+DB.Tables.GJ_ywdy.TABLENAME+"."
		+DB.Tables.GJ_ywdy.Fields.ywdm+" and " +DB.Tables.GJ_ywpd.TABLENAME+"."+
		DB.Tables.GJ_ywpd.Fields.bbh +" =  " +nbbh  + " and " + frequrncy
		+" between "+ DB.Tables.GJ_ywpd.Fields.plxx 
		+" and "+ DB.Tables.GJ_ywpd.Fields.plsx;
		System.out.println("sql1="+sql);
	
		
		DBUtil util = new DBUtil();
		PreparedStatement statement = null;
		ResultSet rSet = null;
		Connection connection = util.openConnection();
		try {
			statement = connection.prepareStatement(sql);
			rSet = statement.executeQuery();
			while (rSet.next()) {
				LinkTables link =  getLinksFromTables(rSet);
				links.add(link);
		
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		
			try {
				connection.close();
				statement.close();
				rSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
		
		return links;
	}

	/**
	 * 仅通过版本号查询数据库
	 * @param mbbh
	 * @return
	 */
	public List<LinkTables> getAllInfors(String mbbh){
		
		String sql = "select distinct " +DB.Tables.GJ_ywdy.Fields.ywmc+","
		+DB.Tables.GJ_ywpd.TABLENAME+"."+DB.Tables.GJ_ywpd.Fields.ywdm+","
		+DB.Tables.GJ_ywpd.Fields.plxx+","
		+DB.Tables.GJ_ywpd.Fields.plsx+","+DB.Tables.GJ_ywpd.TABLENAME+"."+DB.Tables.GJ_ywpd.Fields.pdxh+","
		+DB.Tables.GJ_ywjzgx.Fields.jzh
		+" from "
		+DB.Tables.GJ_ywdy.TABLENAME+","+DB.Tables.GJ_ywpd.TABLENAME+" LEFT OUTER JOIN "
		+DB.Tables.GJ_ywjzgx.TABLENAME +" USING("+ DB.Tables.GJ_ywpd.Fields.pdxh+")"
		+" where "+DB.Tables.GJ_ywpd.TABLENAME+"."+DB.Tables.GJ_ywpd.Fields.ywdm+" = "+DB.Tables.GJ_ywdy.TABLENAME+"."
		+DB.Tables.GJ_ywdy.Fields.ywdm+" and " +DB.Tables.GJ_ywpd.TABLENAME+"."+
		DB.Tables.GJ_ywpd.Fields.bbh +" =?";
		
		List<LinkTables>  linkTables = new ArrayList<LinkTables>();
		
		DBUtil util = new DBUtil();
		PreparedStatement statement = null;
		ResultSet rSet = null;
		Connection connection = util.openConnection();
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, mbbh);
			rSet = statement.executeQuery();
			while (rSet.next()) {
				LinkTables links =  getLinksFromTables(rSet);
				linkTables.add(links);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		
			try {
				connection.close();
				statement.close();
				rSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
		
		return linkTables;
		
	}
	

	private LinkTables getLinksFromTables(ResultSet rSet) {
		// TODO Auto-generated method stub
		LinkTables linkTables = new LinkTables();
		try {
			linkTables.setYwdm(rSet.getString(DB.Tables.GJ_ywdy.Fields.ywdm));
			linkTables.setYwmc(rSet.getString(DB.Tables.GJ_ywdy.Fields.ywmc));
			linkTables.setPdxh(rSet.getString(DB.Tables.GJ_ywpd.Fields.pdxh));
			linkTables.setJzh(rSet.getString(DB.Tables.GJ_jz.Fields.jzh));
			linkTables.setPlsx(rSet.getString(DB.Tables.GJ_ywpd.Fields.plsx));
			linkTables.setPlxx(rSet.getString(DB.Tables.GJ_ywpd.Fields.plxx));
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return linkTables;
	}

	public String getYwdyBymc(String ywmc) {
		// TODO Auto-generated method stub
		String ywdyContent = "";
		String sql = " select " +DB.Tables.GJ_ywdy.Fields.ywdy
				+ " from " +DB.Tables.GJ_ywdy.TABLENAME + " where "
				+ DB.Tables.GJ_ywdy.Fields.ywmc + " =?";
		DBUtil  util = new DBUtil();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rSet = null;
		connection = util.openConnection();
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, ywmc);
			rSet = statement.executeQuery();
			if (rSet.next()) {
				ywdyContent = rSet.getString(DB.Tables.GJ_ywdy.Fields.ywdy);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		
			try {
				connection.close();
				statement.close();
				rSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		return ywdyContent;
	}

	public String getJznrByjz(String jzh) {
		// TODO Auto-generated method stub
		String jzhContent = "";
		String sql = " select " +DB.Tables.GJ_jz.Fields.jznr
				+ " from " +DB.Tables.GJ_jz.TABLENAME + " where "
				+DB.Tables.GJ_jz.TABLENAME +"."
				+ DB.Tables.GJ_jz.Fields.jzh + " =?";
		DBUtil  util = new DBUtil();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rSet = null;
		connection = util.openConnection();
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, jzh);
			rSet = statement.executeQuery();
			if (rSet.next()) {
				jzhContent = rSet.getString(DB.Tables.GJ_jz.Fields.jznr);

			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		
			try {
				connection.close();
				statement.close();
				rSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		return jzhContent;
	}
}
