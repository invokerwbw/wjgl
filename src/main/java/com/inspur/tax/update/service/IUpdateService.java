package com.inspur.tax.update.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public interface IUpdateService {

	public Map<String, String> exeOrgUpdate(HttpServletRequest request);

	/**
	 * 
	 * sql文件上传
	 *
	 * @param files
	 * @return
	 * @since 2018年3月20日 下午8:19:08
	 */
	Map<String, Object> importSql(MultipartFile... files);
}
