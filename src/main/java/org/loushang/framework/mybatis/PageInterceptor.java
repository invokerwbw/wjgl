package org.loushang.framework.mybatis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 覆盖loushang-framework中的分页拦截器
 * <p>
 * 目的是
 * <ul>
 * <li>添加对h2的支持</li>
 * </ul>
 *
 * @author wbw
 * @since 2016年9月23日 下午1:57:49
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class PageInterceptor implements Interceptor {

	private static Logger log = LoggerFactory.getLogger(PageInterceptor.class);

	private boolean totalCount = false;

	/**
	 * 分页拦截器，检查参数中是否存在rowSelection参数，如果存在，则进行分页处理
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object intercept(Invocation ivk) throws Throwable {

		RoutingStatementHandler statementHandler = (RoutingStatementHandler) ivk.getTarget();
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

		BoundSql boundSql = delegate.getBoundSql();
		// Configuration configuration = mappedStatement.getConfiguration();

		if (ivk.getTarget() instanceof RoutingStatementHandler) {

			String sqlTypeName = mappedStatement.getSqlCommandType().name();
			String sql = boundSql.getSql();

			// 只处理SELECT 语句 && 过滤查询总条数的SQL
			if (sqlTypeName.equals("SELECT") && !sql.startsWith("SELECT COUNT(*) FROM")) {

				// 分页SQL<select>中parameterType属性对应的实体参数，即Mapper接口中执行分页方法的参数,该参数不得为空
				Object parameterObject = boundSql.getParameterObject();

				if (parameterObject instanceof Map && (((Map) parameterObject).containsKey("limit"))) {
					Map paraMap = (Map) parameterObject;
					int pageSize = -1;
					if (paraMap.get("limit") instanceof Integer) {
						pageSize = (Integer) paraMap.get("limit");
					} else {
						pageSize = Integer.parseInt((String) paraMap.get("limit"));
					}

					// 只处理分页的select语句
					if (pageSize != -1) {
						Connection connection = (Connection) ivk.getArgs()[0];

						String dbtype = DialectUtil.getDBTypeByDBId(connection.getMetaData().getDatabaseProductName());

						// 如果存在分页查询条件，则进行分页处理
						if (totalCount) {
							// sqlserver子查询sql中包含order by报错
							if ("sqlserver".equals(dbtype) && sql.toUpperCase().contains("ORDER BY")) {
								sql = sql.trim();
								sql = "select top 100 percent" + sql.substring(6);
							}
							// 取得记录的总行数
							String countSql = "select count(0) from (" + sql + ")   tmp_count"; // 记录统计
							PreparedStatement countStmt = connection.prepareStatement(countSql);
							setParameters(countStmt, mappedStatement, boundSql, parameterObject);
							ResultSet rs = countStmt.executeQuery();
							int count = 0;
							if (rs.next()) {
								count = rs.getInt(1);
							}
							rs.close();
							countStmt.close();

							// 将总行数放入全局的容器中，便于将来取出
							PageUtil.setTotalCount(count);
						}
						String pageSql = generatePageSql(sql, paraMap, dbtype);
						ReflectHelper.setValueByFieldName(boundSql, "sql", pageSql); // 将分页sql语句反射回BoundSql.
					}
				}
			}
		}

		return ivk.proceed();
	}

	/**
	 * 对SQL参数(?)设值
	 * 
	 * @param ps
	 *            {@linkplain java.sql.PreparedStatement PreparedStatement}
	 * @param mappedStatement
	 *            {@linkplain org.apache.ibatis.mapping.MappedStatement
	 *            MappedStatement}
	 * @param boundSql
	 *            {@linkplain org.apache.ibatis.mapping.BoundSql BoundSql}
	 * @param parameterObject
	 *            参数对象
	 * @see org.apache.ibatis.scripting.defaults.DefaultParameterHandler
	 * @throws SQLException
	 *             SQLException
	 * @since 2016年9月23日 下午2:06:59
	 */
	@SuppressWarnings("unchecked")
	private void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql,
			Object parameterObject) throws SQLException {
		ErrorContext.instance().activity("setting parameters").object(mappedStatement.getParameterMap().getId());
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		if (parameterMappings != null) {
			Configuration configuration = mappedStatement.getConfiguration();
			TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
			MetaObject metaObject = parameterObject == null ? null : configuration.newMetaObject(parameterObject);
			for (int i = 0; i < parameterMappings.size(); i++) {
				ParameterMapping parameterMapping = parameterMappings.get(i);
				if (parameterMapping.getMode() != ParameterMode.OUT) {
					Object value;
					String propertyName = parameterMapping.getProperty();
					PropertyTokenizer prop = new PropertyTokenizer(propertyName);
					if (parameterObject == null) {
						value = null;
					} else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
						value = parameterObject;
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						value = boundSql.getAdditionalParameter(propertyName);
					} else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX)
							&& boundSql.hasAdditionalParameter(prop.getName())) {
						value = boundSql.getAdditionalParameter(prop.getName());
						if (value != null) {
							value = configuration.newMetaObject(value)
									.getValue(propertyName.substring(prop.getName().length()));
						}
					} else {
						value = metaObject == null ? null : metaObject.getValue(propertyName);
					}
					@SuppressWarnings("rawtypes")
					TypeHandler typeHandler = parameterMapping.getTypeHandler();
					if (typeHandler == null) {
						log.error(getClass().getName() + "(177):There was no TypeHandler found for parameter "
								+ propertyName + " of statement " + mappedStatement.getId());
						throw new ExecutorException("There was no TypeHandler found for parameter " + propertyName
								+ " of statement " + mappedStatement.getId());
					}
					typeHandler.setParameter(ps, i + 1, value, parameterMapping.getJdbcType());
				}
			}
		}
	}

	/**
	 * 根据数据库方言,生成特定的分页sql
	 * 
	 * @param sql
	 *            待分页sql
	 * @param map
	 *            分页参数
	 * @param dialect
	 *            数据库方言类型
	 * @return 分页sql
	 * @since 2016年9月23日 下午2:05:37
	 */
	private String generatePageSql(String sql, @SuppressWarnings("rawtypes") Map map, String dialect) {

		if (notEmpty(dialect)) {
			StringBuffer pageSql = new StringBuffer();
			int start;
			int end;

			if (map.get("start") instanceof Integer) {
				start = (Integer) map.get("start");
			} else {
				start = Integer.parseInt((String) map.get("start"));
			}
			if (map.get("limit") instanceof Integer) {
				end = (Integer) map.get("limit");
			} else {
				end = Integer.parseInt((String) map.get("limit"));
			}

			if ("mysql".equals(dialect)) {
				pageSql.append(sql);
				pageSql.append(" limit " + start + "," + end);
			} else if ("oracle".equals(dialect)) {
				pageSql.append("SELECT * FROM (SELECT TMP_TB.*,ROWNUM ROW_ID FROM (");
				pageSql.append(sql);
				// 解决Oracle分页查询排序重复问题
				if (sql.toUpperCase().indexOf("ORDER BY") == -1) {
					// 若SQL中不包含ORDER BY，则强制增加一个排序
					// 若SQl包含ORDER BY，但排序条件不唯一，也有可能造成查询重复问题，这种情况需要项目自己添加唯一排序条件解决
					pageSql.append(" ORDER BY ROWNUM ASC");
				}
				pageSql.append(") TMP_TB WHERE ROWNUM<=");
				pageSql.append(start + end);
				pageSql.append(") WHERE ROW_ID>=");
				pageSql.append(start + 1);
			} else if ("db2".equals(dialect)) {
				pageSql.append("SELECT * FROM ( SELECT B.*, ROWNUMBER() OVER() AS RN FROM    (    ");
				pageSql.append(sql);
				pageSql.append(" ) AS B   ) AS A  ");
				pageSql.append(" WHERE A.RN BETWEEN " + (start + 1) + " AND " + (start + end) + " ");

			} else if ("sqlserver".equals(dialect)) {
				sql = sql.toUpperCase();
				String tableName = "";
				int fIdx = sql.lastIndexOf("FROM") + 4;
				int wIdx = sql.lastIndexOf("WHERE");
				if (wIdx != -1) {
					tableName = sql.substring(fIdx, wIdx).trim();
				} else {
					tableName = sql.substring(fIdx).trim();
				}
				// 获取table主键字段名称
				String pk = "select name from sysobjects where parent_obj=object_id('" + tableName
						+ "') and xtype='PK'";
				// sqlserver分页查询使用ROW_NUMBER()函数需要结合OVER()中的ORDER BY语句
				pageSql.append("SELECT * FROM ( SELECT B.*, ROW_NUMBER() OVER(ORDER BY (");
				pageSql.append(pk);
				pageSql.append(")) AS RN FROM    (    ");
				pageSql.append(sql);
				pageSql.append(" ) AS B   ) AS A  ");
				pageSql.append(" WHERE A.RN BETWEEN " + (start + 1) + " AND " + (start + end) + " ");

			} else if ("hana".equals(dialect)) {
				// hana分页处理
				pageSql.append("SELECT * FROM ( SELECT B.*, ROW_NUMBER() OVER() AS RN FROM    (    ");
				pageSql.append(sql);
				pageSql.append(" ) AS B   ) AS A  ");
				pageSql.append(" WHERE A.RN BETWEEN " + (start + 1) + " AND " + (start + end) + " ");

			} else if ("postgresql".equals(dialect)) {
				pageSql.append(sql);
				pageSql.append(" limit " + end + " offset " + start);
			} else if ("h2".equals(dialect)) {
				pageSql.append(sql);
				pageSql.append(" limit " + end + " offset " + start);
			}
			return pageSql.toString();
		} else {
			return sql;
		}
	}

	public Object plugin(Object arg0) {
		return Plugin.wrap(arg0, this);
	}

	public static boolean isEmpty(String dialect) {

		return dialect == null || dialect.trim().equals("");
	}

	public static boolean notEmpty(String dialect) {
		return dialect != null && !dialect.trim().equals("");
	}

	private static String getParameterValue(Object obj) {
		String value = null;
		if (obj instanceof String) {
			value = "'" + obj.toString() + "'";
		} else if (obj instanceof Date) {
			DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
			value = "'" + formatter.format(new Date()) + "'";
		} else {
			if (obj != null) {
				value = obj.toString();
			} else {
				value = "";
			}
		}
		return value;
	}

	public static String showSql(Configuration configuration, BoundSql boundSql) {

		Object parameterObject = boundSql.getParameterObject();
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
		if (parameterMappings.size() > 0 && parameterObject != null) {
			TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
			if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
				sql = sql.replaceFirst("\\?", getParameterValue(parameterObject));
			} else {
				MetaObject metaObject = configuration.newMetaObject(parameterObject);
				for (ParameterMapping parameterMapping : parameterMappings) {
					String propertyName = parameterMapping.getProperty();
					if (metaObject.hasGetter(propertyName)) {
						Object obj = metaObject.getValue(propertyName);
						sql = sql.replaceFirst("\\?", getParameterValue(obj));
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						Object obj = boundSql.getAdditionalParameter(propertyName);
						sql = sql.replaceFirst("\\?", getParameterValue(obj));
					}
				}
			}
		}
		return sql;
	}

	public void setProperties(Properties properties) {
		// 是否返回记录总数
		if (properties.get("totalCount") != null) {
			String _totalCount = (String) properties.get("totalCount");
			totalCount = Boolean.parseBoolean(_totalCount);
		}

	}
}
