package com.inspur.tax.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.StringUtils;

import com.inspur.tax.utils.PropertiesLoader;

import redis.clients.jedis.JedisPoolConfig;

/**
 * redis配置
 *
 * @author wbw
 * @since 2016年9月23日 下午1:34:17
 */
@Configuration
@Profile("redis")
public class RedisConfig {

	private final PropertiesLoader redisProperties = new PropertiesLoader("redis.properties");

	/**
	 * redis的模式（哨兵模式:sentinel;单机模式:standalone）
	 */
	@Value("${system.redis.mode}")
	private String redisMode;

	/**
	 * 配置连接池
	 * 
	 * @return {@linkplain redis.clients.jedis.JedisPoolConfig 连接池配置}
	 * @since 2016年9月23日 下午1:34:32
	 */
	@Bean
	public JedisPoolConfig jedisPoolConfig() {

		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

		jedisPoolConfig.setMaxIdle(redisProperties.getInteger("redis.pool.maxIdle"));
		jedisPoolConfig.setMaxTotal(redisProperties.getInteger("redis.pool.maxTotal"));
		jedisPoolConfig.setMaxWaitMillis(redisProperties.getLong("redis.pool.maxWaitMillis"));
		jedisPoolConfig.setTestOnBorrow(redisProperties.getBoolean("redis.pool.testOnBorrow"));

		return jedisPoolConfig;
	}

	/**
	 * 配置连接工厂
	 * 
	 * @param jpc
	 *            {@linkplain redis.clients.jedis.JedisPoolConfig 连接池配置}
	 * @return {@linkplain org.springframework.data.redis.connection.jedis.JedisConnectionFactory
	 *         连接工厂}
	 * @since 2016年9月23日 下午1:36:08
	 */
	@Bean
	public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jpc) {

		/**
		 * 根据config.properties中system.redis.mode配置连接工厂(哨兵模式:sentinel;单机模式:
		 * standalone)
		 */
		if ("sentinel".equals(redisMode)) {

			RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration(
					redisProperties.getProperty("redis.sentinel.master"),
					StringUtils.commaDelimitedListToSet(redisProperties.getProperty("redis.sentinel.hostAndPost")));

			return new JedisConnectionFactory(sentinelConfig, jpc);
		} else {

			JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();

			jedisConnectionFactory.setHostName(redisProperties.getProperty("redis.host"));
			jedisConnectionFactory.setPort(redisProperties.getInteger("redis.port"));
			jedisConnectionFactory.setPassword(redisProperties.getProperty("redis.password"));
			jedisConnectionFactory.setTimeout(redisProperties.getInteger("redis.timeout"));
			jedisConnectionFactory.setDatabase(redisProperties.getInteger("redis.database"));
			jedisConnectionFactory.setPoolConfig(jpc);

			return jedisConnectionFactory;
		}
	}

	/**
	 * 配置redis模板类
	 * <p>
	 * 用于操作redis
	 * 
	 * @param jcf
	 *            {@linkplain org.springframework.data.redis.connection.jedis.JedisConnectionFactory
	 *            连接工厂}
	 * @return {@linkplain org.springframework.data.redis.core.StringRedisTemplate
	 *         redis模板类}
	 * @since 2016年9月23日 下午1:37:05
	 */
	@Bean
	public StringRedisTemplate stringRedisTemplate(JedisConnectionFactory jcf) {

		StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();

		stringRedisTemplate.setConnectionFactory(jcf);
		stringRedisTemplate.setKeySerializer(new StringRedisSerializer());
		stringRedisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());

		return stringRedisTemplate;
	}

}