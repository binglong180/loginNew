package com.niu.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManger {
	// 1、私有化属性
	private static ConfigManger configManger = null;
	private static Properties properties = null;
	// 2、私有化构造
	private ConfigManger() {
		// 3、加载Properties
		properties = new Properties();
		InputStream is = ConfigManger.class.getClassLoader()
				.getResourceAsStream("");
		try {
			properties.load(is);
		} catch (IOException e) {
			try {
				is.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public static ConfigManger getInstance() {
		configManger = new ConfigManger();
		return configManger;
	}

	public static String getPorValue(String key) {
		if (configManger == null) {
			getInstance();
		}
		return properties.getProperty(key);
	}

}
