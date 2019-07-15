package com.inspur.tax.login.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inspur.tax.common.constant.SessionConstant;
import com.inspur.tax.common.data.Employee;
import com.inspur.tax.login.service.ILoginService;
import com.inspur.tax.utils.PropertiesLoader;
import com.inspur.tax.utils.ViewUtil;


@Controller
@RequestMapping("/sysLogin")
public class LoginController {
	
	private static final PropertiesLoader CONFIG_PROPERTIES = new PropertiesLoader("config.properties");
	private static final String WELCOME_PAGE = CONFIG_PROPERTIES.getProperty("system.welcomePage");
	private static final String PORTAL_PAGE = CONFIG_PROPERTIES.getProperty("system.portalPage");
	
	@Autowired
	private ILoginService loginService;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping("/login")
	public String login( HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, String> map = (HashMap<String, String>) ViewUtil.convertParameterMAP(request.getParameterMap());
		
		String username = map.get("j_username");
		String password = map.get("j_password");
		Employee employee = loginService.isExistUser(username);
		
		HttpSession session = request.getSession(false);
		
		if(session==null) {
			return WELCOME_PAGE;
		}
		
		if(session.getAttribute(SessionConstant.SESSION_EN_NAME) != null){
			return PORTAL_PAGE;
		}
		
		if(employee == null){
			logger.info("errorMessage:当前登录账户不存在");
			request.setAttribute("errorMessage", "userError");
			return WELCOME_PAGE;
		}
		if (employee != null){
			if(employee.getName_En() == null) {
			logger.info("errorMessage:当前登录账户不存在");
			request.setAttribute("errorMessage", "userError");
			return WELCOME_PAGE;
			}else if(!password.equals(employee.getPassword())){
			logger.info("errorMessage:密码错误");
			request.setAttribute("errorMessage", "pwdError");
			return WELCOME_PAGE;
			}
		}
		session.setAttribute(SessionConstant.SESSION_CH_NAME, employee.getName_Ch());
		session.setAttribute(SessionConstant.SESSION_EN_NAME, employee.getName_En());
		
		return PORTAL_PAGE;
	}
		
		
		/**
		 * 
		 * 菜单页
		 *
		 * @return
		 * @since 2017年4月24日 上午11:17:41
		 */
		@RequestMapping("/portal")
		public String portal() {
			return PORTAL_PAGE;
		}
		
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
		
		
		@RequestMapping("/logout")
		public String logout(HttpServletRequest request, HttpServletResponse response) {
			HttpSession session = request.getSession();
			session.removeAttribute(SessionConstant.SESSION_CH_NAME);
			session.removeAttribute(SessionConstant.SESSION_EN_NAME);
			return WELCOME_PAGE;
		}
		
}