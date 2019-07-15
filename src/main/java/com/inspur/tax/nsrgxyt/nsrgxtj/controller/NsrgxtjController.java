package com.inspur.tax.nsrgxyt.nsrgxtj.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inspur.tax.nsrgxyt.nsrgxtj.service.NsrgxtjService;

/**
 * 
 * 纳税人关系统计
 *
 * @author wbw
 * @since 2017年10月30日 上午10:25:12
 */
@Controller
@RequestMapping("/nsrgxtj")
public class NsrgxtjController {

	@Resource
	private NsrgxtjService nsrgxtjService;

	/**
	 * 
	 * 纳税人关系查询页面
	 *
	 * @return
	 * @since 2017年11月2日 上午9:18:45
	 */
	@RequestMapping("/toNsrgxcx")
	public String toNsrgxcx() {
		return "nsrgxyt/nsrgxtj/gxList";
	}

	/**
	 * 
	 * 纳税人关系统计页面
	 *
	 * @return
	 * @since 2017年11月2日 上午9:19:11
	 */
	@RequestMapping("/toNsrgxtj")
	public String toNsrgxtj() {
		return "nsrgxyt/nsrgxtj/gxtjList";
	}

	/**
	 * 
	 * 关系超链接
	 *
	 * @return
	 * @since 2017年11月2日 下午2:18:54
	 */
	@RequestMapping("/toGxLink")
	public String toGxLink() {
		return "nsrgxyt/nsrgxtj/gxLink";
	}

	/**
	 * 
	 * 获取汇总表中纳税人关系
	 *
	 * @param parameter
	 * @return
	 * @since 2017年11月1日 下午7:43:49
	 */
	@RequestMapping("/listGx")
	@ResponseBody
	public Map<String, Object> listGx(@RequestBody Map<String, Object> parameter) {
		return nsrgxtjService.listGx(parameter);
	}

	/**
	 * 
	 * 获取关系统计列表信息
	 *
	 * @param parameter
	 * @return
	 * @since 2017年11月2日 上午9:28:07
	 */
	@RequestMapping("/listGxtj")
	@ResponseBody
	public Map<String, Object> listGxtj(@RequestBody Map<String, Object> parameter) {
		return nsrgxtjService.listGxtj(parameter);
	}

}