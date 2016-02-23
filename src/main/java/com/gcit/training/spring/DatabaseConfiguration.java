package com.gcit.training.spring;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
public class DatabaseConfiguration {
	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/library";
	private String username = "root";
	private String password = "admin";

	@Bean(destroyMethod = "shutdown", name = "datasource")
	@Profile("dev")
	public DataSource embeddedDataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
				.addScript("file:src/test/java/library_schema.sql")
				.addScript("file:src/test/java/testData.sql").build();
	}

	@Bean(name = "datasource")
	@Profile("prod")
	public DataSource productionDataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(driver);
		ds.setUrl(url);
		ds.setUsername(username);
		ds.setPassword(password);
		ds.setInitialSize(5);
		ds.setMaxActive(10);
		return ds;
	}

}
