package com.inspur.tax.modify.controller;

import static com.inspur.tax.nsrgxyt.common.constant.ControllerConstant.*;
import static com.inspur.tax.utils.Collections.newHashMap;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inspur.tax.modify.service.IModifyService;

@Controller
@RequestMapping("/modify")
public class ModifyController {

	@Autowired
	private IModifyService modifyService;

	@RequestMapping("/list")
	public String searchList(HttpServletRequest request, HttpServletResponse response) {
		return "modify/modifyList";
	}

	@RequestMapping("/listData")
	@ResponseBody
	public Map<String, Object> listData(@RequestBody Map<String, Object> parameter, HttpServletRequest request,
			HttpServletResponse response) {
		return modifyService.listData(parameter);
	}

	@RequestMapping("/toAddStd")
	public String toAddStd(Model model) {
		Map<String, Object> std = newHashMap();

		model.addAttribute("std", std);
		model.addAttribute(FLAG, ADD);

		return "modify/std";
	}

	@RequestMapping("/toUpdateStd")
	public String toUpdateStd(@RequestParam String bzh, Model model) {
		Map<String, Object> std = modifyService.getStd(bzh);

		model.addAttribute("std", std);
		model.addAttribute(FLAG, UPDATE);

		return "modify/std";
	}

	@RequestMapping(value = "/deleteStd")
	@ResponseBody
	public boolean deleteStd(@RequestParam String bzh) {
		return modifyService.deleteStd(bzh);
	}

	@RequestMapping(value = "/updateStd")
	@ResponseBody
	public boolean updateStd(@RequestBody Map<String, Object> std) {
		return modifyService.updateStd(std);
	}

	@RequestMapping(value = "/insertStd")
	@ResponseBody
	public boolean insertStd(@RequestBody Map<String, Object> std) {
		return modifyService.insertStd(std);
	}

}