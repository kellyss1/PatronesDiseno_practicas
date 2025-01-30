package com.patrones.config;

import java.io.InputStreamReader;
import java.io.Reader;

import com.google.gson.Gson;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class ConfigJsonImpl implements ConfigJson {
	
	@Getter @Setter
	@ToString
	static class Config {
		String usuario;
		String password;
		String url;
	}
	
	private Config config;
	
	public ConfigJsonImpl( ) {
		var is = this.getClass().getResourceAsStream("/application.json");
		Reader rd = new InputStreamReader(is);
		
		Gson gson = new Gson();
		
		config = gson.fromJson(rd, Config.class);
	}

	@Override
	public String getUsuario() {
		return config.usuario;
	}

	@Override
	public String getPassword() {
		return config.password;
	}

	@Override
	public String getUrl() {
		return config.url;
	}
	
	public ConfigProperties asConfigProperties() {
		return new ConfigProperties() {			
			@Override
			public String usuario() {
				return getUsuario();
			}
			
			@Override
			public String url() {
				return getUrl();
			}
			
			@Override
			public String password() {
				return getPassword();
			}
		};
	}

}
