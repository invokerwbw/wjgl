package com.inspur.tax.modify.dao;

import java.util.List;
import java.util.Map;

public interface ModifyMapper {

	List<Map<String, String>> listData(Map<String, Object> parameter);

}