package com.hp.bean;

public class GJ_jz {

	private String jzh;
	private String jznr;
	public GJ_jz(String jzh, String jznr) {
		super();
		this.jzh = jzh;
		this.jznr = jznr;
	}
	@Override
	public String toString() {
		return "GJ_jz [jzh=" + jzh + ", jznr=" + jznr + "]";
	}
	public String getJzh() {
		return jzh;
	}
	public void setJzh(String jzh) {
		this.jzh = jzh;
	}
	public String getJznr() {
		return jznr;
	}
	public void setJznr(String jznr) {
		this.jznr = jznr;
	}
	
	
}
