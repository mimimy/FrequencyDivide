package com.hp.bean;

public class GJ_ywpd {

	private String pdxh;//频段序号
	private String bbh;
	private String ywdm;
	private String plxx;
	private String plsx;
	private String qqhf;
	private String zyyw;
	private String sytj;
	private String yt;
	
	public GJ_ywpd(String pdxh, String bbh, String ywdm, String plxx,
			String plsx, String qqhf, String zyyw, String sytj, String yt) {
		super();
		this.pdxh = pdxh;
		this.bbh = bbh;
		this.ywdm = ywdm;
		this.plxx = plxx;
		this.plsx = plsx;
		this.qqhf = qqhf;
		this.zyyw = zyyw;
		this.sytj = sytj;
		this.yt = yt;
	}

	@Override
	public String toString() {
		return "GJ_ywpd [bbh=" + bbh + ", pdxh=" + pdxh + ", plsx=" + plsx
				+ ", plxx=" + plxx + ", qqhf=" + qqhf + ", sytj=" + sytj
				+ ", yt=" + yt + ", ywdm=" + ywdm + ", zyyw=" + zyyw + "]";
	}

	public String getPdxh() {
		return pdxh;
	}

	public void setPdxh(String pdxh) {
		this.pdxh = pdxh;
	}

	public String getBbh() {
		return bbh;
	}

	public void setBbh(String bbh) {
		this.bbh = bbh;
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

	public String getQqhf() {
		return qqhf;
	}

	public void setQqhf(String qqhf) {
		this.qqhf = qqhf;
	}

	public String getZyyw() {
		return zyyw;
	}

	public void setZyyw(String zyyw) {
		this.zyyw = zyyw;
	}

	public String getSytj() {
		return sytj;
	}

	public void setSytj(String sytj) {
		this.sytj = sytj;
	}

	public String getYt() {
		return yt;
	}

	public void setYt(String yt) {
		this.yt = yt;
	}
	
	
	
}
