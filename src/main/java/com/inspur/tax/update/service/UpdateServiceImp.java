package com.inspur.tax.update.service;

import static com.inspur.tax.utils.Collections.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.filter.config.ConfigTools;
import com.inspur.tax.utils.PropertiesLoader;

@Service("updateService")
public class UpdateServiceImp implements IUpdateService {

	private Logger logger = LoggerFactory.getLogger(UpdateServiceImp.class);

	public Map<String, String> exeOrgUpdate(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		String filePath = request.getParameter("filePath");
		String fileName = request.getParameter("fileName");

		File file = new File(filePath + fileName);
		if (!file.exists()) {
			map.put("msg", "更新文件不存在，请重新上传后重试");
			map.put("flag", "100");
			return map;
		}

		ClassLoader cl = UpdateServiceImp.class.getClassLoader();
		InputStream is = cl.getResourceAsStream("/config.properties");

		Properties props = new Properties();
		try {
			props.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			String password = ConfigTools.decrypt("password");
			System.out.println("password===>" + password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// String cmdArray[] = {"D:/Java/mysql/bin/mysql.exe -uusername -ppassword","use
		// dbname","source C:/Users/yf/Desktop/update/procedure20160516.sql"};
		return null;
	}

	@Override
	public Map<String, Object> importSql(MultipartFile... files) {

		Map<String, Object> result = newHashMap();

		boolean flag = true;
		StringBuffer message = new StringBuffer();

		if (files != null && files.length > 0) {
			MultipartFile file = files[0];
			if (file != null) {
				String originalFilename = file.getOriginalFilename();
				String wjlx = originalFilename
						.substring(originalFilename.lastIndexOf('.') == -1 ? originalFilename.length()
								: originalFilename.lastIndexOf('.'), originalFilename.length());
				if (".sql".equalsIgnoreCase(wjlx)) {

					String linepara = ";";
					BufferedReader is = null;

					Connection conn = null;
					Statement stmt = null;

					PropertiesLoader p = new PropertiesLoader("datasource.properties");
					String forname = p.getProperty("dataSource.mysql.driver");
					String url = p.getProperty("dataSource.mysql.url");
					String username = p.getProperty("dataSource.mysql.username");
					String password = p.getProperty("dataSource.mysql.password");
					String publickey = p.getProperty("dataSource.mysql.publickey");
					try {
						password = ConfigTools.decrypt(publickey, password);
						Driver driver = (Driver) Class.forName(forname).newInstance();
						DriverManager.registerDriver(driver);
						Properties props = new Properties();
						props.put("user", username);
						props.put("password", password);
						conn = DriverManager.getConnection(url, props);
						stmt = conn.createStatement();

						is = new BufferedReader(new InputStreamReader(file.getInputStream()));
						String readline = "";
						StringBuffer sqlstr = new StringBuffer();
						int i = 0;
						while ((readline = is.readLine()) != null) {
							readline = readline.trim();

							if (readline.startsWith("--") || readline.equalsIgnoreCase("COMMIT;")
									|| readline.startsWith("##"))
								continue;
							sqlstr.append(" " + readline);
							if (readline.endsWith(linepara)) {
								try {
									sqlstr.append(linepara);
									stmt.executeUpdate(sqlstr.toString().replaceAll(linepara + linepara, ""));
									i++;
								} catch (SQLException e) {
									flag = false;
									message.append("执行SQL出错：" + sqlstr.toString().replaceAll(linepara + linepara, "")
											+ "错误异常信息：" + e.getMessage());
									logger.error(e.getMessage());
								}
								sqlstr = new StringBuffer();
							}
						}
						is.close();
						is = null;
						message.append("更新完毕！本次更新：" + i + "条");

					} catch (Exception e) {
						flag = false;
						message.append("发生异常：" + e.getMessage());
						logger.error(e.getMessage());
					} finally {
						try {
							if (stmt != null) {
								stmt.close();
								stmt = null;
							}
							if (conn != null) {
								conn.close();
								conn = null;
							}
						} catch (SQLException e) {
							logger.error(e.getMessage());
						}
					}

				}
			}
		}

		result.put("flag", flag);
		result.put("message", message.toString());

		return result;
	}

}