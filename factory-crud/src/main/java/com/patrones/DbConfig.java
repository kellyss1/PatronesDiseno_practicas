package com.patrones;

import java.sql.Connection;
import java.sql.SQLException;

import com.patrones.config.ConfigJson;
import com.patrones.config.ConfigJsonImpl;
import com.patrones.config.ConfigProperties;
import com.patrones.factory.Factory;
import com.patrones.factory.Inicializar;
import com.patrones.factory.anotaciones.MiComponente;
import com.patrones.factory.anotaciones.MiLogger;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@MiComponente(name="dbConfig", singleton = true)
public class DbConfig {
	private HikariConfig config = new HikariConfig();
	private HikariDataSource dataSource;
	
//	{
//		config.setJdbcUrl("jdbc:sqlite:patrones.db");
//		config.setUsername("sa");
//		config.setPassword("");
//		config.setConnectionTimeout(1000); //ms
//		
//		dataSource = new HikariDataSource(config);
//	}
	
	private void config(ConfigProperties cfg) {		
		config.setJdbcUrl(cfg.url());
		config.setUsername(cfg.usuario());
		config.setPassword(cfg.password());
		config.setConnectionTimeout(1000); //ms
	}
	
	public Connection getConnection() {
		if(dataSource==null) {
			
			//ConfigProperties cfg = new ConfigPropertiesImpl();
			//config(cfg);
			
			ConfigJson cfg = new ConfigJsonImpl();

			config(cfg.asConfigProperties());

			dataSource = new HikariDataSource(config);
		}
		
		try {
			return dataSource.getConnection();
		} 
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void onInit(Factory factory) {
		// TODO Auto-generated method stub
		
	}
}
