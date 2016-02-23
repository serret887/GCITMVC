package com.gcit.training.spring;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@EnableTransactionManagement
@Configuration
@Import({ DatabaseConfiguration.class, DAOConfiguration.class, ServiceConfiguration.class,
		ProfilesConfiguration.class })
public class LMSConfig {

	@Autowired
	DataSource dataSource;

	@Bean(name = "transactionManager")
	@Profile("dev")
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean
	public JdbcTemplate template() {
		JdbcTemplate template = new JdbcTemplate();
		template.setDataSource(dataSource);
		return template;
	}

}
