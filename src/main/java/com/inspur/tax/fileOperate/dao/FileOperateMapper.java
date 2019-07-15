package com.inspur.tax.fileOperate.dao;

import java.util.Map;

public interface FileOperateMapper {

	public Map<String, Object> stdInfo(String bzh);
	
	public String getA298ByA100(String bzh);

	public Map<String, String> getFilePathByBzh(String bzh);
	
	public Map<String, String> checkBzhIsExist(String bzh);
}
