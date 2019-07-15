package com.inspur.tax.query.dao;

import java.util.List;
import java.util.Map;

public interface QueryMapper {

	List<Map<String, String>> listStd(Map<String, Object> parameter);

	List<Map<String, String>> listUser();

	Map<String, String> queryUserInfo(String account);

	Map<String, String> groupStd(Map<String, Object> parameter);

}
