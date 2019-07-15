package com.inspur.tax.fileOperate.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.inspur.tax.fileOperate.service.IFileOperateService;

@Controller
@RequestMapping("/fileOperate")
public class fileOperateController {

	@Resource
	public IFileOperateService fileOperateService;

	@RequestMapping(value = "/readPdf", method = RequestMethod.GET)
	public void readPdf(HttpServletRequest request, HttpServletResponse response) throws IOException {
		fileOperateService.readPdf(request, response);
	}

	@RequestMapping(value = "/stdInfo", method = RequestMethod.GET)
	public ModelAndView stdInfo(HttpServletRequest request, HttpServletResponse response) {
		String bzh = "";
		try {
			bzh = new String(request.getParameter("bzh").trim().getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, Object> map = fileOperateService.stdInfo(bzh);

		request.setAttribute("map", map);
		return new ModelAndView("wjgl/stdInfo", map);
	}

	@RequestMapping("/download")
	public ResponseEntity<byte[]> download(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		return fileOperateService.download(request, response);
	}
	
	@RequestMapping("/checkPDFIsExist")
	@ResponseBody
	public Map<String, Object> checkPDFIsExist(HttpServletRequest request) {
		String bzh = request.getParameter("bzh");
		Map<String, Object> result = fileOperateService.checkPDFIsExist(bzh);
		return result;
	}
	
	@RequestMapping("/checkBzhIsExist")
	@ResponseBody
	public Map<String, Object> checkBzhIsExist(HttpServletRequest request) {
		String bzh = request.getParameter("bzh");
		Map<String, Object> result = fileOperateService.checkBzhIsExist(bzh);
		return result;
	}
}