package com.inspur.tax.nsrgxyt.nsrgxtj.service;

import java.util.Map;

/**
 * 
 * 纳税人关系统计
 *
 * @author wbw
 * @since 2017年10月30日 上午10:26:13
 */
public interface NsrgxtjService {

	/**
	 * 
	 * 获取汇总表中纳税人关系
	 *
	 * @param parameter
	 * @return
	 * @since 2017年11月1日 下午7:36:18
	 */
	Map<String, Object> listGx(Map<String, Object> parameter);

	/**
	 * 
	 * 获取关系统计列表信息
	 *
	 * @param parameter
	 * @return
	 * @since 2017年11月2日 上午9:20:39
	 */
	Map<String, Object> listGxtj(Map<String, Object> parameter);

}