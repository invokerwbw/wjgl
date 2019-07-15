package com.inspur.tax.sys.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface ISysService {

	public Map<String, String> addUser(HttpServletRequest req) throws Exception;
	
	public Map<String, String> deleteUser(HttpServletRequest req) throws Exception;

}
