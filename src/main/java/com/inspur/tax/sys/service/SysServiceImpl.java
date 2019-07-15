package com.inspur.tax.sys.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inspur.tax.sys.dao.SysMapper;
import com.inspur.tax.utils.TranUtil;


@Service("sysService")
public class SysServiceImpl implements ISysService{

	@Autowired
	private SysMapper sysMapper;
	
	@Override
	public Map<String, String> addUser(HttpServletRequest req){

		Map<String, String> userMap = new HashMap<String, String>();
		Map<String, String> resMap = new HashMap<String, String>();
		String flag = req.getParameter("editFlag");
		userMap.put("account", req.getParameter("account"));
		userMap.put("password", req.getParameter("password"));
		userMap.put("xingming", req.getParameter("xingming"));
		userMap.put("bumen", req.getParameter("bumen"));
		userMap.put("beizhu", req.getParameter("beizhu"));
		
		TranUtil lt = new TranUtil().begin();
		try {
			//修改用户
			if(flag != null && !("".equals(flag))){
				sysMapper.updateUser(userMap);
				lt.commit();
			}else{//新增用户
				sysMapper.addUser(userMap);
				lt.commit();
			}
			resMap.put("saveFlag", "success");
		} catch (Exception e) {
			lt.rollback();
			resMap.put("saveFlag", "error");
			e.printStackTrace();
		}
		return resMap;
	}
	
	@Override
	public Map<String, String> deleteUser(HttpServletRequest req) throws Exception{

		Map<String, String> resMap = new HashMap<String, String>();
		TranUtil lt = new TranUtil().begin();
		try {
				sysMapper.deleteUser(req.getParameter("account"));
				lt.commit();
			resMap.put("deleteFlag", "success");
		} catch (Exception e) {
			lt.rollback();
			resMap.put("deleteFlag", "error");
			e.printStackTrace();
			throw e;
		}
		return resMap;
	}
}
