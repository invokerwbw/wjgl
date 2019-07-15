package com.inspur.tax.common.autorun.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * 自执行方法注解
 * <p>
 * 使用{@linkplain com.inspur.tax.common.autorun.annotation.ITaxAutoRun @ITaxAutoRun}注解并实现
 * {@linkplain com.inspur.tax.common.autorun.IAutoRun IAutoRun}
 * 接口的类,在项目启动时会执行其重写的
 * {@linkplain com.inspur.tax.common.autorun.IAutoRun#autorun() autorun()}方法
 *
 * @author wbw
 * @since 2016年9月23日 下午1:47:11
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface ITaxAutoRun {
}