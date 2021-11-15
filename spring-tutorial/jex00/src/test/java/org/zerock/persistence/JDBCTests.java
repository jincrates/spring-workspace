package org.zerock.persistence;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class JDBCTests {
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testConnection() {

		try(Connection con = DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/mydb?serverTimezone=UTC",
				"book_ex",
				"1q2w3e4r!")) {
			log.info(con);
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
	}
}
