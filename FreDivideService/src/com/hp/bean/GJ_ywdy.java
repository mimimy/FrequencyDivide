package com.hp.bean;

import javax.print.DocFlavor.STRING;

public class GJ_ywdy {

	private String ywdm; //业务代码
	private String sjdm;//
	private String ywmc;//业务名称
	private String ywdy;//业务定义
	
	public GJ_ywdy(String ywdm, String sjdm, String ywmc, String ywdy) {
		super();
		this.ywdm = ywdm;
		this.sjdm = sjdm;
		this.ywmc = ywmc;
		this.ywdy = ywdy;
	}
	@Override
	public String toString() {
		return "GJ_ywdy [sjdm=" + sjdm + ", ywdm=" + ywdm + ", ywdy=" + ywdy
				+ ", ywmc=" + ywmc + "]";
	}
	public String getYwdm() {
		return ywdm;
	}
	public void setYwdm(String ywdm) {
		this.ywdm = ywdm;
	}
	public String getSjdm() {
		return sjdm;
	}
	public void setSjdm(String sjdm) {
		this.sjdm = sjdm;
	}
	public String getYwmc() {
		return ywmc;
	}
	public void setYwmc(String ywmc) {
		this.ywmc = ywmc;
	}
	public String getYwdy() {
		return ywdy;
	}
	public void setYwdy(String ywdy) {
		this.ywdy = ywdy;
	}
	
	
}
