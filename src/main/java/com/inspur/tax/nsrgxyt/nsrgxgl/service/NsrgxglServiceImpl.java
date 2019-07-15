package com.inspur.tax.nsrgxyt.nsrgxgl.service;

import static com.inspur.tax.utils.Collections.newArrayList;
import static com.inspur.tax.utils.Collections.newHashMap;

import java.util.List;
import java.util.Map;

import org.loushang.framework.mybatis.PageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.inspur.tax.nsrgxyt.common.constant.ServiceConstant.*;
import static com.inspur.tax.nsrgxyt.nsrgxgl.constant.NsrgxglConstant.*;
import com.inspur.tax.nsrgxyt.nsrgxgl.dao.NsrgxglDao;
import com.inspur.tax.utils.TranUtil;
import com.inspur.tax.utils.json.JsonUtil;

/**
 * 
 * 纳税人关系管理
 *
 * @author wbw
 * @since 2017年10月30日 上午10:23:48
 */
@Service
public class NsrgxglServiceImpl implements NsrgxglService {

	private static final Logger LOG = LoggerFactory.getLogger(NsrgxglServiceImpl.class);

	@Autowired
	private NsrgxglDao nsrgxglDao;

	@Override
	public Map<String, String> getGxfl(String gxflDm) {
		return nsrgxglDao.getGxfl(gxflDm);
	}

	@Override
	public Map<String, String> getGxlx(String gxlxBm) {
		return nsrgxglDao.getGxlx(gxlxBm);
	}

	@Override
	public Map<String, Object> listGxfl(Map<String, Object> parameter) {
		Map<String, Object> gxflData = newHashMap();

		List<Map<String, String>> gxflList = nsrgxglDao.listGxfl(parameter);
		int total = PageUtil.getTotalCount();
		gxflData.put(DATA, gxflList);
		gxflData.put(TOTAL, total != -1 ? total : gxflList.size());

		return gxflData;
	}

	@Override
	public Map<String, Object> listGxlx(Map<String, Object> parameter) {
		Map<String, Object> gxlxData = newHashMap();

		List<Map<String, String>> gxlxList = nsrgxglDao.listGxlx(parameter);
		int total = PageUtil.getTotalCount();
		gxlxData.put(DATA, gxlxList);
		gxlxData.put(TOTAL, total != -1 ? total : gxlxList.size());

		return gxlxData;
	}

	@Override
	public String initGxflDm() {
		return nsrgxglDao.initGxflDm();
	}

	@Override
	public String initGxlxBm(String gxflDm) {
		return nsrgxglDao.initGxlxBm(gxflDm);
	}

	@Override
	public boolean addGxfl(Map<String, Object> gxfl) {
		return nsrgxglDao.addGxfl(gxfl) > 0;
	}

	@Override
	public Map<String, Object> addGxlx(Map<String, Object> gxlx) {

		// 将字段信息转换为json字符串
		if (gxlx.get(GXLX_ZD) != null) {
			String zdJson = JsonUtil.objectToJsonStr(gxlx.get(GXLX_ZD));
			gxlx.put(GXLX_ZD, zdJson);
		}

		Map<String, Object> result = newHashMap();

		result.put(FLAG, nsrgxglDao.addGxlx(gxlx) > 0);
		result.put(GXLX_BM, gxlx.get(GXLX_BM));

		return result;
	}

	@Override
	public boolean updateGxfl(Map<String, Object> gxfl) {
		return nsrgxglDao.updateGxfl(gxfl) > 0;
	}

	@Override
	public Map<String, Object> updateGxlx(Map<String, Object> gxlx) {

		// 将字段信息转换为json字符串
		if (gxlx.get(GXLX_ZD) != null) {
			String zdJson = JsonUtil.objectToJsonStr(gxlx.get(GXLX_ZD));
			gxlx.put(GXLX_ZD, zdJson);
		}

		Map<String, Object> result = newHashMap();

		result.put(FLAG, nsrgxglDao.updateGxlx(gxlx) > 0);
		result.put(GXLX_BM, gxlx.get(GXLX_BM));

		return result;
	}

	@Override
	public boolean enabledGxfl(String gxflDm) {
		boolean flag = false;

		TranUtil tran = new TranUtil().begin();
		try {
			flag = nsrgxglDao.enabledGxfl(gxflDm) > 0;
			if (flag) {
				// 同时启用关系分类下的关系类型
				nsrgxglDao.enabledGxlxByGxflDm(gxflDm);
			}
			tran.commit();
		} catch (Exception e) {
			flag = false;
			tran.rollback();
			LOG.error("enabledGxfl ERROR", e);
		}

		return flag;
	}

	@Override
	public boolean disabledGxfl(String gxflDm) {
		boolean flag = false;

		TranUtil tran = new TranUtil().begin();
		try {
			flag = nsrgxglDao.disabledGxfl(gxflDm) > 0;
			if (flag) {
				// 同时停用关系分类下的关系类型
				nsrgxglDao.disabledGxlxByGxflDm(gxflDm);
			}
			tran.commit();
		} catch (Exception e) {
			flag = false;
			tran.rollback();
			LOG.error("disabledGxfl ERROR", e);
		}

		return flag;
	}

	@Override
	public boolean enabledGxlx(String gxlxBm) {
		return nsrgxglDao.enabledGxlx(gxlxBm) > 0;
	}

	@Override
	public boolean disabledGxlx(String gxlxBm) {
		return nsrgxglDao.disabledGxlx(gxlxBm) > 0;
	}

	@Override
	public boolean delGxfl(String gxflDm) {
		boolean flag = false;

		TranUtil tran = new TranUtil().begin();
		try {
			flag = nsrgxglDao.delGxfl(gxflDm) > 0;
			if (flag) {
				// 同时删除关系分类下的关系类型
				nsrgxglDao.delGxlxByGxflDm(gxflDm);
			}
			tran.commit();
		} catch (Exception e) {
			flag = false;
			tran.rollback();
			LOG.error("delGxfl ERROR", e);
		}

		return flag;
	}

	@Override
	public boolean delGxlx(String gxlxBm) {
		return nsrgxglDao.delGxlx(gxlxBm) > 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> listZd(Map<String, Object> parameter) {
		Map<String, Object> zdData = newHashMap();

		List<Map<String, String>> zdList = null;
		if (parameter.containsKey(GXLX_BM)) {
			String gxlxBm = (String) parameter.get(GXLX_BM);
			String zdJson = nsrgxglDao.getZd(gxlxBm);
			if (zdJson != null) {
				zdList = JsonUtil.jsonStrToObject(zdJson, List.class);
			} else {
				zdList = newArrayList();
			}
		} else {
			zdList = newArrayList();
		}
		zdData.put(DATA, zdList);

		return zdData;
	}

}