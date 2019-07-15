package com.inspur.tax.common.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.DefaultResourceLoader;

/**
 * Spring Cache 整合EhCache
 *
 * @author wbw
 * @since 2016年9月23日 下午1:33:20
 */
@Configuration
@EnableCaching
@Profile("ehcache")
public class EhcacheConfig {

	@Bean
	public EhCacheManagerFactoryBean ehcache() {

		EhCacheManagerFactoryBean ehcache = new EhCacheManagerFactoryBean();

		ehcache.setConfigLocation(new DefaultResourceLoader().getResource("classpath:ehcache.xml"));

		return ehcache;
	}

	@Bean
	public CacheManager ehcacheManager(EhCacheManagerFactoryBean ehcache) {
		return new EhCacheCacheManager(ehcache.getObject());
	}

}