package com.foxminded.univer.spring.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.foxminded.univer.dao.DaoException;
import com.foxminded.univer.dao.PropertiesHolder;

public class DataSourceForJdbcTemplate {

	private String driver;
	private String url;
	private String user;
	private String password;
	
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driver);
		dataSource.setUrl(url);
		dataSource.setUsername(user);
		dataSource.setPassword(password);
		return (DataSource) dataSource;
	}
}
