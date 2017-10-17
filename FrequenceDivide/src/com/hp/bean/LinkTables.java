package com.hp.bean;

public class LinkTables {

	private String pdxh;//频段序号
	private String ywdm;
	private String plxx;
	private String plsx;
	private String ywmc;//业务名称
	private String jzh;

	
	public LinkTables() {
		super();
	}
	public LinkTables(String pdxh,String ywdm, String plxx, String plsx, String ywmc,
			String jzh) {
		super();
		this.pdxh = pdxh;
		this.ywdm = ywdm;
		this.plxx = plxx;
		this.plsx = plsx;
		this.ywmc = ywmc;
		this.jzh = jzh;
	}

	public String getPdxh() {
		return pdxh;
	}
	public void setPdxh(String pdxh) {
		this.pdxh = pdxh;
	}
	public String getYwdm() {
		return ywdm;
	}
	public void setYwdm(String ywdm) {
		this.ywdm = ywdm;
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
	public String getYwmc() {
		return ywmc;
	}
	public void setYwmc(String ywmc) {
		this.ywmc = ywmc;
	}

	public String getJzh() {
		return jzh;
	}
	public void setJzh(String jzh) {
		this.jzh = jzh;
	}
	@Override
	public String toString() {
		return "LinkTables [pdxh=" + pdxh + ", ywdm=" + ywdm + ", plxx=" + plxx
				+ ", plsx=" + plsx + ", ywmc=" + ywmc + ", jzh=" + jzh + "]";
	}

	
	
	
	
	
	
}
