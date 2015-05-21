package business;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;


interface DBMSAccess {
	String jdbcDriverClassName();

	String jdbcUrl(String hostname, String port);

	Connection getConnection(BasicDataSource base) throws SQLException;
}
