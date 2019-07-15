package com.inspur.tax.query.service;

import static com.inspur.tax.utils.Collections.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.loushang.framework.mybatis.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inspur.tax.nsrgxyt.common.constant.ServiceConstant;
import com.inspur.tax.query.dao.QueryMapper;
import com.inspur.tax.query.util.SplitKeyWord;

@Service("queryService")
public class QueryServiceImpl implements IQueryService {

	@Autowired
	private QueryMapper queryMapper;

	@Override
	public Map<String, Object> listStd(Map<String, Object> parameter) {
		Map<String, Object> stdData = newHashMap();

		if (parameter != null && parameter.containsKey("status")) {
			String status = (String) parameter.get("status");
			if (status != null && !"".equals(status)) {
				parameter.put("zhuangtai", status.split(","));
			}
		}
		if (parameter.containsKey("leibie")) {
			String leibie = (String) parameter.get("leibie");
			if ("all".equals(leibie)) {
				parameter.put("leibie", null);
			}
			if ("gb".equals(leibie)) {
				parameter.put("leibie", "G");
			}
			if ("hb".equals(leibie)) {
				parameter.put("leibie", "C");
			}
			if ("db".equals(leibie)) {
				parameter.put("leibie", "D");
			}
			if ("tb".equals(leibie)) {
				parameter.put("leibie", "T");
			}
			if ("gw".equals(leibie)) {
				parameter.put("leibie", "N");
			}
		}
		List<String> l = new ArrayList<String>();
		// 根据输入的关键词，切分出标准号和标准名称
		if (parameter.get("keyword") != null && !"".equals(parameter.get("keyword"))) {
			Map<String, Object> splitMap = SplitKeyWord
					.splitBzhAndZwmc(((String) parameter.get("keyword")).toUpperCase());
			parameter.put("a100_1", splitMap.get("a100_1"));
			parameter.put("a100_2", splitMap.get("a100_2"));

			for (String s : (String[]) splitMap.get("keyword")) {
				l.add(s);
			}
			parameter.put("list", l);
		} else {
			parameter.put("a100_1", null);
			parameter.put("a100_2", null);
			parameter.put("list", null);
		}

		List<Map<String, String>> list = queryMapper.listStd(parameter);

		List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();

		if (list != null && list.size() > 0) {
			for (Map<String, String> wp : list) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("bzh", "<b>" + wp.get("bzh") + "</b>");
				if (wp.get("ywmc") == null) {
					map.put("bzmc", wp.get("zwmc") + "brflag" + "");
				} else {
					map.put("bzmc", wp.get("zwmc") + "brflag" + wp.get("ywmc"));
				}
				map.put("fbrq", wp.get("fbrq"));
				map.put("ssrq", wp.get("ssrq"));
				map.put("ys", wp.get("ys"));
				map.put("zt", wp.get("zt"));
				map.put("cz", wp.get("bzh"));
				dataList.add(map);
			}
		}

		int total = PageUtil.getTotalCount();
		stdData.put(ServiceConstant.DATA, dataList);
		stdData.put(ServiceConstant.TOTAL, total != -1 ? total : dataList.size());

		return stdData;
	}

	@Override
	public Map<String, Object> listUser() {
		Map<String, Object> userData = newHashMap();

		List<Map<String, String>> list = queryMapper.listUser();
		List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();

		if (list != null && list.size() > 0) {
			int i = 0;
			for (Map<String, String> wp : list) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("num", String.valueOf(++i));
				map.put("xingming", wp.get("name"));
				map.put("zhanghao", wp.get("account"));
				map.put("bumen", wp.get("department"));
				map.put("beizhu", wp.get("mark"));
				map.put("cz", wp.get("account"));
				dataList.add(map);
			}
		}

		int total = PageUtil.getTotalCount();
		userData.put(ServiceConstant.DATA, dataList);
		userData.put(ServiceConstant.TOTAL, total != -1 ? total : dataList.size());

		return userData;
	}

	public Map<String, String> queryUserInfo(String account) {
		return queryMapper.queryUserInfo(account);
	}

	public Map<String, String> groupStd(Map<String, Object> parameter) {

		if (parameter != null && parameter.containsKey("status")) {
			String status = (String) parameter.get("status");
			if (status != null && !"".equals(status)) {
				parameter.put("zhuangtai", status.split(","));
			}
		}

		List<String> l = new ArrayList<String>();
		// 根据输入的关键词，切分出标准号和标准名称
		if (parameter.get("keyword") != null && !"".equals(parameter.get("keyword"))) {
			Map<String, Object> splitMap = SplitKeyWord
					.splitBzhAndZwmc(((String) parameter.get("keyword")).toUpperCase());
			parameter.put("a100_1", splitMap.get("a100_1"));
			parameter.put("a100_2", splitMap.get("a100_2"));

			for (String s : (String[]) splitMap.get("keyword")) {
				l.add(s);
			}
			parameter.put("list", l);
		} else {
			parameter.put("a100_1", null);
			parameter.put("a100_2", null);
			parameter.put("list", null);
		}
		return queryMapper.groupStd(parameter);
	}
}
