package com.inspur.tax.common.config;

import org.loushang.framework.util.SpringContextHolder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * Spring Cache 整合Redis
 *
 * @author wbw
 * @since 2016年9月23日 下午1:33:53
 */
@Configuration
@EnableCaching
@Profile("redisCache")
public class RedisCacheConfig {

	/**
	 * 缓存数据在redis中的有效时间
	 */
	@Value("${system.cache.redis.timeout}")
	private long defaultExpiration;

	@Bean
	public CacheManager redisCacheManager() {

		StringRedisTemplate stringRedisTemplate = SpringContextHolder.getBean("stringRedisTemplate");
		RedisCacheManager cacheManager = new RedisCacheManager(stringRedisTemplate);

		cacheManager.setDefaultExpiration(defaultExpiration);

		return cacheManager;
	}

}