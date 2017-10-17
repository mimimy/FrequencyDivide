package com.hp.bean;

public class GJ_ywjzgx {

	private String pdxh;//频段序号
	private String jzh;
	
	
	
	public GJ_ywjzgx(String pdxh, String jzh) {
		super();
		this.pdxh = pdxh;
		this.jzh = jzh;
	}
	public GJ_ywjzgx() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "GJ_ywjzgx [jzh=" + jzh + ", pdxh=" + pdxh + "]";
	}
	public String getPdxh() {
		return pdxh;
	}
	public void setPdxh(String pdxh) {
		this.pdxh = pdxh;
	}
	public String getJzh() {
		return jzh;
	}
	public void setJzh(String jzh) {
		this.jzh = jzh;
	}
	
}
