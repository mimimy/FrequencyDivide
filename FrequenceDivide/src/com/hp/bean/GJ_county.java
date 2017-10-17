package com.hp.bean;

///对应ppzs的表国家的数据表
public class GJ_county {

	private String gjdm;//国家代码
	private String gjmc;//国家名称
	private String xh;//国家序号
	private String bz;//备注
	
	@Override
	public String toString() {
		return "GJ_county [bz=" + bz + ", gjdm=" + gjdm + ", gjmc=" + gjmc
				+ ", xh=" + xh + "]";
	}
	
	public GJ_county() {
		// TODO Auto-generated constructor stub
	}
	//构造函数
	public GJ_county(String gjdm, String gjmc, String xh, String bz) {
		super();
		this.gjdm = gjdm;
		this.gjmc = gjmc;
		this.xh = xh;
		this.bz = bz;
	}
	
	public String getGjdm() {
		return gjdm;
	}
	public void setGjdm(String gjdm) {
		this.gjdm = gjdm;
	}
	public String getGjmc() {
		return gjmc;
	}
	public void setGjmc(String gjmc) {
		this.gjmc = gjmc;
	}
	public String getGjxh() {
		return xh;
	}
	public void setGjxh(String gjxh) {
		this.xh = gjxh;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	

	
}
