package com.inspur.tax.nsrgxyt.nsrgxgl.dao;

import java.util.List;
import java.util.Map;

/**
 * 
 * 纳税人关系管理
 *
 * @author wbw
 * @since 2017年10月30日 上午10:22:14
 */
public interface NsrgxglDao {

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
	 * @since 2017年10月30日 下午2:28:11
	 */
	List<Map<String, String>> listGxfl(Map<String, Object> parameter);

	/**
	 * 
	 * 获取关系类型列表
	 *
	 * @param parameter
	 * @return
	 * @since 2017年10月30日 下午2:28:41
	 */
	List<Map<String, String>> listGxlx(Map<String, Object> parameter);

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
	int addGxfl(Map<String, Object> gxfl);

	/**
	 * 
	 * 添加关系类型
	 *
	 * @param gxlx
	 * @return
	 * @since 2017年10月30日 下午2:54:30
	 */
	int addGxlx(Map<String, Object> gxlx);

	/**
	 * 
	 * 修改关系分类
	 *
	 * @param gxfl
	 * @return
	 * @since 2017年10月30日 下午3:06:53
	 */
	int updateGxfl(Map<String, Object> gxfl);

	/**
	 * 
	 * 修改关系类型
	 *
	 * @param gxlx
	 * @return
	 * @since 2017年10月30日 下午3:06:48
	 */
	int updateGxlx(Map<String, Object> gxlx);

	/**
	 * 
	 * 启用指定关系分类
	 *
	 * @param gxflDm
	 * @return
	 * @since 2017年10月30日 下午3:20:40
	 */
	int enabledGxfl(String gxflDm);

	/**
	 * 
	 * 停用指定关系分类
	 *
	 * @param gxflDm
	 * @return
	 * @since 2017年10月30日 下午3:21:03
	 */
	int disabledGxfl(String gxflDm);

	/**
	 * 
	 * 启用指定关系类型
	 *
	 * @param gxlxBm
	 * @return
	 * @since 2017年10月30日 下午3:27:18
	 */
	int enabledGxlx(String gxlxBm);

	/**
	 * 
	 * 根据关系分类代码启用关系类型
	 *
	 * @param gxflDm
	 * @return
	 * @since 2017年10月30日 下午3:28:10
	 */
	int enabledGxlxByGxflDm(String gxflDm);

	/**
	 * 
	 * 停用指定关系类型
	 *
	 * @param gxlxBm
	 * @return
	 * @since 2017年10月30日 下午3:29:29
	 */
	int disabledGxlx(String gxlxBm);

	/**
	 * 
	 * 根据关系分类代码停用关系类型
	 *
	 * @param gxflDm
	 * @return
	 * @since 2017年10月30日 下午3:29:55
	 */
	int disabledGxlxByGxflDm(String gxflDm);

	/**
	 * 
	 * 删除指定关系分类
	 *
	 * @param gxflDm
	 * @return
	 * @since 2017年10月30日 下午3:40:31
	 */
	int delGxfl(String gxflDm);

	/**
	 * 
	 * 删除指定关系类型
	 *
	 * @param gxlxBm
	 * @return
	 * @since 2017年10月30日 下午3:41:19
	 */
	int delGxlx(String gxlxBm);

	/**
	 * 
	 * 根据关系分类代码删除关系类型
	 *
	 * @param gxflDm
	 * @return
	 * @since 2017年10月30日 下午3:42:20
	 */
	int delGxlxByGxflDm(String gxflDm);

	/**
	 * 
	 * 获取指定关系类型字段
	 *
	 * @param gxlxBm
	 * @return
	 * @since 2017年10月31日 上午11:50:18
	 */
	String getZd(String gxlxBm);

}