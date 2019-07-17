package com.inspur.tax.modify.service;

import java.util.Map;

public interface IModifyService {

	Map<String, Object> listData(Map<String, Object> parameter);

	Map<String, Object> getStd(String bzh);

	boolean checkStd(String bzh);

	boolean deleteStd(String bzh);

	boolean updateStd(Map<String, Object> std);

	boolean insertStd(Map<String, Object> std);
}
