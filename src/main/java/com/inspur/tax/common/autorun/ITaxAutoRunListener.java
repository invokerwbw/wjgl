package com.inspur.tax.common.autorun;

import java.lang.reflect.Method;

import org.loushang.framework.util.SpringContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.inspur.tax.common.autorun.annotation.ITaxAutoRun;

/**
 * 自执行处理
 * <p>
 * 使用{@linkplain com.inspur.tax.common.autorun.annotation.ITaxAutoRun @ITaxAutoRun}注解并实现
 * {@linkplain com.inspur.tax.common.autorun.IAutoRun IAutoRun}
 * 接口的类,在项目启动时会执行其重写的
 * {@linkplain com.inspur.tax.common.autorun.IAutoRun#autorun() autorun()}方法
 *
 * @author wbw
 * @since 2016年9月23日 下午1:41:49
 */
public class ITaxAutoRunListener implements ApplicationListener<ContextRefreshedEvent> {

	private static final Logger logger = LoggerFactory.getLogger(ITaxAutoRunListener.class);

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (event.getApplicationContext().getDisplayName().equals("Root WebApplicationContext")) {
			logger.info("------iTax Auto Run started------");
			// 获取上下文
			ApplicationContext context = event.getApplicationContext();
			// 获取所有beanNames
			String[] beanNames = context.getBeanNamesForType(Object.class);
			for (String beanName : beanNames) {
				ITaxAutoRun autoRun = context.findAnnotationOnBean(beanName, ITaxAutoRun.class);
				// 判断该类是否含有AutoRun注解
				IAutoRun ar = null;
				if (autoRun != null) {
					Method[] methods = context.getBean(beanName).getClass().getMethods();
					for (Method method : methods) {
						// 判断该方法是否为autorun方法
						if ("autorun".equals(method.getName())) {
							ar = SpringContextHolder.getBean(beanName);
							if (ar != null) {
								if (ar.autorun()) {
									logger.info(beanName + " autorun method is success");
								} else {
									logger.warn(beanName + " autorun method is failure");
								}
							} else {
								logger.warn("get bean " + beanName + " is null");
							}
						}
					}
				}
			}
			logger.info("------iTax Auto Run finished------");
		}
	}
}
