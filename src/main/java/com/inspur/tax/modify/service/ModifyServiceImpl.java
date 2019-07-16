package com.inspur.tax.modify.service;

import static com.inspur.tax.utils.Collections.*;

import java.util.List;
import java.util.Map;

import org.loushang.framework.mybatis.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inspur.tax.modify.dao.ModifyMapper;
import com.inspur.tax.nsrgxyt.common.constant.ServiceConstant;
import com.inspur.tax.query.util.SplitKeyWord;

@Service("modifyService")
public class ModifyServiceImpl implements IModifyService {

	@Autowired
	private ModifyMapper modifyMapper;

	@Override
	public Map<String, Object> listData(Map<String, Object> parameter) {
		Map<String, Object> data = newHashMap();
		List<Map<String, String>> dataList = newArrayList();

		if (parameter != null && parameter.containsKey("status")) {
			String status = (String) parameter.get("status");
			if (status != null && !"".equals(status)) {
				parameter.put("zhuangtai", status.split(","));
			}
		}

		if (parameter.containsKey("leibie")) {
			String leibie = (String) parameter.get("leibie");
			if (leibie != null) {
				switch (leibie) {
				case "all":
					parameter.put("leibie", null);
					break;
				case "gb":
					parameter.put("leibie", "G");
					break;
				case "hb":
					parameter.put("leibie", "C");
					break;
				case "db":
					parameter.put("leibie", "D");
					break;
				case "tb":
					parameter.put("leibie", "T");
					break;
				case "gw":
					parameter.put("leibie", "N");
					break;
				default:
					break;
				}
			}
		}

		// 根据输入的关键词，切分出标准号和标准名称
		if (parameter.get("keyword") != null && !"".equals(parameter.get("keyword"))) {

			Map<String, Object> splitMap = SplitKeyWord
					.splitBzhAndZwmc(((String) parameter.get("keyword")).toUpperCase());

			List<String> kList = newArrayList();
			for (String keyword : (String[]) splitMap.get("keyword")) {
				kList.add(keyword);
			}

			parameter.put("list", kList);
			parameter.put("a100_1", splitMap.get("a100_1"));
			parameter.put("a100_2", splitMap.get("a100_2"));

		} else {
			parameter.put("a100_1", null);
			parameter.put("a100_2", null);
			parameter.put("list", null);
		}

		List<Map<String, String>> list = modifyMapper.listData(parameter);
		Map<String, String> dataMap = null;
		for (Map<String, String> wp : list) {
			dataMap = newHashMap();

			dataMap.put("bzh", "<b>" + wp.get("bzh") + "</b>");

			if (wp.get("ywmc") == null) {
				dataMap.put("bzmc", wp.get("zwmc") + "brflag" + "");
			} else {
				dataMap.put("bzmc", wp.get("zwmc") + "brflag" + wp.get("ywmc"));
			}

			dataMap.put("fbrq", wp.get("fbrq"));
			dataMap.put("ssrq", wp.get("ssrq"));
			dataMap.put("ys", wp.get("ys"));
			dataMap.put("zt", wp.get("zt"));
			dataMap.put("cz", wp.get("bzh"));

			dataList.add(dataMap);
		}

		int total = PageUtil.getTotalCount();
		data.put(ServiceConstant.DATA, dataList);
		data.put(ServiceConstant.TOTAL, total != -1 ? total : dataList.size());

		return data;
	}

	@Override
	public Map<String, Object> getStd(String bzh) {
		return modifyMapper.getStd(bzh);
	}

	@Override
	public boolean deleteStd(String bzh) {
		return modifyMapper.deleteStd(bzh) > 0;
	}

	@Override
	public boolean updateStd(Map<String, Object> std) {
		return modifyMapper.updateStd(std) > 0;
	}

	@Override
	public boolean insertStd(Map<String, Object> std) {
		return modifyMapper.insertStd(std) > 0;
	}

}
