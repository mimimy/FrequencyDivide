package com.hp.bean;

public class LinkTables {
	
	private String bbh;//版本号 主键
	private String pdxh;//频段序号
	private String ywdm;
	private String plxx;
	private String plsx;
	private String ywmc;//业务名称
	private String jzh;
	
	public LinkTables() {
		super();
	}

	public LinkTables(String bbh, String pdxh, String ywdm, String plxx,
			String plsx, String ywmc, String jzh) {
		super();
		this.bbh = bbh;
		this.pdxh = pdxh;
		this.ywdm = ywdm;
		this.plxx = plxx;
		this.plsx = plsx;
		this.ywmc = ywmc;
		this.jzh = jzh;
	}

	public String getBbh() {
		return bbh;
	}

	public void setBbh(String bbh) {
		this.bbh = bbh;
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
	if (jzh ==null || jzh == "") {
		jzh = "无校注信息";
	}
		this.jzh = jzh;
	}

	@Override
	public String toString() {
		return "LinkTables [bbh=" + bbh + ", jzh=" + jzh + ", pdxh=" + pdxh
				+ ", plsx=" + plsx + ", plxx=" + plxx + ", ywdm=" + ywdm
				+ ", ywmc=" + ywmc + "]";
	}


	
}

