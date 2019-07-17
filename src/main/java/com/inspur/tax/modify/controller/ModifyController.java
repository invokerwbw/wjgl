package com.inspur.tax.modify.controller;

import static com.inspur.tax.nsrgxyt.common.constant.ControllerConstant.*;
import static com.inspur.tax.utils.Collections.newHashMap;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inspur.tax.modify.service.IModifyService;
import com.inspur.tax.modify.util.Plupload;
import com.inspur.tax.modify.util.PluploadUtil;
import com.inspur.tax.utils.PropertiesLoader;

@Controller
@RequestMapping("/modify")
public class ModifyController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ModifyController.class);

	private static final PropertiesLoader CONFIG_PROPERTIES = new PropertiesLoader("config.properties");
	private static final String STORE_URL = CONFIG_PROPERTIES.getProperty("standard.storeurl");

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

	@RequestMapping("/toAddStdPDF")
	public String toAddStdPDF(@RequestParam String bzh, Model model) {
		model.addAttribute("bzh", bzh);
		return "modify/pdfUpload";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public void upload(@RequestParam String bzh, Plupload plupload, HttpServletRequest request,
			HttpServletResponse response) {

		plupload.setRequest(request);
		// 文件存储路径
		File dir = new File(STORE_URL);

		try {
			// 上传文件
			PluploadUtil.upload(plupload, dir);
			// 保存路径
			Map<String, Object> std = newHashMap();
			std.put("bzh", bzh);
			std.put("filePath", STORE_URL + "\\" + plupload.getFileName());
			modifyService.updateStdFilePath(std);
		} catch (IllegalStateException e) {
			LOGGER.error(e.getMessage());
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}

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