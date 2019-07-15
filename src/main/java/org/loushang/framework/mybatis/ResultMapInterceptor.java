package org.loushang.framework.mybatis;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.resultset.DefaultResultSetHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inspur.tax.utils.StringUtils;

/**
 * 覆盖loushang-framework中的ResultMap拦截器
 * <p>
 * 目的是
 * <ul>
 * <li>添加对驼峰命名法转换的支持</li>
 * </ul>
 * 
 * @author wbw
 * @since 2016年9月23日 下午2:03:59
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }),
		@Signature(method = "handleResultSets", type = ResultSetHandler.class, args = { Statement.class }) })
public class ResultMapInterceptor implements Interceptor {

	private static Logger log = LoggerFactory.getLogger(ResultMapInterceptor.class);

	@SuppressWarnings("unchecked")
	public Object intercept(Invocation ivk) throws Throwable {

		Object target = ivk.getTarget();

		/**
		 * 获取返回值类型
		 */
		if (ResultMapUtil.getUpperOrLower() != null && target instanceof RoutingStatementHandler) {

			RoutingStatementHandler statementHandler = (RoutingStatementHandler) target;
			BaseStatementHandler delegate = (BaseStatementHandler) ReflectHelper.getValueByFieldName(statementHandler,
					"delegate");
			MappedStatement mappedStatement = (MappedStatement) ReflectHelper.getValueByFieldName(delegate,
					"mappedStatement");

			List<ResultMap> rms = mappedStatement.getResultMaps();
			ResultMap rm = rms != null && rms.size() > 0 ? rms.get(0) : null;
			String type = rm != null && rm.getType() != null ? rm.getType().getName() : "";
			if ("java.util.HashMap".equals(type) || "java.util.Map".equals(type)) {
				ResultMapUtil.threadInfo.set(true);
			}

		}

		/**
		 * 统一Map类型返回值大小写
		 */
		if (ResultMapUtil.getUpperOrLower() != null && target instanceof DefaultResultSetHandler) {

			if (ResultMapUtil.threadInfo.get() != null && (Boolean) ResultMapUtil.threadInfo.get()) {
				// 获取到当前的Statement
				Statement stmt = (Statement) ivk.getArgs()[0];
				// 通过Statement获取到当前的结果集，对其进行处理，并返回对应的处理结果
				ResultMapUtil.threadInfo.remove();
				return handleResultSet(stmt.getResultSet());
			}
		}

		ResultMapUtil.threadInfo.remove();

		return ivk.proceed();
	}

	/**
	 * 处理结果集
	 * 
	 * @param resultSet
	 *            需要处理的{@linkplain java.sql.ResultSet 结果集}
	 * @return 处理后的结果集
	 * @since 2016年9月23日 下午2:01:07
	 */
	private Object handleResultSet(ResultSet resultSet) {

		if (resultSet != null) {
			List<Object> resultList = new ArrayList<Object>();
			try {
				ResultSetMetaData rsmd = resultSet.getMetaData();
				int columnCount = rsmd.getColumnCount();
				// 把每一行对应的Key和Value存放到Map中
				while (resultSet.next()) {
					// 定义用于存放Key-Value的Map
					Map<Object, Object> map = new HashMap<Object, Object>();
					// 根据配置属性，将Map的key统一为大写或小写
					for (int i = 1; i <= columnCount; i++) {

						Object value = resultSet.getObject(rsmd.getColumnName(i));

						if ("camel".equals(ResultMapUtil.getUpperOrLower())) {// 驼峰命名法
							map.put(StringUtils.toCamelCase(rsmd.getColumnName(i)), value);
						} else if ("upper".equals(ResultMapUtil.getUpperOrLower())) {
							map.put(rsmd.getColumnName(i).toUpperCase(), value);
						} else {
							map.put(rsmd.getColumnName(i).toLowerCase(), value);
						}

					}

					// 把封装好的Map存放到List中并进行返回
					resultList.add(map);
				}
			} catch (SQLException e) {
				log.error("Mybatis返回值Map统一大小处理出错！", e);
			} finally {
				closeResultSet(resultSet);
			}

			return resultList;
		}

		return null;
	}

	/**
	 * 关闭结果集
	 * 
	 * @param resultSet
	 *            需要关闭的{@linkplain java.sql.ResultSet 结果集}
	 * @see java.sql.ResultSet#close()
	 * @since 2016年9月23日 下午2:00:43
	 */
	private void closeResultSet(ResultSet resultSet) {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException e) {

		}
	}

	public Object plugin(Object obj) {
		return Plugin.wrap(obj, this);
	}

	public void setProperties(Properties properties) {
		// 是否返回记录总数
		ResultMapUtil.setUpperOrLower(null);
		if (properties.get("upperOrLower") != null) {
			String upperOrLower = (String) properties.get("upperOrLower");
			if ("camel".equals(upperOrLower.toLowerCase())) {
				ResultMapUtil.setUpperOrLower("camel");
			} else if ("upper".equals(upperOrLower.toLowerCase())) {
				ResultMapUtil.setUpperOrLower("upper");
			} else if ("lower".equals(upperOrLower.toLowerCase())) {
				ResultMapUtil.setUpperOrLower("lower");
			} else {
				log.warn("Mybatis返回值Map统一大小写配置属性{" + upperOrLower + "}为无效值，属性为 upper、lower 或 camel!");
			}
		}
	}

}
