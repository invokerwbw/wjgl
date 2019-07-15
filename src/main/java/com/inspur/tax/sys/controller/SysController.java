package com.inspur.tax.sys.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.inspur.tax.query.service.IQueryService;
import com.inspur.tax.sys.service.ISysService;
import com.inspur.tax.utils.PropertiesLoader;

/**
 * 
 * 系统页面跳转
 *
 * @author wbw
 * @since 2017年4月24日 上午11:17:34
 */
@Controller
@RequestMapping("/sys")
public class SysController {

	private static final PropertiesLoader CONFIG_PROPERTIES = new PropertiesLoader("config.properties");
	private static final String PORTAL_PAGE = CONFIG_PROPERTIES.getProperty("system.portalPage");
	private static final String WELCOME_PAGE = CONFIG_PROPERTIES.getProperty("system.welcomePage");
	private static final String USERLIST_PAGE = CONFIG_PROPERTIES.getProperty("system.userListPage");
	private static final String USERINFO_PAGE = CONFIG_PROPERTIES.getProperty("system.userInfoPage");

	@Autowired
	private ISysService sysService;

	@Autowired
	private IQueryService queryService;

	/**
	 * 
	 * 欢迎页
	 *
	 * @return
	 * @since 2017年4月24日 上午11:17:38
	 */
	@RequestMapping("/welcome")
	public String welcome() {
		return WELCOME_PAGE;
	}

	/**
	 * 用户列表页
	 * 
	 * @since 2017年4月24日 上午11:17:38
	 */
	@RequestMapping("/userListPage")
	public String userPage() {
		return USERLIST_PAGE;
	}

	/**
	 * 添加,编辑用户信息页
	 * 
	 * @since 2017年4月24日 上午11:17:38
	 */
	@RequestMapping("/userInfoPage")
	public ModelAndView userInfo(HttpServletRequest request) {

		String account = request.getParameter("account");
		Map<String, String> model = new HashMap<String, String>();

		// 编辑
		if (account != null) {
			Map<String, String> userMap = queryService.queryUserInfo(account);
			model.put("account", userMap.get("account"));
			model.put("name", userMap.get("name"));
			model.put("password", userMap.get("password"));
			model.put("department", userMap.get("department"));
			model.put("mark", userMap.get("mark"));
		}
		request.setAttribute("model", model);
		return new ModelAndView(USERINFO_PAGE, model);
	}

	/**
	 * 菜单页
	 * 
	 * @since 2017年4月24日 上午11:17:41
	 */
	@RequestMapping("/portal")
	public String portal() {
		return PORTAL_PAGE;
	}

	/**
	 * 添加用户
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/addUser")
	@ResponseBody
	public Map<String, String> addUser(HttpServletRequest req) throws Exception {
		return sysService.addUser(req);
	}

	@RequestMapping("/deleteUser")
	@ResponseBody
	public Map<String, String> deleteUser(HttpServletRequest req) throws Exception {
		return sysService.deleteUser(req);
	}

}