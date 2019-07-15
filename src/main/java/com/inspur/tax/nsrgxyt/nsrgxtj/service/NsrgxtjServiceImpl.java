package com.inspur.tax.nsrgxyt.nsrgxtj.service;

import static com.inspur.tax.utils.Collections.newHashMap;

import java.util.List;
import java.util.Map;

import org.loushang.framework.mybatis.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inspur.tax.nsrgxyt.common.constant.ServiceConstant;
import com.inspur.tax.nsrgxyt.nsrgxtj.dao.NsrgxtjDao;

/**
 * 
 * 纳税人关系统计
 *
 * @author wbw
 * @since 2017年10月30日 上午10:26:42
 */
@Service
public class NsrgxtjServiceImpl implements NsrgxtjService {

	@Autowired
	private NsrgxtjDao nsrgxtjDao;

	@Override
	public Map<String, Object> listGx(Map<String, Object> parameter) {
		Map<String, Object> gxData = newHashMap();

		List<Map<String, String>> gxList = nsrgxtjDao.listGx(parameter);

		int total = PageUtil.getTotalCount();
		gxData.put(ServiceConstant.DATA, gxList);
		gxData.put(ServiceConstant.TOTAL, total != -1 ? total : gxList.size());

		return gxData;
	}

	@Override
	public Map<String, Object> listGxtj(Map<String, Object> parameter) {
		Map<String, Object> gxtjData = newHashMap();

		List<Map<String, String>> gxtjList = nsrgxtjDao.listGxtj(parameter);

		int total = PageUtil.getTotalCount();
		gxtjData.put(ServiceConstant.DATA, gxtjList);
		gxtjData.put(ServiceConstant.TOTAL, total != -1 ? total : gxtjList.size());

		return gxtjData;
	}

}