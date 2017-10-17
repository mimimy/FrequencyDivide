package com.hp.bean;

public class GJ_Hfbb {

	private String bbh;//版本号 主键
	private String gjdm; //国家代码
	private String bbmc; //版本名称
	private String bbrq;//版本日期
	private String bbxz;
	private String pzdw;
	private String yly;
	

	public GJ_Hfbb(String bbh, String gjdm, String bbmc, String bbrq, String bbxz,
			String pzdw, String yly) {
		super();
		this.bbh = bbh;
		this.gjdm = gjdm;
		this.bbmc = bbmc;
		this.bbrq = bbrq;
		this.bbxz = bbxz;
		this.pzdw = pzdw;
		this.yly = yly;
	}
	public GJ_Hfbb() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "GJ_Hfbb [bbh=" + bbh + ", bbmc=" + bbmc + ", bbrq=" + bbrq
				+ ", bbxz=" + bbxz + ", gjdm=" + gjdm + ", pzdw=" + pzdw
				+ ", yly=" + yly + "]";
	}
	public String getBbh() {
		return bbh;
	}
	public void setBbh(String bbh) {
		this.bbh = bbh;
	}
	public String getGjdm() {
		return gjdm;
	}
	public void setGjdm(String gjdm) {
		this.gjdm = gjdm;
	}
	public String getBbmc() {
		return bbmc;
	}
	public void setBbmc(String bbmc) {
		this.bbmc = bbmc;
	}
	public String getBbrq() {
		return bbrq;
	}
	public void setBbrq(String bbrq) {
		this.bbrq = bbrq;
	}
	public String getBbxz() {
		return bbxz;
	}
	public void setBbxz(String bbxz) {
		this.bbxz = bbxz;
	}
	public String getPzdw() {
		return pzdw;
	}
	public void setPzdw(String pzdw) {
		this.pzdw = pzdw;
	}
	public String getYly() {
		return yly;
	}
	public void setYly(String yly) {
		this.yly = yly;
	}
	
	
}
