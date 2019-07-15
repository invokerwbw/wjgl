package com.inspur.tax.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inspur.tax.common.data.Employee;
import com.inspur.tax.login.dao.LoginMapper;

@Service("loginService")
public class LoginServiceImpl implements ILoginService {

	@Autowired
	private LoginMapper loginMapper;

	@Override
	public Employee isExistUser(String username) {
		return loginMapper.getUserInfo(username);
	}

}
