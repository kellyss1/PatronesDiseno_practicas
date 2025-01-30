package com.patrones.config;

public interface ConfigJson {
	public String getUsuario();
	public String getPassword();
	public String getUrl();
	
	public ConfigProperties asConfigProperties();
}
