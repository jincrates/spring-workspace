package org.zerock.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

// 해당 클래스의 인스턴스를 이용해서 설정 파일을 대신함
@Configuration
@ComponentScan(basePackages = {"org.zerock.sample"})
@MapperScan(basePackages = {"org.zerock.mapper"})
public class RootConfig {

	@Bean
	public DataSource dataSource() {
		HikariConfig hikariConfig = new HikariConfig();
		//hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
		//hikariConfig.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/mydb?serverTimezone=UTC");

		// log4jdbc 설정
		hikariConfig.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		hikariConfig.setJdbcUrl("jdbc:log4jdbc:mysql://127.0.0.1:3306/mydb?serverTimezone=Asia/Seoul");
		hikariConfig.setUsername("book_ex");
		hikariConfig.setPassword("1q2w3e4r!");
		
		HikariDataSource dataSource = new HikariDataSource(hikariConfig);
		
		return dataSource;
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource());
		
		return (SqlSessionFactory) sqlSessionFactory.getObject();
		
	}
}
