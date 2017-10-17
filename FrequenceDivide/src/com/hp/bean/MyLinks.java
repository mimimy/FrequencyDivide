package com.hp.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyLinks {


	private String plxx;
	private String plsx;

	private List<String> pdxhList = new ArrayList<String>();
	private List<String >ywdmList=new ArrayList<String>();
	private List<String> ywList = new ArrayList<String>();//由业务代码获取业务名称
	private List<String> pdjzList = new ArrayList<String>();//由频段号查找校注号
	public MyLinks(String plxx, String plsx, List<String> pdxhList,
			List<String> ywdmList, List<String> ywList, List<String> pdjzList) {
		super();
		this.plxx = plxx;
		this.plsx = plsx;
		this.pdxhList = pdxhList;
		this.ywdmList = ywdmList;
		this.ywList = ywList;
		this.pdjzList = pdjzList;
	}
	public MyLinks() {
		super();
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
	public List<String> getPdxhList() {
		return pdxhList;
	}
	public void setPdxhList(List<String> pdxhList) {
		this.pdxhList = pdxhList;
	}
	public List<String> getYwdmList() {
		return ywdmList;
	}
	public void setYwdmList(List<String> ywdmList) {
		this.ywdmList = ywdmList;
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
		return "MyLinks [plxx=" + plxx + ", plsx=" + plsx + ", pdxhList="
				+ pdxhList + ", ywdmList=" + ywdmList + ", ywList=" + ywList
				+ ", pdjzList=" + pdjzList + "]";
	}


	
	
}
