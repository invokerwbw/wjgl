package com.inspur.tax.fileOperate.service;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

public interface IFileOperateService {

	public void downLoadStd(HttpServletRequest request, HttpServletResponse response) throws IOException;

	public Map<String, Object> stdInfo(String bzh);

	public void readPdf(HttpServletRequest request, HttpServletResponse response) throws IOException;

	public ResponseEntity<byte[]> download(HttpServletRequest request, HttpServletResponse response) throws IOException;

	public Map<String, Object> checkPDFIsExist(String bzh);
	
	public Map<String, Object> checkBzhIsExist(String bzh);
	
}
