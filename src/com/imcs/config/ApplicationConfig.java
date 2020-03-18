package com.imcs.config;

import java.util.ResourceBundle;

public class ApplicationConfig {

	private static ApplicationConfig instance;
	ResourceBundle resource = null;

	private ApplicationConfig() {
		this.resource = resource.getBundle("AppConfig");
	}

	public static ApplicationConfig getInstance() {
		if (instance == null) {
			instance = new ApplicationConfig();
		}
		return instance;
	}

	public String getProperty(String key) {
		return this.resource.getString(key);
	}

}
