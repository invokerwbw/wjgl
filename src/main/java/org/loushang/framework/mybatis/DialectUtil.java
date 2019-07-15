package org.loushang.framework.mybatis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 覆盖loushang-framework中的数据库识别工具
 * <p>
 * 目的是
 * <ul>
 * <li>添加对h2的支持</li>
 * </ul>
 *
 * @author wbw
 * @since 2016年9月23日 下午1:55:52
 */
public class DialectUtil {

	private static final Logger logger = LoggerFactory.getLogger(DialectUtil.class);

	/**
	 * 根据_databaseId匹配的数据库类型
	 * <p>
	 * 没有匹配的数据库皆识别为db2
	 * 
	 * @param _databaseId
	 *            数据库ID
	 * @return 匹配的数据库类型
	 * @since 2016年9月23日 下午1:56:24
	 */
	public static String getDBTypeByDBId(String _databaseId) {
		if (logger.isDebugEnabled()) {
			logger.debug("数据库类型为：" + _databaseId);
		}
		if (null != _databaseId && !"".equals(_databaseId)) {
			if (_databaseId.toLowerCase().contains("db2")) {
				return "db2";
			} else if (_databaseId.toLowerCase().contains("oracle")) {
				return "oracle";
			} else if (_databaseId.toLowerCase().contains("mysql")) {
				return "mysql";
			} else if (_databaseId.toLowerCase().contains("hdb")) {
				return "hana";
			} else if (_databaseId.toLowerCase().contains("sql server")) {
				return "sqlserver";
			} else if (_databaseId.toLowerCase().contains("postgresql")) {
				return "postgresql";
			} else if (_databaseId.toLowerCase().contains("h2")) {
				return "h2";
			}

		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("未获取到数据库类型");
			}
			return "db2";
		}
		return "db2";
	}

}
