package com.hp.bean;

public interface DB {

	String DBNAME = "ppzs_data";
	interface Tables {

		interface User {
			String TABLENAME = "users";

			interface Fields {
				String USERID = "userId";
				String ACCOUNT = "account";
				String PASSWORD = "password";
				String NAME = "name";
				String GENDER = "gender";
				String CONTACT = "contact";
				String REMARK = "remark";
			}
		}
		
		
		interface GJ_county{
			String TABLENAME = "tbl_cxfw_plzy_gj";
			interface Fields {
				String gjdm ="gjdm" ;
				String gjmc = "gjmc";
				String gjxh ="xh";
				String bz = "bz";
			}
		}
		interface GJ_Hfbb{
			String	TABLENAME ="tbl_cxfw_plzy_hfbb";
			interface Fields{
				String bbh = "bbh";//版本号 主键
				String gjdm = "gjdm"; //国家代码
				String bbmc = "bbmc"; //版本名称
				String bbrq = "bbrq";//版本日期
				String bbxz = "bbxz";
				String pzdw = "pzdw";
				String yly = "yly";
			}
		}
		interface GJ_jz{
			String	TABLENAME ="tbl_cxfw_plzy_jz";
			interface Fields{
				String jzh = "jzh";
				String jznr = "jznr";
			}
		}
		interface GJ_ywdy{
			String	TABLENAME ="tbl_cxfw_plzy_ywdy";
			interface Fields{
				String ywdm ="ywdm";
				String ywdy = "ywdy";
				String sjdm = "sjdm"; 
				String ywmc = "ywmc"; //

			}
		}
		interface GJ_ywjzgx{
			String	TABLENAME ="tbl_cxfw_plzy_ywjzgx";
			interface Fields{
				String pdxh = "pdxh";
				String jzh = "jzh"; 
			}
		}
		interface GJ_ywpd{
			String	TABLENAME ="tbl_cxfw_plzy_ywpd";
			interface Fields{
				String pdxh = "pdxh";//版本号 主键
				String bbh = "bbh"; //国家代码
				String ywdm = "ywdm"; //版本名
				String plxx = "plxx";
				String plsx = "plsx";
				String qqhf = "qqhf";
				String zyyw = "zyyw";
				String sytj ="sytj";
				String yt ="yt";

			}
		}

		
	
	}
}
