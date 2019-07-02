package cn.itcast.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/*
 * Code JDBC util class, get the connection of the database
 * Way to read configuration files
 * Read the coinfiguration file, get the connection, execute it once
 */

public class JDBCUtilsConfig {

	private static Connection con;
	private static String driverClass;
	private static String url;
	private static String username;
	private static String password;

	static {
		try {
			readConfig();
			Class.forName(driverClass);
			con = DriverManager.getConnection(url, username, password);
		} catch (Exception ex) {
			throw new RuntimeException("Failed to connect database.");
		}

	}

	public static Connection getConnection() {
		return con;
	}

	public static void readConfig() throws Exception {
		InputStream in = JDBCUtilsConfig.class.getClassLoader().getResourceAsStream("database.properties");
		Properties pro = new Properties();
		pro.load(in);

		driverClass = pro.getProperty("driverClass");
		url = pro.getProperty("url");
		username = pro.getProperty("username");
		password = pro.getProperty("password");
	}

}
