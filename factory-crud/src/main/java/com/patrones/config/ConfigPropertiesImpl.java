package com.patrones.config;

import java.util.Properties;

public class ConfigPropertiesImpl implements ConfigProperties {
	
	String usuario;
	String password;
	String url;
	
	
	public ConfigPropertiesImpl() {
		try( var is = this.getClass().getResourceAsStream("/application.properties") ) {
	
			Properties props = new Properties();
			props.load(is);
			
			usuario = props.getProperty("db.usuario");
			password = props.getProperty("db.password");
			url = props.getProperty("db.url");
		}
		catch(Exception ex) {
			
		}
	}

	@Override
	public String usuario() {
		return usuario;
	}

	@Override
	public String password() {
		return password;
	}

	@Override
	public String url() {
		return url;
	}

}
