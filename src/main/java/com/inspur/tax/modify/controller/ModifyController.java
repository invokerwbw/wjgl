package com.inspur.tax.modify.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

}