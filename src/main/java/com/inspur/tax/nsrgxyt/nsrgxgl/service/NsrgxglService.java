package com.inspur.tax.nsrgxyt.nsrgxgl.service;

import java.util.Map;

/**
 * 
 * 纳税人关系管理
 *
 * @author wbw
 * @since 2017年10月30日 上午10:23:07
 */
public interface NsrgxglService {

	/**
	 * 
	 * 获取指定关系分类
	 *
	 * @param gxflDm
	 * @return
	 * @since 2017年10月30日 下午4:20:17
	 */
	Map<String, String> getGxfl(String gxflDm);

	/**
	 * 
	 * 获取指定关系类型
	 *
	 * @param gxlxBm
	 * @return
	 * @since 2017年10月30日 下午4:20:33
	 */
	Map<String, String> getGxlx(String gxlxBm);

	/**
	 * 
	 * 获取关系分类列表
	 *
	 * @param parameter
	 * @return
	 * @since 2017年10月30日 下午2:37:36
	 */
	Map<String, Object> listGxfl(Map<String, Object> parameter);

	/**
	 * 
	 * 获取关系类型列表
	 *
	 * @param parameter
	 * @return
	 * @since 2017年10月30日 下午2:37:31
	 */
	Map<String, Object> listGxlx(Map<String, Object> parameter);

	/**
	 * 
	 * 生成关系分类代码
	 *
	 * @return
	 * @since 2017年10月30日 下午2:44:36
	 */
	String initGxflDm();

	/**
	 * 
	 * 生成关系类型编码
	 *
	 * @param gxflDm
	 * @return
	 * @since 2017年10月30日 下午2:45:00
	 */
	String initGxlxBm(String gxflDm);

	/**
	 * 
	 * 添加关系分类
	 *
	 * @param gxfl
	 * @return
	 * @since 2017年10月30日 下午2:54:03
	 */
	boolean addGxfl(Map<String, Object> gxfl);

	/**
	 * 
	 * 添加关系类型
	 *
	 * @param gxlx
	 * @return
	 * @since 2017年10月30日 下午2:54:30
	 */
	Map<String, Object> addGxlx(Map<String, Object> gxlx);

	/**
	 * 
	 * 修改关系分类
	 *
	 * @param gxfl
	 * @return
	 * @since 2017年10月30日 下午3:06:53
	 */
	boolean updateGxfl(Map<String, Object> gxfl);

	/**
	 * 
	 * 修改关系类型
	 *
	 * @param gxlx
	 * @return
	 * @since 2017年10月30日 下午3:06:48
	 */
	Map<String, Object> updateGxlx(Map<String, Object> gxlx);

	/**
	 * 
	 * 启用指定关系分类
	 *
	 * @param gxflDm
	 * @return
	 * @since 2017年10月30日 下午3:20:40
	 */
	boolean enabledGxfl(String gxflDm);

	/**
	 * 
	 * 停用指定关系分类
	 *
	 * @param gxflDm
	 * @return
	 * @since 2017年10月30日 下午3:56:52
	 */
	boolean disabledGxfl(String gxflDm);

	/**
	 * 
	 * 启用指定关系类型
	 *
	 * @param gxlxBm
	 * @return
	 * @since 2017年10月30日 下午3:27:18
	 */
	boolean enabledGxlx(String gxlxBm);

	/**
	 * 
	 * 停用指定关系类型
	 *
	 * @param gxlxBm
	 * @return
	 * @since 2017年10月30日 下午3:29:29
	 */
	boolean disabledGxlx(String gxlxBm);

	/**
	 * 
	 * 删除指定关系分类
	 *
	 * @param gxflDm
	 * @return
	 * @since 2017年10月30日 下午3:40:31
	 */
	boolean delGxfl(String gxflDm);

	/**
	 * 
	 * 删除指定关系类型
	 *
	 * @param gxlxBm
	 * @return
	 * @since 2017年10月30日 下午3:41:19
	 */
	boolean delGxlx(String gxlxBm);

	/**
	 * 
	 * 获取字段列表
	 *
	 * @param parameter
	 * @return
	 * @since 2017年10月31日 上午11:48:52
	 */
	Map<String, Object> listZd(Map<String, Object> parameter);

}