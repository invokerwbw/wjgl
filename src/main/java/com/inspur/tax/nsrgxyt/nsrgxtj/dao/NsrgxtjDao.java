package com.inspur.tax.nsrgxyt.nsrgxtj.dao;

import java.util.List;
import java.util.Map;

/**
 * 
 * 纳税人关系统计
 *
 * @author wbw
 * @since 2017年10月30日 上午10:25:34
 */
public interface NsrgxtjDao {

	/**
	 * 
	 * 获取汇总表中纳税人关系
	 *
	 * @param parameter
	 * @return
	 * @since 2017年11月1日 下午7:32:27
	 */
	List<Map<String, String>> listGx(Map<String, Object> parameter);

	/**
	 * 
	 * 获取关系统计列表信息
	 *
	 * @param parameter
	 * @return
	 * @since 2017年11月2日 上午9:16:59
	 */
	List<Map<String, String>> listGxtj(Map<String, Object> parameter);

}