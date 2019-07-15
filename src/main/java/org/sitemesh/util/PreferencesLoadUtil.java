package org.sitemesh.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @ClassName: PreferencesLoadUtil
 * @Description: 覆盖loushang-sitemesh中参数加载工具类，目的是解决加载root.prefs文件可能会报错的问题
 * @author wbw
 * @date 2016年9月1日 下午2:11:30
 *
 */
public class PreferencesLoadUtil {

	private Log logger = LogFactory.getLog(getClass());

	public static final String PATH = "/settings/root.prefs";

	protected static final char SEPARATOR = '/';

	private static Map<String, String> preferences = null;

	public PreferencesLoadUtil(HttpServletRequest request) {
		load();
	}

	private void load() {
		if (preferences == null) {
			preferences = new HashMap<String, String>();
			boolean flag = true;
			InputStream input = null;
			Properties result = new Properties();
			try {
				input = Thread.currentThread().getContextClassLoader().getResourceAsStream(PATH);
				result.load(input);
			} catch (IOException e) {
				logger.error(e);
				flag = false;
			} finally {
				if (input != null) {
					try {
						input.close();
					} catch (Exception e) {
						logger.error(e);
						flag = false;
					}
				}
			}
			if (flag) {
				// 读取key-value
				for (Iterator<Object> i = result.keySet().iterator(); i.hasNext();) {
					String fullKey = (String) i.next();
					String key = null;

					int lastIndex = fullKey.lastIndexOf(SEPARATOR);
					if (lastIndex == -1) {
						key = fullKey;
					} else {
						key = fullKey.substring(lastIndex + 1);
					}
					String value = result.getProperty(fullKey);
					if (value != null) {
						value = value.trim();
						preferences.put(key, value);
					}
				}
			}
		}
	}

	public Map<String, String> getPreferences() {
		return preferences;
	}

}
