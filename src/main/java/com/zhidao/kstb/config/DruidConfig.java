package com.zhidao.kstb.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Configuring the druid data source
 *
 * @author <致道科技>YunTong
 * <p>Created on 2021-6-9<p>
 */
@Configuration
public class DruidConfig {
	/**
	 * Get the druid data source
	 *
	 * @author <致道科技>YunTong
	 * @return druid data source
	 * <p>Created on 2021-6-9<p>
	 */
	@ConfigurationProperties(prefix = "spring.datasource")
	@Bean
	public DataSource druidDataSource() {
		return new DruidDataSource();
	}
}
