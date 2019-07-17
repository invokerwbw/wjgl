package com.inspur.tax.modify.dao;

import java.util.List;
import java.util.Map;

public interface ModifyMapper {

	List<Map<String, String>> listData(Map<String, Object> parameter);

	Map<String, Object> getStd(String bzh);

	int deleteStd(String bzh);

	int updateStd(Map<String, Object> std);

	int insertStd(Map<String, Object> std);

	int updateStdFilePath(Map<String, Object> std);

}