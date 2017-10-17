package com.hp.dao;

import java.util.List;

import com.hp.bean.GJ_county;



public interface UserService {

	public boolean login(String username,String password);
	public List<GJ_county> getAllCounty();
}
