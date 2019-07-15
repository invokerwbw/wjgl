package com.inspur.tax.query.service;

import java.util.Map;

public interface IQueryService {

	public Map<String, Object> listStd(Map<String, Object> parameter);

	public Map<String, Object> listUser();

	public Map<String, String> queryUserInfo(String account);

	public Map<String, String> groupStd(Map<String, Object> parameter);

	// public Map<String, String> groupStd(HttpServletRequest request, Map<String,
	// Object> parameter);

}
