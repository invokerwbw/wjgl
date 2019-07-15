package com.inspur.tax.nsrgxyt.nsrgxgl.controller;

import static com.inspur.tax.nsrgxyt.nsrgxgl.constant.NsrgxglConstant.*;
import static com.inspur.tax.utils.Collections.newHashMap;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.inspur.tax.nsrgxyt.common.constant.ControllerConstant.*;
import com.inspur.tax.nsrgxyt.nsrgxgl.service.NsrgxglService;

/**
 * 
 * 纳税人关系管理
 *
 * @author wbw
 * @since 2017年10月30日 上午10:21:45
 */
@Controller
@RequestMapping("/nsrgxgl")
public class NsrgxglController {

	@Resource
	private NsrgxglService nsrgxglService;

	/**
	 * 
	 * 关系分类页面
	 *
	 * @return
	 * @since 2017年10月30日 下午2:20:52
	 */
	@RequestMapping("/toGxfl")
	public String toGxfl() {
		return "nsrgxyt/nsrgxgl/gxflList";
	}

	/**
	 * 
	 * 获取关系分类列表
	 *
	 * @param parameter
	 * @return
	 * @since 2017年10月30日 下午6:51:17
	 */
	@RequestMapping("/listGxfl")
	@ResponseBody
	public Map<String, Object> listGxfl(@RequestBody Map<String, Object> parameter) {
		return nsrgxglService.listGxfl(parameter);
	}

	/**
	 * 
	 * 关系类型页面
	 *
	 * @param gxflDm
	 * @param model
	 * @return
	 * @since 2017年10月30日 下午2:25:39
	 */
	@RequestMapping("/toGxlx/{gxflDm}")
	public String toGxlx(@PathVariable String gxflDm, Model model) {
		model.addAttribute(GXFL_DM, gxflDm);
		return "nsrgxyt/nsrgxgl/gxlxList";
	}

	/**
	 * 
	 * 获取关系类型列表
	 *
	 * @param parameter
	 * @return
	 * @since 2017年10月30日 下午6:52:12
	 */
	@RequestMapping("/listGxlx")
	@ResponseBody
	public Map<String, Object> listGxlx(@RequestBody Map<String, Object> parameter) {
		return nsrgxglService.listGxlx(parameter);
	}

	/**
	 * 
	 * 关系分类添加页面
	 *
	 * @param model
	 * @return
	 * @since 2017年10月30日 下午4:06:46
	 */
	@RequestMapping("/toAddGxfl")
	public String toAddGxfl(Model model) {
		Map<String, String> gxfl = newHashMap();
		gxfl.put(GXFL_DM, nsrgxglService.initGxflDm());

		model.addAttribute(GXFL, gxfl);
		model.addAttribute(FLAG, ADD);

		return "nsrgxyt/nsrgxgl/gxfl";
	}

	/**
	 * 
	 * 关系类型添加页面
	 *
	 * @param gxflDm
	 * @param model
	 * @return
	 * @since 2017年10月30日 下午4:10:03
	 */
	@RequestMapping("/toAddGxlx/{gxflDm}")
	public String toAddGxlx(@PathVariable String gxflDm, Model model) {
		Map<String, String> gxlx = newHashMap();
		gxlx.put(GXFL_DM, gxflDm);
		gxlx.put(GXLX_BM, nsrgxglService.initGxlxBm(gxflDm));

		model.addAttribute(GXLX, gxlx);
		model.addAttribute(FLAG, ADD);

		return "nsrgxyt/nsrgxgl/gxlx";
	}

	/**
	 * 
	 * 关系分类修改页面
	 *
	 * @param gxflDm
	 * @param model
	 * @return
	 * @since 2017年10月30日 下午4:30:15
	 */
	@RequestMapping("/toUpdateGxfl/{gxflDm}")
	public String toUpdateGxfl(@PathVariable String gxflDm, Model model) {
		Map<String, String> gxfl = nsrgxglService.getGxfl(gxflDm);

		model.addAttribute(GXFL, gxfl);
		model.addAttribute(FLAG, UPDATE);

		return "nsrgxyt/nsrgxgl/gxfl";
	}

	/**
	 * 
	 * 关系类型修改页面
	 *
	 * @param gxlxBm
	 * @param model
	 * @return
	 * @since 2017年10月30日 下午4:32:03
	 */
	@RequestMapping("/toUpdateGxlx/{gxlxBm}")
	public String toUpdateGxlx(@PathVariable String gxlxBm, Model model) {
		Map<String, String> gxlx = nsrgxglService.getGxlx(gxlxBm);

		model.addAttribute(GXLX, gxlx);
		model.addAttribute(FLAG, UPDATE);

		return "nsrgxyt/nsrgxgl/gxlx";
	}

	/**
	 * 
	 * 关系类型查看页面
	 *
	 * @param gxlxBm
	 * @param model
	 * @return
	 * @since 2017年10月31日 上午11:39:16
	 */
	@RequestMapping("/toViewGxlx/{gxlxBm}")
	public String toViewGxlx(@PathVariable String gxlxBm, Model model) {
		Map<String, String> gxlx = nsrgxglService.getGxlx(gxlxBm);

		model.addAttribute(GXLX, gxlx);
		model.addAttribute(FLAG, VIEW);

		return "nsrgxyt/nsrgxgl/gxlxDetail";
	}

	/**
	 * 
	 * 删除关系分类
	 *
	 * @param gxflDm
	 * @return
	 * @since 2017年10月30日 下午4:33:41
	 */
	@RequestMapping(value = "/deleteGxfl/{gxflDm}")
	@ResponseBody
	public boolean deleteGxfl(@PathVariable String gxflDm) {
		return nsrgxglService.delGxfl(gxflDm);
	}

	/**
	 * 
	 * 删除关系类型
	 *
	 * @param gxlxBm
	 * @return
	 * @since 2017年10月30日 下午4:35:13
	 */
	@RequestMapping(value = "/deleteGxlx/{gxlxBm}")
	@ResponseBody
	public boolean deleteGxlx(@PathVariable String gxlxBm) {
		return nsrgxglService.delGxlx(gxlxBm);
	}

	/**
	 * 
	 * 添加关系分类
	 *
	 * @param parameter
	 * @return
	 * @since 2017年4月24日 下午4:32:20
	 */
	@RequestMapping(value = "/addGxfl")
	@ResponseBody
	public boolean addGxfl(@RequestBody Map<String, Object> gxfl) {
		return nsrgxglService.addGxfl(gxfl);
	}

	/**
	 * 
	 * 添加关系类型
	 *
	 * @param gxlx
	 * @return
	 * @since 2017年10月31日 上午11:41:02
	 */
	@RequestMapping(value = "/addGxlx")
	@ResponseBody
	public Map<String, Object> addGxlx(@RequestBody Map<String, Object> gxlx) {
		return nsrgxglService.addGxlx(gxlx);
	}

	/**
	 * 
	 * 修改关系分类
	 *
	 * @param gxfl
	 * @return
	 * @since 2017年10月30日 下午5:08:09
	 */
	@RequestMapping(value = "/updateGxfl")
	@ResponseBody
	public boolean updateGxfl(@RequestBody Map<String, Object> gxfl) {
		return nsrgxglService.updateGxfl(gxfl);
	}

	/**
	 * 
	 * 修改关系类型
	 *
	 * @param gxlx
	 * @return
	 * @since 2017年10月31日 上午11:40:52
	 */
	@RequestMapping(value = "/updateGxlx")
	@ResponseBody
	public Map<String, Object> updateGxlx(@RequestBody Map<String, Object> gxlx) {
		return nsrgxglService.updateGxlx(gxlx);
	}

	/**
	 * 
	 * 启用指定关系分类
	 *
	 * @param gxflDm
	 * @return
	 * @since 2017年10月30日 下午5:09:42
	 */
	@RequestMapping(value = "/enabledGxfl/{gxflDm}")
	@ResponseBody
	public boolean enabledGxfl(@PathVariable String gxflDm) {
		return nsrgxglService.enabledGxfl(gxflDm);
	}

	/**
	 * 
	 * 停用指定关系分类
	 *
	 * @param gxflDm
	 * @return
	 * @since 2017年10月30日 下午5:11:30
	 */
	@RequestMapping(value = "/disabledGxfl/{gxflDm}")
	@ResponseBody
	public boolean disabledGxfl(@PathVariable String gxflDm) {
		return nsrgxglService.disabledGxfl(gxflDm);
	}

	/**
	 * 
	 * 启用指定关系类型
	 *
	 * @param gxlxBm
	 * @return
	 * @since 2017年10月30日 下午5:12:16
	 */
	@RequestMapping(value = "/enabledGxlx/{gxlxBm}")
	@ResponseBody
	public boolean enabledGxlx(@PathVariable String gxlxBm) {
		return nsrgxglService.enabledGxlx(gxlxBm);
	}

	/**
	 * 
	 * 停用指定关系类型
	 *
	 * @param gxlxBm
	 * @return
	 * @since 2017年10月30日 下午5:10:38
	 */
	@RequestMapping(value = "/disabledGxlx/{gxlxBm}")
	@ResponseBody
	public boolean disabledGxlx(@PathVariable String gxlxBm) {
		return nsrgxglService.disabledGxlx(gxlxBm);
	}

	/**
	 * 
	 * 获取字段信息
	 *
	 * @param parameter
	 * @return
	 * @since 2017年10月31日 上午11:54:03
	 */
	@RequestMapping("/listZd")
	@ResponseBody
	public Map<String, Object> listZd(@RequestBody Map<String, Object> parameter) {
		return nsrgxglService.listZd(parameter);
	}

}