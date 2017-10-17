package com.hp.dao;

import java.util.List;

import com.hp.bean.GJ_county;
import com.hp.bean.LinkTables;

public interface PlzyGJ {

	public List<GJ_county> getCountry();
	public String getCountryNameByDM(String gjdm);
	public List<LinkTables> getAllInfors(String bbh);
}
