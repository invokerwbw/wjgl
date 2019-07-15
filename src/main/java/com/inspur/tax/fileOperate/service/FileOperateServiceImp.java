package com.inspur.tax.fileOperate.service;

import static com.inspur.tax.utils.Collections.*;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.inspur.tax.fileOperate.dao.FileOperateMapper;

@Service("fileOperateService")
public class FileOperateServiceImp implements IFileOperateService {
	@Resource
	private FileOperateMapper fileOperateMapper;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void downLoadStd(HttpServletRequest request, HttpServletResponse response) throws IOException {

	}

	@Override
	public Map<String, Object> stdInfo(String bzh) {
		if ("".equals(bzh) || bzh == null) {
			return null;
		}
		Map<String, Object> map = fileOperateMapper.stdInfo(bzh);
		// 本地数据库未收录代替、被代替标准
		if (map == null) {
			// Map<String, Object> resMap = new HashMap<String, Object>();
			// resMap.put("message", "未收录此标准，请与管理员联系");
			// resMap.put("flag", false);
			// return resMap;
			return null;
		}
		String dtbz = (String) map.get("dtbz");
		String bdtbz = (String) map.get("bdtbz");

		List<Map<String, String>> dtbzList = new ArrayList<Map<String, String>>();
		List<Map<String, String>> bdtbzList = new ArrayList<Map<String, String>>();

		// 多个代替标准以英文分号分割
		if (dtbz != null && !("".equals(dtbz))) {
			String dtbzArr[] = dtbz.split(";");
			for (String a100 : dtbzArr) {
				Map<String, String> dtbzMap = new HashMap<String, String>();
				String str = fileOperateMapper.getA298ByA100(a100);
				if (str != null && !("".equals(str))) {
					dtbzMap.put("a298", str);
					dtbzMap.put("a100", a100);
					dtbzList.add(dtbzMap);
				} else {
					dtbzMap.put("a100", a100);
					dtbzList.add(dtbzMap);
				}
			}
		}
		// 多个被代替标准以英文分好分割
		if (bdtbz != null && !("".equals(bdtbz))) {
			String bdtbzArr[] = bdtbz.split(";");
			for (String a100 : bdtbzArr) {
				Map<String, String> bdtbzMap = new HashMap<String, String>();
				String str = fileOperateMapper.getA298ByA100(a100);
				if (str != null && !("".equals(str))) {
					bdtbzMap.put("a298", str);
					bdtbzMap.put("a100", a100);
					bdtbzList.add(bdtbzMap);
				} else {
					bdtbzMap.put("a100", a100);
					bdtbzList.add(bdtbzMap);
				}
			}
		}
		if (map.get("qcdw") == null) {
			map.put("qcdw", null);
		} else {
			map.put("qcdw", ((String) map.get("qcdw")).replaceAll(";", "<br/>"));
		}
		map.put("dtbzList", dtbzList);
		map.put("bdtbzList", bdtbzList);
		return map;
	}

	@Override
	public void readPdf(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String bzh = request.getParameter("bzh");
		Map<String, String> resMap = fileOperateMapper.getFilePathByBzh(bzh);
		String filePath = resMap.get("filepath");
		String filePageNum = resMap.get("ys");

		if (filePath == null || "".equals(filePath) || filePageNum == null || "0".equals(filePageNum)) {
			String errorMessage = "未收录此标准";
			OutputStream outputStream = response.getOutputStream();
			outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
			outputStream.close();
			return;
		}
		/* 根据a1000 拆得标准文件名,然后拼出文件路径 */
		String pdfName = filePath.substring(filePath.lastIndexOf("\\") + 1, filePath.length());

		ClassLoader cl = FileOperateServiceImp.class.getClassLoader();
		InputStream is = cl.getResourceAsStream("/config.properties");

		Properties props = new Properties();
		try {
			props.load(is);
			String storeurl = props.getProperty("standard.storeurl");
			filePath = storeurl + "\\" + pdfName;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File file = null;
		file = new File(filePath);

		if (!file.exists()) {
			String errorMessage = "标准文件丢失";
			System.out.println(errorMessage);
			OutputStream outputStream = response.getOutputStream();
			outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
			outputStream.close();
			return;
		}

		String mimeType = URLConnection.guessContentTypeFromName(file.getName());
		if (mimeType == null) {
			mimeType = "application/octet-stream";
		}

		response.setContentType(mimeType);
		response.setHeader("Content-Disposition", "inline");
		response.setContentLength((int) file.length());

		InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

		FileCopyUtils.copy(inputStream, response.getOutputStream());
	}

	public ResponseEntity<byte[]> download(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		String bzh = request.getParameter("bzh");
		Map<String, String> resMap = fileOperateMapper.getFilePathByBzh(bzh);
		String filePath = resMap.get("filepath");
		String filePageNum = resMap.get("ys");

		if (filePath == null || "".equals(filePath) || filePageNum == null || "0".equals(filePageNum)) {
			String errorMessage = "未收录此标准";
			OutputStream outputStream = response.getOutputStream();
			outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
			outputStream.close();
			return null;
		}

		/* 根据a1000 拆得标准文件名,然后拼出文件路径 */
		String pdfName = filePath.substring(filePath.lastIndexOf("\\") + 1, filePath.length());

		ClassLoader cl = FileOperateServiceImp.class.getClassLoader();
		InputStream is = cl.getResourceAsStream("/config.properties");

		Properties props = new Properties();
		try {
			props.load(is);
			String storeurl = props.getProperty("standard.storeurl");
			filePath = storeurl + "\\" + pdfName;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		File file = new File(filePath);

		if (!file.exists()) {
			String errorMessage = "标准文本丢失";
			System.out.println(errorMessage);
			OutputStream outputStream = response.getOutputStream();
			outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
			outputStream.close();
			return null;
		}

		InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

		byte[] fileByte = null;

		HttpHeaders headers = new HttpHeaders();

		try {

			// is = request.getSession().getServletContext().getResourceAsStream("D:\\DB37_T
			// 3040-2017.pdf");

			if (inputStream != null) {
				fileByte = IOUtils.toByteArray(inputStream);
				// application/octet-stream 二进制流数据（最常见的文件下载）
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				// 通知浏览器以attachment（下载方式）
				headers.setContentDispositionFormData("attachment", bzh + ".pdf");
			}
		} catch (MalformedURLException e) {
			fileByte = null;
			logger.error(e.getMessage());
		} catch (UnsupportedEncodingException e) {
			fileByte = null;
			logger.error(e.getMessage());
		} catch (IOException e) {
			fileByte = null;
			logger.error(e.getMessage());
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
		}

		if (fileByte != null) {
			return new ResponseEntity<byte[]>(fileByte, headers, HttpStatus.OK);
		} else {
			return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	public Map<String, Object> checkPDFIsExist(String bzh) {
		Map<String, Object> result = newHashMap();
		boolean flag = true;
		String message = "";

		Map<String, String> resMap = fileOperateMapper.getFilePathByBzh(bzh);
		String filePath = resMap.get("filepath");
		String filePageNum = resMap.get("ys");

		if (filePath == null || "".equals(filePath) || filePageNum == null || "0".equals(filePageNum)) {
			flag = false;
			message = "未收录此标准";
		} else {
			/* 根据a1000 拆得标准文件名,然后拼出文件路径 */
			String pdfName = filePath.substring(filePath.lastIndexOf("\\") + 1, filePath.length());

			ClassLoader cl = FileOperateServiceImp.class.getClassLoader();
			InputStream is = cl.getResourceAsStream("/config.properties");

			Properties props = new Properties();
			try {
				props.load(is);
				String storeurl = props.getProperty("standard.storeurl");
				filePath = storeurl + "\\" + pdfName;

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			File file = new File(filePath);
			if (!file.exists()) {
				flag = false;
				message = "标准文本丢失";
			}
		}

		result.put("flag", flag);
		result.put("message", message);

		return result;
	}

	@Override
	public Map<String, Object> checkBzhIsExist(String bzh) {

		Map<String, Object> result = newHashMap();
		boolean flag = true;
		String message = "";

		Map<String, String> resMap = fileOperateMapper.checkBzhIsExist(bzh);

		if (resMap == null) {
			flag = false;
			message = "未收录此条标准，请与管理员联系";
		}

		result.put("flag", flag);
		result.put("message", message);

		return result;

	}
}