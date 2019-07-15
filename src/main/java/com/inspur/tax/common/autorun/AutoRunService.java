package com.inspur.tax.common.autorun;

import com.inspur.tax.common.autorun.annotation.ITaxAutoRun;

/**
 * 自执行方法示例
 *
 * @author wbw
 * @since 2016年9月23日 下午1:40:23
 */
@ITaxAutoRun
public class AutoRunService implements IAutoRun {

	@Override
	public boolean autorun() {
		System.out.println("Don't try so hard, the best things come when you least expect them to");
		return true;
	}

}