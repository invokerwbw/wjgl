package com.inspur.tax.common.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;

import com.inspur.tax.utils.PropertiesLoader;

/**
 * 启动时设置<tt>spring.profiles.active</tt>
 * <p>
 * 用于不同运行环境的选择
 *
 * @author wbw
 * @since 2016年9月23日 下午1:38:52
 */
public class SetRunningMode implements WebApplicationInitializer {

	private static final Logger log = LoggerFactory.getLogger(SetRunningMode.class);

	private final PropertiesLoader configProperties = new PropertiesLoader("config.properties");
	/**
	 * 是否使用redis
	 */
	private boolean isUseRedis = configProperties.getBoolean("system.redis.isUseRedis");
	/**
	 * spring cache集成模式
	 */
	private String cacheMode = configProperties.getProperty("system.cache.mode");
	/**
	 * 数据源模式
	 */
	private String dataSourceMode = configProperties.getProperty("system.dataSource.mode");
	/**
	 * 是否使用greenplum
	 */
	private boolean isUseGreenplum = configProperties.getBoolean("system.dataSource.isUseGreenplum");
	/**
	 * 是否使用mq生产者
	 */
	private boolean isUseProducer = configProperties.getBoolean("system.mq.isUseProducer");
	/**
	 * 是否使用mq消费者
	 */
	private boolean isUseConsumer = configProperties.getBoolean("system.mq.isUseConsumer");

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {

		if (!isUseRedis && "redisCache".equals(cacheMode)) {
			log.warn("system.redis.isUseRedis is " + isUseRedis + ", but cacheMode is " + cacheMode);
			cacheMode = "ehcache";
			log.warn("cacheMode changes to " + cacheMode);
		}

		String actives = "";
		if (isUseProducer) {
			actives += ",iTaxMQProducer";
		}
		if (isUseConsumer) {
			actives += ",iTaxMQConsumer";
		}
		if (isUseRedis) {
			actives += ",redis";
		}
		if (isUseGreenplum) {
			actives += ",greenplum";
		}

		boolean flag = servletContext.setInitParameter("spring.profiles.active",
				cacheMode + "," + dataSourceMode + actives);

		if (flag) {
			log.info("isUseRedis:" + isUseRedis);
			log.info("cacheMode:" + cacheMode);
			log.info("dataSourceMode:" + dataSourceMode);
			log.info("isUseProducer:" + isUseProducer);
			log.info("isUseConsumer:" + isUseConsumer);
			log.info("isUseGreenplum:" + isUseGreenplum);
		} else {
			log.warn("set spring.profiles.active failure...");
		}
	}

}