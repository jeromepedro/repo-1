package business;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

public class MSSQLAccess implements DBMSAccess {

	@Override
	public String jdbcDriverClassName() {
		return "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	}

	@Override
	public String jdbcUrl(final String hostname, final String port) {
		StringBuilder buf = new StringBuilder("jdbc:sqlserver://");
		buf.append(hostname).append(':').append(port)
				.append(";databaseName=app01test;integratedSecurity=false;");
		return buf.toString();
	}

	@Override
	public Connection getConnection(final BasicDataSource base)
			throws SQLException {

		return base.getConnection();
	}

}
