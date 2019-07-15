package com.inspur.tax.sys.dao;

import java.util.Map;

public interface SysMapper {

	public void addUser(Map<String, String> userMap);

	public void updateUser(Map<String, String> userMap);

	public void deleteUser(String account);
}
