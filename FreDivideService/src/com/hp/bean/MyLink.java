package com.hp.bean;

import java.util.List;
import java.util.Map;

public class MyLink {

/*	private String bbh;//版本号 主键
	private String bbmc; //版本名称
	private String bbrq;//版本日期	
	private String ywmc;//业务名称
	private String ywdy;//业务定义
	private String jzh;
	private String jznr;
	*/
	private String pdxh;//频段序号
	private String ywdm;
	private String plxx;
	private String plsx;

	private List<String> ywList;//由业务代码获取业务名称
	private List<String> pdjzList;//由频段号查找校注号
//	private List<Map<String, String>> jzjznr;//由校注号查找校注内容
//	private List<Map<String, String>> jzjznr;//由校注号查找校注内容	
	public String getPdxh() {
		return pdxh;
	}
	public void setPdxh(String pdxh) {
		this.pdxh = pdxh;
	}
	public String getYwdm() {
		return ywdm;
	}
	public MyLink setYwdm(String ywdm) {
		this.ywdm = ywdm;
		return null;
	}
	public String getPlxx() {
		return plxx;
	}
	public void setPlxx(String plxx) {
		this.plxx = plxx;
	}
	public String getPlsx() {
		return plsx;
	}
	public void setPlsx(String plsx) {
		this.plsx = plsx;
	}
	public List<String> getYwList() {
		return ywList;
	}
	public void setYwList(List<String> ywList) {
		this.ywList = ywList;
	}
	public List<String> getPdjzList() {
		return pdjzList;
	}
	public void setPdjzList(List<String> pdjzList) {
		this.pdjzList = pdjzList;
	}
	@Override
	public String toString() {
		return "MyLink [pdjzList=" + pdjzList + ", pdxh=" + pdxh + ", plsx="
				+ plsx + ", plxx=" + plxx + ", ywList=" + ywList + ", ywdm="
				+ ywdm + "]";
	}
	public MyLink(String pdxh, String ywdm, String plxx, String plsx,
			List<String> ywList, List<String> pdjzList) {
		super();
		this.pdxh = pdxh;
		this.ywdm = ywdm;
		this.plxx = plxx;
		this.plsx = plsx;
		this.ywList = ywList;
		this.pdjzList = pdjzList;
	}
	public MyLink() {
		super();
	}

	
	
}
