package com.inspur.tax.common.redis;

import java.util.concurrent.TimeUnit;

import org.loushang.framework.util.SpringContextHolder;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.inspur.tax.utils.PropertiesLoader;
import com.inspur.tax.utils.json.JsonUtil;

/**
 * 
 * @ClassName: RedisBaseDao
 * @Description: 提供操作redis的方法
 * @author wbw
 * @date 2016年8月4日 上午11:07:38
 *
 */
public abstract class RedisBaseDao {

	private static PropertiesLoader configProperties = new PropertiesLoader("config.properties");

	private static boolean isUseRedis = configProperties.getBoolean("system.redis.isUseRedis");

	private static StringRedisTemplate stringRedisTemplate = null;

	static {
		if (isUseRedis) {
			stringRedisTemplate = SpringContextHolder.getBean("stringRedisTemplate");
		}
	}

	/**
	 * 向redis中插入永不过期的数据
	 * 
	 * @param key
	 *            键
	 * @param object
	 *            要存入的对象
	 * @return 是否成功
	 * @since 2016年10月25日 上午11:51:22
	 */
	protected boolean insert(String key, Object object) {
		if (isUseRedis) {
			stringRedisTemplate.opsForValue().set(key, JsonUtil.objectToJsonStr(object));
		}
		return isUseRedis;
	}

	/**
	 * 向redis中插入含过期时间的数据(单位:秒)
	 * 
	 * @param key
	 *            键
	 * @param object
	 *            要存入的对象
	 * @param timeout
	 *            超时时间
	 * @return 是否成功
	 * @since 2016年10月25日 上午11:51:33
	 */
	protected boolean insert(String key, Object object, Long timeout) {
		if (isUseRedis) {
			stringRedisTemplate.opsForValue().set(key, JsonUtil.objectToJsonStr(object), timeout, TimeUnit.SECONDS);
		}
		return isUseRedis;
	}

	/**
	 * 向redis中插入含过期时间的数据,需指定时间单位
	 * 
	 * @param key
	 *            键
	 * @param object
	 *            要存入的对象
	 * @param timeout
	 *            超时时间
	 * @param unit
	 *            时间单位
	 * @return 是否成功
	 * @since 2016年10月25日 上午11:51:42
	 */
	protected boolean insert(String key, Object object, Long timeout, TimeUnit unit) {
		if (isUseRedis) {
			stringRedisTemplate.opsForValue().set(key, JsonUtil.objectToJsonStr(object), timeout, unit);
		}
		return isUseRedis;
	}

	/**
	 * 删除redis中指定的数据
	 * 
	 * @param key
	 *            键
	 * @return 是否成功
	 * @since 2016年10月25日 上午11:51:52
	 */
	protected boolean delete(String key) {
		if (isUseRedis) {
			stringRedisTemplate.delete(key);
		}
		return isUseRedis;
	}

	/**
	 * 获取redis中指定的数据，并以指定类型返回
	 * 
	 * @param <T>
	 *            Type Java类
	 * 
	 * @param key
	 *            键
	 * @param valueType
	 *            redis中对象的类型
	 * @return 在redis中对象
	 * @since 2016年10月25日 上午11:52:01
	 */
	protected <T> T select(String key, Class<T> valueType) {
		if (isUseRedis) {
			String value = stringRedisTemplate.opsForValue().get(key);
			return value == null ? null : JsonUtil.jsonStrToObject(value, valueType);
		} else {
			return null;
		}
	}

	/**
	 * 是否使用redis
	 * 
	 * @return 是否使用redis
	 * @since 2016年10月25日 上午11:52:09
	 */
	public boolean isUseRedis() {
		return isUseRedis;
	}

}