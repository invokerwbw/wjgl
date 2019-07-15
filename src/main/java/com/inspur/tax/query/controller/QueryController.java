package com.inspur.tax.query.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inspur.tax.common.constant.SessionConstant;
import com.inspur.tax.query.service.IQueryService;
import com.inspur.tax.utils.PropertiesLoader;

@Controller
@RequestMapping("/search")
public class QueryController {

	private static final PropertiesLoader CONFIG_PROPERTIES = new PropertiesLoader("config.properties");
	private static final String WELCOME_PAGE = CONFIG_PROPERTIES.getProperty("system.welcomePage");

	@Autowired
	private IQueryService queryService;

	@RequestMapping("/toStdList")
	public String searchList(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		if (session.getAttribute(SessionConstant.SESSION_EN_NAME) == null) {
			return WELCOME_PAGE;
		}
		return "wjgl/queryList";
	}

	@RequestMapping("/groupStd")
	@ResponseBody
	public Map<String, Map<String, String>> groupStd(@RequestBody Map<String, Object> parameter,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Map<String, String>> map = new HashMap<String, Map<String, String>>();
		map.put("groupMap", queryService.groupStd(parameter));
		return map;
	}

	@RequestMapping("/listStd")
	@ResponseBody
	public Map<String, Object> listStd(@RequestBody Map<String, Object> parameter, HttpServletRequest request,
			HttpServletResponse response) {

		return queryService.listStd(parameter);
	}

	@RequestMapping("/listUser")
	@ResponseBody
	public Map<String, Object> listUser() {

		return queryService.listUser();
	}

}