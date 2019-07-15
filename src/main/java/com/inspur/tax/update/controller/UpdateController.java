package com.inspur.tax.update.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.inspur.tax.update.service.IUpdateService;

@Controller
@RequestMapping("/update")
public class UpdateController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private IUpdateService updateService;

	@RequestMapping("/bzgx")
	public String bzgx() {
		return "wjgl/bzgx";
	}

	@RequestMapping("/updatePage")
	public String indexPage(HttpServletRequest request, HttpServletResponse response) {
		return "wjgl/updatePage";
	}

	@RequestMapping("/opreatePage")
	public String opreatePage(HttpServletRequest request, HttpServletResponse response) {
		return "wjgl/operatePage";
	}

	@RequestMapping("/updateOrg")
	@ResponseBody
	public Map<String, String> updateOrg(HttpServletRequest request, HttpServletResponse response) {

		Map<String, String> map = new HashMap<String, String>();

		updateService.exeOrgUpdate(request);

		map.put("flag", "success");
		return map;
	}

	@RequestMapping(value = "/doUpload", method = RequestMethod.POST)
	public void doUploadFile(@RequestParam(value = "uploadify", required = false) MultipartFile file,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");// 设置日期格式

		String filePath = "d:\\temp\\file\\";
		String fileName = file.getOriginalFilename();

		// 本机测试路径
		try {
			FileUtils.copyInputStreamToFile(file.getInputStream(),
					new File("d:\\temp\\file\\", df.format(new Date()) + fileName));
			dataMap.put("filePath", filePath);
			dataMap.put("fileName", df.format(new Date()) + fileName);
			logger.info("文件上传成功");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		ObjectMapper mapper = new ObjectMapper();
		String data = mapper.writeValueAsString(dataMap);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(data);
	}

	@RequestMapping("/importSql")
	@ResponseBody
	public Map<String, Object> importSql(@RequestParam(value = "importFile") MultipartFile... files) {
		return updateService.importSql(files);
	}

}